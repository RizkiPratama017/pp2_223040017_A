import java.awt.event.*;
import javax.swing.*;

public class tugas extends JFrame {

    public boolean CheckboxSelected;

    public tugas() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");

        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(0, 0, 400, 700);

        JLabel labelinput = new JLabel("Nama");
        labelinput.setBounds(15, 20, 350, 10);
        inputPanel.add(labelinput);

        JTextField textField = new JTextField();
        textField.setBounds(15, 40, 350, 30);
        inputPanel.add(textField);

        JLabel labelinput1 = new JLabel("Nomor Hp");
        labelinput1.setBounds(15, 80, 350, 10);
        inputPanel.add(labelinput1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(15, 100, 350, 30);
        inputPanel.add(textField1);

        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15, 140, 350, 10);
        inputPanel.add(labelRadio);

        JRadioButton radioButton = new JRadioButton("Laki - Laki", true);
        radioButton.setBounds(15, 160, 100, 30);
        inputPanel.add(radioButton);

        JRadioButton radioButton1 = new JRadioButton("Perempuan");
        radioButton1.setBounds(125, 160, 100, 30);
        inputPanel.add(radioButton1);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton);
        bg.add(radioButton1);

        JLabel labelSavings = new JLabel("Jenis Tabungan");
        labelSavings.setBounds(15, 200, 350, 10);
        inputPanel.add(labelSavings);

        String[] savingsOptions = {"Diamond", "Platinum", "Gold", "Silver"};
        JList<String> savingsList = new JList<>(savingsOptions);
        JScrollPane savingsScrollPane = new JScrollPane(savingsList);
        savingsScrollPane.setBounds(15, 220, 350, 60);
        inputPanel.add(savingsScrollPane);

        JLabel labelFreq = new JLabel("Frekuensi Transaksi per Bulan");
        labelFreq.setBounds(15, 290, 350, 10);
        inputPanel.add(labelFreq);

        JSpinner freqSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        freqSpinner.setBounds(15, 310, 350, 30);
        inputPanel.add(freqSpinner);

        JLabel labelDob = new JLabel("Tanggal Lahir");
        labelDob.setBounds(15, 350, 350, 10);
        inputPanel.add(labelDob);

        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setBounds(15, 370, 350, 30);
        inputPanel.add(dobSpinner);

        JLabel labelPwd = new JLabel("Password");
        labelPwd.setBounds(15, 410, 350, 10);
        inputPanel.add(labelPwd);

        JPasswordField pwdField = new JPasswordField();
        pwdField.setBounds(15, 430, 350, 30);
        inputPanel.add(pwdField);

        JLabel labelConfirmPwd = new JLabel("Confirm Password");
        labelConfirmPwd.setBounds(15, 470, 350, 10);
        inputPanel.add(labelConfirmPwd);

        JPasswordField confirmPwdField = new JPasswordField();
        confirmPwdField.setBounds(15, 490, 350, 30);
        inputPanel.add(confirmPwdField);

        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15, 530, 200, 30);
        inputPanel.add(checkBox);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 570, 100, 30);
        inputPanel.add(buttonSimpan);

        JButton buttonReset = new JButton("Reset");
        buttonReset.setBounds(120, 570, 100, 30);
        inputPanel.add(buttonReset);

        JButton buttonKeluar = new JButton("Keluar");
        buttonKeluar.setBounds(225, 570, 100, 30);
        inputPanel.add(buttonKeluar);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(420, 20, 350, 600);
        txtOutput.setEditable(false);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                CheckboxSelected = e.getStateChange() == 1;
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField1.setText("");
                bg.clearSelection();
                savingsList.clearSelection();
                freqSpinner.setValue(1);
                dobSpinner.setValue(new java.util.Date());
                pwdField.setText("");
                confirmPwdField.setText("");
                checkBox.setSelected(false);
                txtOutput.setText("");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonKeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();
                String nomorHp = textField1.getText();
                String jenisKelamin = radioButton.isSelected() ? radioButton.getText() : radioButton1.getText();
                String jenisTabungan = savingsList.getSelectedValue();
                int frekuensi = (Integer) freqSpinner.getValue();
                java.util.Date tanggalLahir = (java.util.Date) dobSpinner.getValue();
                String wargaNegara = CheckboxSelected ? "Ya" : "Tidak";
                boolean passwordMatch = new String(pwdField.getPassword()).equals(new String(confirmPwdField.getPassword()));

                txtOutput.setText("Nama: " + nama + 
                                "\nNomor Hp: " + nomorHp + 
                                "\nJenis Kelamin: " + jenisKelamin + 
                                "\nJenis Tabungan: " + jenisTabungan + 
                                "\nFrekuensi Transaksi per Bulan: " + frekuensi + 
                                "\nTanggal Lahir: " + dateEditor.getFormat().format(tanggalLahir) + 
                                "\nWarga Negara Asing: " + wargaNegara + 
                                "\nPassword Match: " + (passwordMatch ? "Cocok" : "Tidak Cocok") + 
                                "\n========================================");
            }
        });

        this.add(inputPanel);
        this.add(txtOutput);
        this.setSize(800, 700);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tugas t = new tugas();
                t.setVisible(true);
            }
        });
    }
}
