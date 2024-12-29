import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JPanel[] row = new JPanel[5];
    JButton[] button = new JButton[19];
    String[] buttonString = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "/", "C", "?", "+/-", "=", "0"
    };

    int[] dimW = {430, 70, 150, 140};
    int[] dimH = {50, 60};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
    boolean[] function = new boolean[4];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(2, 25);
    Font font = new Font("Times New Roman", Font.BOLD, 20);

    Calculator() {
        super("Calculator");
        setDesign();
        setSize(500, 350);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5, 5);
        setLayout(grid);

        for (int i = 0; i < 4; i++) {
            function[i] = false;
        }
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
        for (int i = 0; i < 5; i++) {
            row[i] = new JPanel();
        }
        row[0].setLayout(f1);
        for (int i = 1; i < 5; i++) {
            row[i].setLayout(f2);
        }

        for (int i = 0; i < 19; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setBackground(Color.white);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }

        display.setFont(font);
        display.setEditable(false);
        display.setPreferredSize(displayDimension);

        row[0].add(display);
        add(row[0]);

        for (int i = 0; i < 19; i++) {
            if (i == 14) {
                row[1].add(button[i]);
                add(row[1]);
            } else if (i == 15 || i == 16 || i == 17) {
                row[4].add(button[i]);
                add(row[4]);
            } else if (i == 18) {
                row[3].add(button[i]);
                add(row[3]);
            } else {
                row[(i / 4) + 1].add(button[i]);
                add(row[(i / 4) + 1]);
            }
        }

        setVisible(true);
    }

    public void setDesign() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting look and feel: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if ("0123456789.".contains(command)) {
            display.append(command);
        } else if (command.equals("+")) {
            processOperator(0);
        } else if (command.equals("-")) {
            processOperator(1);
        } else if (command.equals("*")) {
            processOperator(2);
        } else if (command.equals("/")) {
            processOperator(3);
        } else if (command.equals("=")) {
            calculateResult();
        } else if (command.equals("C")) {
            clear();
        } else if (command.equals("+/-")) {
            toggleSign();
        }
    }

    private void processOperator(int operatorIndex) {
        if (!display.getText().isEmpty()) {
            temporary[0] = Double.parseDouble(display.getText());
            function[operatorIndex] = true;
            display.setText("");
        }
    }

    private void calculateResult() {
        if (!display.getText().isEmpty()) {
            temporary[1] = Double.parseDouble(display.getText());
            double result = 0;
            if (function[0]) {
                result = temporary[0] + temporary[1];
            } else if (function[1]) {
                result = temporary[0] - temporary[1];
            } else if (function[2]) {
                result = temporary[0] * temporary[1];
            } else if (function[3]) {
                result = temporary[0] / temporary[1];
            }
            display.setText(Double.toString(result));
            for (int i = 0; i < 4; i++) {
                function[i] = false;
            }
            temporary[0] = 0;
            temporary[1] = 0;
        }
    }

    private void clear() {
        display.setText("");
        for (int i = 0; i < 4; i++) {
            function[i] = false;
        }
        temporary[0] = 0;
        temporary[1] = 0;
    }

    private void toggleSign() {
        if (!display.getText().isEmpty()) {
            double value = Double.parseDouble(display.getText());
            value = value * -1;
            display.setText(Double.toString(value));
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
