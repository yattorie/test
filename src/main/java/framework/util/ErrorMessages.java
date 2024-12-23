package framework.util;

public enum ErrorMessages {
    DEPENDENCY_NOT_FOUND("Dependency not found for type: %s"),
    INJECTION_FAILED("Failed to inject dependencies into bean: %s"),
    BEAN_NOT_FOUND("Bean not found for type: %s"),
    FAILED_INIT("Failed to initialize ApplicationContext"),
    BEAN_CREATION_FAILED("Failed to create bean for class: %s"),
    MULTIPLE_AUTOWIRED_CONSTRUCTORS("Failed because multiple autowired constructors"),
    FAILED_TO_PROCESS_BEAN("Failed to process bean: %s"),
    FAILED_TO_CREATE_BEAN("Failed to create bean: "),
    PACKAGE_NOT_FOUND("Package not found: "),
    CLASS_NOT_FOUND("Class not found: %s");


    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    public String getDescription(Object... args) {
        return String.format(description, args);
    }
}
