package view;

import controller.KurirController;
import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {

    private KurirController controller;

    public RegisterView() {
        controller = new KurirController();
        setTitle("Registrasi Kurir");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel labelNama = new JLabel("Nama:");
        JTextField fieldNama = new JTextField();

        JLabel labelEmail = new JLabel("Email:");
        JTextField fieldEmail = new JTextField();

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField fieldPassword = new JPasswordField();

        JLabel labelNoTelepon = new JLabel("No Telepon:");
        JTextField fieldNoTelepon = new JTextField();

        JLabel labelAlamat = new JLabel("Alamat:");
        JTextField fieldAlamat = new JTextField();

        JButton btnRegister = new JButton("Daftar");
        btnRegister.addActionListener(e -> {
            String nama = fieldNama.getText();
            String email = fieldEmail.getText();
            String password = new String(fieldPassword.getPassword());
            String noTelepon = fieldNoTelepon.getText();
            String alamat = fieldAlamat.getText();
            boolean success = controller.registerKurir(nama, email, password, noTelepon, alamat);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registrasi Berhasil!");
                new LoginView().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registrasi Gagal!");
            }
        });

        panel.add(labelNama);
        panel.add(fieldNama);
        panel.add(labelEmail);
        panel.add(fieldEmail);
        panel.add(labelPassword);
        panel.add(fieldPassword);
        panel.add(labelNoTelepon);
        panel.add(fieldNoTelepon);
        panel.add(labelAlamat);
        panel.add(fieldAlamat);
        panel.add(new JLabel());
        panel.add(btnRegister);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterView().setVisible(true));
    }
}
