package framework.util;

import framework.exception.ClassScanningException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {

    public static List<Class<?>> scanPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        File directory = new File(ClassLoader.getSystemResource(path).getFile());

        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException(ErrorMessages.PACKAGE_NOT_FOUND.getDescription() + packageName);
        }

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {
                try {
                    String className = packageName + '.' + file.getName().replace(".class", "");
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new ClassScanningException(ErrorMessages.CLASS_NOT_FOUND.getDescription() + file.getName(), e);
                }
            }
        }
        return classes;
    }
}
