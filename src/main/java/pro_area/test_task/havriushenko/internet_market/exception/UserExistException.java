package pro_area.test_task.havriushenko.internet_market.exception;

public class UserExistException extends IllegalArgumentException {

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }
}
