import org.junit.Test;
import static org.junit.Assert.*;

import panes.AddUserPanel;

public class AddUserPanelTest {

    @Test
    public void testIsValidEmail() {
        AddUserPanel panel = new AddUserPanel(null);

        assertTrue(panel.isValidEmail("test@example.com"));
        assertFalse(panel.isValidEmail("test@example"));
        assertFalse(panel.isValidEmail("test@.com"));
        assertFalse(panel.isValidEmail("test@com"));
        assertFalse(panel.isValidEmail("testexample.com"));
    }

    @Test
    public void testIsNumeric() {
        AddUserPanel panel = new AddUserPanel(null);

        assertTrue(panel.isNumeric("12345"));
        assertFalse(panel.isNumeric("123a5"));
        assertFalse(panel.isNumeric("abcde"));
    }

    @Test
    public void testValidateFields() {
        AddUserPanel panel = new AddUserPanel(null);

        // Simulate setting text fields with valid input
        panel.textField.setText("John");
        panel.textField_1.setText("Doe");
        panel.textField_2.setText("Male");
        panel.textField_3.setText("01-01-1990");
        panel.textField_4.setText("1234567890");
        panel.textField_5.setText("123 Main St");
        panel.textField_6.setText("john.doe@example.com");
        panel.textField_7.setText("A1234567");

        // Simulate setting text fields with invalid input (empty email)
        panel.textField_6.setText("");
        assertFalse(panel.validateFields());

        // Simulate setting text fields with invalid input (invalid email)
        panel.textField_6.setText("john.doe@example");
        assertFalse(panel.validateFields());

        // Simulate setting text fields with invalid input (non-numeric phone number)
        panel.textField_4.setText("1234abc");
        assertFalse(panel.validateFields());

        // Simulate setting text fields with invalid input (non-numeric passport number)
        panel.textField_4.setText("1234567890");
        panel.textField_7.setText("A12345bc");
        assertFalse(panel.validateFields());
    }
}
