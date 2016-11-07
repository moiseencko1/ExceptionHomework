/**
 * Created by Acer on 05.11.2016.
 */
public interface Terminal {

    int accountState(Card card);

    boolean putMoney(Card card, int money);

    boolean getMoney(Card card, int money);
}
