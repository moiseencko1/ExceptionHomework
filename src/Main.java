import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Acer on 05.11.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Card> cards = new HashMap<>();
        TerminalServer server = setInitialValues(cards);
        Map<String, Method> methods = getMethods();
        Terminal cashMachine = new CashMachine(server);

        new Application(cashMachine, cards, methods);

//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0001"}));
//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0001"}));
//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0001"}));
//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0001"}));
//        Thread.sleep(3000);
//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0000"}));
//        Thread.sleep(3000);
//        System.out.println(printResultMethod(cashMachine, methods.get("signIn"), new Object[]{cards.get("Moiseencko"), "0000"}));
//        System.out.println(printResultMethod(cashMachine, methods.get("accountState"), new Object[]{cards.get("Moiseencko")}));
//        System.out.println(printResultMethod(cashMachine, methods.get("getMoney"), new Object[]{cards.get("Moiseencko"), 2}));
//        System.out.println(printResultMethod(cashMachine, methods.get("putMoney"), new Object[]{cards.get("Moiseencko"), 1100}));
//        System.out.println(printResultMethod(cashMachine, methods.get("accountState"), new Object[]{cards.get("Moiseencko")}));
//        System.out.println(printResultMethod(cashMachine, methods.get("getMoney"), new Object[]{cards.get("Moiseencko"), 500}));
//        System.out.println(printResultMethod(cashMachine, methods.get("accountState"), new Object[]{cards.get("Moiseencko")}));
//        System.out.println(printResultMethod(cashMachine, methods.get("putMoney"), new Object[]{cards.get("Stepa"), 1100}));
//        System.out.println(printResultMethod(cashMachine, methods.get("accountState"), new Object[]{cards.get("Moiseencko")}));
    }

    protected static String printResultMethod(Terminal cashMachine, Method m, Object[] args) {
        StringBuilder s = new StringBuilder(m.getName() + "(" + ((Card) args[0]).getName() +
                (args.length > 1 ? ", " + args[1] : "") + "): ");
        try {
            s.append(m.invoke(cashMachine, args));
        } catch (Exception e) {
            s.append(e.getCause().getMessage());
        } finally {
            return s.toString();
        }
    }

    public static TerminalServer setInitialValues(Map<String, Card> cards) {
        TerminalServer server = new TerminalServer();
        cards.put("Chepkov", new Card("Chepkov", "0000-0000-0000-0000"));
        cards.put("Stepa", new Card("Stepa", "0000-0000-0000-0001"));
        cards.put("Moiseencko", new Card("Moiseencko", "0000-0000-0000-0002"));
        server.addAccount(cards.get("Chepkov"), "0000".hashCode(), 34);
        server.addAccount(cards.get("Stepa"), "0000".hashCode(), 25);
        server.addAccount(cards.get("Moiseencko"), "0000".hashCode(), 1);
        return server;
    }

    public static Map getMethods() {
        Map<String, Method> methods = new HashMap<>();
        Method[] methodsArray = CashMachine.class.getMethods();
        for (Method method : methodsArray) {
            methods.put(method.getName(), method);
        }
        return methods;
    }
}
