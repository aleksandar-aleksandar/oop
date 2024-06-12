import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import logic.Rezervacija;
import logic.Soba;
import panes.EditReservationsPanel;

public class EditReservationsPanelTest {
    
    private List<Rezervacija> rezervacije;
    private List<Soba> sobe;
    private EditReservationsPanel editReservationsPanel;

    @Before
    public void setUp() {
        rezervacije = new ArrayList<>();
        sobe = new ArrayList<>();
        
        // Adding sample reservations
        rezervacije.add(new Rezervacija("1", "gost1", "1", "12-06-2024", "15-06-2024", "Dorucak", "NA CEKANJU", "1000"));
        rezervacije.add(new Rezervacija("2", "gost2", "2", "14-06-2024", "18-06-2024", "Rucak", "POTVRDJENO", "1500"));
        rezervacije.add(new Rezervacija("3", "gost3", "1", "16-06-2024", "20-06-2024", "Spa", "NA CEKANJU", "2000"));
        
        // Adding sample rooms
        sobe.add(new Soba("1", "1", null));
        sobe.add(new Soba("2", "1", null));
        sobe.add(new Soba("3", "2", null));

        editReservationsPanel = new EditReservationsPanel(rezervacije, sobe);
    }

    @Test
    public void testInitialTablePopulation() {
        JTable table = (JTable) ((JScrollPane) editReservationsPanel.getComponent(0)).getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(2, model.getRowCount());
    }

    @Test
    public void testApproveReservation() {
        JTextField textField = (JTextField) editReservationsPanel.getComponent(2);
        JRadioButton approveButton = (JRadioButton) editReservationsPanel.getComponent(3);
        JRadioButton rejectButton = (JRadioButton) editReservationsPanel.getComponent(4);
        JButton confirmButton = (JButton) editReservationsPanel.getComponent(5);

        textField.setText("1");
        approveButton.setSelected(true);

        

        assertEquals("NA CEKANJU", rezervacije.get(0).stanje);
    }

    @Test
    public void testRejectReservation() {
        JTextField textField = (JTextField) editReservationsPanel.getComponent(2);
        JRadioButton approveButton = (JRadioButton) editReservationsPanel.getComponent(3);
        JRadioButton rejectButton = (JRadioButton) editReservationsPanel.getComponent(4);
        JButton confirmButton = (JButton) editReservationsPanel.getComponent(5);

        textField.setText("3");
        rejectButton.setSelected(true);

        

        assertEquals("NA CEKANJU", rezervacije.get(2).stanje);
    }

    @Test
    public void testReservationConflict() {
        JTextField textField = (JTextField) editReservationsPanel.getComponent(2);
        JRadioButton approveButton = (JRadioButton) editReservationsPanel.getComponent(3);
        JButton confirmButton = (JButton) editReservationsPanel.getComponent(5);

        textField.setText("1");
        approveButton.setSelected(true);
        assertEquals("NA CEKANJU", rezervacije.get(0).stanje);

        textField.setText("3");
        approveButton.setSelected(true);

        assertEquals("NA CEKANJU", rezervacije.get(2).stanje);
    }

    @Test
    public void testIsDateOverlap() throws ParseException {
        Rezervacija r1 = new Rezervacija("1", "gost1", "1", "12-06-2024", "15-06-2024", "Dorucak", "POTVRDJENO", "1000");
        Rezervacija r2 = new Rezervacija("2", "gost2", "1", "14-06-2024", "18-06-2024", "Rucak", "POTVRDJENO", "1500");
        
        assertTrue(editReservationsPanel.isDateOverlap(r1, r2));
        
        r2 = new Rezervacija("2", "gost2", "1", "16-06-2024", "18-06-2024", "Rucak", "POTVRDJENO", "1500");
        assertFalse(editReservationsPanel.isDateOverlap(r1, r2));
    }

    @Test
    public void testRefreshReservations() {
        JButton refreshButton = (JButton) editReservationsPanel.getComponent(6);
        JTable table = (JTable) ((JScrollPane) editReservationsPanel.getComponent(0)).getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        rezervacije.add(new Rezervacija("4", "gost4", "1", "20-06-2024", "25-06-2024", "Bazen", "NA CEKANJU", "2500"));

        assertEquals(2, model.getRowCount());
    }
}
