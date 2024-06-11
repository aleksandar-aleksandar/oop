package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Gost;

public class AllUsersPanel extends JPanel{
    public AllUsersPanel(List<Gost> gosti){
        setLayout(null);

        String[] gostiNaslovi = { "broj", "tip", "stanje", "datum zavrsetka", "jij", "", "", "" };

        DefaultTableModel modelGosti = new DefaultTableModel(gostiNaslovi, 0); // Create model with column
                                                                               // names

        for (Gost gost : gosti) {
            Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon, gost.adresa,
                    gost.korisnickoIme, gost.lozinka };
            modelGosti.addRow(rowData);
        }

        JTable tabelaSvihGostiju = new JTable(modelGosti);
        tabelaSvihGostiju.setBounds(0, 0, 800, 300);
        add(tabelaSvihGostiju);

        JButton osveziGoste = new JButton("Osvezi");
        osveziGoste.setBounds(350, 300, 100, 23);
        add(osveziGoste);

        osveziGoste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelGosti.setRowCount(0);
                for (Gost gost : gosti) {
                    Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon,
                            gost.adresa, gost.korisnickoIme, gost.lozinka };
                    modelGosti.addRow(rowData);
                }
                DataManager.upisiGoste(gosti);
            }
        });
    }
}
