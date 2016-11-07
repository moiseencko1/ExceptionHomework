import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;

/**
 * Created by Acer on 06.11.2016.
 */
public class Application {
    private JTextField pinCode;
    private JTextField money;
    private Choice commands;
    private Choice choiceCard;
    private JButton doCommand;
    private JTextArea textArea;
    private JPanel panel;
    private JFrame frame;
    Deque<String> responses = new ArrayDeque<>();

    public Application(Terminal cashMachine, Map<String, Card> cards, Map<String, Method> methods) {
        commands = new Choice();
        commands.add("signIn");
        commands.add("accountState");
        commands.add("getMoney");
        commands.add("putMoney");
        commands.addItemListener(e -> {
            Object item = e.getItem();
            money.setEditable(!item.equals("accountState") & !item.equals("signIn"));
            pinCode.setEditable(item.equals("signIn"));
        });

        choiceCard = new Choice();
        for (Map.Entry<String, Card> entry : cards.entrySet()) {
            choiceCard.add(entry.getKey());
        }

        doCommand = new JButton("do");
        doCommand.addActionListener(e -> {
            ArrayList listArgs = new ArrayList();
            listArgs.add(cards.get(choiceCard.getSelectedItem()));
            if (pinCode.isEditable()) listArgs.add(pinCode.getText());
            if (money.isEditable()) {
                try {
                    listArgs.add(Integer.parseInt(money.getText()));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Incorrect field money");
                    return;
                }
            }
            Object[] args = listArgs.toArray();
            StringBuilder response = new StringBuilder();
            if (responses.size() > 10) responses.removeLast();
            responses.addFirst(Main.printResultMethod(cashMachine, methods.get(commands.getSelectedItem()), args));
            for (String s : responses) {
                response.append(s + "\n");
            }
            textArea.setText(response.toString());
        });

        textArea = new JTextArea(5, 40);
        textArea.setEditable(false);

        JLabel commandLabel = new JLabel("command:");
        JLabel cardLabel = new JLabel("card:");
        JLabel pinCodeLabel = new JLabel("pin:");
        JLabel moneyLabel = new JLabel("money:");

        pinCode = new JTextField();
        pinCode.setText("0000");
        pinCode.selectAll();
        money = new JTextField();
        money.setEditable(false);
        money.setText("100");
        money.selectAll();

        panel = new JPanel();
        panel.add(commandLabel);
        panel.add(commands);
        panel.add(cardLabel);
        panel.add(choiceCard);
        panel.add(pinCodeLabel);
        panel.add(pinCode);
        panel.add(moneyLabel);
        panel.add(money);
        panel.add(doCommand);
        panel.add(textArea);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 650, 300);
        frame.add(panel);
        frame.setVisible(true);
    }

}
