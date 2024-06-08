package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.*;

public class RecepcionarTabPane extends JFrame {
    RecepcionarTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge) {
        super("Recepcionar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

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

        JPanel dodajKorisnika = new JPanel();
        tabbedPane.addTab("Novi Gost", dodajKorisnika);
        dodajKorisnika.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ime");
        lblNewLabel.setBounds(32, 70, 46, 14);
        dodajKorisnika.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prezime");
        lblNewLabel_1.setBounds(32, 114, 46, 14);
        dodajKorisnika.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Pol");
        lblNewLabel_2.setBounds(32, 161, 46, 14);
        dodajKorisnika.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Datum rodjenja");
        lblNewLabel_3.setBounds(32, 210, 98, 14);
        dodajKorisnika.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Broj telefona");
        lblNewLabel_4.setBounds(32, 258, 86, 14);
        dodajKorisnika.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Adresa");
        lblNewLabel_5.setBounds(32, 304, 46, 14);
        dodajKorisnika.add(lblNewLabel_5);

        JTextField textField = new JTextField();
        textField.setBounds(155, 67, 86, 20);
        dodajKorisnika.add(textField);
        textField.setColumns(10);

        JTextField textField_1 = new JTextField();
        textField_1.setBounds(155, 111, 86, 20);
        dodajKorisnika.add(textField_1);
        textField_1.setColumns(10);

        JTextField textField_2 = new JTextField();
        textField_2.setBounds(155, 158, 86, 20);
        dodajKorisnika.add(textField_2);
        textField_2.setColumns(10);

        JTextField textField_3 = new JTextField();
        textField_3.setBounds(155, 207, 86, 20);
        dodajKorisnika.add(textField_3);
        textField_3.setColumns(10);

        JTextField textField_4 = new JTextField();
        textField_4.setBounds(155, 255, 86, 20);
        dodajKorisnika.add(textField_4);
        textField_4.setColumns(10);

        JTextField textField_5 = new JTextField();
        textField_5.setBounds(155, 301, 86, 20);
        dodajKorisnika.add(textField_5);
        textField_5.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Email");
        lblNewLabel_6.setBounds(317, 70, 98, 14);
        dodajKorisnika.add(lblNewLabel_6);

        JTextField textField_6 = new JTextField();
        textField_6.setBounds(438, 67, 86, 20);
        dodajKorisnika.add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Broj pasosa");
        lblNewLabel_7.setBounds(317, 114, 46, 14);
        dodajKorisnika.add(lblNewLabel_7);

        JTextField textField_7 = new JTextField();
        textField_7.setBounds(438, 111, 86, 20);
        dodajKorisnika.add(textField_7);
        textField_7.setColumns(10);

        JButton btnNewButton = new JButton("Dodaj");
        btnNewButton.setBounds(317, 275, 201, 43);
        dodajKorisnika.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }

        });

        JPanel sviGosti = new JPanel();
        sviGosti.setLayout(null);
        tabbedPane.add(sviGosti, "Svi Gosti");

        String[] gostiNaslovi = { "broj", "tip", "stanje", "datum zavrsetka", "jij", "", "", "" };

        DefaultTableModel modelGosti = new DefaultTableModel(gostiNaslovi, 0); // Create model with column
                                                                               // names

        for (Gost gost : gosti) {
            Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon, gost.adresa,
                    gost.korisnickoIme, gost.lozinka };
            modelGosti.addRow(rowData);
        }

        JTable tabelaSvihGostiju = new JTable(modelGosti);
        tabelaSvihGostiju.setBounds(0, 0, 800, 300);
        sviGosti.add(tabelaSvihGostiju);

        JButton osveziGoste = new JButton("Osvezi");
        osveziGoste.setBounds(350, 300, 100, 23);
        sviGosti.add(osveziGoste);

        osveziGoste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelGosti.setRowCount(0);
                for (Gost gost : gosti) {
                    Object[] rowData = { gost.ime, gost.prezime, gost.pol, gost.datumRodjenja, gost.brojTelefon,
                            gost.adresa, gost.korisnickoIme, gost.lozinka };
                    modelGosti.addRow(rowData);
                }
                DataManager.upisiGoste(gosti);
            }
        });

        JPanel checkIn = new JPanel();
        checkIn.setLayout(null);
        tabbedPane.addTab("Check In", checkIn);

        JLabel lblNewLabel10 = new JLabel("Upisite broj rezervacije");
        lblNewLabel10.setBounds(225, 137, 136, 27);
        checkIn.add(lblNewLabel10);

        JTextField textField10 = new JTextField();
        textField10.setBounds(370, 140, 86, 20);
        checkIn.add(textField10);
        textField10.setColumns(10);

        JButton btnNewButton10 = new JButton("Check In");
        btnNewButton10.setBounds(315, 175, 89, 23);
        checkIn.add(btnNewButton10);

        JLabel lblNewLabel_110 = new JLabel("Label za error/uspesno rezervisanje");
        lblNewLabel_110.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_110.setBounds(224, 221, 279, 14);
        checkIn.add(lblNewLabel_110);

        btnNewButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean reserved = false;
                String sobaBroj = "";
                String gost = "";
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.id.equals(textField10.getText()) && rezervacija.stanje.equals("POTVRDJENO")) {
                        for (Soba soba : sobe) {
                            if (soba.tip.equals(rezervacija.tipSobe)) {
                                rezervacija.stanje = soba.brojSobe;
                                soba.stanje = "ZAUZETO";
                                lblNewLabel_1.setText(
                                        "Uspesno zauzeta soba " + soba.brojSobe + " za gosta " + rezervacija.gost);
                                sobaBroj = soba.brojSobe;
                                gost = rezervacija.gost;
                                reserved = true;
                                break;
                            }
                        }

                    }
                }

                if (reserved == true) {
                    DataManager.upisiSobe(sobe);
                    DataManager.upisiRezervacije(rezervacije);
                    lblNewLabel_110.setText(
                            "Uspesno zauzeta soba " + sobaBroj + " za gosta " + gost);
                } else {
                    lblNewLabel_110.setText("Neuspesna rezervacija");
                }

            }
        });

        JPanel checkOut = new JPanel();
        checkOut.setLayout(null);
        tabbedPane.addTab("Check Out", checkOut);

        JLabel labell11 = new JLabel("Upisite broj sobe");
        labell11.setBounds(245, 137, 116, 27);
        checkOut.add(labell11);

        JTextField textField1 = new JTextField();
        textField1.setBounds(370, 140, 86, 20);
        checkOut.add(textField1);
        textField1.setColumns(10);

        JButton btnNewButton1 = new JButton("Check Out");
        btnNewButton1.setBounds(311, 175, 97, 23);
        checkOut.add(btnNewButton1);

        JLabel label122 = new JLabel("Label za error/uspesno rezervisanje");
        label122.setHorizontalAlignment(SwingConstants.CENTER);
        label122.setBounds(224, 221, 279, 14);
        checkOut.add(label122);

        btnNewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkedOut = false;
                for (Soba soba : sobe) {
                    if (soba.brojSobe.equals(textField1.getText())) {
                        soba.stanje = "ZA CISCENJE";
                        checkedOut = true;
                        break;
                    }
                }

                if (checkedOut == true) {
                    label122.setText("Uspesno ste se odjavili iz sobe!");
                } else {
                    label122.setText("Neuspesno odjavljivanje iz sobe!");
                }
            }
        });

        JPanel regulisiRezervacije = new JPanel();
        regulisiRezervacije.setLayout(null);
        tabbedPane.addTab("Regulisi rezervacije", regulisiRezervacije);

        String[] rezervacijeNaslovi = { "broj", "tip", "stanje", "datum zavrsetka", "jij" };

        DefaultTableModel modelRezervacija = new DefaultTableModel(rezervacijeNaslovi, 0); // Create model with column
                                                                                           // names

        for (Rezervacija rezervacija : rezervacije) {
            if (rezervacija.stanje.equals("NA CEKANJU")) {
                Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe, rezervacija.datumPocetka,
                        rezervacija.datumZavrsetka, rezervacija.dodatneUsluge, rezervacija.stanje, };
                modelRezervacija.addRow(rowData);
            }
            // Add the row to the model
        }

        JTable table = new JTable(modelRezervacija);
        table.setEnabled(false);
        table.setBounds(186, 44, 386, 180);
        regulisiRezervacije.add(table);

        JLabel lblNewLabel2 = new JLabel("Upisite broj rezervacije");
        lblNewLabel2.setBounds(183, 264, 135, 14);
        regulisiRezervacije.add(lblNewLabel2);

        JTextField textField2 = new JTextField();
        textField2.setBounds(312, 261, 86, 20);
        regulisiRezervacije.add(textField2);
        textField2.setColumns(10);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Potvrdi");
        rdbtnNewRadioButton.setBounds(420, 260, 79, 20);
        regulisiRezervacije.add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Odbij");
        rdbtnNewRadioButton_1.setBounds(509, 260, 59, 23);
        regulisiRezervacije.add(rdbtnNewRadioButton_1);

        ButtonGroup radioButtonGroup = new ButtonGroup();

        // Add both radio buttons to the group
        radioButtonGroup.add(rdbtnNewRadioButton);
        radioButtonGroup.add(rdbtnNewRadioButton_1);

        JButton btnNewButton3 = new JButton("Submit");
        btnNewButton3.setBounds(328, 312, 80, 23);
        regulisiRezervacije.add(btnNewButton3);

        JButton osveziRezervacije11 = new JButton("Osvezi");
        osveziRezervacije11.setBounds(200, 312, 80, 23);
        regulisiRezervacije.add(osveziRezervacije11);

        osveziRezervacije11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija.setRowCount(0);
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.stanje.equals("NA CEKANJU")) {
                        Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                                rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                                rezervacija.stanje };
                        modelRezervacija.addRow(rowData);
                    }
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });

        // refresh data in

        JPanel sveRezervacije = new JPanel();
        tabbedPane.addTab("Sve rezervacije", sveRezervacije);
        sveRezervacije.setLayout(null);

        String[] rezervacijeNaslovi1 = { "broj", "tip", "stanje", "datum zavrsetka", "jij", "korae", "loool", "234" };

        DefaultTableModel modelRezervacija1 = new DefaultTableModel(rezervacijeNaslovi1, 0);

        for (Rezervacija rezervacija : rezervacije) {
            Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                    rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                    rezervacija.stanje, rezervacija.cena };
            modelRezervacija1.addRow(rowData);
            // Add the row to the model
        }

        btnNewButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Rezervacija rezervacija : rezervacije) {
                    if (rezervacija.id.equals(textField2.getText())) {
                        if (rdbtnNewRadioButton.isSelected()) {
                            rezervacija.stanje = "POTVRDJENO";
                        } else if (rdbtnNewRadioButton_1.isSelected()) {
                            rezervacija.stanje = "ODBIJENO";
                        }
                        break;
                    }
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JTable tabelaSvihRezervacija = new JTable(modelRezervacija1);
        tabelaSvihRezervacija.setBounds(0, 0, 800, 300);
        sveRezervacije.add(tabelaSvihRezervacija);

        JButton obrisiRezervaciju = new JButton("Obrisi");
        obrisiRezervaciju.setBounds(300, 350, 100, 23);
        sveRezervacije.add(obrisiRezervaciju);
        obrisiRezervaciju.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabelaSvihRezervacija.getSelectedRow();
                if (row >= 0) {
                    modelRezervacija1.removeRow(row);
                }

                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JButton osveziRezervacije = new JButton("Osvezi");
        osveziRezervacije.setBounds(200, 350, 100, 23);
        sveRezervacije.add(osveziRezervacije);

        osveziRezervacije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija1.setRowCount(0);
                for (Rezervacija rezervacija : rezervacije) {
                    Object[] rowData = { rezervacija.id, rezervacija.gost, rezervacija.tipSobe,
                            rezervacija.datumPocetka, rezervacija.datumZavrsetka, rezervacija.dodatneUsluge,
                            rezervacija.stanje, rezervacija.cena };
                    modelRezervacija1.addRow(rowData);
                    // Add the row to the model
                }
                DataManager.upisiRezervacije(rezervacije);
            }
        });

        JButton sacuvajStanjeRezervacija = new JButton("Sacuvaj");
        sacuvajStanjeRezervacija.setBounds(400, 350, 100, 23);
        sveRezervacije.add(sacuvajStanjeRezervacija);

        sacuvajStanjeRezervacija.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Rezervacija> updatedRezervacije = new ArrayList<>();
                for (int i = 0; i < modelRezervacija1.getRowCount(); i++) {
                    String id = (String) modelRezervacija1.getValueAt(i, 0);
                    String gost = (String) modelRezervacija1.getValueAt(i, 1);
                    String tipSobe = (String) modelRezervacija1.getValueAt(i, 2);
                    String datumPocetka = (String) modelRezervacija1.getValueAt(i, 3);
                    String datumZavrsetka = (String) modelRezervacija1.getValueAt(i, 4);
                    String dodatneUsluge = (String) modelRezervacija1.getValueAt(i, 5);
                    String stanje = (String) modelRezervacija1.getValueAt(i, 6);
                    String cena = (String) modelRezervacija1.getValueAt(i, 7);
                    updatedRezervacije
                            .add(new Rezervacija(id, gost, tipSobe, datumPocetka, datumZavrsetka, dodatneUsluge,
                                    stanje, cena));
                }
                DataManager.upisiRezervacije(updatedRezervacije);
            }
        });

        JPanel sveSobe = new JPanel();
        tabbedPane.addTab("Sve sobe", sveSobe);
        sveSobe.setLayout(null);

        String[] rezervacijeNaslovi2 = { "broj", "tip", "stanje" };
        DefaultTableModel modelRezervacija2 = new DefaultTableModel(rezervacijeNaslovi2, 0); // Create model with column

        for (Soba soba : sobe) {
            Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
            modelRezervacija2.addRow(rowData);
            // Add the row to the model
        }
        JTable tabelaSvihSobe = new JTable(modelRezervacija2);
        tabelaSvihSobe.setBounds(0, 0, 800, 200);
        tabelaSvihSobe.setEnabled(false);
        sveSobe.add(tabelaSvihSobe);

        JButton osveziSobe = new JButton("Osvezi");
        osveziSobe.setBounds(350, 300, 100, 23);
        sveSobe.add(osveziSobe);

        osveziSobe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelRezervacija2.setRowCount(0);
                for (Soba soba : sobe) {
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    modelRezervacija2.addRow(rowData);
                    // Add the row to the model
                }
                DataManager.upisiSobe(sobe);
            }
        });
    }

    public static void main(String[] args) {

    }
}
