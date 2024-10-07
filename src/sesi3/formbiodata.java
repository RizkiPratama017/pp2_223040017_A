import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class formbiodata extends JFrame {
    
    private JTextField namaField;
    private JTextField hpField;
    private JRadioButton lakiLakiRadio;
    private JRadioButton perempuanRadio;
    private JCheckBox wnaCheckBox;
    private JTextArea outputArea;

    public formbiodata() {
        setTitle("Form Biodata");
        setSize(400, 600);  // Adjusted size to fit the output area
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel titleLabel = new JLabel("Form Biodata");
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(titleLabel, gbc);
        
        JLabel namaLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(namaLabel, gbc);
        
        namaField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(namaField, gbc);
       
        JLabel hpLabel = new JLabel("Nomor HP:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(hpLabel, gbc);
        
        hpField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(hpField, gbc);
        
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(jenisKelaminLabel, gbc);
        
        JPanel jenisKelaminPanel = new JPanel();
        jenisKelaminPanel.setLayout(new BoxLayout(jenisKelaminPanel, BoxLayout.Y_AXIS));
        ButtonGroup jenisKelaminGroup = new ButtonGroup();
        lakiLakiRadio = new JRadioButton("Laki-Laki");
        perempuanRadio = new JRadioButton("Perempuan");
        jenisKelaminGroup.add(lakiLakiRadio);
        jenisKelaminGroup.add(perempuanRadio);
        jenisKelaminPanel.add(lakiLakiRadio);
        jenisKelaminPanel.add(perempuanRadio);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(jenisKelaminPanel, gbc);
    
       
        wnaCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(wnaCheckBox, gbc);
        
        JButton simpanButton = new JButton("Simpan");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(simpanButton, gbc);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc);

        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOutput();
            }
        });

        setVisible(true);
    }

    private void displayOutput() {
        String nama = namaField.getText();
        String nomorHP = hpField.getText();
        String jenisKelamin = lakiLakiRadio.isSelected() ? "Laki-Laki" : "Perempuan";
        String wna = wnaCheckBox.isSelected() ? "Ya" : "Tidak";

        String output = String.format(
            "Nama: %s\nNomor HP: %s\nJenis Kelamin: %s\nWarga Negara Asing: %s",
            nama, nomorHP, jenisKelamin, wna
        );

        outputArea.setText(output);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                formbiodata f = new formbiodata();
                f.setVisible(true);
            }
        });
    }
}
