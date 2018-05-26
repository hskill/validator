package info.ideatower.springboot.validator.core.exception;

/**
 * Rule不能实例化的异常
 */
public class RuleCannotInstanceException extends RuntimeException {

    public RuleCannotInstanceException() {
    }

    public RuleCannotInstanceException(String message) {
        super(message);
    }

    public RuleCannotInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleCannotInstanceException(Throwable cause) {
        super(cause);
    }

    public RuleCannotInstanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
