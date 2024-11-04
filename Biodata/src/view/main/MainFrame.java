package view.main;

import dao.BiodataDao;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Biodata;
import view.biodata.BiodataFrame;

public class MainFrame extends JFrame {
    private JTable table;
    private BiodataDao biodataDao;

    public MainFrame() {
        biodataDao = new BiodataDao();
        setTitle("Menu Utama");
        setLayout(new BorderLayout());
       
        JLabel title = new JLabel("Daftar Biodata", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        table = new JTable();
        updateTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Tambah Biodata");
        btnAdd.addActionListener(e -> {
            new BiodataFrame();
        });
        buttonPanel.add(btnAdd);

        JButton btnDelete = new JButton("Hapus Biodata");
        btnDelete.addActionListener(e -> deleteSelectedBiodata());
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTable() {
        try {
            List<Biodata> biodataList = biodataDao.getAllBiodata();
            String[] columnNames = {"ID", "Nama", "Alamat", "Telepon", "Jenis Kelamin", "Hobi", "Pendidikan", "Kota Asal"};
            Object[][] data = new Object[biodataList.size()][8];

            for (int i = 0; i < biodataList.size(); i++) {
                Biodata biodata = biodataList.get(i);
                data[i][0] = biodata.getId();
                data[i][1] = biodata.getNama();
                data[i][2] = biodata.getAlamat();
                data[i][3] = biodata.getTelepon();
                data[i][4] = biodata.getJenisKelamin();
                data[i][5] = biodata.getHobi();
                data[i][6] = biodata.getPendidikan();
                data[i][7] = biodata.getKotaAsal();
            }

            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data: " + e.getMessage());
        }
    }

    private void deleteSelectedBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus biodata ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    biodataDao.deleteBiodata(id);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    updateTable();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih biodata yang ingin dihapus.");
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
