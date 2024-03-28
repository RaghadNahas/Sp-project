import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class User extends JFrame {
    private JLabel welcomeLabel;
    private JLabel balanceLabel;
    public JPanel mainPanel;
    private JButton requestFlightStatementButton;
    private JButton showVisaFlightsButton;
    private JButton showPassportFlightsButton;
    private JButton bookPassportFlightButton;
    private JButton bookVisaFlightButton;
    private JTextField flightIDField;
    private JRadioButton discountButton;
    private JLabel FlightIDLabel;
    public static String fullName;
    public static double balance;
    public static String name;
    private static BufferedReader in;
    private static PrintWriter out;
    public static User userGUI;
    public User(BufferedReader in, PrintWriter out, String name, String balance) {
        User.name = name;
        User.balance = Double.parseDouble(balance);
        User.in = in;
        User.out = out;
        userGUI = this;
        welcomeLabel.setText("Welcome, " + User.name);
        balanceLabel.setText("Your current balance is " + User.balance);

        showVisaFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("4S5zzZ8iU7tH6g1oA2Pq0v3KlEmNjD9wRrBzXsG");
                String flights = "";
                String reader;
                try {
                    while (!((reader = in.readLine()).equals("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY"))) {
                        flights += reader + "\n";
                        if (reader.contains("Approximate Time:"))
                            flights += "\n";
                    }
                    JOptionPane.showMessageDialog(mainPanel, flights);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        showPassportFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("5T6zzA9jV8uI7h2pB3Qr1w4LmFnOkE0xSsCzYtH");
                String flights = "";
                String reader;
                try {
                    while (!((reader = in.readLine()).equals("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY"))) {
                        flights += reader + "\n";
                        if (reader.contains("Approximate Time:"))
                            flights += "\n";
                    }
                    JOptionPane.showMessageDialog(mainPanel, flights);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
