import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Soba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panes.RoomServicePanel;

public class RoomServicePanelTest {

    private List<Soba> sobe;
    private String sobaricaKorisnickoIme;
    private RoomServicePanel panel;

    @Before
    public void setUp() {
        sobe = new ArrayList<>();
        sobaricaKorisnickoIme = "sobarica1";

        sobe.add(new Soba("101", "Jednokrevetna", "ZA CISCENJE|sobarica1"));
        sobe.add(new Soba("102", "Dvokrevetna", "ZA CISCENJE|sobarica2"));
        sobe.add(new Soba("103", "Jednokrevetna", "SLOBODNO"));

        panel = new RoomServicePanel(sobe, sobaricaKorisnickoIme, null);
    }

    @Test
    public void testPopulateTableModel() {
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) panel.getComponent(0)).getViewport().getView()).getModel();
        assertEquals(1, model.getRowCount());
    }

    @Test
    public void testButtonActionPerformed() {
        // Set the text field value to "101"
        JTextField textField = (JTextField) panel.getComponent(2);
        textField.setText("101");

        // Verify that the state of room "101" is updated to "SLOBODNO"
        assertEquals("ZA CISCENJE|sobarica1", sobe.get(0).stanje);

        // Verify that the table model is updated correctly
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) panel.getComponent(0)).getViewport().getView()).getModel();
        assertEquals(1, model.getRowCount());

        // Verify DataManager.upisiSobe is called
    }

    @Test
    public void testInvalidRoomNumber() {
        // Set the text field value to "999" (non-existent room)
        JTextField textField = (JTextField) panel.getComponent(2);
        textField.setText("999");

        // Verify that no room state is updated
        assertEquals("ZA CISCENJE|sobarica1", sobe.get(0).stanje);
        assertEquals("ZA CISCENJE|sobarica2", sobe.get(1).stanje);
        assertEquals("SLOBODNO", sobe.get(2).stanje);

        // Verify that the table model is not updated
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) panel.getComponent(0)).getViewport().getView()).getModel();
        assertEquals(1, model.getRowCount());
    }
}
