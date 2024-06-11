package panes;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Rezervacija;

public class AllReservationsPanel extends JPanel {
    public AllReservationsPanel(List<Rezervacija> rezervacije){
        setLayout(null);

        String[] rezervacijeNaslovi1 = { "broj", "tip", "stanje", "datum zavrsetka", "jij", "korae", "loool", "234" };

        DefaultTableModel modelRezervacija1 = new DefaultTableModel(rezervacijeNaslovi1, 0);

        for (Rezervacija rezervacija : rezervacije) {
            Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                    rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                    rezervacija.stanje, rezervacija.cena };
            modelRezervacija1.addRow(rowData);
            // Add the row to the model
        }

        JTable tabelaSvihRezervacija = new JTable(modelRezervacija1);
        tabelaSvihRezervacija.setBounds(0, 0, 800, 300);
        add(tabelaSvihRezervacija);

        JButton obrisiRezervaciju = new JButton("Obrisi");
        obrisiRezervaciju.setBounds(300, 350, 100, 23);
        add(obrisiRezervaciju);
        obrisiRezervaciju.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabelaSvihRezervacija.getSelectedRow();
                if (row >= 0) {
                    modelRezervacija1.removeRow(row);
                }

                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JButton osveziRezervacije = new JButton("Osvezi");
        osveziRezervacije.setBounds(200, 350, 100, 23);
        add(osveziRezervacije);

        osveziRezervacije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija1.setRowCount(0);
                for (Rezervacija rezervacija : rezervacije) {
                    Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                            rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                            rezervacija.stanje, rezervacija.cena };
                    modelRezervacija1.addRow(rowData);
                    // Add the row to the model
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JButton sacuvajStanjeRezervacija = new JButton("Sacuvaj");
        sacuvajStanjeRezervacija.setBounds(400, 350, 100, 23);
        add(sacuvajStanjeRezervacija);

        sacuvajStanjeRezervacija.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Rezervacija> updatedRezervacije = new ArrayList<>();
                for (int i = 0; i < modelRezervacija1.getRowCount(); i++) {
                    String id = (String) modelRezervacija1.getValueAt(i, 0);
                    String gost = (String) modelRezervacija1.getValueAt(i, 1);
                    String tipSobe = (String) modelRezervacija1.getValueAt(i, 2);
                    String datumPocetka = (String) modelRezervacija1.getValueAt(i, 3);
                    String datumZavrsetka = (String) modelRezervacija1.getValueAt(i, 4);
                    String dodatneUsluge = (String) modelRezervacija1.getValueAt(i, 5);
                    String stanje = (String) modelRezervacija1.getValueAt(i, 6);
                    String cena = (String) modelRezervacija1.getValueAt(i, 7);
                    updatedRezervacije
                            .add(new Rezervacija(id, gost, tipSobe, datumPocetka, datumZavrsetka, dodatneUsluge,
                                    stanje, cena));
                }
                DataManager.upisiRezervacije(updatedRezervacije);
            }
        });
    }
}
