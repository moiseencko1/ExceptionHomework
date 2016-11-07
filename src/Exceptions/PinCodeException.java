package Exceptions;

/**
 * Created by Acer on 06.11.2016.
 */
public class PinCodeException extends RuntimeException {
    public PinCodeException() {
        super("Enter pin code.");
    }
}
