package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Gost;

public class AllUsersPanel extends JPanel {
    public AllUsersPanel(List<Gost> gosti) {
        setLayout(null);

        // Define meaningful column headers
        String[] gostiNaslovi = { "Ime", "Prezime", "Pol", "Datum Rodjenja", "Broj Telefona", "Adresa", "Email", "Broj Pasosa" };

        // Create model with column names
        DefaultTableModel modelGosti = new DefaultTableModel(gostiNaslovi, 0);

        // Populate table with data
        for (Gost gost : gosti) {
            Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon,
                                 gost.adresa, gost.korisnickoIme, gost.lozinka };
            modelGosti.addRow(rowData);
        }

        // Create table and add it to a scroll pane
        JTable tabelaSvihGostiju = new JTable(modelGosti);
        JScrollPane scrollPane = new JScrollPane(tabelaSvihGostiju);
        scrollPane.setBounds(0, 0, 800, 300);
        add(scrollPane);

        // Refresh button to update table data
        JButton osveziGoste = new JButton("Osvezi");
        osveziGoste.setBounds(350, 320, 100, 23);
        add(osveziGoste);

        osveziGoste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelGosti.setRowCount(0);
                for (Gost gost : gosti) {
                    Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon,
                                         gost.adresa, gost.korisnickoIme, gost.lozinka};
                    modelGosti.addRow(rowData);
                }
                DataManager.upisiGoste(gosti);
            }
        });
    }
}
