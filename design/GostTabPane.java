package design;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.Cenovnik;
import logic.DataManager;
import logic.Gost;
import logic.Rezervacija;
import logic.Soba;
import logic.TipSobe;
import logic.Zaposleni;
import logic.Usluga;

import java.awt.event.ActionEvent;

public class GostTabPane extends JFrame {
    private JTextField textField;
    private JTextField textField_1;

    GostTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, Gost ulogovanGost, List<Usluga> usluge) {

        setTitle("Gost");
        setVisible(true);
        setBounds(100, 100, 800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 784, 411);
        getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Home", null, panel, null);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Odjavi se");
        btnNewButton.setBounds(289, 180, 220, 35);
        panel.add(btnNewButton);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Rezervisi sobu", null, panel_1, null);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Tip Sobe");
        lblNewLabel.setBounds(58, 61, 73, 14);
        panel_1.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(154, 57, 82, 22);
        panel_1.add(comboBox);

        for (TipSobe tip : tipoviSobe) {
            comboBox.addItem(tip.naziv);
        }

        JLabel lblNewLabel_1 = new JLabel("Datum pocetka");
        lblNewLabel_1.setBounds(58, 99, 72, 14);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Datum zavrsetka");
        lblNewLabel_1_1.setBounds(58, 135, 82, 14);
        panel_1.add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setBounds(154, 96, 86, 20);
        panel_1.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(154, 132, 86, 20);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1 = new JButton("Rezervisi");

        JCheckBox chckbxNewCheckBox = new JCheckBox("Dorucak");
        chckbxNewCheckBox.setBounds(319, 95, 97, 23);
        panel_1.add(chckbxNewCheckBox);

        JCheckBox chckbxRucak = new JCheckBox("Rucak");
        chckbxRucak.setBounds(319, 121, 97, 23);
        panel_1.add(chckbxRucak);

        JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("Vecera");
        chckbxNewCheckBox_1_1.setBounds(319, 147, 97, 23);
        panel_1.add(chckbxNewCheckBox_1_1);

        JCheckBox chckbxNewCheckBox_1_1_1 = new JCheckBox("Spa");
        chckbxNewCheckBox_1_1_1.setBounds(319, 175, 97, 23);
        panel_1.add(chckbxNewCheckBox_1_1_1);

        JCheckBox chckbxNewCheckBox_1_1_1_1 = new JCheckBox("Bazen");
        chckbxNewCheckBox_1_1_1_1.setBounds(319, 201, 97, 23);
        panel_1.add(chckbxNewCheckBox_1_1_1_1);

        JLabel lblNewLabel_2 = new JLabel("Dodatne usluge");
        lblNewLabel_2.setBounds(319, 61, 97, 14);
        panel_1.add(lblNewLabel_2);

        btnNewButton_1.setBounds(58, 201, 178, 22);
        panel_1.add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxId = 0;
                for (Rezervacija rezervacija : rezervacije) {
                    if (Integer.parseInt(rezervacija.id) > maxId) {
                        maxId = Integer.parseInt(rezervacija.id);
                    }
                }
                String noviId = Integer.toString(maxId + 1);
                String id = noviId;
                String gost = ulogovanGost.korisnickoIme;
                String datumPocetka = textField.getText();
                String datumZavrsetka = textField_1.getText();
                String dodatneUsluge = "";
                if (chckbxNewCheckBox.isSelected()) {
                    dodatneUsluge = dodatneUsluge + "Dorucak ";
                }
                if (chckbxRucak.isSelected()) {
                    dodatneUsluge = dodatneUsluge + "Rucak ";
                }
                if (chckbxNewCheckBox_1_1.isSelected()) {
                    dodatneUsluge = dodatneUsluge + "Vecera ";
                }
                if (chckbxNewCheckBox_1_1_1.isSelected()) {
                    dodatneUsluge = dodatneUsluge + "Spa ";
                }
                if (chckbxNewCheckBox_1_1_1_1.isSelected()) {
                    dodatneUsluge = dodatneUsluge + "Bazen ";
                }
                String tipSobe = (String) comboBox.getSelectedItem();

                int cena = izracunajCenu(cene, datumPocetka, datumZavrsetka, tipSobe, dodatneUsluge);

                rezervacije.add(
                        new Rezervacija(id, gost, tipSobe, datumPocetka, datumZavrsetka, dodatneUsluge, "NA CEKANJU",
                                Integer.toString(cena)));
                DataManager.upisiRezervacije(rezervacije);
            }

        });

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        tabbedPane.addTab("Moje rezervacije", null, panel_2, null);

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
        panel_2.add(table);

        JLabel lblNewLabel_3 = new JLabel("Rezervacije");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(282, 0, 214, 26);
        panel_2.add(lblNewLabel_3);

        JTextField textField_2 = new JTextField();
        textField_2.setBounds(361, 299, 89, 20);
        panel_2.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Broj rezervacije koju zelite da otkazete");
        lblNewLabel_4.setBounds(308, 270, 202, 14);
        panel_2.add(lblNewLabel_4);

        JButton btnNewButton_2 = new JButton("Otkazi");
        btnNewButton_2.setBounds(361, 324, 89, 23);
        panel_2.add(btnNewButton_2);

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

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        tabbedPane.addTab("Cenovnici", null, panel_3, null);

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
        panel_3.add(table1);

    }

    public int izracunajCenu(List<Cenovnik> cene, String datumPocetka, String datumZavrsetka, String tipSobe,
            String dodatneUsluge) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        int cena = 0;

        List<String> usluge = new ArrayList<String>();
        usluge.add(tipSobe);
        for (String usluga : dodatneUsluge.split(" ")) {
            usluge.add(usluga.trim());
        }

        for (String usluga : usluge) {
            System.out.println(usluga);
        }

        @SuppressWarnings("deprecation")
        Date datumPocetkaDate = new Date(1900, 1, 1);
        @SuppressWarnings("deprecation")
        Date datumZavrsetkaDate = new Date(1900, 1, 1);

        try {
            datumPocetkaDate = dateFormat.parse(datumPocetka);
            datumZavrsetkaDate = dateFormat.parse(datumZavrsetka);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Date> sviDatumiOstanka = getDatesBetween(datumPocetkaDate, datumZavrsetkaDate);

        for (Date datum : sviDatumiOstanka) {
            for (Cenovnik cenovnik : cene) {
                try {
                    if (dateFormat.parse(cenovnik.datumPocetka).before(datum)
                            && dateFormat.parse(cenovnik.datumZavrsetka).after(datum)) {
                        for (String usluga : usluge) {
                            if (usluga.equals("1")) {
                                cena = cena + Integer.parseInt(cenovnik.cena1);
                            } else if (usluga.equals("2")) {
                                cena = cena + Integer.parseInt(cenovnik.cena2);
                            } else if (usluga.equals("1+1")) {
                                cena = cena + Integer.parseInt(cenovnik.cena3);
                            } else if (usluga.equals("2+1")) {
                                cena = cena + Integer.parseInt(cenovnik.cena4);
                            } else if (usluga.equals("2+2")) {
                                cena = cena + Integer.parseInt(cenovnik.cena5);
                            } else if (usluga.equals("Dorucak")) {
                                cena = cena + Integer.parseInt(cenovnik.cena6);
                            } else if (usluga.equals("Rucak")) {
                                cena = cena + Integer.parseInt(cenovnik.cena7);
                            } else if (usluga.equals("Vecera")) {
                                cena = cena + Integer.parseInt(cenovnik.cena8);
                            } else if (usluga.equals("Bazen")) {
                                cena = cena + Integer.parseInt(cenovnik.cena9);
                            } else if (usluga.equals("Spa")) {
                                cena = cena + Integer.parseInt(cenovnik.cena10);
                            }

                            System.out.println(cena);
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        return cena;
    }

    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            Date currentDate = calendar.getTime();
            datesInRange.add(currentDate);
            calendar.add(Calendar.DATE, 1);
        }

        return datesInRange;
    }

}
