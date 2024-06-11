package panes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Rezervacija;

public class EditReservationsPanel extends JPanel   {
    public EditReservationsPanel(List<Rezervacija> rezervacije){
        setLayout(null);
        String[] rezervacijeNaslovi = { "broj", "tip", "stanje", "datum zavrsetka", "jij" };

        DefaultTableModel modelRezervacija = new DefaultTableModel(rezervacijeNaslovi, 0); // Create model with column
                                                                                           // names

        for (Rezervacija rezervacija : rezervacije) {
            if (rezervacija.stanje.equals("NA CEKANJU")) {
                Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe, rezervacija.datumPocetka,
                        rezervacija.datumZavrsetka, rezervacija.dodatneUsluge, rezervacija.stanje, };
                modelRezervacija.addRow(rowData);
            }
            // Add the row to the model
        }

        JTable table = new JTable(modelRezervacija);
        table.setEnabled(false);
        table.setBounds(186, 44, 386, 180);
        add(table);

        JLabel lblNewLabel2 = new JLabel("Upisite broj rezervacije");
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

        // Add both radio buttons to the group
        radioButtonGroup.add(rdbtnNewRadioButton);
        radioButtonGroup.add(rdbtnNewRadioButton_1);

        JButton btnNewButton3 = new JButton("Submit");
        btnNewButton3.setBounds(328, 312, 80, 23);
        add(btnNewButton3);

        btnNewButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.id.equals(textField2.getText())) {
                        if (rdbtnNewRadioButton.isSelected()) {
                            rezervacija.stanje = "POTVRDJENO";
                        } else if (rdbtnNewRadioButton_1.isSelected()) {
                            rezervacija.stanje = "ODBIJENO";
                        }
                        break;
                    }
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JButton osveziRezervacije11 = new JButton("Osvezi");
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
}
