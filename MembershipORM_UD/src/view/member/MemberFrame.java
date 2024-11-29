package view.member;

import dao.JenisMemberDao;
import dao.MemberDao;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import model.JenisMember;
import model.Member;
import view.memberdetail.MemberDetailFrame;

public class MemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama: ");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member: ");
        labelJenis.setBounds(15, 100, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 210, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Member selectedMember = memberList.get(row);
                        MemberDetailFrame detailFrame = new MemberDetailFrame(selectedMember, memberDao, jenisMemberDao);
                        detailFrame.populateComboJenis();
                        detailFrame.setVisible(true);
                    }
                }
            }
        });

        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);
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

    public String getName() {
        String nama = textFieldNama.getText().trim();
        if (nama.isEmpty()) {
            showAlert("Nama tidak boleh kosong atau hanya terdiri dari spasi.");
            return null;
        }
        return nama;
    }

    public JenisMember getJenisMember() {
        if (comboJenis.getSelectedIndex() == -1) {
            showAlert("Pilih jenis member terlebih dahulu.");
            return null;
        }
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        memberList.add(member);
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void disableSaveButton(JButton button) {
        button.setEnabled(false);
    }

    public void enableSaveButton(JButton button) {
        button.setEnabled(true);
    }

    public static class MemberButtonSimpanActionListener implements ActionListener {

        private MemberFrame memberFrame;
        private MemberDao memberDao;

        public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
            this.memberFrame = memberFrame;
            this.memberDao = memberDao;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            memberFrame.disableSaveButton(button);

            String nama = memberFrame.getName();
            if (nama == null || nama.trim().isEmpty()) {
                memberFrame.showAlert("Nama tidak boleh kosong atau hanya terdiri dari spasi.");
                memberFrame.enableSaveButton(button);
                return;
            }

            JenisMember jenisMember = memberFrame.getJenisMember();
            if (jenisMember == null) {
                memberFrame.showAlert("Pilih jenis member terlebih dahulu.");
                memberFrame.enableSaveButton(button);
                return;
            }

            boolean isDuplicate = false;
            for (Member existingMember : memberFrame.memberList) {
                if (existingMember.getNama().equalsIgnoreCase(nama)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                memberFrame.showAlert("Member dengan nama ini sudah ada di tampilan.");
                memberFrame.enableSaveButton(button);
                return;
            }

            Member member = new Member();
            member.setId(java.util.UUID.randomUUID().toString());
            member.setNama(nama);
            member.setJenisMember(jenisMember);
            member.setJenisMemberId(jenisMember.getId());

            memberFrame.addMember(member);
            memberDao.insert(member);

            memberFrame.enableSaveButton(button);
        }

    }
}
