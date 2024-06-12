package panes;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.Cenovnik;

public class PricesInfoPanel extends JPanel {
    public PricesInfoPanel(List<Cenovnik> cene) {
        setLayout(null);

        String[] columnHeaders = { "Cena 1", "Cena 2", "Cena 3", "Cena 4", "Cena 5", "Cena 6", "Cena 7", "Cena 8", "Cena 9", "Cena 10", "Datum početka", "Datum završetka" };

        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

        for (Cenovnik cenovnik : cene) {
            Object[] rowData = { cenovnik.cena1, cenovnik.cena2, cenovnik.cena3, cenovnik.cena4, cenovnik.cena5, cenovnik.cena6, cenovnik.cena7, cenovnik.cena8, cenovnik.cena9, cenovnik.cena10, cenovnik.datumPocetka, cenovnik.datumZavrsetka };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 29, 759, 230);
        add(scrollPane);
    }
}
