package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Rezervacija;

public class EditReservationsUserPanel extends JPanel{
    public EditReservationsUserPanel(List<Rezervacija> rezervacije){
        setLayout(null);
        
        // Column headers for the table
        String[] columnHeaders = { "ID", "Tip sobe", "Pocetak", "Zavrsetak", "Dodatne usluge", "Stanje",  };

        // Create table model with column headers
        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

        // Populate the table model with reservation data
        for (Rezervacija rezervacija : rezervacije) {
            Object[] rowData = { rezervacija.id, rezervacija.tipSobe, rezervacija.datumPocetka,
                    rezervacija.datumZavrsetka, rezervacija.dodatneUsluge, rezervacija.stanje };
            model.addRow(rowData);
        }

        // Create the table with the model
        JTable table = new JTable(model);
        table.setEnabled(false);

        // Add scroll pane to the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 29, 759, 230);
        add(scrollPane);

        // Label for the title
        JLabel titleLabel = new JLabel("Rezervacije");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(282, 0, 214, 26);
        add(titleLabel);

        // Text field for entering reservation ID
        JTextField idTextField = new JTextField();
        idTextField.setBounds(361, 299, 89, 20);
        add(idTextField);
        idTextField.setColumns(10);

        // Label for the ID text field
        JLabel idLabel = new JLabel("Broj rezervacije koju želite da otkažete");
        idLabel.setBounds(308, 270, 202, 14);
        add(idLabel);

        // Button to cancel the reservation
        JButton cancelButton = new JButton("Otkaži");
        cancelButton.setBounds(361, 324, 89, 23);
        add(cancelButton);

        // Add action listener to the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered reservation ID
                String id = idTextField.getText();
                
                // Validate if the ID is not empty
                if (id.isEmpty()) {
                    // Display an error message if the ID is empty
                    JOptionPane.showMessageDialog(null, "Molimo unesite broj rezervacije.");
                    return;
                }

                // Search for the reservation with the entered ID and cancel it
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
