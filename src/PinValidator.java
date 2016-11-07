import Exceptions.IncorrectPinCodeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Acer on 05.11.2016.
 */
public class PinValidator {
    private final Map<Card, Integer> passwords = new HashMap<>();

    public void addAccount(Card card, int hachPinCode) {
        passwords.put(card, hachPinCode);
    }

    public void removeAccount(Card card) {
        passwords.remove(card);
    }

    public void validate(Card card, int hashPinCode) {
        if (!passwords.get(card).equals(hashPinCode))
            throw new IncorrectPinCodeException();
    }
}
