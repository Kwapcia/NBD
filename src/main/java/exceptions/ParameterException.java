package exceptions;

public class ParameterException extends IllegalArgumentException {
    public ParameterException(String message) {
        super(message);
    }
}
