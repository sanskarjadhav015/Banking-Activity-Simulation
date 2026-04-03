package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class Signup2 extends JFrame implements ActionListener {

    JComboBox comboBox, comboBox2, comboBox3, comboBox4, comboBox5;
    JTextField textPan, textAadhar;
    JRadioButton r1, r2, e1, e2;
    JButton next, cancel;
    String formno;

    Signup2(String formno) {

        this.formno = formno;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 208, 76));

        // 🔹 Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(150, 5, 100, 100);
        panel.add(image);

        JLabel l1 = new JLabel("Page 2");
        l1.setBounds(300, 30, 300, 40);
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        panel.add(l1);

        JLabel l2 = new JLabel("Additional Details");
        l2.setBounds(300, 60, 300, 40);
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        panel.add(l2);

        // 🔹 Religion
        comboBox = new JComboBox(new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Other"});
        comboBox.setBounds(350, 120, 320, 30);
        panel.add(new JLabel("Religion:")).setBounds(100,120,200,30);
        panel.add(comboBox);

        // 🔹 Category
        comboBox2 = new JComboBox(new String[]{"General", "OBC", "SC", "ST", "Other"});
        comboBox2.setBounds(350, 170, 320, 30);
        panel.add(new JLabel("Category:")).setBounds(100,170,200,30);
        panel.add(comboBox2);

        // 🔹 Income
        comboBox3 = new JComboBox(new String[]{"Null", "<1,50,000", "<2,50,000", "5,00,000", "Up to 10,00,000", "Above 10,00,000"});
        comboBox3.setBounds(350, 220, 320, 30);
        panel.add(new JLabel("Income:")).setBounds(100,220,200,30);
        panel.add(comboBox3);

        // 🔹 Education
        comboBox4 = new JComboBox(new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"});
        comboBox4.setBounds(350, 270, 320, 30);
        panel.add(new JLabel("Education:")).setBounds(100,270,200,30);
        panel.add(comboBox4);

        // 🔹 Occupation
        comboBox5 = new JComboBox(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"});
        comboBox5.setBounds(350, 320, 320, 30);
        panel.add(new JLabel("Occupation:")).setBounds(100,320,200,30);
        panel.add(comboBox5);

        // 🔹 PAN
        panel.add(new JLabel("PAN Number:")).setBounds(100,370,200,30);
        textPan = new JTextField();
        textPan.setBounds(350, 370, 320, 30);
        panel.add(textPan);

        // 🔹 Aadhar
        panel.add(new JLabel("Aadhar Number:")).setBounds(100,420,200,30);
        textAadhar = new JTextField();
        textAadhar.setBounds(350, 420, 320, 30);
        panel.add(textAadhar);

        // 🔹 Senior Citizen
        panel.add(new JLabel("Senior Citizen:")).setBounds(100,470,200,30);
        r1 = new JRadioButton("Yes");
        r1.setBounds(350, 470, 80, 30);
        r2 = new JRadioButton("No");
        r2.setBounds(450, 470, 80, 30);
        panel.add(r1);
        panel.add(r2);

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);

        // 🔹 Existing Account
        panel.add(new JLabel("Existing Account:")).setBounds(100,520,200,30);
        e1 = new JRadioButton("Yes");
        e1.setBounds(350, 520, 80, 30);
        e2 = new JRadioButton("No");
        e2.setBounds(450, 520, 80, 30);
        panel.add(e1);
        panel.add(e2);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(e1);
        bg2.add(e2);

        // 🔹 Buttons
        next = new JButton("Next");
        next.setBounds(570, 600, 100, 30);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 600, 100, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(e -> System.exit(0));
        panel.add(cancel);

        // 🔥 REAL-TIME VALIDATION
        textPan.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetterOrDigit(c) || textPan.getText().length() >= 10) {
                    e.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textPan.setText(textPan.getText().toUpperCase());
            }
        });

        textAadhar.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || textAadhar.getText().length() >= 12) {
                    e.consume();
                }
            }
        });

        panel.setPreferredSize(new Dimension(800, 800));

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText().trim();
        String aadhar = textAadhar.getText().trim();

        String scitizen = r1.isSelected() ? "Yes" : r2.isSelected() ? "No" : "";
        String eAccount = e1.isSelected() ? "Yes" : e2.isSelected() ? "No" : "";

        // 🔥 VALIDATION BLOCK
        if (pan.isEmpty() || aadhar.isEmpty() || scitizen.isEmpty() || eAccount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        if (!pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            JOptionPane.showMessageDialog(null, "Invalid PAN (ABCDE1234F)");
            return;
        }

        if (!aadhar.matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null, "Aadhar must be 12 digits");
            return;
        }

        try {
            Connn c = new Connn();

            String q = "INSERT INTO signuptwo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.connection.prepareStatement(q);

            ps.setString(1, formno);
            ps.setString(2, rel);
            ps.setString(3, cate);
            ps.setString(4, inc);
            ps.setString(5, edu);
            ps.setString(6, occ);
            ps.setString(7, pan);
            ps.setString(8, aadhar);
            ps.setString(9, scitizen);
            ps.setString(10, eAccount);

            ps.executeUpdate();

            new Signup3(formno);
            setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("");
    }
}