package framework.util;

public enum Messages {
    REGISTERED_CLASS("Registered class: %s"),
    DEPENDENCY_INJECTED("Injected dependency: %s into %s"),
    BEAN_CREATED("Creating instance of: %s using %s constructor"),
    PROCESSING_COMPONENT("Processing @Component: "),
    CREATED_AND_REG_CLASS("Created and registered class: ");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
