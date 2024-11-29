package view.jenismember;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel {

    private String[] columnNames = {"Nama"};
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
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
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
        }

        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(JenisMember value) {
        int index = data.indexOf(value);
        if (index != -1) {
            data.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public void update(JenisMember value, int index) {
        data.set(index, value);
        fireTableRowsUpdated(index, index);
    }

    public void setData(List<JenisMember> data) {
        this.data = data;
        fireTableDataChanged();
    }
}
