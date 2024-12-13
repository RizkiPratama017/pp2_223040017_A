package view;

import controller.KurirController;
import controller.PekerjaanController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Kurir;
import model.Pekerjaan;

public class KurirView extends JFrame {

    private KurirController controller;
    private PekerjaanController pekerjaanController;
    private Kurir loggedInKurir;

    private DefaultTableModel kurirTableModel;
    private DefaultTableModel pekerjaanTableModel;

    private JTable kurirTable;
    private JTable pekerjaanTable;

    public KurirView(Kurir kurir) {
        loggedInKurir = kurir;
        controller = new KurirController();
        pekerjaanController = new PekerjaanController();

        setTitle("Dashboard Kurir");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] kurirColumns = {"ID", "Nama", "Email", "No Telepon", "Alamat"};
        kurirTableModel = new DefaultTableModel(kurirColumns, 0);
        kurirTable = new JTable(kurirTableModel);
        JScrollPane kurirScrollPane = new JScrollPane(kurirTable);
        panel.add(kurirScrollPane, BorderLayout.NORTH);

        loadKurirData();

        String[] pekerjaanColumns = {"ID", "Alamat Penjemputan", "Alamat Pengantaran", "Berat Barang", "Jenis Barang"};
        pekerjaanTableModel = new DefaultTableModel(pekerjaanColumns, 0);
        pekerjaanTable = new JTable(pekerjaanTableModel);
        JScrollPane pekerjaanScrollPane = new JScrollPane(pekerjaanTable);
        panel.add(pekerjaanScrollPane, BorderLayout.CENTER);

        loadPekerjaanData();

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAddPekerjaan = new JButton("Tambah Pekerjaan");
        JButton btnUpdatePekerjaan = new JButton("Update Pekerjaan");
        JButton btnDeletePekerjaan = new JButton("Hapus Pekerjaan");

        btnAddPekerjaan.addActionListener(this::onAddPekerjaan);
        btnUpdatePekerjaan.addActionListener(this::onUpdatePekerjaan);
        btnDeletePekerjaan.addActionListener(this::onDeletePekerjaan);

        buttonPanel.add(btnAddPekerjaan);
        buttonPanel.add(btnUpdatePekerjaan);
        buttonPanel.add(btnDeletePekerjaan);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void loadKurirData() {
        kurirTableModel.setRowCount(0);

        kurirTableModel.addRow(new Object[]{
            loggedInKurir.getId(),
            loggedInKurir.getNama(),
            loggedInKurir.getEmail(),
            loggedInKurir.getNoTelepon(),
            loggedInKurir.getAlamat()
        });
    }

    private void loadPekerjaanData() {
        pekerjaanTableModel.setRowCount(0);

        List<Pekerjaan> pekerjaanList = pekerjaanController.getPekerjaanByKurir(loggedInKurir.getId());

        for (Pekerjaan pekerjaan : pekerjaanList) {
            pekerjaanTableModel.addRow(new Object[]{
                pekerjaan.getId(),
                pekerjaan.getAlamatPenjemputan(),
                pekerjaan.getAlamatPengantaran(),
                pekerjaan.getBeratBarang(),
                pekerjaan.getJenisBarang()
            });
        }
    }

    private void onAddPekerjaan(ActionEvent e) {
        JTextField fieldAlamatPenjemputan = new JTextField();
        JTextField fieldAlamatPengantaran = new JTextField();
        JTextField fieldBeratBarang = new JTextField();
        JTextField fieldJenisBarang = new JTextField();

        Object[] message = {
            "Alamat Penjemputan:", fieldAlamatPenjemputan,
            "Alamat Pengantaran:", fieldAlamatPengantaran,
            "Berat Barang:", fieldBeratBarang,
            "Jenis Barang:", fieldJenisBarang
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Tambah Pekerjaan", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String alamatPenjemputan = fieldAlamatPenjemputan.getText();
            String alamatPengantaran = fieldAlamatPengantaran.getText();
            String beratBarang = fieldBeratBarang.getText();
            String jenisBarang = fieldJenisBarang.getText();

            if (alamatPenjemputan.isEmpty() || alamatPengantaran.isEmpty() || beratBarang.isEmpty() || jenisBarang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
                return;
            }

            Pekerjaan pekerjaan = new Pekerjaan(loggedInKurir.getId(), alamatPenjemputan, alamatPengantaran, beratBarang, jenisBarang);
            boolean success = pekerjaanController.addPekerjaan(pekerjaan);
            if (success) {
                JOptionPane.showMessageDialog(this, "Pekerjaan berhasil ditambahkan!");
                loadPekerjaanData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan pekerjaan!");
            }
        }
    }

    private void onUpdatePekerjaan(ActionEvent e) {
        int selectedRow = pekerjaanTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih pekerjaan yang akan diperbarui!");
            return;
        }

        int id = (int) pekerjaanTable.getValueAt(selectedRow, 0);
        String alamatPenjemputan = (String) pekerjaanTable.getValueAt(selectedRow, 1);
        String alamatPengantaran = (String) pekerjaanTable.getValueAt(selectedRow, 2);
        String beratBarang = (String) pekerjaanTable.getValueAt(selectedRow, 3);
        String jenisBarang = (String) pekerjaanTable.getValueAt(selectedRow, 4);

        JTextField fieldAlamatPenjemputan = new JTextField(alamatPenjemputan);
        JTextField fieldAlamatPengantaran = new JTextField(alamatPengantaran);
        JTextField fieldBeratBarang = new JTextField(beratBarang);
        JTextField fieldJenisBarang = new JTextField(jenisBarang);

        Object[] message = {
            "Alamat Penjemputan:", fieldAlamatPenjemputan,
            "Alamat Pengantaran:", fieldAlamatPengantaran,
            "Berat Barang:", fieldBeratBarang,
            "Jenis Barang:", fieldJenisBarang
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Update Pekerjaan", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            alamatPenjemputan = fieldAlamatPenjemputan.getText();
            alamatPengantaran = fieldAlamatPengantaran.getText();
            beratBarang = fieldBeratBarang.getText();
            jenisBarang = fieldJenisBarang.getText();

            if (alamatPenjemputan.isEmpty() || alamatPengantaran.isEmpty() || beratBarang.isEmpty() || jenisBarang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
                return;
            }

            Pekerjaan pekerjaan = new Pekerjaan(id, loggedInKurir.getId(), alamatPenjemputan, alamatPengantaran, beratBarang, jenisBarang);
            boolean success = pekerjaanController.updatePekerjaan(pekerjaan);
            if (success) {
                JOptionPane.showMessageDialog(this, "Pekerjaan berhasil diperbarui!");
                loadPekerjaanData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui pekerjaan!");
            }
        }
    }

    private void onDeletePekerjaan(ActionEvent e) {
        int selectedRow = pekerjaanTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih pekerjaan yang akan dihapus!");
            return;
        }

        int id = (int) pekerjaanTable.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus pekerjaan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = pekerjaanController.deletePekerjaan(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Pekerjaan berhasil dihapus!");
                loadPekerjaanData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus pekerjaan!");
            }
        }
    }
}
