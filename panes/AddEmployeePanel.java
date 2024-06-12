package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logic.Administrator;
import logic.DataManager;
import logic.Recepcionar;
import logic.Sobarica;
import logic.Zaposleni;

public class AddEmployeePanel extends JPanel {

    public JTextField textField;
    public JTextField textField_1;
    public JTextField textField_2;
    public JTextField textField_3;
    public JTextField textField_4;
    public JTextField textField_5;
    public JTextField textField_6;
    public JTextField textField_7;
    public JTextField textField_8;
    public JTextField textField_9;

    public AddEmployeePanel(List<Zaposleni> zaposleni) {
        setLayout(null);
        
        JLabel lblNewLabel4 = new JLabel("Pozicija");
        lblNewLabel4.setBounds(32, 40, 46, 14);
        add(lblNewLabel4);

        JComboBox<String> tipoviZaposlenih = new JComboBox<>();
        tipoviZaposlenih.setBounds(155, 40, 86, 20);
        add(tipoviZaposlenih);

        tipoviZaposlenih.addItem("administrator");
        tipoviZaposlenih.addItem("recepcionar");
        tipoviZaposlenih.addItem("sobarica");

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

        JLabel lblNewLabel_6 = new JLabel("Korisnicko ime");
        lblNewLabel_6.setBounds(317, 70, 98, 14);
        add(lblNewLabel_6);

        textField_6 = new JTextField();
        textField_6.setBounds(438, 67, 86, 20);
        add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Lozinka");
        lblNewLabel_7.setBounds(317, 114, 46, 14);
        add(lblNewLabel_7);

        textField_7 = new JTextField();
        textField_7.setBounds(438, 111, 86, 20);
        add(textField_7);
        textField_7.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel(" Godine iskustva");
        lblNewLabel_8.setBounds(317, 161, 86, 14);
        add(lblNewLabel_8);

        textField_8 = new JTextField();
        textField_8.setBounds(438, 158, 86, 20);
        add(textField_8);
        textField_8.setColumns(10);

        JLabel lblNewLabel_8_1 = new JLabel("Nivo strucne spreme");
        lblNewLabel_8_1.setBounds(317, 210, 86, 14);
        add(lblNewLabel_8_1);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(438, 207, 86, 20);
        add(textField_9);

        JLabel lblPlata = new JLabel("Plata: ");
        lblPlata.setBounds(317, 320, 86, 14);
        add(lblPlata);

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
                String datum = textField_3.getText();
                String broj = textField_4.getText();
                String adresa = textField_5.getText();
                String korisnickoIme = textField_6.getText();
                String godine = textField_8.getText();
                String lozinka = textField_7.getText();
                String nivo = textField_9.getText();
                String poz = tipoviZaposlenih.getSelectedItem().toString();
                String plata = Integer.toString(izracunajPlatu(godine, nivo));

                if (poz.equals("administrator")) {
                    zaposleni.add(new Administrator(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka, godine, nivo, plata));
                } else if (poz.equals("recepcionar")) {
                    zaposleni.add(new Recepcionar(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka, godine, nivo,plata));
                } else if (poz.equals("sobarica")) {
                    zaposleni.add(new Sobarica(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka, godine, nivo,plata));
                }

                DataManager.upisiZaposlene(zaposleni);
                lblPlata.setText("Plata: " + plata);
                JOptionPane.showMessageDialog(null, "Zaposleni uspešno dodat!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public boolean validateFields() {
        if (textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty() || textField_2.getText().trim().isEmpty() ||
            textField_3.getText().trim().isEmpty() || textField_4.getText().trim().isEmpty() || textField_5.getText().trim().isEmpty() ||
            textField_6.getText().trim().isEmpty() || textField_7.getText().trim().isEmpty() || textField_8.getText().trim().isEmpty() ||
            textField_9.getText().trim().isEmpty()) {
            return false;
        }
        
        try {
            Integer.parseInt(textField_8.getText());
            Integer.parseInt(textField_9.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

    public int izracunajPlatu(String godine, String nivo) {
        int plata = 30000;
        plata = plata + 10000 * Integer.parseInt(nivo);
        plata = plata + 2500 * Integer.parseInt(godine);
        return plata;
    }
}
