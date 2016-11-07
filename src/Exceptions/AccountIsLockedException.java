package Exceptions;

/**
 * Created by Acer on 06.11.2016.
 */
public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(long timeMillis) {
        super("Pin code was entered wrong 3 times. Try again in " + timeMillis / 1000 + "  seconds");
    }
}
