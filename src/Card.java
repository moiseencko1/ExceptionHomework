/**
 * Created by Acer on 06.11.2016.
 */
public class Card implements Comparable<Card> {
    private final String name;
    private final String number;

    public Card(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Card o) {
        int value = number.compareTo(o.getNumber());
        return value != 0 ? value : name.compareTo(o.name);
    }
}
