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
        tabbedPane.addTab("Svi GOSTI", new AllUsersPanel(gosti));
        tabbedPane.addTab("CHECK IN", new UserCheckInPanel(rezervacije, gosti, sobe));
        tabbedPane.addTab("CheckOut", new UserCheckOutPanel(sobe));
        tabbedPane.addTab("Regulisi rezze", new EditReservationsPanel(rezervacije));
        tabbedPane.addTab("Sve rezervacije", new AllReservationsPanel(rezervacije));
        tabbedPane.addTab("SVE SOBE", new AllRoomsPanel(sobe));
    }

}
