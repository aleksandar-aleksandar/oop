package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Rezervacija;

public class EditReservationsUserPanel extends JPanel{
    public EditReservationsUserPanel(List<Rezervacija> rezervacije){
        setLayout(null);

        String[] nasloviRezervacije = { "id", "tip sobe", "pocetak", "zavrsetak", "dodatne usluge", "stanje" };

        DefaultTableModel modelRezervacije = new DefaultTableModel(nasloviRezervacije, 0); // Create model with column
                                                                                           // names

        for (Rezervacija rezervacija : rezervacije) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { rezervacija.id, rezervacija.tipSobe, rezervacija.datumPocetka,
                    rezervacija.datumZavrsetka, rezervacija.dodatneUsluge, rezervacija.stanje };
            modelRezervacije.addRow(rowData); // Add the row to the model
        }

        JTable table = new JTable(modelRezervacije);
        table.setBounds(10, 29, 759, 230);
        table.setEnabled(false);
        add(table);

        JLabel lblNewLabel_3 = new JLabel("Rezervacije");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(282, 0, 214, 26);
        add(lblNewLabel_3);

        JTextField textField_2 = new JTextField();
        textField_2.setBounds(361, 299, 89, 20);
        add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Broj rezervacije koju zelite da otkazete");
        lblNewLabel_4.setBounds(308, 270, 202, 14);
        add(lblNewLabel_4);

        JButton btnNewButton_2 = new JButton("Otkazi");
        btnNewButton_2.setBounds(361, 324, 89, 23);
        add(btnNewButton_2);

        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField_2.getText();
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.id.equals(id)) {
                        rezervacija.stanje = "OTKAZANA";
                        DataManager.upisiRezervacije(rezervacije);
                        break;
                    }
                }
            }
        });
    }
}
