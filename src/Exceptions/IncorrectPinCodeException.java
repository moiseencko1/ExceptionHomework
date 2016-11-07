package Exceptions;

/**
 * Created by Acer on 06.11.2016.
 */
public class IncorrectPinCodeException extends RuntimeException {
    public IncorrectPinCodeException() {
        super("Incorrect pin code.");
    }
}
