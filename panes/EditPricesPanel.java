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
import logic.Cenovnik;
import logic.DataManager;

public class EditPricesPanel extends JPanel {
    public EditPricesPanel(List<Cenovnik> cene) {
        setLayout(null);

        String[] nasloviCene = { "Cena 1", "Cena 2", "Cena 3", "Cena 4", "Cena 5", "Cena 6", "Cena 7", "Cena 8", "Cena 9", "Cena 10", "Datum Pocetka", "Datum Zavrsetka" };

        DefaultTableModel modelCene = new DefaultTableModel(nasloviCene, 0);

        for (Cenovnik cena : cene) {
            Object[] rowData = { cena.cena1, cena.cena2, cena.cena3, cena.cena4, cena.cena5, cena.cena6, cena.cena7, cena.cena8, cena.cena9, cena.cena10, cena.datumPocetka, cena.datumZavrsetka };
            modelCene.addRow(rowData);
        }

        JTable tabelaCena = new JTable(modelCene);
        JScrollPane scrollPane = new JScrollPane(tabelaCena);
        scrollPane.setBounds(100, 25, 600, 300);
        add(scrollPane);

        JButton refreshCene = new JButton("Osvezi");
        refreshCene.setBounds(10, 350, 80, 23);
        add(refreshCene);
        refreshCene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelCene.setRowCount(0);
                for (Cenovnik cena : cene) {
                    Object[] rowData = { cena.cena1, cena.cena2, cena.cena3, cena.cena4, cena.cena5, cena.cena6, cena.cena7, cena.cena8, cena.cena9, cena.cena10, cena.datumPocetka, cena.datumZavrsetka };
                    modelCene.addRow(rowData);
                }
                DataManager.upisiCene(cene);
            }
        });

        JButton sacuvajStanjeCene = new JButton("Sacuvaj");
        sacuvajStanjeCene.setBounds(710, 350, 80, 23);
        add(sacuvajStanjeCene);
        sacuvajStanjeCene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cenovnik> updatedCene = new ArrayList<>();
                for (int i = 0; i < modelCene.getRowCount(); i++) {
                    String cena1 = (String) modelCene.getValueAt(i, 0);
                    String cena2 = (String) modelCene.getValueAt(i, 1);
                    String cena3 = (String) modelCene.getValueAt(i, 2);
                    String cena4 = (String) modelCene.getValueAt(i, 3);
                    String cena5 = (String) modelCene.getValueAt(i, 4);
                    String cena6 = (String) modelCene.getValueAt(i, 5);
                    String cena7 = (String) modelCene.getValueAt(i, 6);
                    String cena8 = (String) modelCene.getValueAt(i, 7);
                    String cena9 = (String) modelCene.getValueAt(i, 8);
                    String cena10 = (String) modelCene.getValueAt(i, 9);
                    String datumPocetka = (String) modelCene.getValueAt(i, 10);
                    String datumZavrsetka = (String) modelCene.getValueAt(i, 11);
                    updatedCene.add(new Cenovnik(cena1, cena2, cena3, cena4, cena5, cena6, cena7, cena8, cena9, cena10, datumPocetka, datumZavrsetka));
                }
                DataManager.upisiCene(updatedCene);
            }
        });

        JButton obrisiCenu = new JButton("Obrisi odabranu uslugu");
        obrisiCenu.setBounds(100, 350, 600, 23);
        add(obrisiCenu);

        obrisiCenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaCena.getSelectedRow();
                if (row >= 0) {
                    modelCene.removeRow(row);
                    cene.remove(row);
                    DataManager.upisiCene(cene);
                }
            }
        });
    }
}
