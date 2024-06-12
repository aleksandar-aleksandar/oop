package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Soba;

public class AllRoomsPanel extends JPanel {
    public AllRoomsPanel(List<Soba> sobe){
        setLayout(null);

        String[] rezervacijeNaslovi2 = { "broj", "tip", "stanje" };
        DefaultTableModel modelRezervacija2 = new DefaultTableModel(rezervacijeNaslovi2, 0); // Create model with column

        for (Soba soba : sobe) {
            Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
            modelRezervacija2.addRow(rowData);
            // Add the row to the model
        }
        JTable tabelaSvihSobe = new JTable(modelRezervacija2);
        tabelaSvihSobe.setBounds(0, 0, 800, 200);
        tabelaSvihSobe.setEnabled(false);
        add(tabelaSvihSobe);

        JButton osveziSobe = new JButton("Osvezi");
        osveziSobe.setBounds(350, 300, 100, 23);
        add(osveziSobe);

        osveziSobe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija2.setRowCount(0);
                for (Soba soba : sobe) {
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    modelRezervacija2.addRow(rowData);
                    // Add the row to the model
                }
                DataManager.upisiSobe(sobe);
            }
        });
    }
}
