package design;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import logic.*;
import panes.AddEmployeePanel;
import panes.AddPricesPanel;
import panes.AddRoomPanel;
import panes.EditEmployeePanel;
import panes.EditPricesPanel;
import panes.EditRoomPanel;
import panes.LogOutPanel;

public class AdminTabPane extends JFrame {


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

        tabbedPane.addTab("Odjava", new LogOutPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.addTab("Dodaj zaposlenog", new AddEmployeePanel(zaposleni));
        tabbedPane.addTab("Uredi zaposlenog", new EditEmployeePanel(zaposleni));
        tabbedPane.addTab("Dodaj SOBE", new AddRoomPanel(sobe, tipoviSobe));
        tabbedPane.addTab("Uredi SOBE",new EditRoomPanel(sobe));
        tabbedPane.addTab("Dodaj CENE", new AddPricesPanel(cene));
        tabbedPane.addTab("Uredi CENE", new EditPricesPanel(cene));
    }

    public static void main(String[] args) {

    }

}
