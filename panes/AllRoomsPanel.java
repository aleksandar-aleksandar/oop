package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Soba;

public class AllRoomsPanel extends JPanel {
    public AllRoomsPanel(List<Soba> sobe){
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
        
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 780, 250);
        add(scrollPane);

        // Add a refresh button
        JButton refreshButton = new JButton("Osveži");
        refreshButton.setBounds(350, 320, 100, 23);
        add(refreshButton);

        // Add action listener to the refresh button
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear existing data from the table
                model.setRowCount(0);
                
                // Populate the table with the latest room data
                for (Soba soba : sobe) {
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    model.addRow(rowData);
                }
                
                // Update the room data in the database
                DataManager.upisiSobe(sobe);
            }
        });
    }
}
