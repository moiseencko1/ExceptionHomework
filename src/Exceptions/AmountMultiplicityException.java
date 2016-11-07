package Exceptions;

/**
 * Created by Acer on 06.11.2016.
 */
public class AmountMultiplicityException extends RuntimeException {
    public AmountMultiplicityException() {
        super("Cash machine cannot get that amount money. Please enter a amount a multiple of 100.");
    }
}
