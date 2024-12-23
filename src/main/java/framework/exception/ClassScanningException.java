package framework.exception;

public class ClassScanningException extends RuntimeException {
    public ClassScanningException(String message, ClassNotFoundException e) {
        super(message);
    }
}
