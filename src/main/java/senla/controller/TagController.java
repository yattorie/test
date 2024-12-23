package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Tag;
import senla.service.TagService;
import senla.service.impl.TagServiceImpl;
import senla.util.parser.TagParser;

import java.io.IOException;

@WebServlet("/tags/*")
public class TagController extends HttpServlet {
    private final TagService tagService = TagServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("tags", tagService.getAllTags());
            request.getRequestDispatcher("/WEB-INF/jsp/tags.jsp").forward(request, response);
        } else {
            int id = TagParser.parseIdFromPath(action);
            tagService.getTagById(id).ifPresentOrElse(
                    tag -> {
                        try {
                            request.setAttribute("tag", tag);
                            request.getRequestDispatcher("/WEB-INF/jsp/tag-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tag not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Tag tag = TagParser.createTagFromRequest(request);
        tagService.createTag(tag);
        response.sendRedirect(request.getContextPath() + "/tags");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tag ID is required");
            return;
        }

        int id = TagParser.parseIdFromPath(action);
        tagService.deleteTag(id);
        response.sendRedirect(request.getContextPath() + "/tags");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tag ID is required");
            return;
        }

        int id = TagParser.parseIdFromPath(action);

        tagService.getTagById(id).ifPresentOrElse(
                existingTag -> {
                    Tag updatedTag = TagParser.updateTagFromRequest(request, existingTag);
                    tagService.updateTag(updatedTag);
                    try {
                        response.sendRedirect(request.getContextPath() + "/tags");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tag not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}