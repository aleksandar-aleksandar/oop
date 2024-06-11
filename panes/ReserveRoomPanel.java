package panes;

import java.awt.event.ActionEvent;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.DataManager;
import logic.Gost;
import logic.Rezervacija;
import logic.Cenovnik;
import logic.TipSobe;

public class ReserveRoomPanel extends JPanel{

    private JTextField textField;
    private JTextField textField_1;
    public ReserveRoomPanel(List<TipSobe> tipoviSobe, List<Cenovnik> cene, List<Rezervacija> rezervacije, Gost ulogovanGost){
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Tip Sobe");
        lblNewLabel.setBounds(58, 61, 73, 14);
        add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(154, 57, 82, 22);
        add(comboBox);

        for (TipSobe tip : tipoviSobe) {
            comboBox.addItem(tip.naziv);
        }

        JLabel lblNewLabel_1 = new JLabel("Datum pocetka");
        lblNewLabel_1.setBounds(58, 99, 72, 14);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Datum zavrsetka");
        lblNewLabel_1_1.setBounds(58, 135, 82, 14);
        add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setBounds(154, 96, 86, 20);
        add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(154, 132, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1 = new JButton("Rezervisi");

        JCheckBox chckbxNewCheckBox = new JCheckBox("Dorucak");
        chckbxNewCheckBox.setBounds(319, 95, 97, 23);
        add(chckbxNewCheckBox);

        JCheckBox chckbxRucak = new JCheckBox("Rucak");
        chckbxRucak.setBounds(319, 121, 97, 23);
        add(chckbxRucak);

        JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("Vecera");
        chckbxNewCheckBox_1_1.setBounds(319, 147, 97, 23);
        add(chckbxNewCheckBox_1_1);

        JCheckBox chckbxNewCheckBox_1_1_1 = new JCheckBox("Spa");
        chckbxNewCheckBox_1_1_1.setBounds(319, 175, 97, 23);
        add(chckbxNewCheckBox_1_1_1);

        JCheckBox chckbxNewCheckBox_1_1_1_1 = new JCheckBox("Bazen");
        chckbxNewCheckBox_1_1_1_1.setBounds(319, 201, 97, 23);
        add(chckbxNewCheckBox_1_1_1_1);

        JLabel lblNewLabel_2 = new JLabel("Dodatne usluge");
        lblNewLabel_2.setBounds(319, 61, 97, 14);
        add(lblNewLabel_2);

        btnNewButton_1.setBounds(58, 201, 178, 22);
        add(btnNewButton_1);

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
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
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
