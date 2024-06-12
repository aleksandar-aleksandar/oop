package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logic.Soba;
import logic.Sobarica;
import logic.Zaposleni;

public class UserCheckOutPanel extends JPanel {
    public UserCheckOutPanel(List<Soba> sobe, List<Zaposleni> zaposleni){
        setLayout(null);
        JLabel labell11 = new JLabel("Upisite broj sobe");
        labell11.setBounds(245, 137, 116, 27);
        add(labell11);

        JTextField textField1 = new JTextField();
        textField1.setBounds(370, 140, 86, 20);
        add(textField1);
        textField1.setColumns(10);

        JButton btnNewButton1 = new JButton("Check Out");
        btnNewButton1.setBounds(311, 175, 97, 23);
        add(btnNewButton1);

        JLabel label122 = new JLabel("Label za error/uspesno rezervisanje");
        label122.setHorizontalAlignment(SwingConstants.CENTER);
        label122.setBounds(224, 221, 279, 14);
        add(label122);

        String sobaricaKorisnickoIme = findLeastFrequentSobarica(sveSobarice(zaposleni));
        btnNewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkedOut = false;
                for (Soba soba : sobe) {
                    if (soba.brojSobe.equals(textField1.getText())) {
                        soba.stanje = "ZA CISCENJE|"+ sobaricaKorisnickoIme;
                        checkedOut = true;
                        break;
                    }
                }

                if (checkedOut == true) {
                    label122.setText("Uspesno ste se odjavili iz sobe!");
                } else {
                    label122.setText("Neuspesno odjavljivanje iz sobe!");
                }
            }
        });
    }

    public List<String> sveSobarice(List <Zaposleni> zaposleni){
        List<String> sobarice = new ArrayList<>();

        for (Zaposleni radnik: zaposleni){
            if(radnik instanceof Sobarica){
                sobarice.add(radnik.korisnickoIme);
            }
        }

        return sobarice;
    }

    public static String findLeastFrequentSobarica(List<String> sobarice) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();
    
        // Count frequency of each worker
        for (String sobarica : sobarice) {
            frequencyMap.put(sobarica, frequencyMap.getOrDefault(sobarica, 0) + 1);
        }
    
        // Find the minimum frequency
        int minFrequency = Integer.MAX_VALUE;
        String leastFrequentSobarica = null;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() < minFrequency) {
                leastFrequentSobarica = entry.getKey();
                minFrequency = entry.getValue();
            }
        }
    
        return leastFrequentSobarica;
    
    }
}
