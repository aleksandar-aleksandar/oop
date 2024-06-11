package panes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import design.Login;
import logic.Cenovnik;
import logic.Gost;
import logic.Rezervacija;
import logic.Soba;
import logic.TipSobe;
import logic.Usluga;
import logic.Zaposleni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogOutPanel extends JPanel {
    public LogOutPanel(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge){
        setLayout(null);

        JButton odjava =  new JButton("Odjavi se");
        odjava.setBounds(289, 180, 220, 35);
        add(odjava);

        odjava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge);
                ((JFrame) getTopLevelAncestor()).dispose();
            }
        });
    }
}
