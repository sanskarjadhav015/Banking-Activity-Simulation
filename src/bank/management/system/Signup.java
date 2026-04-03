package bank.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    JRadioButton r1, r2, m1, m2, m3;
    JButton next, cancel;

    JTextField textName, textFname, textEmail, textAdd, textcity, textState, textPin;
    JDateChooser dateChooser;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = "" + Math.abs(first4);

    Signup() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(222, 255, 228));

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(25, 10, 100, 100);
        panel.add(image);

        JLabel label1 = new JLabel("APPLICATION FORM NO. " + first);
        label1.setBounds(160, 20, 600, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(label1);

        JLabel label2 = new JLabel("Personal Details");
        label2.setBounds(280, 80, 300, 30);
        panel.add(label2);

        // Full Name
        panel.add(new JLabel("Full Name:")).setBounds(100, 150, 200, 30);
        textName = new JTextField();
        textName.setBounds(300, 150, 400, 30);
        panel.add(textName);

        // Father Name
        panel.add(new JLabel("Father Name:")).setBounds(100, 200, 200, 30);
        textFname = new JTextField();
        textFname.setBounds(300, 200, 400, 30);
        panel.add(textFname);

        // Gender
        panel.add(new JLabel("Gender:")).setBounds(100, 250, 200, 30);
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setBounds(300, 250, 80, 30);
        r2.setBounds(400, 250, 100, 30);
        panel.add(r1);
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        // DOB
        panel.add(new JLabel("DOB:")).setBounds(100, 300, 200, 30);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 300, 400, 30);
        panel.add(dateChooser);

        // Email
        panel.add(new JLabel("Email:")).setBounds(100, 350, 200, 30);
        textEmail = new JTextField();
        textEmail.setBounds(300, 350, 400, 30);
        panel.add(textEmail);

        // Marital Status
        panel.add(new JLabel("Marital Status:")).setBounds(100, 400, 200, 30);
        m1 = new JRadioButton("Married");
        m2 = new JRadioButton("Unmarried");
        m3 = new JRadioButton("Other");

        m1.setBounds(300, 400, 100, 30);
        m2.setBounds(420, 400, 120, 30);
        m3.setBounds(560, 400, 100, 30);

        panel.add(m1);
        panel.add(m2);
        panel.add(m3);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(m1);
        bg2.add(m2);
        bg2.add(m3);

        // Address
        panel.add(new JLabel("Address:")).setBounds(100, 450, 200, 30);
        textAdd = new JTextField();
        textAdd.setBounds(300, 450, 400, 30);
        panel.add(textAdd);

        // City
        panel.add(new JLabel("City:")).setBounds(100, 500, 200, 30);
        textcity = new JTextField();
        textcity.setBounds(300, 500, 400, 30);
        panel.add(textcity);

        // Pincode
        panel.add(new JLabel("Pincode:")).setBounds(100, 550, 200, 30);
        textPin = new JTextField();
        textPin.setBounds(300, 550, 400, 30);
        panel.add(textPin);

        // State
        panel.add(new JLabel("State:")).setBounds(100, 600, 200, 30);
        textState = new JTextField();
        textState.setBounds(300, 600, 400, 30);
        panel.add(textState);

        // ✅ Instant letter validation
        addLetterValidation(textName, "Full Name");
        addLetterValidation(textFname, "Father Name");
        addLetterValidation(textcity, "City");
        addLetterValidation(textState, "State");

        // Buttons
        next = new JButton("Next");
        next.setBounds(600, 700, 100, 30);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 700, 100, 30);
        cancel.addActionListener(e -> System.exit(0));
        panel.add(cancel);

        panel.setPreferredSize(new Dimension(850, 850));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    // ✅ instant validation while typing
    private void addLetterValidation(JTextField field, String fieldName) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    JOptionPane.showMessageDialog(null,
                            fieldName + " can contain only letters");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() != next) return;

        String formno = first;
        String name = textName.getText().trim();
        String fname = textFname.getText().trim();

        String dob = "";
        if (dateChooser.getDate() != null) {
            dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        }

        String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
        String email = textEmail.getText().trim();
        String marital = m1.isSelected() ? "Married" :
                m2.isSelected() ? "Unmarried" :
                        m3.isSelected() ? "Other" : null;

        String address = textAdd.getText().trim();
        String city = textcity.getText().trim();
        String pincode = textPin.getText().trim();
        String state = textState.getText().trim();

        // Validation
        if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || gender == null ||
                email.isEmpty() || marital == null || address.isEmpty() ||
                city.isEmpty() || pincode.isEmpty() || state.isEmpty()) {

            JOptionPane.showMessageDialog(null, "All fields required");
            return;
        }

        if (!pincode.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "Pincode must be 6 digits");
            return;
        }

        try {
            Connn c = new Connn();

            String q = "INSERT INTO signup VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.connection.prepareStatement(q);

            ps.setString(1, formno);
            ps.setString(2, name);
            ps.setString(3, fname);
            ps.setString(4, dob);
            ps.setString(5, gender);
            ps.setString(6, email);
            ps.setString(7, marital);
            ps.setString(8, address);
            ps.setString(9, city);
            ps.setString(10, pincode);
            ps.setString(11, state);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Page 1 Saved Successfully");

            SwingUtilities.invokeLater(() -> {
                new Signup2(formno).setVisible(true);
                dispose();
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}