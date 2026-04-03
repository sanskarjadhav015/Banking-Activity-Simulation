package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6;
    JButton s, cancel;
    String formno;

    Signup3(String formno) {

        this.formno = formno;

        // 🔹 Panel (for scroll + fullscreen support)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(215, 252, 252));

        // 🔹 Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(150, 5, 100, 100);
        panel.add(image);

        JLabel l1 = new JLabel("Page 3:");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        panel.add(l1);

        JLabel l2 = new JLabel("Account Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(280, 70, 400, 40);
        panel.add(l2);

        // 🔹 Account Type
        panel.add(new JLabel("Account Type:")).setBounds(100, 140, 200, 30);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        r1.setBounds(100, 180, 200, 30);
        r2.setBounds(350, 180, 250, 30);
        r3.setBounds(100, 220, 200, 30);
        r4.setBounds(350, 220, 250, 30);

        panel.add(r1); panel.add(r2); panel.add(r3); panel.add(r4);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2); bg.add(r3); bg.add(r4);

        // 🔹 Services
        panel.add(new JLabel("Services Required:")).setBounds(100, 300, 200, 30);

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");

        c1.setBounds(100, 350, 200, 30);
        c2.setBounds(350, 350, 200, 30);
        c3.setBounds(100, 400, 200, 30);
        c4.setBounds(350, 400, 200, 30);
        c5.setBounds(100, 450, 200, 30);
        c6.setBounds(350, 450, 200, 30);

        panel.add(c1); panel.add(c2); panel.add(c3);
        panel.add(c4); panel.add(c5); panel.add(c6);

        // 🔹 Buttons
        s = new JButton("Submit");
        s.setBounds(250, 550, 120, 30);
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.addActionListener(this);
        panel.add(s);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 550, 120, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e -> System.exit(0));
        panel.add(cancel);

        // 🔹 Scroll + Fullscreen
        panel.setPreferredSize(new Dimension(800, 700));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // 🔹 Account Type
        String atype = null;

        if (r1.isSelected()) atype = "Saving Account";
        else if (r2.isSelected()) atype = "Fixed Deposit Account";
        else if (r3.isSelected()) atype = "Current Account";
        else if (r4.isSelected()) atype = "Recurring Deposit Account";

        // 🔥 VALIDATION
        if (atype == null) {
            JOptionPane.showMessageDialog(null, "Select Account Type");
            return;
        }

        // 🔹 Services
        String fac = "";

        if (c1.isSelected()) fac += "ATM CARD ";
        if (c2.isSelected()) fac += "Internet Banking ";
        if (c3.isSelected()) fac += "Mobile Banking ";
        if (c4.isSelected()) fac += "Email Alerts ";
        if (c5.isSelected()) fac += "Cheque Book ";
        if (c6.isSelected()) fac += "E-Statement ";

        if (fac.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select at least one service");
            return;
        }

        // 🔹 Generate Card + PIN
        Random ran = new Random();

        long cardNum = (ran.nextLong() % 90000000L) + 1409963000000000L;
        String cardno = "" + Math.abs(cardNum);

        long pinNum = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Math.abs(pinNum);

        try {

            Connn conn = new Connn();

            // 🔹 Insert signup3
            String q1 = "INSERT INTO signupthree VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps1 = conn.connection.prepareStatement(q1);

            ps1.setString(1, formno);
            ps1.setString(2, atype);
            ps1.setString(3, cardno);
            ps1.setString(4, pin);
            ps1.setString(5, fac);

            ps1.executeUpdate();

            // 🔹 Insert login
            String q2 = "INSERT INTO login VALUES (?, ?, ?)";
            PreparedStatement ps2 = conn.connection.prepareStatement(q2);

            ps2.setString(1, formno);
            ps2.setString(2, cardno);
            ps2.setString(3, pin);

            ps2.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Card Number: " + cardno + "\nPIN: " + pin);

            new Deposit(pin);
            setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup3("");
    }
}