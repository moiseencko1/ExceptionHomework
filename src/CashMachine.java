import Exceptions.AmountMultiplicityException;
import Exceptions.PinCodeException;

/**
 * Created by Acer on 05.11.2016.
 */
public class CashMachine implements Terminal {
    private final TerminalServer server;
    private Card currentUser = null;

    public CashMachine(TerminalServer server) {
        this.server = server;
    }

    public boolean signIn(Card card, String pinCode) {
        server.validate(card, pinCode.hashCode());
        currentUser = card;//danger. may be incorrect link
        return true;
    }

    public boolean signOut(Card card) {
        if (currentUser.equals(card)) currentUser = null;
        else throw new IllegalArgumentException("This card doesn't connect.");
        return true;
    }

    private void isUserConnect(Card card) {
        if (!card.equals(currentUser)) {
            currentUser = null;
            throw new PinCodeException();
        }
    }

    @Override
    public int accountState(Card card) {
        isUserConnect(card);
        return server.accountState(card);
    }

    @Override
    public boolean putMoney(Card card, int money) {
        isUserConnect(card);
        if (money % 100 != 0)
            throw new AmountMultiplicityException();
        if (money == 0)
            throw new IllegalArgumentException("Amount should be more than 0.");
        server.putMoney(card, money);
        return true;
    }

    @Override
    public boolean getMoney(Card card, int money) {
        isUserConnect(card);
        if (money % 100 != 0)
            throw new AmountMultiplicityException();
        if (money == 0)
            throw new IllegalArgumentException("Amount should be more than 0.");
        server.getMoney(card, money);
        return true;
    }
}
