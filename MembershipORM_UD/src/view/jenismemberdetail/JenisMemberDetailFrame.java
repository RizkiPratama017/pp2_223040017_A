package view.jenismemberdetail;

import dao.JenisMemberDao;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.JenisMember;

public class JenisMemberDetailFrame extends JFrame {

    private JTextField textFieldId;
    private JTextField textFieldNama;
    private JenisMember jenisMember;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberDetailFrame(JenisMember jenisMember, JenisMemberDao jenisMemberDao) {
        this.jenisMember = jenisMember;
        this.jenisMemberDao = jenisMemberDao;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(15, 40, 350, 10);
        textFieldId = new JTextField(jenisMember.getId());
        textFieldId.setBounds(15, 60, 350, 30);
        textFieldId.setEditable(false);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 100, 350, 10);
        textFieldNama = new JTextField(jenisMember.getNama());
        textFieldNama.setBounds(15, 120, 350, 30);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(15, 240, 100, 40);
        buttonUpdate.addActionListener(e -> updateMember());

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(125, 240, 100, 40);
        buttonDelete.addActionListener(e -> deleteMember());

        this.add(labelId);
        this.add(textFieldId);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(buttonUpdate);
        this.add(buttonDelete);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    private void updateMember() {
        jenisMember.setNama(textFieldNama.getText());
        int rowsUpdated = jenisMemberDao.update(jenisMember);
        if (rowsUpdated > 0) {
            showAlert("Jenis Member berhasil diperbarui.");
            this.dispose();
        } else {
            showAlert("Gagal memperbarui data.");
        }
    }

    private void deleteMember() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus jenis member ini?",
                "Konfirmasi Penghapusan",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int rowsDeleted = jenisMemberDao.delete(jenisMember.getId());
            if (rowsDeleted > 0) {
                showAlert("Jenis Member berhasil dihapus.");
                this.dispose();
            } else {
                showAlert("Gagal menghapus data.");
            }
        }
    }

    private void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
