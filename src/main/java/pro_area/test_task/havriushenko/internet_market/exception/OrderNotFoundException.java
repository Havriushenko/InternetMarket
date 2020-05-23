package pro_area.test_task.havriushenko.internet_market.exception;

public class OrderNotFoundException extends IllegalArgumentException {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String s) {
        super(s);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }
}
