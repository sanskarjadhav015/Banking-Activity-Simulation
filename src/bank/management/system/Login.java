package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;

    JButton button1, button2, button3, button4;

    Login() {
        super("Bank Management System");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Background Image
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(
                screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
        JLabel iiimage = new JLabel(new ImageIcon(iii2));
        iiimage.setBounds(0, 0, screenSize.width, screenSize.height);
        add(iiimage);

        // Bank icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(350, 10, 100, 100);
        iiimage.add(image);

        // Labels
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Arial", Font.BOLD, 36));
        label1.setBounds(230, 120, 450, 40);
        iiimage.add(label1);

        label2 = new JLabel("Card No:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Arial", Font.BOLD, 22));
        label2.setBounds(200, 200, 150, 30);
        iiimage.add(label2);

        textField2 = new JTextField();
        textField2.setBounds(350, 200, 250, 30);
        iiimage.add(textField2);

        label3 = new JLabel("PIN:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Arial", Font.BOLD, 22));
        label3.setBounds(200, 260, 150, 30);
        iiimage.add(label3);

        passwordField3 = new JPasswordField();
        passwordField3.setBounds(350, 260, 250, 30);
        iiimage.add(passwordField3);

        // Buttons
        button1 = new JButton("SIGN IN");
        button1.setBounds(300, 320, 120, 30);
        button1.addActionListener(this);
        iiimage.add(button1);

        button2 = new JButton("CLEAR");
        button2.setBounds(450, 320, 120, 30);
        button2.addActionListener(this);
        iiimage.add(button2);

        button3 = new JButton("SIGN UP");
        button3.setBounds(300, 370, 270, 30);
        button3.addActionListener(this);
        iiimage.add(button3);

        button4 = new JButton("EXIT");
        button4.setBounds(screenSize.width - 150, 20, 100, 30);
        button4.setBackground(Color.RED);
        button4.setForeground(Color.WHITE);
        button4.addActionListener(this);
        iiimage.add(button4);

        // 🔥 Real-time validation (digits only)
        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        passwordField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // 🔹 SIGN IN
        if (e.getSource() == button1) {

            String cardno = textField2.getText().trim();
            String pin = new String(passwordField3.getPassword()).trim();

            // 🔥 VALIDATIONS
            if (cardno.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            if (!cardno.matches("\\d{8,16}")) {
                JOptionPane.showMessageDialog(null, "Card must be 8–16 digits");
                return;
            }

            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                return;
            }

            try {
                Connn c = new Connn();

                String q = "SELECT * FROM login WHERE card_number=? AND pin=?";
                PreparedStatement ps = c.connection.prepareStatement(q);

                ps.setString(1, cardno);
                ps.setString(2, pin);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new main_Class(pin);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Card or PIN");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // 🔹 CLEAR
        else if (e.getSource() == button2) {
            textField2.setText("");
            passwordField3.setText("");
        }

        // 🔹 SIGNUP
        else if (e.getSource() == button3) {
            new Signup();
            setVisible(false);
        }

        // 🔹 EXIT
        else if (e.getSource() == button4) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}