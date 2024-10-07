import java.awt.*;
import javax.swing.*;

public class formbiodata extends JFrame {
    
    public formbiodata() {
        setTitle("Form Biodata");
        setSize(400, 500);
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
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(namaLabel, gbc);
        
        JTextField namaField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(namaField, gbc);
       
        JLabel hpLabel = new JLabel("Nomor HP:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(hpLabel, gbc);
        
        JTextField hpField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(hpField, gbc);
        
        
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(jenisKelaminLabel, gbc);
        
        JPanel jenisKelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup jenisKelaminGroup = new ButtonGroup();
        JRadioButton lakiLakiRadio = new JRadioButton("Laki-Laki");
        JRadioButton perempuanRadio = new JRadioButton("Perempuan"+"\n");
        jenisKelaminGroup.add(lakiLakiRadio);
        jenisKelaminGroup.add(perempuanRadio);
        jenisKelaminPanel.add(lakiLakiRadio);
        jenisKelaminPanel.add(perempuanRadio);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(jenisKelaminPanel, gbc);
        
       
        JCheckBox wnaCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(wnaCheckBox, gbc);
        
        
        JButton simpanButton = new JButton("Simpan");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(simpanButton, gbc);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public  void  run(){
            formbiodata f = new formbiodata();
            f.setVisible(true);
            }
        });
    }
}