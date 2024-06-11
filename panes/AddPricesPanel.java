package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Cenovnik;
import logic.DataManager;

public class AddPricesPanel extends JPanel {
    public AddPricesPanel(List<Cenovnik> cene){
        setLayout(null);

        JLabel dorucakLbl = new JLabel("Dorucak");
        dorucakLbl.setBounds(70, 23, 84, 20);
        add(dorucakLbl);

        JTextField dorucakTxt = new JTextField();
        dorucakTxt.setBounds(170, 23, 86, 20);
        add(dorucakTxt);

        JLabel rucakLbl = new JLabel("Rucak");
        rucakLbl.setBounds(70, 63, 84, 20);
        add(rucakLbl);

        JTextField rucakTxt = new JTextField();
        rucakTxt.setBounds(170, 63, 86, 20);
        add(rucakTxt);

        JLabel veceraLbl = new JLabel("Vecera");
        veceraLbl.setBounds(70, 103, 84, 20);
        add(veceraLbl);

        JTextField veceraTxt = new JTextField();
        veceraTxt.setBounds(170, 103, 86, 20);
        add(veceraTxt);

        JLabel bazenLbl = new JLabel("Bazen");
        bazenLbl.setBounds(70, 143, 84, 20);
        add(bazenLbl);

        JTextField bazenTxt = new JTextField();
        bazenTxt.setBounds(170, 143, 86, 20);
        add(bazenTxt);

        JLabel spaLbl = new JLabel("Spa");
        spaLbl.setBounds(70, 183, 84, 20);
        add(spaLbl);

        JTextField spaTxt = new JTextField();
        spaTxt.setBounds(170, 183, 86, 20);
        add(spaTxt);

        JLabel dpLbl = new JLabel("Pocetak");
        dpLbl.setBounds(70, 223, 84, 20);
        add(dpLbl);

        JTextField dpTxt = new JTextField();
        dpTxt.setBounds(170, 223, 86, 20);
        add(dpTxt);

        JLabel dzLbl = new JLabel("Zavrsetak");
        dzLbl.setBounds(70, 263, 84, 20);
        add(dzLbl);

        JTextField dzTxt = new JTextField();
        dzTxt.setBounds(170, 263, 86, 20);
        add(dzTxt);

        JLabel jednokrevetnaLbl = new JLabel("Soba (1)");
        jednokrevetnaLbl.setBounds(270, 23, 84, 20);
        add(jednokrevetnaLbl);

        JTextField jednokrevetnaTxt = new JTextField();
        jednokrevetnaTxt.setBounds(370, 23, 86, 20);
        add(jednokrevetnaTxt);

        JLabel dvokrevetnaLbl = new JLabel("Soba (2)");
        dvokrevetnaLbl.setBounds(270, 63, 84, 20);
        add(dvokrevetnaLbl);

        JTextField dvokrevetnaTxt = new JTextField();
        dvokrevetnaTxt.setBounds(370, 63, 86, 20);
        add(dvokrevetnaTxt);

        JLabel dvokrevetna2Lbl = new JLabel("Soba (1+1)");
        dvokrevetna2Lbl.setBounds(270, 103, 84, 20);
        add(dvokrevetna2Lbl);

        JTextField dvokrevetna2Txt = new JTextField();
        dvokrevetna2Txt.setBounds(370, 103, 86, 20);
        add(dvokrevetna2Txt);

        JLabel trokrevetnaLbl = new JLabel("Soba (2+1)");
        trokrevetnaLbl.setBounds(270, 143, 84, 20);
        add(trokrevetnaLbl);

        JTextField trokrevetnaTxt = new JTextField();
        trokrevetnaTxt.setBounds(370, 143, 86, 20);
        add(trokrevetnaTxt);

        JLabel cetvorokrevetnaLbl = new JLabel("Soba (2+2)");
        cetvorokrevetnaLbl.setBounds(270, 183, 84, 20);
        add(cetvorokrevetnaLbl);

        JTextField cetvorokrevetnaTxt = new JTextField();
        cetvorokrevetnaTxt.setBounds(370, 183, 86, 20);
        add(cetvorokrevetnaTxt);

        JButton sacuvajCene = new JButton("Sacuvaj");
        sacuvajCene.setBounds(70, 303, 180, 23);
        add(sacuvajCene);

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
    }
}
