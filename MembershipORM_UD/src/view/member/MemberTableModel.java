package view.member;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Member;

public class MemberTableModel extends AbstractTableModel {

    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                // Add null check for getJenisMember()
                if (rowItem.getJenisMember() != null) {
                    value = rowItem.getJenisMember().getNama();
                } else {
                    value = "No Jenis Member";  // or any default value
                }
                break;
        }

        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeMember(Member member) {
        int index = data.indexOf(member);
        if (index != -1) {
            data.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public void setData(List<Member> data) {
        this.data = data;
        fireTableDataChanged();
    }
}
