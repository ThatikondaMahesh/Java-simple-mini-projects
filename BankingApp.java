import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankingApp extends JFrame implements ActionListener {
    private JTextField accountBalanceField;
    private JButton depositButton, withdrawButton;

    private double balance = 1000.0; // Initial balance

    public BankingApp() {
        // Frame setup
        setTitle("Simple Banking Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel balanceLabel = new JLabel("Account Balance:");
        accountBalanceField = new JTextField(String.valueOf(balance));
        accountBalanceField.setEditable(false);

        JLabel depositLabel = new JLabel("Deposit Amount:");
        JTextField depositField = new JTextField();

        JLabel withdrawLabel = new JLabel("Withdraw Amount:");
        JTextField withdrawField = new JTextField();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        // Add components to panel
        panel.add(balanceLabel);
        panel.add(accountBalanceField);
        panel.add(depositLabel);
        panel.add(depositField);
        panel.add(withdrawLabel);
        panel.add(withdrawField);

        // Add buttons and action listeners
        panel.add(depositButton);
        depositButton.addActionListener(this);

        panel.add(withdrawButton);
        withdrawButton.addActionListener(this);

        // Add panel to the frame
        add(panel);

        // Set frame visibility
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            performDeposit();
        } else if (e.getSource() == withdrawButton) {
            performWithdrawal();
        }
    }

    private void performDeposit() {
        String depositAmountStr = JOptionPane.showInputDialog("Enter deposit amount:");
        try {
            double depositAmount = Double.parseDouble(depositAmountStr);
            if (depositAmount > 0) {
                balance += depositAmount;
                updateBalanceField();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid deposit amount. Please enter a positive value.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void performWithdrawal() {
        String withdrawalAmountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
        try {
            double withdrawalAmount = Double.parseDouble(withdrawalAmountStr);
            if (withdrawalAmount > 0 && withdrawalAmount <= balance) {
                balance -= withdrawalAmount;
                updateBalanceField();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid withdrawal amount. Please enter a valid value.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void updateBalanceField() {
        accountBalanceField.setText(String.valueOf(balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingApp());
    }
}
