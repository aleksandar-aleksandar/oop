package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Rezervacija;

public class AllReservationsPanel extends JPanel {
    public AllReservationsPanel(List<Rezervacija> rezervacije) {
        setLayout(null);

        String[] rezervacijeNaslovi1 = { "ID", "Gost", "Tip Sobe", "Datum Pocetka", "Datum Zavrsetka", "Dodatne Usluge", "Stanje", "Cena" };

        DefaultTableModel modelRezervacija1 = new DefaultTableModel(rezervacijeNaslovi1, 0);

        for (Rezervacija rezervacija : rezervacije) {
            Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                    rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                    rezervacija.stanje, rezervacija.cena };
            modelRezervacija1.addRow(rowData);
        }

        JTable tabelaSvihRezervacija = new JTable(modelRezervacija1);
        JScrollPane scrollPane = new JScrollPane(tabelaSvihRezervacija);
        scrollPane.setBounds(0, 0, 800, 300);
        add(scrollPane);

        JButton obrisiRezervaciju = new JButton("Obrisi");
        obrisiRezervaciju.setBounds(300, 350, 100, 23);
        add(obrisiRezervaciju);
        obrisiRezervaciju.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabelaSvihRezervacija.getSelectedRow();
                if (row >= 0) {
                    modelRezervacija1.removeRow(row);
                    rezervacije.remove(row);
                    DataManager.upisiRezervacije(rezervacije);
                } else {
                    JOptionPane.showMessageDialog(null, "Odaberite red za brisanje.");
                }
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
                boolean valid = true;

                for (int i = 0; i < modelRezervacija1.getRowCount(); i++) {
                    String id = (String) modelRezervacija1.getValueAt(i, 0);
                    String gost = (String) modelRezervacija1.getValueAt(i, 1);
                    String tipSobe = (String) modelRezervacija1.getValueAt(i, 2);
                    String datumPocetka = (String) modelRezervacija1.getValueAt(i, 3);
                    String datumZavrsetka = (String) modelRezervacija1.getValueAt(i, 4);
                    String dodatneUsluge = (String) modelRezervacija1.getValueAt(i, 5);
                    String stanje = (String) modelRezervacija1.getValueAt(i, 6);
                    String cena = (String) modelRezervacija1.getValueAt(i, 7);

                    // Validate fields
                    if (id == null || id.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "ID ne može biti prazan.");
                        break;
                    }
                    if (gost == null || gost.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Gost ne može biti prazan.");
                        break;
                    }
                    if (tipSobe == null || tipSobe.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Tip sobe ne može biti prazan.");
                        break;
                    }
                    if (datumPocetka == null || datumPocetka.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Datum početka ne može biti prazan.");
                        break;
                    }
                    if (datumZavrsetka == null || datumZavrsetka.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Datum završetka ne može biti prazan.");
                        break;
                    }
                    if (stanje == null || stanje.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Stanje ne može biti prazno.");
                        break;
                    }
                    if (cena == null || cena.isEmpty()) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Cena ne može biti prazna.");
                        break;
                    }
                    try {
                        Double.parseDouble(cena);
                    } catch (NumberFormatException ex) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Cena mora biti broj.");
                        break;
                    }

                    if (valid) {
                        updatedRezervacije.add(new Rezervacija(id, gost, tipSobe, datumPocetka, datumZavrsetka,
                                dodatneUsluge, stanje, cena));
                    }
                }

                if (valid) {
                    DataManager.upisiRezervacije(updatedRezervacije);
                    JOptionPane.showMessageDialog(null, "Rezervacije su uspešno sačuvane.");
                }
            }
        });
    }
}
