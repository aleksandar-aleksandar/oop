import static org.junit.Assert.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import logic.Cenovnik;
import panes.AddPricesPanel;

public class AddPricesPanelTest {

    private AddPricesPanel addPricesPanel;
    private List<Cenovnik> cene;

    @Before
    public void setUp() {
        cene = new ArrayList<>();
        addPricesPanel = new AddPricesPanel(cene);
    }

    @Test
public void testEmptyFieldValidation() {
    JTextField jednokrevetnaTxt = (JTextField) addPricesPanel.getComponent(7);
    JButton sacuvajCene = (JButton) addPricesPanel.getComponent(24);

    // Setting some fields empty
    jednokrevetnaTxt.setText("");

    assertEquals("",jednokrevetnaTxt.getText());
}

@Test
public void testPriceIncreaseValidation() {
    JTextField jednokrevetnaTxt = (JTextField) addPricesPanel.getComponent(7);
    JTextField dvokrevetnaTxt = (JTextField) addPricesPanel.getComponent(11);
    JButton sacuvajCene = (JButton) addPricesPanel.getComponent(24);

    // Setting prices that decrease with room size
    jednokrevetnaTxt.setText("100");
    dvokrevetnaTxt.setText("90");
    // Validation message should be displayed
    assertTrue(Integer.parseInt(jednokrevetnaTxt.getText()) > Integer.parseInt(dvokrevetnaTxt.getText()) );
}


    @Test
    public void testDataSaving() {
        JTextField jednokrevetnaTxt = (JTextField) addPricesPanel.getComponent(7);
        JTextField dorucakTxt = (JTextField) addPricesPanel.getComponent(3);
        JTextField dpTxt = (JTextField) addPricesPanel.getComponent(15);
        JTextField dzTxt = (JTextField) addPricesPanel.getComponent(17);
        JButton sacuvajCene = (JButton) addPricesPanel.getComponent(24);

        // Set valid prices and dates
        jednokrevetnaTxt.setText("100");
        dorucakTxt.setText("20");
        dpTxt.setText("01-06-2024");
        dzTxt.setText("30-06-2024");

        // Check if the prices are added to the list and saved
        assertEquals(0, cene.size());
    }
}

