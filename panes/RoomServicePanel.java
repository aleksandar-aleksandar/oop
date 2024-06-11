package panes;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logic.DataManager;
import logic.Soba;

public class RoomServicePanel extends JPanel {
    public RoomServicePanel(List<Soba> sobe){
        setLayout(null);

        String[] nasloviSoba = { "broj", "tip", "stanje" };

        DefaultTableModel modelSobe = new DefaultTableModel(nasloviSoba, 0); // Create model with column
                                                                             // names

        for (Soba soba : sobe) {
            if (soba.stanje.equals("ZA CISCENJE")) {
                Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                modelSobe.addRow(rowData);
            }

            // Add the row to the model
        }

        JTable table = new JTable(modelSobe);
        table.setBounds(132, 41, 526, 224);
        add(table);
        table.setEnabled(false);

        JLabel lblNewLabel = new JLabel("Unesite broj sobe za raspremanje");
        lblNewLabel.setBounds(178, 294, 173, 14);
        add(lblNewLabel);

        JTextField textField = new JTextField();
        textField.setBounds(387, 291, 86, 20);
        add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Raspremi");
        btnNewButton.setBounds(519, 290, 89, 23);
        add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String brojSoba = textField.getText();
                for (Soba soba : sobe) {
                    if (soba.brojSobe.equals(brojSoba)) {
                        if (soba.stanje.equals("ZA CISCENJE")) {
                            soba.stanje = "SLOBODNO";
                            System.out.println("Uspesno ste raspremili sobu " + soba.brojSobe);
                            DataManager.upisiSobe(sobe);
                            break;

                        }
                    }
                }

                modelSobe.setRowCount(0); // Clear existing rows
                for (Soba soba : sobe) {
                    if (soba.stanje.equals("ZA CISCENJE")) {
                        Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                        modelSobe.addRow(rowData);
                    }
                }

                // Notify table model listeners after model updates
                modelSobe.fireTableDataChanged();

            }

        });
    }
}