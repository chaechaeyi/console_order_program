package kr.co.simple.order.exception;

/**
 * 재고 부족 exceptoin
 */
public class SoldOutException extends RuntimeException{
    public SoldOutException() {
        super();
    }

    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoldOutException(Throwable cause) {
        super(cause);
    }
}
