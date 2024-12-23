package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Report;
import senla.service.ReportService;
import senla.service.impl.ReportServiceImpl;
import senla.util.parser.ReportParser;

import java.io.IOException;

@WebServlet("/reports/*")
public class ReportController extends HttpServlet {
    private final ReportService reportService = ReportServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("reports", reportService.getAllReports());
            request.getRequestDispatcher("/WEB-INF/jsp/reports.jsp").forward(request, response);
        } else {
            int id = ReportParser.parseIdFromPath(action);
            reportService.getReportById(id).ifPresentOrElse(
                    report -> {
                        try {
                            request.setAttribute("report", report);
                            request.getRequestDispatcher("/WEB-INF/jsp/report-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Report not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Report report = ReportParser.createReportFromRequest(request);
        reportService.createReport(report);
        response.sendRedirect(request.getContextPath() + "/reports");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Report ID is required");
            return;
        }

        int id = ReportParser.parseIdFromPath(action);
        reportService.deleteReport(id);
        response.sendRedirect(request.getContextPath() + "/reports");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Report ID is required");
            return;
        }

        int id = ReportParser.parseIdFromPath(action);

        reportService.getReportById(id).ifPresentOrElse(
                existingReport -> {
                    Report updatedReport = ReportParser.updateReportFromRequest(request, existingReport);
                    reportService.updateReport(updatedReport);
                    try {
                        response.sendRedirect(request.getContextPath() + "/reports");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Report not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}