package view.biodata;

import dao.BiodataDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Biodata;
import view.main.MainFrame;

public class BiodataFrame extends JFrame {
    private JTextField txtNama, txtAlamat, txtTelepon;
    private JRadioButton radio1, radio2;
    private JCheckBox checkBox1, checkBox2;
    private JComboBox<String> comboBox;
    private JList<String> list;
    private BiodataDao biodataDao;

    public BiodataFrame() {
        biodataDao = new BiodataDao();
        setTitle("Form Input Biodata");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nama:"), gbc);
        
        gbc.gridx = 1;
        txtNama = new JTextField(20);
        add(txtNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Alamat:"), gbc);
        
        gbc.gridx = 1;
        txtAlamat = new JTextField(20);
        add(txtAlamat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Telepon:"), gbc);
        
        gbc.gridx = 1;
        txtTelepon = new JTextField(20);
        add(txtTelepon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Jenis Kelamin:"), gbc);
        
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel();
        radio1 = new JRadioButton("Laki-laki");
        radio2 = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radio1);
        genderGroup.add(radio2);
        genderPanel.add(radio1);
        genderPanel.add(radio2);
        add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Hobi:"), gbc);
        
        gbc.gridx = 1;
        JPanel hobbyPanel = new JPanel();
        checkBox1 = new JCheckBox("Basket");
        checkBox2 = new JCheckBox("Sepakbola");
        hobbyPanel.add(checkBox1);
        hobbyPanel.add(checkBox2);
        add(hobbyPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Pendidikan:"), gbc);
        
        gbc.gridx = 1;
        comboBox = new JComboBox<>(new String[]{"SD", "SMP", "SMA"});
        add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Kota Asal:"), gbc);
        
        gbc.gridx = 1;
        list = new JList<>(new String[]{"Jakarta", "Bandung", "Surabaya"});
        JScrollPane cityScrollPane = new JScrollPane(list);
        cityScrollPane.setPreferredSize(new Dimension(150, 60));
        add(cityScrollPane, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Biodata biodata = new Biodata();
                biodata.setNama(txtNama.getText());
                biodata.setAlamat(txtAlamat.getText());
                biodata.setTelepon(txtTelepon.getText());
                biodata.setJenisKelamin(radio1.isSelected() ? "Laki-laki" : "Perempuan");
                String hobi = (checkBox1.isSelected() ? "Basket " : "") + (checkBox2.isSelected() ? "Sepakbola" : "");
                biodata.setHobi(hobi.isEmpty() ? "Tidak ada" : hobi);
                biodata.setPendidikan((String) comboBox.getSelectedItem());
                biodata.setKotaAsal(list.getSelectedValue());

                try {
                    biodataDao.insertBiodata(biodata);
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                    resetForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        add(btnSave, gbc);

        gbc.gridy = 8; 
        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame();
            }
        });
        add(btnBack, gbc);

        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void resetForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        radio1.setSelected(false);
        radio2.setSelected(false);
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        comboBox.setSelectedIndex(0);
        list.clearSelection();
    }
}
