package panes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Soba;

public class EditRoomPanel extends JPanel{
    public EditRoomPanel(List<Soba> sobe){
        setLayout(null);

        // Column headers for the table
        String[] columnHeaders = { "Broj", "Tip", "Stanje" };

        // Create table model with column headers
        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

        // Populate the table model with room data
        for (Soba soba : sobe) {
            Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
            model.addRow(rowData);
        }

        // Create the table with the model
        JTable table = new JTable(model);

        // Add scroll pane to the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 25, 600, 300);
        add(scrollPane);

        // Button to save room state changes
        JButton saveButton = new JButton("Sačuvaj");
        saveButton.setBounds(710, 350, 80, 23);
        add(saveButton);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Soba> updatedRoomList = new ArrayList<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String broj = (String) model.getValueAt(i, 0);
                    String tip = (String) model.getValueAt(i, 1);
                    String stanje = (String) model.getValueAt(i, 2);

                    Soba updatedRoom = new Soba(broj, tip, stanje);
                    updatedRoomList.add(updatedRoom);
                }
                DataManager.upisiSobe(updatedRoomList);
            }
        });

        // Button to refresh room data
        JButton refreshButton = new JButton("Osveži");
        refreshButton.setBounds(10, 350, 80, 23);
        add(refreshButton);

        // Add action listener to the refresh button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Soba soba : sobe) {
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    model.addRow(rowData);
                }
            }
        });

        // Button to delete selected room
        JButton deleteButton = new JButton("Obriši odabranu sobu");
        deleteButton.setBounds(100, 350, 600, 23);
        add(deleteButton);

        // Add action listener to the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                    sobe.remove(row);
                    DataManager.upisiSobe(sobe);
                }
            }
        });
    }
}
