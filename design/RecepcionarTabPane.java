package design;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import logic.*;
import panes.AddUserPanel;
import panes.AllReservationsPanel;
import panes.AllRoomsPanel;
import panes.AllUsersPanel;
import panes.EditReservationsPanel;
import panes.LogOutPanel;
import panes.UserCheckInPanel;
import panes.UserCheckOutPanel;

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

        tabbedPane.addTab("Odjava", new LogOutPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.addTab("Registruj Gosta", new AddUserPanel(gosti));
        tabbedPane.addTab("Svi Gost", new AllUsersPanel(gosti));
        tabbedPane.addTab("Check In", new UserCheckInPanel(rezervacije, gosti, sobe));
        tabbedPane.addTab("Check Out", new UserCheckOutPanel(sobe, zaposleni));
        tabbedPane.addTab("Reguliši Rezervacije", new EditReservationsPanel(rezervacije, sobe));
        tabbedPane.addTab("Sve Rezervacije", new AllReservationsPanel(rezervacije));
        tabbedPane.addTab("Sve Sobe", new AllRoomsPanel(sobe));
    }

}
