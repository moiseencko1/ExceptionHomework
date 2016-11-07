import Exceptions.AccountIsLockedException;
import Exceptions.IncorrectPinCodeException;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by Acer on 05.11.2016.
 */
public class TerminalServer {
    private final PinValidator pinValidator = new PinValidator();
    private final Map<Card, Integer> accounts = new HashMap<>();
    private final LinkedHashMap<Card, Pair<Integer, Long>> failConnectionLog = new LinkedHashMap<>();

    public void addAccount(Card card, int hashPinCode, int money) {
        pinValidator.addAccount(card, hashPinCode);
        accounts.put(card, money);
    }

    public void removeAccount(Card card) {
        pinValidator.removeAccount(card);
        accounts.remove(card);
    }

    public void validate(Card card, int hashPinCode) {
        try {
            if (!accounts.containsKey(card))
                throw new IllegalArgumentException("Account with this name isn't existed.");
            Pair pair = failConnectionLog.get(card);
            if (failConnectionLog.containsKey(card) && failConnectionLog.get(card).getKey() > 2) {
                if (pair.getValue().equals((long) 0)) {
                    failConnectionLog.put(card, new Pair<>(3, System.currentTimeMillis()));
                    throw new AccountIsLockedException(5000);
                } else {
                    long l = System.currentTimeMillis() - failConnectionLog.get(card).getValue();
                    if (l <= 5000) throw new AccountIsLockedException(5000 - l + 999);
                    else failConnectionLog.put(card, new Pair(0, (long) 0));
                }
            }
            pinValidator.validate(card, hashPinCode);
            failConnectionLog.remove(card);
        } catch (AccountIsLockedException e) {
            throw e;
        } catch (IncorrectPinCodeException e) {
            failConnectionLog.putIfAbsent(card, new Pair(0, (long) 0));
            failConnectionLog.put(card, new Pair(failConnectionLog.get(card).getKey() + 1, (long) 0));
            throw e;
        }
    }

    public int accountState(Card card) {
        return accounts.get(card);
    }

    public void getMoney(Card card, int money) {
        if (accounts.get(card) - money >= 0)
            accounts.put(card, accounts.get(card) - money);
        else throw new IllegalArgumentException("Account don't have amount requested");
    }

    public void putMoney(Card card, int money) {
        accounts.put(card, accounts.get(card) + money);
    }
}
