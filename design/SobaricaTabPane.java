package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SobaricaTabPane extends JFrame {

    SobaricaTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge) {
        super("Sobarica");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        JPanel home = new JPanel();
        tabbedPane.addTab("Home", home);
        home.setLayout(null);

        JButton odjava = new JButton("Odjavi se");
        odjava.setBounds(289, 180, 220, 35);
        home.add(odjava);
        odjava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge);
                dispose();
            }
        });

        JPanel raspremanjeSoba = new JPanel();
        tabbedPane.addTab("Raspremanje Sobe", raspremanjeSoba);
        raspremanjeSoba.setLayout(null);

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
        raspremanjeSoba.add(table);
        table.setEnabled(false);

        JLabel lblNewLabel = new JLabel("Unesite broj sobe za raspremanje");
        lblNewLabel.setBounds(178, 294, 173, 14);
        raspremanjeSoba.add(lblNewLabel);

        JTextField textField = new JTextField();
        textField.setBounds(387, 291, 86, 20);
        raspremanjeSoba.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Raspremi");
        btnNewButton.setBounds(519, 290, 89, 23);
        raspremanjeSoba.add(btnNewButton);

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
