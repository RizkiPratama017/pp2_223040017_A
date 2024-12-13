package view;

import controller.KurirController;
import java.awt.*;
import javax.swing.*;
import model.Kurir;

public class LoginView extends JFrame {

    private KurirController controller;

    public LoginView() {
        controller = new KurirController();
        setTitle("Login Kurir");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel labelEmail = new JLabel("Email:");
        JTextField fieldEmail = new JTextField();

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField fieldPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            String email = fieldEmail.getText();
            String password = new String(fieldPassword.getPassword());
            Kurir kurir = controller.loginKurir(email, password);
            if (kurir != null) {
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                new KurirView(kurir).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login Gagal! Cek email dan password.");
            }
        });

        JButton btnRegister = new JButton("Daftar");
        btnRegister.addActionListener(e -> {
            new RegisterView().setVisible(true);
            this.dispose();
        });

        panel.add(labelEmail);
        panel.add(fieldEmail);
        panel.add(labelPassword);
        panel.add(fieldPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
