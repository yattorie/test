package framework.exception;

public class DependencyNotFoundException extends RuntimeException {
  public DependencyNotFoundException(String message) {
    super(message);
  }
}
