package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Zaposleni;

public class EditEmployeePanel extends JPanel{
    public EditEmployeePanel(List<Zaposleni> zaposleni){
        setLayout(null);

        String[] naslovi = { "Ime", "Prezime", "pol", "datum", "broj", "adresa", "korisnicko ime", "godine staze",
                "lozinka", "nivo strucne spreme" };

        DefaultTableModel model = new DefaultTableModel(naslovi, 0); // Create model with column names

        for (Zaposleni zap : zaposleni) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { zap.ime, zap.prezime, zap.pol,
                    zap.datumRodjenja, zap.brojTelefon, zap.adresa,
                    zap.korisnickoIme, zap.godineStaza, zap.lozinka,
                    zap.nivoStrucneSpreme };
            model.addRow(rowData); // Add the row to the model
        }
        JTable tabelaZaposlenih = new JTable(model);
        JScrollBar scrollBar = new JScrollBar();
        tabelaZaposlenih.add(scrollBar);
        tabelaZaposlenih.repaint();
        tabelaZaposlenih.setBounds(100, 25, 600, 300);
        add(tabelaZaposlenih);

        JButton sacuvajStanjeButton = new JButton("Sacuvaj");
        sacuvajStanjeButton.setBounds(710, 350, 80, 23);
        add(sacuvajStanjeButton);
        sacuvajStanjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Zaposleni> updatedZaposleniList = new ArrayList<>();

                for (int i = 0; i < model.getRowCount(); i++) {
                    String ime = (String) model.getValueAt(i, 0);
                    String prezime = (String) model.getValueAt(i, 1);
                    String pol = (String) model.getValueAt(i, 2);
                    String datum = (String) model.getValueAt(i, 3);
                    String broj = (String) model.getValueAt(i, 4);
                    String adresa = (String) model.getValueAt(i, 5);
                    String korisnickoIme = (String) model.getValueAt(i, 6);
                    String godine = (String) model.getValueAt(i, 7);
                    String nivo = (String) model.getValueAt(i, 9);
                    String lozinka = (String) model.getValueAt(i, 8);

                    Zaposleni zap = zaposleni.get(i);

                    zap.ime = ime;
                    zap.prezime = prezime;
                    zap.pol = pol;
                    zap.datumRodjenja = datum;
                    zap.brojTelefon = broj;
                    zap.adresa = adresa;
                    zap.korisnickoIme = korisnickoIme;
                    zap.godineStaza = godine;
                    zap.lozinka = lozinka;
                    zap.nivoStrucneSpreme = nivo;

                    updatedZaposleniList.add(zap);
                }

                DataManager.upisiZaposlene(updatedZaposleniList);
            }

        });

        JButton obrisiZaposlenog = new JButton("Obrisi odabranog zaposlenog");

        JButton refreshButton = new JButton("Osvezi");
        refreshButton.setBounds(10, 350, 80, 23);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Zaposleni zap : zaposleni) {
                    // Create a new row with data extracted from Zaposleni object
                    Object[] rowData = { zap.ime, zap.prezime, zap.pol,
                            zap.datumRodjenja, zap.brojTelefon, zap.adresa,
                            zap.korisnickoIme, zap.godineStaza, zap.lozinka,
                            zap.nivoStrucneSpreme };
                    model.addRow(rowData); // Add the row to the model
                }
            }
        });

        obrisiZaposlenog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaZaposlenih.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                    zaposleni.remove(row);
                    DataManager.upisiZaposlene(zaposleni);
                }
            }
        });
        obrisiZaposlenog.setBounds(100, 350, 600, 23);
        add(obrisiZaposlenog);

    }
}
