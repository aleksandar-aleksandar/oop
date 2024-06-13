package panes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import logic.Rezervacija;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PieChartRoomTypesPanel extends JPanel {

    public PieChartRoomTypesPanel(List<Rezervacija> rezervacije) {
        // Create a new PieChart
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Distribution of Reservation Types").build();

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

        Map<String, Integer> reservationTypesCount = new HashMap<>();

        for (Rezervacija rezervacija : rezervacije) {
            Date datumPocetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = sdf.parse(rezervacija.datumPocetka);
            } catch (ParseException e) {
                e.printStackTrace();
                continue; // Skip this reservation if the dates are not valid
            }

            // Check if the reservation is within the last year
            if (datumPocetkaRezervacije.after(startDateDate) && datumPocetkaRezervacije.before(endDateDate)) {
                String reservationType = getReservationType(rezervacija);

                // Update the HashMap with the count of each reservation type
                reservationTypesCount.put(reservationType, reservationTypesCount.getOrDefault(reservationType, 0) + 1);
            }
        }

        // Add data to the chart
        for (Map.Entry<String, Integer> entry : reservationTypesCount.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        // Create a XChartPanel to display the chart
        XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);

        // Add the chart panel to this JPanel
        add(chartPanel);
    }

    /**
     * Determine the type of reservation ("POTVRDJENO" for all states not "NA CEKANJU", "ODBIJENO", or "OTKAZANO").
     */
    private String getReservationType(Rezervacija rezervacija) {
        switch (rezervacija.stanje) {
            case "NA CEKANJU":
            case "ODBIJENO":
            case "OTKAZANA":
                return rezervacija.stanje;
            default:
                return "POTVRDJENO";
        }
    }

    public static void main(String[] args) {
        // Example to test the panel
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pie Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PieChartRoomTypesPanel(null)); // Pass your list of Rezervacija
            frame.pack();
            frame.setVisible(true);
        });
    }
}
