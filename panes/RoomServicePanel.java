package panes;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logic.DataManager;
import logic.Rezervacija;
import logic.Soba;

public class RoomServicePanel extends JPanel {

    public DefaultTableModel modelSobe;
    public JTextField textField;
    public List<Soba> sobe;
    public String sobaricaKorisnickoIme;

    public RoomServicePanel(List<Soba> sobe, String sobaricaKorisnickoIme, List<Rezervacija> rezervacije) {
        this.sobe = sobe;
        this.sobaricaKorisnickoIme = sobaricaKorisnickoIme;
        initializeComponents();
    }

    public void initializeComponents() {
        setLayout(null);
        initializeTable();
        initializeInputField();
        initializeButton();
    }

    public void initializeTable() {
        String[] nasloviSoba = {"Broj sobe", "Tip", "Stanje"};
        modelSobe = new DefaultTableModel(nasloviSoba, 0);

        populateTableModel();

        JTable table = new JTable(modelSobe);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(132, 41, 526, 224);
        add(scrollPane);
    }

    public void initializeInputField() {
        JLabel lblNewLabel = new JLabel("Unesite broj sobe za raspremanje");
        lblNewLabel.setBounds(178, 294, 173, 14);
        add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(387, 291, 86, 20);
        add(textField);
        textField.setColumns(10);
    }

    public void initializeButton() {
        JButton btnNewButton = new JButton("Raspremi");
        btnNewButton.setBounds(519, 290, 89, 23);
        add(btnNewButton);

        btnNewButton.addActionListener(createButtonActionListener());
    }

    public ActionListener createButtonActionListener() {
        return evt -> {
            String brojSoba = textField.getText();
            for (Soba soba : sobe) {
                if (soba.brojSobe.equals(brojSoba)) {
                    if (!soba.stanje.equals("SLOBODNO") && !soba.stanje.equals("ZAUZETO")) {
                        String stanjeSobe = soba.stanje.split("\\|")[0];
                        String sobarica = soba.stanje.split("\\|")[1];
                        if (stanjeSobe.equals("ZA CISCENJE") && sobarica.equals(sobaricaKorisnickoIme)) {
                            soba.stanje = "SLOBODNO";
                            DataManager.upisiSobe(sobe);
                            break;
                        }
                    }
                }
            }
            
            for(Rezervacija rezervacija: rezervacije){
                if(rezervacija.brojSobe.equals(brojSoba)){
                    rezervacija.stanje += "|"+sobaricaKorisnickoIme;
                    DataManager.upisiRezervacije(rezervacije);
                    break;
                }
            }

            refreshTableModel();
        };
    }

    public void populateTableModel() {
        for (Soba soba : sobe) {
            if (!soba.stanje.equals("SLOBODNO") && !soba.stanje.equals("ZAUZETO")) {
                String stanjeSobe = soba.stanje.split("\\|")[0];
                String sobarica = soba.stanje.split("\\|")[1];
                if (stanjeSobe.equals("ZA CISCENJE") && sobarica.equals(sobaricaKorisnickoIme)) {
                    Object[] rowData = {soba.brojSobe, soba.tip, soba.stanje};
                    modelSobe.addRow(rowData);
                }
            }
        }
    }

    public void refreshTableModel() {
        modelSobe.setRowCount(0); // Clear existing rows
        populateTableModel();
        modelSobe.fireTableDataChanged(); // Notify table model listeners
    }
}
