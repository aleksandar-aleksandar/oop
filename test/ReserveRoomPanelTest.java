import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import logic.Cenovnik;
import logic.DataManager;
import logic.Gost;
import logic.Rezervacija;
import logic.TipSobe;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.mockito.Mockito;

import panes.ReserveRoomPanel;

public class ReserveRoomPanelTest {

    private List<TipSobe> tipoviSobe;
    private List<Cenovnik> cene;
    private List<Rezervacija> rezervacije;
    private Gost ulogovanGost;
    private ReserveRoomPanel panel;

    @Before
    public void setUp() {
        tipoviSobe = new ArrayList<>();
        cene = new ArrayList<>();
        rezervacije = new ArrayList<>();
        ulogovanGost = new Gost("ime", "prezime", "muski", "10-10-2000", "32423423443", null, null, null);

        // Add mock data
        tipoviSobe.add(new TipSobe("Jednokrevetna"));
        tipoviSobe.add(new TipSobe("Dvokrevetna"));

        cene.add(new Cenovnik("1000", "2000", "1500", "2500", "3000", "200", "300", "400", "500", "600","01-01-2023", "31-12-2023"));

        panel = new ReserveRoomPanel(tipoviSobe, cene, rezervacije, ulogovanGost);
    }

    @Test
    public void testReserveRoomPanelInitialization() {
        assertEquals(13, panel.getComponentCount());

        @SuppressWarnings("unchecked")
        JComboBox<String> comboBox = (JComboBox<String>) panel.getComponent(1);
        assertEquals(2, comboBox.getItemCount());
        assertEquals("Jednokrevetna", comboBox.getItemAt(0));
        assertEquals("Dvokrevetna", comboBox.getItemAt(1));
    }

    @Test
    public void testValidateFieldsEmptyDates() {
        JTextField textField = (JTextField) panel.getComponent(4);
        JTextField textField_1 = (JTextField) panel.getComponent(5);

        textField.setText("");
        textField_1.setText("");

        assertEquals(false, panel.validateFields());
    }

    @Test
    public void testValidateFieldsInvalidDateFormat() {
        JTextField textField = (JTextField) panel.getComponent(4);
        JTextField textField_1 = (JTextField) panel.getComponent(5);

        textField.setText("invalid date");
        textField_1.setText("invalid date");

        assertEquals(false, panel.validateFields());
    }

    @Test
    public void testValidateFieldsEndDateBeforeStartDate() throws ParseException {
        JTextField textField = (JTextField) panel.getComponent(4);
        JTextField textField_1 = (JTextField) panel.getComponent(5);

        textField.setText("01-06-2023");
        textField_1.setText("01-05-2023");

        assertEquals(false, panel.validateFields());
    }

    @Test
    public void testValidateFieldsValidDates() throws ParseException {
        JTextField textField = (JTextField) panel.getComponent(4);
        JTextField textField_1 = (JTextField) panel.getComponent(5);

        textField.setText("01-05-2023");
        textField_1.setText("01-06-2023");

        assertEquals(true, panel.validateFields());
    }

    @Test
    public void testIzracunajCenu() {
        String datumPocetka = "01-05-2023";
        String datumZavrsetka = "03-05-2023";
        String tipSobe = "1";
        String dodatneUsluge = "Dorucak Rucak Vecera";

        int expectedCena = 1000 * 3 + 200 * 3 + 300 * 3 + 400 * 3; // 3 days, including additional services
        int actualCena = panel.izracunajCenu(cene, datumPocetka, datumZavrsetka, tipSobe, dodatneUsluge);

        assertEquals(expectedCena, actualCena);
    }

    @Test
    public void testActionPerformedValidReservation() {
        JTextField textField = (JTextField) panel.getComponent(4);
        JTextField textField_1 = (JTextField) panel.getComponent(5);
        JComboBox<String> comboBox = (JComboBox<String>) panel.getComponent(1);
        JCheckBox chckbxDorucak = (JCheckBox) panel.getComponent(7);

        textField.setText("01-05-2023");
        textField_1.setText("03-05-2023");
        comboBox.setSelectedItem("Jednokrevetna");
        chckbxDorucak.setSelected(true);
        assertEquals(0, rezervacije.size());
    }
}

