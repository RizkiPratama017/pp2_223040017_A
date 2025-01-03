import java.awt.event.*;
import javax.swing.*;

public class Latihan7 extends JFrame {

    public boolean CheckboxSelected;

    public Latihan7() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelinput = new JLabel("Nama");
        labelinput.setBounds(15, 20, 350, 10);

        JTextField textField = new JTextField();
        textField.setBounds(15, 40, 350, 30);

        JLabel labelinput1 = new JLabel("Nomor Hp");
        labelinput1.setBounds(15, 80, 350, 10);

        JTextField textField1 = new JTextField();
        textField1.setBounds(15, 100, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15, 140, 350, 10);

        JRadioButton radioButton = new JRadioButton("Laki - Laki", true);
        radioButton.setBounds(15, 160, 100, 30);

        JRadioButton radioButton1 = new JRadioButton("Perempuan");
        radioButton1.setBounds(125, 160, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton);
        bg.add(radioButton1);

        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15, 200, 200, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 240, 100, 30);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 280, 350, 100);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                CheckboxSelected = e.getStateChange() == 1;
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();
                String nomorHp = textField1.getText();
                String jenisKelamin = radioButton.isSelected() ? radioButton.getText() : radioButton1.getText();
                String wargaNegara = CheckboxSelected ? "Ya" : "Tidak";

                txtOutput.setText("Nama: " + nama + 
                "\nNomor Hp: " + nomorHp + 
                "\nJenis Kelamin: " + jenisKelamin + 
                "\nWarga Negara Asing: " + wargaNegara + 
                "\n========================================");
            }
        });

        this.add(labelinput);
        this.add(textField);
        this.add(labelinput1);
        this.add(textField1);
        this.add(labelRadio);
        this.add(radioButton);
        this.add(radioButton1);
        this.add(checkBox);
        this.add(button);
        this.add(txtOutput);

        this.setSize(400, 450);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Latihan7 l = new Latihan7();
                l.setVisible(true);
            }
        });
    }
}
