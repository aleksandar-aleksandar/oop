package panes;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.Cenovnik;

public class PricesInfoPanel extends JPanel{
    public PricesInfoPanel(List<Cenovnik> cene){
        setLayout(null);

        String[] nasloviCenovnici = { "id", "tip sobe", "pocetak", "zavrsetak", "dodatne usluge", "stanje", "id",
                "tip sobe", "pocetak", "zavrsetak", "dodatne usluge", "stanje" };

        DefaultTableModel modelCenovnici = new DefaultTableModel(nasloviCenovnici, 0); // Create model with column
                                                                                       // names

        for (Cenovnik cenovnik : cene) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { cenovnik.cena1, cenovnik.cena2, cenovnik.cena3, cenovnik.cena4, cenovnik.cena5,
                    cenovnik.cena6, cenovnik.cena7, cenovnik.cena8, cenovnik.cena9, cenovnik.cena10,
                    cenovnik.datumPocetka, cenovnik.datumZavrsetka, cenovnik.datumZavrsetka };
            modelCenovnici.addRow(rowData); // Add the row to the model
        }

        JTable table1 = new JTable(modelCenovnici);
        table1.setBounds(10, 29, 759, 230);
        table1.setEnabled(false);
        add(table1);
    }
}
