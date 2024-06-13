package panes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import logic.Rezervacija;

public class PieChartRoomServicePanel extends JPanel {

    public PieChartRoomServicePanel(List<Rezervacija> rezervacije) {
        // Create a new PieChart
        PieChart chart = new PieChartBuilder().width(500).height(300).title("Broj očišćenih soba po radniku").build();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDateDate = null;
        Date endDateDate = new Date(); // End date is today

        try {
            // Calculate start date of last year
            long oneYearMillis = 365L * 24 * 60 * 60 * 1000;
            startDateDate = new Date(endDateDate.getTime() - oneYearMillis);
        } catch (Exception ex) {
            ex.printStackTrace();
            return; // Exit if there's an error with date calculation
        }

        Map<String, Integer> brojOciscenihSobaPoRadniku = new HashMap<>();

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

            // Check if the reservation is within the last year and in a valid state
            if ((!rezervacija.stanje.equals("OTKAZANA") && !rezervacija.stanje.equals("ODBIJENO") && 
                 !rezervacija.stanje.equals("POTVRDJENO") && !rezervacija.stanje.equals("NA CEKANJU")) &&
                 datumPocetkaRezervacije.after(startDateDate) && datumZavrsetkaRezervacije.before(endDateDate)) {
                
                // Extract the worker's name who cleaned the room
                String[] stanjeParts = rezervacija.stanje.split("\\|");
                if (stanjeParts.length > 1) {
                    String workerName = stanjeParts[1];

                    // Update the HashMap with the number of rooms cleaned by the worker
                    brojOciscenihSobaPoRadniku.put(workerName, brojOciscenihSobaPoRadniku.getOrDefault(workerName, 0) + 1);
                }
            }
        }

        // Add data to the chart
        for (Map.Entry<String, Integer> entry : brojOciscenihSobaPoRadniku.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        // Create a XChartPanel to display the chart
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);

        // Add the chart panel to this JPanel
        add(chartPanel);
    }

    public static void main(String[] args) {
        // Example to test the panel
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PieChartRoomServicePanel(null));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
