package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logic.DataManager;
import logic.Rezervacija;
import logic.Gost;
import logic.Soba;

public class UserCheckInPanel extends JPanel {
    public UserCheckInPanel(List<Rezervacija> rezervacije, List<Gost> gosti, List<Soba> sobe){
        setLayout(null);

        JLabel lblNewLabel10 = new JLabel("Upisite broj rezervacije");
        lblNewLabel10.setBounds(225, 137, 136, 27);
        add(lblNewLabel10);

        JTextField textField10 = new JTextField();
        textField10.setBounds(370, 140, 86, 20);
        add(textField10);
        textField10.setColumns(10);

        JButton btnNewButton10 = new JButton("Check In");
        btnNewButton10.setBounds(315, 175, 89, 23);
        add(btnNewButton10);

        JLabel lblNewLabel_110 = new JLabel("Label za error/uspesno rezervisanje");
        lblNewLabel_110.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_110.setBounds(224, 221, 279, 14);
        add(lblNewLabel_110);

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
    }
}
