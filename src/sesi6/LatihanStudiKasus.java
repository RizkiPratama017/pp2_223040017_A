import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LatihanStudiKasus extends JFrame {
    private JTextField textField;
    private JTextArea textArea;
    private JRadioButton radio1, radio2;
    private JCheckBox checkBox1, checkBox2;
    private JComboBox<String> comboBox;
    private JList<String> list;
    private JTable table;
    private DefaultTableModel tableModel;
    private ButtonGroup genderGroup;

    public LatihanStudiKasus() {
        setTitle("Aplikasi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //no 1
        // Membuat JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem = new JMenuItem("Form Input");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //no 2 
        // Panel Form Input
        JPanel panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBounds(20, 20, 400, 300);

        // JTextField
        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setBounds(10, 10, 100, 25);
        textField = new JTextField();
        textField.setBounds(120, 10, 250, 25);
        panelForm.add(nameLabel);
        panelForm.add(textField);

        // JTextArea
        JLabel addressLabel = new JLabel("Alamat:");
        addressLabel.setBounds(10, 50, 100, 25);
        textArea = new JTextArea();
        JScrollPane addressScrollPane = new JScrollPane(textArea);
        addressScrollPane.setBounds(120, 50, 250, 60);
        panelForm.add(addressLabel);
        panelForm.add(addressScrollPane);

        // JRadioButton
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        genderLabel.setBounds(10, 130, 100, 25);
        radio1 = new JRadioButton("Laki-laki");
        radio2 = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup();
        genderGroup.add(radio1);
        genderGroup.add(radio2);
        radio1.setBounds(120, 130, 100, 25);
        radio2.setBounds(220, 130, 100, 25);
        panelForm.add(genderLabel);
        panelForm.add(radio1);
        panelForm.add(radio2);

        // JCheckBox
        JLabel hobbyLabel = new JLabel("Hobi:");
        hobbyLabel.setBounds(10, 170, 100, 25);
        checkBox1 = new JCheckBox("Basket");
        checkBox2 = new JCheckBox("Sepakbola");
        checkBox1.setBounds(120, 170, 100, 25);
        checkBox2.setBounds(220, 170, 100, 25);
        panelForm.add(hobbyLabel);
        panelForm.add(checkBox1);
        panelForm.add(checkBox2);

        // JComboBox
        JLabel educationLabel = new JLabel("Pendidikan:");
        educationLabel.setBounds(10, 210, 100, 25);
        comboBox = new JComboBox<>(new String[]{"SD", "SMP", "SMA"});
        comboBox.setBounds(120, 210, 250, 25);
        panelForm.add(educationLabel);
        panelForm.add(comboBox);

        // JList
        JLabel cityLabel = new JLabel("Kota Asal:");
        cityLabel.setBounds(10, 250, 100, 25);
        list = new JList<>(new String[]{"Jakarta", "Bandung", "Surabaya"});
        JScrollPane cityScrollPane = new JScrollPane(list);
        cityScrollPane.setBounds(120, 250, 250, 60);
        panelForm.add(cityLabel);
        panelForm.add(cityScrollPane);

        //no 3
        // JTable untuk menampilkan data
        tableModel = new DefaultTableModel(new String[]{"Nama", "Alamat", "Jenis Kelamin", "Hobi", "Pendidikan", "Kota Asal"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 340, 740, 200);
        add(tableScrollPane);

        // Tombol untuk menambah data ke JTable
        JButton addButton = new JButton("Tambah Data");
        addButton.setBounds(450, 20, 150, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahData();
            }
        });

        add(panelForm);
        add(addButton);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Form Input Terpilih!");
            }
        });
    }

    private void tambahData() {
        // Validasi input
        if (textField.getText().isEmpty() || textArea.getText().isEmpty() || 
            (!radio1.isSelected() && !radio2.isSelected()) || 
            list.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi semua input!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nama = textField.getText();
        String alamat = textArea.getText();
        String jenisKelamin = radio1.isSelected() ? "Laki-laki" : "Perempuan";
        String hobi = (checkBox1.isSelected() ? "Basket " : "") + (checkBox2.isSelected() ? "Sepakbola" : "");
        hobi = hobi.isEmpty() ? " " : hobi;
        String pendidikan = comboBox.getSelectedItem() != null ? (String) comboBox.getSelectedItem() : "";
        String kotaAsal = list.getSelectedValue() != null ? list.getSelectedValue() : "";

        // Tambahkan data ke tabel
        tableModel.addRow(new Object[]{nama, alamat, jenisKelamin, hobi, pendidikan, kotaAsal});

        // Reset form setelah data ditambahkan
        textField.setText("");
        textArea.setText("");
        genderGroup.clearSelection();
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        comboBox.setSelectedIndex(0);
        list.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LatihanStudiKasus app = new LatihanStudiKasus();
            app.setVisible(true);
        });
    }
}
