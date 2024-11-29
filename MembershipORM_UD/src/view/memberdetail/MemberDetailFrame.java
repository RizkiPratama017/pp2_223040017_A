package view.memberdetail;

import dao.JenisMemberDao;
import dao.MemberDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.JenisMember;
import model.Member;

public class MemberDetailFrame extends JFrame {

    private JTextField textFieldId;
    private JTextField textFieldNama;
    private Member member;
    private MemberDao memberDao;
    private JComboBox<String> comboJenis;
    private java.util.List<JenisMember> jenisMemberList;
    private JenisMemberDao jenisMemberDao;

    public MemberDetailFrame(Member member, MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.member = member;
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(15, 40, 350, 10);
        textFieldId = new JTextField(member.getId());
        textFieldId.setBounds(15, 60, 350, 30);
        textFieldId.setEditable(false);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 100, 350, 10);
        textFieldNama = new JTextField(member.getNama());
        textFieldNama.setBounds(15, 120, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member: ");
        labelJenis.setBounds(15, 160, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 180, 350, 30);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(15, 240, 100, 40);
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMember();
            }
        });

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(125, 240, 100, 40);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        this.add(labelId);
        this.add(textFieldId);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(labelJenis);
        this.add(comboJenis);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        if (jenisMemberList == null || jenisMemberList.isEmpty()) {
            jenisMemberList = jenisMemberDao.findAll();
        }
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    private void updateMember() {
        String newName = textFieldNama.getText().trim();
        if (newName.isEmpty()) {
            showAlert("Nama tidak boleh kosong.");
        } else {
            member.setNama(newName);
            int rowsUpdated = memberDao.update(member);
            if (rowsUpdated > 0) {
                showAlert("Member berhasil diperbarui.");
                this.dispose();
            } else {
                showAlert("Gagal memperbarui data.");
            }
        }
    }

    private void deleteMember() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus member ini?",
                "Konfirmasi Penghapusan",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int rowsDeleted = memberDao.delete(member.getId());
            if (rowsDeleted > 0) {
                showAlert("Member berhasil dihapus.");
                this.dispose();
            } else {
                showAlert("Gagal menghapus data.");
            }
        }
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
