package model.exception;

public class CashierWorkException extends Exception {

    public CashierWorkException(String message) {
        super(message);
    }

    public CashierWorkException(String message, Throwable exception) {
        super(message, exception);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
