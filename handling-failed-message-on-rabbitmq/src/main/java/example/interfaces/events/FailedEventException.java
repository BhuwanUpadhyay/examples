package example.interfaces.events;

public class FailedEventException extends RuntimeException {
  private final String source;

  public FailedEventException(String source, Throwable throwable) {
    super(throwable);
    this.source = source;
  }

  public String getSource() {
    return source;
  }
}
