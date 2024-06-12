package design;

import java.util.List;
import javax.swing.*;
import logic.*;
import panes.LogOutPanel;
import panes.RoomServicePanel;

public class SobaricaTabPane extends JFrame {

    SobaricaTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge, String sobaricaKorisnickoIme) {
        super("Sobarica");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        tabbedPane.addTab("Odjava", new LogOutPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.addTab("Raspremi Sobe", new RoomServicePanel(sobe, sobaricaKorisnickoIme));
        

    }
}
