package panes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Rezervacija;
import logic.Soba;

public class EditReservationsPanel extends JPanel {
    public EditReservationsPanel(List<Rezervacija> rezervacije, List<Soba> sobe){
        setLayout(null);
        
        // Headers for the table
        String[] rezervacijeNaslovi = { "Broj", "Gost", "Tip sobe", "Datum početka", "Datum završetka", "Dodatne usluge", "Stanje" };

        DefaultTableModel modelRezervacija = new DefaultTableModel(rezervacijeNaslovi, 0); // Create model with column names

        // Populate the table model
        for (Rezervacija rezervacija : rezervacije) {
            if (rezervacija.stanje.equals("NA CEKANJU")) {
                Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                        rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                        rezervacija.stanje };
                modelRezervacija.addRow(rowData);
            }
        }

        // Create the table with the model
        JTable table = new JTable(modelRezervacija);
        JScrollPane scrollPane = new JScrollPane(table); // Add scroll bar to the table
        scrollPane.setBounds(186, 44, 386, 180); // Set bounds for the scroll pane
        add(scrollPane);

        // Other UI components...
        JLabel lblNewLabel2 = new JLabel("Upišite broj rezervacije");
        lblNewLabel2.setBounds(183, 264, 135, 14);
        add(lblNewLabel2);

        JTextField textField2 = new JTextField();
        textField2.setBounds(312, 261, 86, 20);
        add(textField2);
        textField2.setColumns(10);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Potvrdi");
        rdbtnNewRadioButton.setBounds(420, 260, 79, 20);
        add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Odbij");
        rdbtnNewRadioButton_1.setBounds(509, 260, 59, 23);
        add(rdbtnNewRadioButton_1);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(rdbtnNewRadioButton);
        radioButtonGroup.add(rdbtnNewRadioButton_1);

        JButton btnNewButton3 = new JButton("Potvrdi");
        btnNewButton3.setBounds(328, 312, 80, 23);
        add(btnNewButton3);

        btnNewButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate user input
                if(textField2.getText().isEmpty() || (!rdbtnNewRadioButton.isSelected() && !rdbtnNewRadioButton_1.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Molimo upišite broj rezervacije i izaberite opciju!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                String reservationId = textField2.getText();
                boolean isConfirm = rdbtnNewRadioButton.isSelected();
                
               int brojMogucihSoba = 0;
               int brojZauzetihSoba = 0;

                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.id.equals(reservationId)) {
                        for (Soba soba: sobe){
                            if(soba.tip.equals(rezervacija.tipSobe)){
                                brojMogucihSoba = brojMogucihSoba + 1;
                            }
                        }
                        if (isConfirm) {
                            // Check for clashes with other reservations
                            for (Rezervacija existingReservation : rezervacije) {
                                if (!existingReservation.id.equals(reservationId)) { // Skip the current reservation
                                    if (isDateOverlap(rezervacija, existingReservation) && existingReservation.stanje.equals("POTVRDJENO")) {
                                        brojZauzetihSoba = brojZauzetihSoba + 1;
                                    }
                                }
                            }
                            if(brojMogucihSoba > brojZauzetihSoba){
                                rezervacija.stanje = "POTVRDJENO";
                            }else{  
                                rezervacija.stanje = "ODBIJENO";
                                JOptionPane.showMessageDialog(null, "Konflikt sa postojećom rezervacijom!", "Greška", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            rezervacija.stanje = "ODBIJENO";
                        }
                        break;
                    }
                }
                DataManager.upisiRezervacije(rezervacije);

                System.out.println(brojMogucihSoba);
                System.out.println(brojZauzetihSoba);
            }
        });
        
       
        JButton osveziRezervacije11 = new JButton("Osveži");
        osveziRezervacije11.setBounds(200, 312, 80, 23);
        add(osveziRezervacije11);

        osveziRezervacije11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija.setRowCount(0);
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.stanje.equals("NA CEKANJU")) {
                        Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                                rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                                rezervacija.stanje };
                        modelRezervacija.addRow(rowData);
                    }
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });
    }

    public boolean isDateOverlap(Rezervacija newReservation, Rezervacija existingReservation) {
    // Convert date strings to Date objects
    Date newStartDate = null;
    Date newEndDate = null;
    Date existingStartDate = null;
    Date existingEndDate = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    try {
        newStartDate = dateFormat.parse(newReservation.datumPocetka);
        newEndDate = dateFormat.parse(newReservation.datumZavrsetka);
        existingStartDate = dateFormat.parse(existingReservation.datumPocetka);
        existingEndDate = dateFormat.parse(existingReservation.datumZavrsetka);
    } catch (ParseException e) {
        e.printStackTrace();
    }

    // Check if the date ranges overlap
    return newStartDate != null && newEndDate != null && existingStartDate != null && existingEndDate != null &&
           newStartDate.before(existingEndDate) && existingStartDate.before(newEndDate);
}
}
