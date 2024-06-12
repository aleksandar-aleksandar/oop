package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.nio.sctp.SendFailedNotification;

import logic.Cenovnik;
import logic.Gost;
import logic.Rezervacija;
import logic.Soba;
import logic.TipSobe;
import logic.Usluga;
import logic.Zaposleni;

public class ReportPanel extends JPanel {
    private JComboBox<String> izvestaji;
    private JTextField textField10;
    private JTextField textField20;
    private JTextArea izvestaj;

    public ReportPanel(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge){

        setLayout(null);

        

        JLabel lblNewLabel10 = new JLabel("Datum pocetka");
        lblNewLabel10.setBounds(65, 37, 136, 27);
        add(lblNewLabel10);

        textField10 = new JTextField();
        textField10.setBounds(210, 40, 86, 20);
        add(textField10);
        textField10.setColumns(10);

        JLabel lblNewLabel20 = new JLabel("Datum Zavrsetka");
        lblNewLabel20.setBounds(65, 77, 136, 27);
        add(lblNewLabel20);

        textField20 = new JTextField();
        textField20.setBounds(210, 80, 86, 20);
        add(textField20);
        textField20.setColumns(10);

        JLabel lblNewLabel30 = new JLabel("Tip izvestaja");
        lblNewLabel30.setBounds(65, 117, 136, 27);
        add(lblNewLabel30);

        izvestaji = new JComboBox<>();
        izvestaji.setBounds(210, 120, 166, 20);
        izvestaji.addItem("Prihodi i rashodi");
        izvestaji.addItem("Broj potvrđenih rezervacija");
        izvestaji.addItem("Broj otkazanih rezervacija");
        izvestaji.addItem("Broj odbijenih rezervacija");
        izvestaji.addItem("Broj očišćenih soba");
        izvestaji.addItem("Broj noćenja po tipu sobe");
        izvestaji.addItem("Ukupan prihod svake od soba");
        add(izvestaji);

        JButton nadjiIzvestaj = new JButton("Pretraga");
        nadjiIzvestaj.setBounds(65, 157, 236, 27);
        add(nadjiIzvestaj);

        izvestaj = new JTextArea();
        izvestaj.setBounds(380, 0, 400, 400);
        izvestaj.setLineWrap(true);
        izvestaj.setEditable(false); // Use setEditable(false) instead of disable()
        add(izvestaj);

        nadjiIzvestaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startDate = textField10.getText();
                String endDate = textField20.getText();
                String selectedIzvestaj = (String) izvestaji.getSelectedItem();
                if (selectedIzvestaj != null) {
                    switch (selectedIzvestaj) {
                        case "Prihodi i rashodi":
                            generatePrihodiIRashodiReport(startDate, endDate, rezervacije, zaposleni);
                            break;
                        case "Broj potvrđenih rezervacija":
                            generateBrojPotvrdjenihRezervacijaReport(startDate, endDate, rezervacije);
                            break;
                        case "Broj otkazanih rezervacija":
                            generateBrojOtkazanihRezervacijaReport(startDate, endDate, rezervacije);
                            break;
                        case "Broj odbijenih rezervacija":
                            generateBrojOdbijenihRezervacijaReport(startDate, endDate, rezervacije);
                            break;
                        case "Broj očišćenih soba":
                            generateBrojOciscenihSobaReport(startDate, endDate);
                            break;
                        case "Broj noćenja po tipu sobe":
                            generateBrojNocenjaPoTipuSobeReport(startDate, endDate, rezervacije, tipoviSobe);
                            break;
                        case "Ukupan prihod svake od soba":
                            generateUkupanPrihodSvakeOdSobaReport(startDate, endDate);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    // Methods to generate each type of report
    private void generatePrihodiIRashodiReport(String startDate, String endDate, List<Rezervacija> rezervacije, List<Zaposleni> zaposleni) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int prihod = 0;
        int rashod = 0;
        Date startDateDate = null;
        Date endDateDate = null;
        try {
            startDateDate = sdf.parse(startDate);
            endDateDate= sdf.parse(endDate);
        } catch (ParseException ex) {
        }
        
        for(Rezervacija rezervacija: rezervacije){
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           
            if((!rezervacija.stanje.equals("ODBIJENO") && !rezervacija.stanje.equals("NA CEKANJU")) && datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)){
                prihod = prihod + Integer.parseInt(rezervacija.cena);
            }
        }

        long difference = endDateDate.getTime() - startDateDate.getTime();
        long daysBetween = difference / (1000 * 60 * 60 * 24);
        int monthsBetween = Math.round(daysBetween / 60);

        

        for(Zaposleni radnik : zaposleni){
            rashod = rashod + 124000;
        }
        String report = "Prihod: " + prihod + " Rashod: " + rashod;
        System.out.println(prihod);
        displayReport(report);
    }

    private void generateBrojPotvrdjenihRezervacijaReport(String startDate, String endDate, List<Rezervacija> rezervacije) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = null;
        Date endDateDate = null;
        int brojRez = 0;
        try {
            startDateDate = sdf.parse(startDate);
            endDateDate= sdf.parse(endDate);
        } catch (ParseException ex) {
        }
        
        for(Rezervacija rezervacija: rezervacije){
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           
            if((rezervacija.stanje.equals("POTVRDJENO")) && datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)){
                brojRez = brojRez + 1;
            }
        }
        String report = "Broj rezervacija: " + brojRez;
        displayReport(report);
    }

    private void generateBrojOtkazanihRezervacijaReport(String startDate, String endDate, List<Rezervacija> rezervacije) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = null;
        Date endDateDate = null;
        int brojRez = 0;
        try {
            startDateDate = sdf.parse(startDate);
            endDateDate= sdf.parse(endDate);
        } catch (ParseException ex) {
        }
        
        for(Rezervacija rezervacija: rezervacije){
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           
            if((rezervacija.stanje.equals("OTKAZANA")) && datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)){
                brojRez = brojRez + 1;
            }
        }
        String report = "Broj rezervacija: " + brojRez;
        displayReport(report);
    }

    private void generateBrojOdbijenihRezervacijaReport(String startDate, String endDate, List<Rezervacija> rezervacije) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = null;
        Date endDateDate = null;
        int brojRez = 0;
        try {
            startDateDate = sdf.parse(startDate);
            endDateDate= sdf.parse(endDate);
        } catch (ParseException ex) {
        }
        
        for(Rezervacija rezervacija: rezervacije){
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           
            if((rezervacija.stanje.equals("ODBIJENO")) && datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)){
                brojRez = brojRez + 1;
            }
        }
        String report = "Broj rezervacija: " + brojRez;
        displayReport(report);
    }

    private void generateBrojOciscenihSobaReport(String startDate, String endDate) {
        // Implement logic to generate Broj očišćenih soba report
        // Use startDate and endDate as needed
        String report = "Implement logic for Broj očišćenih soba report here.";
        displayReport(report);
    }

   private void generateBrojNocenjaPoTipuSobeReport(String startDate, String endDate, List<Rezervacija> rezervacije, List<TipSobe> tipoviSobe) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = null;
        Date endDateDate = null;

        try {
            startDateDate = sdf.parse(startDate);
            endDateDate = sdf.parse(endDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return; // Exit if the dates are not valid
        }

        Map<String, Integer> brojNocenjaPoTipuSobe = new HashMap<>();

        for (Rezervacija rezervacija : rezervacije) {
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;

            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
                continue; // Skip this reservation if the dates are not valid
            }

            if (!rezervacija.stanje.equals("ODBIJENO") && !rezervacija.stanje.equals("NA CEKANJU") &&
                    datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)) {

                // Calculate the number of nights for this reservation
                long difference = datumZavrsetkaRezervacije.getTime() - datumPocetkaRezervacije.getTime();
                int brojNocenja = Math.round(difference / (1000 * 60 * 60 * 24));

                // Update the HashMap with the number of nights for the corresponding room type
                String tipSobe = rezervacija.tipSobe;
                brojNocenjaPoTipuSobe.put(tipSobe, brojNocenjaPoTipuSobe.getOrDefault(tipSobe, 0) + brojNocenja);
            }
        }

        // Generate the report string
        StringBuilder report = new StringBuilder();
        report.append("Broj noćenja po tipu sobe:\n");
        for (Map.Entry<String, Integer> entry : brojNocenjaPoTipuSobe.entrySet()) {
            report.append("Tip sobe: ").append(entry.getKey())
                  .append(", Broj noćenja: ").append(entry.getValue()).append("\n");
        }

        // Display the report
        displayReport(report.toString());
    }


    private void generatePrihodPoTipuSobeReport(String startDate, String endDate, List<Rezervacija> rezervacije) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateDate = null;
        Date endDateDate = null;

        try {
            startDateDate = sdf.parse(startDate);
            endDateDate = sdf.parse(endDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return; // Exit if the dates are not valid
        }

        Map<String, Integer> prihodPoTipuSobe = new HashMap<>();

        for (Rezervacija rezervacija : rezervacije) {
            Date datumPocetkaRezervacije = null;
            Date datumZavrsetkaRezervacije = null;

            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
                datumZavrsetkaRezervacije = sdf.parse(rezervacija.datumZavrsetka);
            } catch (ParseException e) {
                e.printStackTrace();
                continue; // Skip this reservation if the dates are not valid
            }

            if (!rezervacija.stanje.equals("ODBIJENO") && !rezervacija.stanje.equals("NA CEKANJU") &&
                    datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)) {

                // Calculate the money earned for this reservation
                int prihod = Integer.parseInt(rezervacija.cena);

                // Update the HashMap with the money earned for the corresponding room type
                String tipSobe = rezervacija.tipSobe;
                prihodPoTipuSobe.put(tipSobe, prihodPoTipuSobe.getOrDefault(tipSobe, 0) + prihod);
            }
        }

        // Generate the report string
        StringBuilder report = new StringBuilder();
        report.append("Prihod po tipu sobe:\n");
        for (Map.Entry<String, Integer> entry : prihodPoTipuSobe.entrySet()) {
            report.append("Tip sobe: ").append(entry.getKey())
                  .append(", Prihod: ").append(entry.getValue()).append("\n");
        }

        // Display the report
        displayReport(report.toString());
    }

    private void displayReport(String report) {
        izvestaj.setText(report);
    }
}
