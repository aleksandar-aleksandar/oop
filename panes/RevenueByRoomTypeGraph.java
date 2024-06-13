package panes;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.markers.SeriesMarkers;

import logic.Rezervacija;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RevenueByRoomTypeGraph extends JPanel {

    public RevenueByRoomTypeGraph(List<Rezervacija> rezervacije) {
        // Create a new XYChart (scatter plot)
        XYChart chart = new XYChartBuilder().width(500).height(350).title("Revenue by Room Type and Month").xAxisTitle("Month").yAxisTitle("Revenue").build();

        // Map to store revenue by room type and month
        Map<String, Map<Integer, Double>> revenueByRoomTypeAndMonth = new HashMap<>();

        // Iterate through reservations to calculate revenue
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (Rezervacija rezervacija : rezervacije) {
            Date datumPocetkaRezervacije = null;
            try {
                datumPocetkaRezervacije = new SimpleDateFormat("dd-MM-yyyy").parse(rezervacija.datumPocetka);
            } catch (ParseException e) {
                e.printStackTrace();
                continue; // Skip this reservation if the date is not valid
            }

            // Extract month and year
            int month = Integer.parseInt(monthFormat.format(datumPocetkaRezervacije));
            int year = Integer.parseInt(yearFormat.format(datumPocetkaRezervacije));

            // Calculate revenue for this reservation
            double revenue = Double.parseDouble(rezervacija.cena);

            // Get or create map for the current room type
            if (!revenueByRoomTypeAndMonth.containsKey(rezervacija.tipSobe)) {
                revenueByRoomTypeAndMonth.put(rezervacija.tipSobe, new HashMap<>());
            }
            Map<Integer, Double> revenueByMonth = revenueByRoomTypeAndMonth.get(rezervacija.tipSobe);

            // Update revenue for the current month
            double currentRevenue = revenueByMonth.getOrDefault(month, 0.0);
            revenueByMonth.put(month, currentRevenue + revenue);
        }

        // Add series to the chart
        for (Map.Entry<String, Map<Integer, Double>> entry : revenueByRoomTypeAndMonth.entrySet()) {
            String roomType = entry.getKey();
            Map<Integer, Double> revenueByMonth = entry.getValue();

            if (revenueByMonth.isEmpty()) {
                continue; // Skip this room type if there are no revenues
            }

            List<Integer> months = new ArrayList<>(revenueByMonth.keySet());
            List<Double> revenues = new ArrayList<>(revenueByMonth.values());
            chart.addSeries(roomType, months, revenues);
        }

        // Create a SwingWrapper to display the chart
        XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
        add(chartPanel);
    }

    public static void main(String[] args) {
        // Example to test the panel
        // Replace null with your actual list of reservations
        List<Rezervacija> rezervacije = new ArrayList<>(); // Replace with your actual list
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Revenue by Room Type and Month");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new RevenueByRoomTypeGraph(rezervacije));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
