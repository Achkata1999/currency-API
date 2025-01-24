package demo.currency.exception;

public class WebserviceException  extends RuntimeException {

    private final String reason;
    private final String subject;

    public WebserviceException() {
        super();
        this.reason = null;
        this.subject = null;
    }

    public WebserviceException(String message) {
        super(message);
        this.reason = null;
        this.subject = null;
    }

    public WebserviceException(String message, Throwable cause) {
        super(message, cause);
        this.reason = null;
        this.subject = null;
    }

    public WebserviceException(String message, String reason, String subject) {
        super(message);
        this.reason = reason;
        this.subject = subject;
    }

    public WebserviceException(String message, String reason ) {
        super(message);
        this.reason = reason;
        this.subject = null;
    }

    public String getReason() {
        return reason;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return String.format("WebserviceException{message=%s, reason=%s, subject=%s}", getMessage(), getReason(),getSubject());
    }
}
