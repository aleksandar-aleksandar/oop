package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logic.*;

public class AdminTabPane extends JFrame {

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

    AdminTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge) {
        super("Admin");
        setVisible(true);
        setResizable(false);
        setBounds(0, 0, 800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // Dodaj zaposlene tab
        JPanel dodajZaposlene = new JPanel();
        tabbedPane.addTab("Novi Zaposleni", dodajZaposlene);
        dodajZaposlene.setLayout(null);

        JLabel lblNewLabel4 = new JLabel("Pozicija");
        lblNewLabel4.setBounds(32, 40, 46, 14);
        dodajZaposlene.add(lblNewLabel4);

        JComboBox<String> tipoviZaposlenih = new JComboBox<>();
        tipoviZaposlenih.setBounds(155, 40, 86, 20);
        dodajZaposlene.add(tipoviZaposlenih);

        tipoviZaposlenih.addItem("administrator");
        tipoviZaposlenih.addItem("recepcionar");
        tipoviZaposlenih.addItem("sobarica");

        JLabel lblNewLabel = new JLabel("Ime");
        lblNewLabel.setBounds(32, 70, 46, 14);
        dodajZaposlene.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prezime");
        lblNewLabel_1.setBounds(32, 114, 46, 14);
        dodajZaposlene.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Pol");
        lblNewLabel_2.setBounds(32, 161, 46, 14);
        dodajZaposlene.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Datum rodjenja");
        lblNewLabel_3.setBounds(32, 210, 98, 14);
        dodajZaposlene.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Broj telefona");
        lblNewLabel_4.setBounds(32, 258, 86, 14);
        dodajZaposlene.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Adresa");
        lblNewLabel_5.setBounds(32, 304, 46, 14);
        dodajZaposlene.add(lblNewLabel_5);

        textField = new JTextField();
        textField.setBounds(155, 67, 86, 20);
        dodajZaposlene.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(155, 111, 86, 20);
        dodajZaposlene.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(155, 158, 86, 20);
        dodajZaposlene.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(155, 207, 86, 20);
        dodajZaposlene.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(155, 255, 86, 20);
        dodajZaposlene.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(155, 301, 86, 20);
        dodajZaposlene.add(textField_5);
        textField_5.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Korisnicko ime");
        lblNewLabel_6.setBounds(317, 70, 98, 14);
        dodajZaposlene.add(lblNewLabel_6);

        textField_6 = new JTextField();
        textField_6.setBounds(438, 67, 86, 20);
        dodajZaposlene.add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Lozinka");
        lblNewLabel_7.setBounds(317, 114, 46, 14);
        dodajZaposlene.add(lblNewLabel_7);

        textField_7 = new JTextField();
        textField_7.setBounds(438, 111, 86, 20);
        dodajZaposlene.add(textField_7);
        textField_7.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel(" Godine iskustva");
        lblNewLabel_8.setBounds(317, 161, 86, 14);
        dodajZaposlene.add(lblNewLabel_8);

        textField_8 = new JTextField();
        textField_8.setBounds(438, 158, 86, 20);
        dodajZaposlene.add(textField_8);
        textField_8.setColumns(10);

        JLabel lblNewLabel_8_1 = new JLabel("Nivo strucne spreme");
        lblNewLabel_8_1.setBounds(317, 210, 86, 14);
        dodajZaposlene.add(lblNewLabel_8_1);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(438, 207, 86, 20);
        dodajZaposlene.add(textField_9);

        JButton btnNewButton = new JButton("Dodaj");
        btnNewButton.setBounds(317, 275, 201, 43);
        dodajZaposlene.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                if (poz.equals("administrator")) {
                    zaposleni.add(new Administrator(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka,
                            godine, nivo));
                } else if (poz.equals("recepcionar")) {
                    zaposleni.add(new Recepcionar(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka,
                            godine, nivo));
                } else if (poz.equals("sobarica")) {
                    zaposleni.add(new Sobarica(ime, prezime, pol, datum, broj, adresa, korisnickoIme, lozinka,
                            godine, nivo));
                }

                System.out.println("kreiramo novog zaposlenog: " + ime + " " + nivo + " " + poz);
                DataManager.upisiZaposlene(zaposleni);
            }
        });

        // Uredi zaposlene tab
        JPanel urediZaposlene = new JPanel();
        tabbedPane.addTab("Uredi zaposlene", urediZaposlene);
        urediZaposlene.setLayout(null);

        String[] naslovi = { "Ime", "Prezime", "pol", "datum", "broj", "adresa", "korisnicko ime", "godine staze",
                "lozinka", "nivo strucne spreme" };

        DefaultTableModel model = new DefaultTableModel(naslovi, 0); // Create model with column names

        for (Zaposleni zap : zaposleni) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { zap.ime, zap.prezime, zap.pol,
                    zap.datumRodjenja, zap.brojTelefon, zap.adresa,
                    zap.korisnickoIme, zap.godineStaza, zap.lozinka,
                    zap.nivoStrucneSpreme };
            model.addRow(rowData); // Add the row to the model
        }
        JTable tabelaZaposlenih = new JTable(model);
        JScrollBar scrollBar = new JScrollBar();
        tabelaZaposlenih.add(scrollBar);
        tabelaZaposlenih.repaint();
        tabelaZaposlenih.setBounds(100, 25, 600, 300);
        urediZaposlene.add(tabelaZaposlenih);

        JButton sacuvajStanjeButton = new JButton("Sacuvaj");
        sacuvajStanjeButton.setBounds(710, 350, 80, 23);
        urediZaposlene.add(sacuvajStanjeButton);
        sacuvajStanjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Zaposleni> updatedZaposleniList = new ArrayList<>();

                for (int i = 0; i < model.getRowCount(); i++) {
                    String ime = (String) model.getValueAt(i, 0);
                    String prezime = (String) model.getValueAt(i, 1);
                    String pol = (String) model.getValueAt(i, 2);
                    String datum = (String) model.getValueAt(i, 3);
                    String broj = (String) model.getValueAt(i, 4);
                    String adresa = (String) model.getValueAt(i, 5);
                    String korisnickoIme = (String) model.getValueAt(i, 6);
                    String godine = (String) model.getValueAt(i, 7);
                    String nivo = (String) model.getValueAt(i, 9);
                    String lozinka = (String) model.getValueAt(i, 8);

                    Zaposleni zap = zaposleni.get(i);

                    zap.ime = ime;
                    zap.prezime = prezime;
                    zap.pol = pol;
                    zap.datumRodjenja = datum;
                    zap.brojTelefon = broj;
                    zap.adresa = adresa;
                    zap.korisnickoIme = korisnickoIme;
                    zap.godineStaza = godine;
                    zap.lozinka = lozinka;
                    zap.nivoStrucneSpreme = nivo;

                    updatedZaposleniList.add(zap);
                }

                DataManager.upisiZaposlene(updatedZaposleniList);
            }
        });

        // on change in the table save that change in zaposleni

        JButton obrisiZaposlenog = new JButton("Obrisi odabranog zaposlenog");

        JButton refreshButton = new JButton("Osvezi");
        refreshButton.setBounds(10, 350, 80, 23);
        urediZaposlene.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (Zaposleni zap : zaposleni) {
                    // Create a new row with data extracted from Zaposleni object
                    Object[] rowData = { zap.ime, zap.prezime, zap.pol,
                            zap.datumRodjenja, zap.brojTelefon, zap.adresa,
                            zap.korisnickoIme, zap.godineStaza, zap.lozinka,
                            zap.nivoStrucneSpreme };
                    model.addRow(rowData); // Add the row to the model
                }
            }
        });

        obrisiZaposlenog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaZaposlenih.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                    zaposleni.remove(row);
                    DataManager.upisiZaposlene(zaposleni);
                }
            }
        });
        obrisiZaposlenog.setBounds(100, 350, 600, 23);
        urediZaposlene.add(obrisiZaposlenog);

        JPanel dodajSobe = new JPanel();
        dodajSobe.setLayout(null);

        JLabel lblNewLabel_9 = new JLabel("Broj sobe");
        lblNewLabel_9.setBounds(70, 23, 84, 20);
        dodajSobe.add(lblNewLabel_9);

        final JTextField textField_10 = new JTextField();
        textField_10.setBounds(145, 23, 86, 20);
        dodajSobe.add(textField_10);
        textField_10.setColumns(10);

        JLabel lblNewLabel_10 = new JLabel("Tip sobe");
        lblNewLabel_10.setBounds(70, 64, 46, 14);
        dodajSobe.add(lblNewLabel_10);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(145, 60, 84, 22);
        dodajSobe.add(comboBox);

        for (TipSobe tip : tipoviSobe) {
            comboBox.addItem(tip.naziv);
        }

        JButton btnNewButton_1 = new JButton("Dodaj sobu");
        btnNewButton_1.setBounds(77, 134, 154, 36);
        dodajSobe.add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean is = false;
                String broj = textField_10.getText();
                String tip = (String) comboBox.getSelectedItem();

                for (Soba soba : sobe) {
                    if (soba.brojSobe.equals(broj)) {
                        is = true;
                    }
                }

                if (is == true) {
                    System.out.println("vec postoji soba s tim brojem");
                } else {
                    sobe.add(new Soba(broj, tip, "SLOBODNO"));
                    System.out.println("kreiramo novu sobu: " + broj + " " + tip);
                }
                DataManager.upisiSobe(sobe);
            }
        });

        JPanel urediSobe = new JPanel();
        urediSobe.setLayout(null);

        String[] nasloviSobe = { "broj", "tip", "stanje" };

        DefaultTableModel modelSobe = new DefaultTableModel(nasloviSobe, 0); // Create model with column names

        for (Soba soba : sobe) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
            modelSobe.addRow(rowData); // Add the row to the model
        }

        JTable tabelaSoba = new JTable(modelSobe);
        tabelaSoba.setBounds(100, 25, 600, 300);
        urediSobe.add(tabelaSoba);

        JButton sacuvajStanjeSoba = new JButton("Sacuvaj");
        sacuvajStanjeSoba.setBounds(710, 350, 80, 23);
        urediSobe.add(sacuvajStanjeSoba);
        sacuvajStanjeSoba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Soba> updatedSobaList = new ArrayList<>();
                for (int i = 0; i < modelSobe.getRowCount(); i++) {
                    String broj = (String) modelSobe.getValueAt(i, 0);
                    String tip = (String) modelSobe.getValueAt(i, 1);
                    String stanje = (String) modelSobe.getValueAt(i, 2);

                    Soba updatedSoba = new Soba(broj, tip, stanje);
                    updatedSobaList.add(updatedSoba);
                }
                DataManager.upisiSobe(updatedSobaList);
            }
        });

        JButton refreshSobe = new JButton("Osvezi");
        refreshSobe.setBounds(10, 350, 80, 23);
        urediSobe.add(refreshSobe);
        
        refreshSobe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelSobe.setRowCount(0);
                for (Soba soba : sobe) {
                    // Create a new row with data extracted from Zaposleni object
                    Object[] rowData = { soba.brojSobe, soba.tip, soba.stanje };
                    modelSobe.addRow(rowData); // Add the row to the model
                }
            }
        });

        JButton obrisiSobu = new JButton("Obrisi odabranu sobu");
        obrisiSobu.setBounds(100, 350, 600, 23);
        urediSobe.add(obrisiSobu);

        obrisiSobu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaSoba.getSelectedRow();
                if (row >= 0) {
                    modelSobe.removeRow(row);
                    sobe.remove(row);
                    DataManager.upisiSobe(sobe);
                }
            }
        });

        JPanel dodajCene = new JPanel();
        dodajCene.setLayout(null);

        JLabel dorucakLbl = new JLabel("Dorucak");
        dorucakLbl.setBounds(70, 23, 84, 20);
        dodajCene.add(dorucakLbl);

        JTextField dorucakTxt = new JTextField();
        dorucakTxt.setBounds(170, 23, 86, 20);
        dodajCene.add(dorucakTxt);

        JLabel rucakLbl = new JLabel("Rucak");
        rucakLbl.setBounds(70, 63, 84, 20);
        dodajCene.add(rucakLbl);

        JTextField rucakTxt = new JTextField();
        rucakTxt.setBounds(170, 63, 86, 20);
        dodajCene.add(rucakTxt);

        JLabel veceraLbl = new JLabel("Vecera");
        veceraLbl.setBounds(70, 103, 84, 20);
        dodajCene.add(veceraLbl);

        JTextField veceraTxt = new JTextField();
        veceraTxt.setBounds(170, 103, 86, 20);
        dodajCene.add(veceraTxt);

        JLabel bazenLbl = new JLabel("Bazen");
        bazenLbl.setBounds(70, 143, 84, 20);
        dodajCene.add(bazenLbl);

        JTextField bazenTxt = new JTextField();
        bazenTxt.setBounds(170, 143, 86, 20);
        dodajCene.add(bazenTxt);

        JLabel spaLbl = new JLabel("Spa");
        spaLbl.setBounds(70, 183, 84, 20);
        dodajCene.add(spaLbl);

        JTextField spaTxt = new JTextField();
        spaTxt.setBounds(170, 183, 86, 20);
        dodajCene.add(spaTxt);

        JLabel dpLbl = new JLabel("Pocetak");
        dpLbl.setBounds(70, 223, 84, 20);
        dodajCene.add(dpLbl);

        JTextField dpTxt = new JTextField();
        dpTxt.setBounds(170, 223, 86, 20);
        dodajCene.add(dpTxt);

        JLabel dzLbl = new JLabel("Zavrsetak");
        dzLbl.setBounds(70, 263, 84, 20);
        dodajCene.add(dzLbl);

        JTextField dzTxt = new JTextField();
        dzTxt.setBounds(170, 263, 86, 20);
        dodajCene.add(dzTxt);

        JLabel jednokrevetnaLbl = new JLabel("Soba (1)");
        jednokrevetnaLbl.setBounds(270, 23, 84, 20);
        dodajCene.add(jednokrevetnaLbl);

        JTextField jednokrevetnaTxt = new JTextField();
        jednokrevetnaTxt.setBounds(370, 23, 86, 20);
        dodajCene.add(jednokrevetnaTxt);

        JLabel dvokrevetnaLbl = new JLabel("Soba (2)");
        dvokrevetnaLbl.setBounds(270, 63, 84, 20);
        dodajCene.add(dvokrevetnaLbl);

        JTextField dvokrevetnaTxt = new JTextField();
        dvokrevetnaTxt.setBounds(370, 63, 86, 20);
        dodajCene.add(dvokrevetnaTxt);

        JLabel dvokrevetna2Lbl = new JLabel("Soba (1+1)");
        dvokrevetna2Lbl.setBounds(270, 103, 84, 20);
        dodajCene.add(dvokrevetna2Lbl);

        JTextField dvokrevetna2Txt = new JTextField();
        dvokrevetna2Txt.setBounds(370, 103, 86, 20);
        dodajCene.add(dvokrevetna2Txt);

        JLabel trokrevetnaLbl = new JLabel("Soba (2+1)");
        trokrevetnaLbl.setBounds(270, 143, 84, 20);
        dodajCene.add(trokrevetnaLbl);

        JTextField trokrevetnaTxt = new JTextField();
        trokrevetnaTxt.setBounds(370, 143, 86, 20);
        dodajCene.add(trokrevetnaTxt);

        JLabel cetvorokrevetnaLbl = new JLabel("Soba (2+2)");
        cetvorokrevetnaLbl.setBounds(270, 183, 84, 20);
        dodajCene.add(cetvorokrevetnaLbl);

        JTextField cetvorokrevetnaTxt = new JTextField();
        cetvorokrevetnaTxt.setBounds(370, 183, 86, 20);
        dodajCene.add(cetvorokrevetnaTxt);

        JButton sacuvajCene = new JButton("Sacuvaj");
        sacuvajCene.setBounds(70, 303, 180, 23);
        dodajCene.add(sacuvajCene);

        sacuvajCene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cena1 = jednokrevetnaTxt.getText().toString();
                String cena2 = dvokrevetnaTxt.getText().toString();
                String cena3 = dvokrevetna2Txt.getText().toString();
                String cena4 = trokrevetnaTxt.getText().toString();
                String cena5 = cetvorokrevetnaTxt.getText().toString();
                String cena6 = dorucakTxt.getText().toString();
                String cena7 = rucakTxt.getText().toString();
                String cena8 = veceraTxt.getText().toString();
                String cena9 = bazenTxt.getText().toString();
                String cena10 = spaTxt.getText().toString();
                String datumPocetka = dpTxt.getText().toString();
                String datumZavrsetka = dzTxt.getText().toString();

                cene.add(new Cenovnik(cena1, cena2, cena3, cena4, cena5, cena6, cena7, cena8, cena9, cena10,
                        datumPocetka, datumZavrsetka));
                DataManager.upisiCene(cene);
            }

        });

        JPanel urediCene = new JPanel();
        urediCene.setLayout(null);

        String[] nasloviCene = { "broj", "tip", "stanje", "datum zavrsetka", "fd", "broj", "tip", "stanje",
                "datum zavrsetka", "fd", "fsd", "looo" };

        DefaultTableModel modelCene = new DefaultTableModel(nasloviCene, 0); // Create model with column names

        for (Cenovnik cena : cene) {
            // Create a new row with data extracted from Zaposleni object
            Object[] rowData = { cena.cena1, cena.cena2, cena.cena3, cena.cena4, cena.cena5, cena.cena6, cena.cena7,
                    cena.cena8, cena.cena9, cena.cena10, cena.datumPocetka, cena.datumZavrsetka };
            modelCene.addRow(rowData); // Add the row to the model
        }

        JTable tabelaCena = new JTable(modelCene);
        tabelaCena.setBounds(100, 25, 600, 300);
        urediCene.add(tabelaCena);

        JButton refreshCene = new JButton("Osvezi");
        refreshCene.setBounds(10, 350, 80, 23);
        urediCene.add(refreshCene);
        refreshCene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelCene.setRowCount(0);
                for (Cenovnik cena : cene) {
                    // Create a new row with data extracted from Zaposleni object
                    Object[] rowData = { cena.cena1, cena.cena2, cena.cena3, cena.cena4, cena.cena5, cena.cena6,
                            cena.cena7,
                            cena.cena8, cena.cena9, cena.cena10, cena.datumPocetka, cena.datumZavrsetka };
                    modelCene.addRow(rowData); // Add the row to the model
                }
                DataManager.upisiCene(cene);
            }

        });

        JButton sacuvajStanjeCene = new JButton("Sacuvaj");
        sacuvajStanjeCene.setBounds(710, 350, 80, 23);
        urediCene.add(sacuvajStanjeCene);
        sacuvajStanjeCene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cenovnik> updatedCene = new ArrayList<>();
                for (int i = 0; i < modelCene.getRowCount(); i++) {

                    String cena1 = (String) modelCene.getValueAt(i, 0);
                    String cena2 = (String) modelCene.getValueAt(i, 1);
                    String cena3 = (String) modelCene.getValueAt(i, 2);
                    String cena4 = (String) modelCene.getValueAt(i, 3);
                    String cena5 = (String) modelCene.getValueAt(i, 4);
                    String cena6 = (String) modelCene.getValueAt(i, 5);
                    String cena7 = (String) modelCene.getValueAt(i, 6);
                    String cena8 = (String) modelCene.getValueAt(i, 7);
                    String cena9 = (String) modelCene.getValueAt(i, 8);
                    String cena10 = (String) modelCene.getValueAt(i, 9);
                    String datumPocetka = (String) modelCene.getValueAt(i, 10);
                    String datumZavrsetka = (String) modelCene.getValueAt(i, 11);
                    updatedCene.add(new Cenovnik(cena1, cena2, cena3, cena4, cena5, cena6, cena7, cena8, cena9, cena10,
                            datumPocetka, datumZavrsetka));
                }

                DataManager.upisiCene(updatedCene);
            }
        });

        JButton obrisiCenu = new JButton("Obrisi odabranu uslugu");
        obrisiCenu.setBounds(100, 350, 600, 23);
        urediCene.add(obrisiCenu);

        obrisiCenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabelaCena.getSelectedRow();
                if (row >= 0) {
                    modelCene.removeRow(row);
                    cene.remove(row);
                    DataManager.upisiCene(cene);
                }
            }
        });

        tabbedPane.addTab("Dodaj sobe", dodajSobe);
        tabbedPane.addTab("Uredi sobe", urediSobe);
        tabbedPane.addTab("Dodaj cene", dodajCene);
        tabbedPane.addTab("Uredi cene", urediCene);
    }

    public static void main(String[] args) {

    }

}
