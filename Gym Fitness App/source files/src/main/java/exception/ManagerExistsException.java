package exception;

public class ManagerExistsException extends RuntimeException {
    public ManagerExistsException(String message) {
        super(message);
    }
}
