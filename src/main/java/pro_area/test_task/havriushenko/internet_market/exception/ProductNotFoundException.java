package pro_area.test_task.havriushenko.internet_market.exception;

public class ProductNotFoundException extends IllegalArgumentException {

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String s) {
        super(s);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}
