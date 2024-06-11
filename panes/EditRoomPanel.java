package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Soba;

public class EditRoomPanel extends JPanel{
    public EditRoomPanel(List<Soba> sobe){
        setLayout(null);

        String[] nasloviSobe = { "broj", "tip", "stanje" };

        DefaultTableModel modelSobe = new DefaultTableModel(nasloviSobe, 0); // Create model with column names

        for (Soba soba : sobe) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
            modelSobe.addRow(rowData); // Add the row to the model
        }

        JTable tabelaSoba = new JTable(modelSobe);
        tabelaSoba.setBounds(100, 25, 600, 300);
        add(tabelaSoba);

        JButton sacuvajStanjeSoba = new JButton("Sacuvaj");
        sacuvajStanjeSoba.setBounds(710, 350, 80, 23);
        add(sacuvajStanjeSoba);
        sacuvajStanjeSoba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Soba> updatedSobaList = new ArrayList<>();
                for (int i = 0; i < modelSobe.getRowCount(); i++) {
                    String broj = (String) modelSobe.getValueAt(i, 0);
                    String tip = (String) modelSobe.getValueAt(i, 1);
                    String stanje = (String) modelSobe.getValueAt(i, 2);

                    Soba updatedSoba = new Soba(broj, tip, stanje);
                    updatedSobaList.add(updatedSoba);
                }
                DataManager.upisiSobe(updatedSobaList);
            }
        });

        JButton refreshSobe = new JButton("Osvezi");
        refreshSobe.setBounds(10, 350, 80, 23);
        add(refreshSobe);
        
        refreshSobe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelSobe.setRowCount(0);
                for (Soba soba : sobe) {
                    // Create a new row with data extracted from Zaposleni object
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    modelSobe.addRow(rowData); // Add the row to the model
                }
            }
        });

        JButton obrisiSobu = new JButton("Obrisi odabranu sobu");
        obrisiSobu.setBounds(100, 350, 600, 23);
        add(obrisiSobu);

        obrisiSobu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaSoba.getSelectedRow();
                if (row >= 0) {
                    modelSobe.removeRow(row);
                    sobe.remove(row);
                    DataManager.upisiSobe(sobe);
                }
            }
        });

    }
}
