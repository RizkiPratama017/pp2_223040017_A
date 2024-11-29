package view.member;

import dao.MemberDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.JenisMember;
import model.Member;

public class MemberButtonSimpanActionListener implements ActionListener {

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

        Member member = new Member();
        member.setId(java.util.UUID.randomUUID().toString());
        member.setNama(nama);
        member.setJenisMember(jenisMember);
        member.setJenisMemberId(jenisMember.getId());

        System.out.println("Adding member: " + member.getNama());

        memberFrame.addMember(member);
        memberDao.insert(member);

        System.out.println("Member added to the database.");

        memberFrame.enableSaveButton(button);
    }
}
