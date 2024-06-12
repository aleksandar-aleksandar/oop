package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logic.DataManager;
import logic.Gost;

public class AddUserPanel extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;

    public AddUserPanel(List<Gost> gosti) {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Ime");
        lblNewLabel.setBounds(32, 70, 46, 14);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prezime");
        lblNewLabel_1.setBounds(32, 114, 46, 14);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Pol");
        lblNewLabel_2.setBounds(32, 161, 46, 14);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Datum rodjenja");
        lblNewLabel_3.setBounds(32, 210, 98, 14);
        add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Broj telefona");
        lblNewLabel_4.setBounds(32, 258, 86, 14);
        add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Adresa");
        lblNewLabel_5.setBounds(32, 304, 46, 14);
        add(lblNewLabel_5);

        textField = new JTextField();
        textField.setBounds(155, 67, 86, 20);
        add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(155, 111, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(155, 158, 86, 20);
        add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(155, 207, 86, 20);
        add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(155, 255, 86, 20);
        add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(155, 301, 86, 20);
        add(textField_5);
        textField_5.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Email");
        lblNewLabel_6.setBounds(317, 70, 98, 14);
        add(lblNewLabel_6);

        textField_6 = new JTextField();
        textField_6.setBounds(438, 67, 86, 20);
        add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Broj pasosa");
        lblNewLabel_7.setBounds(317, 114, 86, 14);
        add(lblNewLabel_7);

        textField_7 = new JTextField();
        textField_7.setBounds(438, 111, 86, 20);
        add(textField_7);
        textField_7.setColumns(10);

        JButton btnNewButton = new JButton("Dodaj");
        btnNewButton.setBounds(317, 275, 201, 43);
        add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateFields()) {
                    JOptionPane.showMessageDialog(null, "Molimo popunite sva polja pravilno.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String ime = textField.getText();
                String prezime = textField_1.getText();
                String pol = textField_2.getText();
                String datumRodjenja = textField_3.getText();
                String brojTelefona = textField_4.getText();
                String adresa = textField_5.getText();
                String email = textField_6.getText();
                String brojPasosa = textField_7.getText();

                gosti.add(new Gost(ime, prezime, pol, datumRodjenja, brojTelefona, adresa, email, brojPasosa));

                DataManager.upisiGoste(gosti);
                JOptionPane.showMessageDialog(null, "Gost uspešno dodat!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private boolean validateFields() {
        if (textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty() || textField_2.getText().trim().isEmpty() ||
            textField_3.getText().trim().isEmpty() || textField_4.getText().trim().isEmpty() || textField_5.getText().trim().isEmpty() ||
            textField_6.getText().trim().isEmpty() || textField_7.getText().trim().isEmpty()) {
            return false;
        }

        if (!isValidEmail(textField_6.getText())) {
            return false;
        }

        if (!isNumeric(textField_4.getText()) || !isNumeric(textField_7.getText())) {
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
