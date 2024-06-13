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
import panes.PieChartRoomServicePanel;
import panes.PieChartRoomTypesPanel;
import panes.ReportPanel;
import panes.RevenueByRoomTypeGraph;

public class AdminTabPane extends JFrame {


    AdminTabPane(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge) {
        super("Admin");
        
        setResizable(false);
        setBounds(0, 0, 800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        add(tabbedPane);

        tabbedPane.addTab("Odjava", new LogOutPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.addTab("Dodaj Zaposlenog", new AddEmployeePanel(zaposleni));
        tabbedPane.addTab("Uredi Zaposlene", new EditEmployeePanel(zaposleni));
        tabbedPane.addTab("Dodaj Sobu", new AddRoomPanel(sobe, tipoviSobe));
        tabbedPane.addTab("Uredi Sobe",new EditRoomPanel(sobe));
        tabbedPane.addTab("Dodaj Cenovnik", new AddPricesPanel(cene));
        tabbedPane.addTab("Uredi Cenovnike", new EditPricesPanel(cene));
        tabbedPane.addTab("Izveštaji", new ReportPanel(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge));
        tabbedPane.add("Pie", new PieChartRoomServicePanel(rezervacije));
        tabbedPane.add("Pie2", new PieChartRoomTypesPanel(rezervacije));
        tabbedPane.add("Graph", new RevenueByRoomTypeGraph(rezervacije));
        setVisible(true);
    }

    public static void main(String[] args) {

    }

}
