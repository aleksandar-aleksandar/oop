package design;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import logic.Cenovnik;
import logic.Gost;
import logic.Rezervacija;
import logic.Soba;
import logic.TipSobe;
import logic.Usluga;
import logic.Zaposleni;
import panes.EditReservationsUserPanel;
import panes.LogOutPanel;
import panes.PricesInfoPanel;
import panes.ReserveRoomPanel;

public class GostTabPane extends JFrame {
    private JTextField textField;
    private JTextField textField_1;

    GostTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, Gost ulogovanGost, List<Usluga> usluge) {

        setTitle("Gost");
        setVisible(true);
        setBounds(100, 100, 800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 784, 411);
        getContentPane().add(tabbedPane);

        tabbedPane.addTab("Odjava", new LogOutPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.addTab("Rezerviraj sobu", new ReserveRoomPanel(tipoviSobe, cene, rezervacije, ulogovanGost));
        tabbedPane.addTab("Moje REZERVACIJE", new EditReservationsUserPanel(rezervacije));
        tabbedPane.addTab("CENOVNICI", new PricesInfoPanel(cene));
    }

    

}
