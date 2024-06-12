import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import panes.AddEmployeePanel;
import logic.Zaposleni;

public class AddEmployeePanelTest {

    @Test
    public void testValidateFields() {
        List<Zaposleni> zaposleni = new ArrayList<>();
        AddEmployeePanel panel = new AddEmployeePanel(zaposleni);

        // Simulate setting text fields with valid input
        panel.textField.setText("John");
        panel.textField_1.setText("Doe");
        panel.textField_2.setText("Male");
        panel.textField_3.setText("01-01-1990");
        panel.textField_4.setText("1234567890");
        panel.textField_5.setText("123 Main St");
        panel.textField_6.setText("john.doe");
        panel.textField_7.setText("password");
        panel.textField_8.setText("5"); // Valid number for years of experience
        panel.textField_9.setText("3"); // Valid number for level of education

        assertTrue(panel.validateFields());

        // Simulate setting text fields with invalid input (empty field)
        panel.textField_9.setText("");
        assertFalse(panel.validateFields());

        // Simulate setting text fields with invalid input (non-numeric experience)
        panel.textField_8.setText("five");
        assertFalse(panel.validateFields());

        // Simulate setting text fields with invalid input (non-numeric education level)
        panel.textField_8.setText("5");
        panel.textField_9.setText("three");
        assertFalse(panel.validateFields());
    }

    @Test
    public void testIzracunajPlatu() {
        List<Zaposleni> zaposleni = new ArrayList<>();
        AddEmployeePanel panel = new AddEmployeePanel(zaposleni);

        // Test with valid input
        int plata = panel.izracunajPlatu("5", "3");
        assertEquals(72500, plata);

        // Test with zero experience and education level
        plata = panel.izracunajPlatu("0", "0");
        assertEquals(30000, plata);

        // Test with maximum input values
        plata = panel.izracunajPlatu("10", "5");
        assertEquals(105000, plata);
    }
}
