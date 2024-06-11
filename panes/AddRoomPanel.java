package panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.TipSobe;
import logic.DataManager;
import logic.Soba;

public class AddRoomPanel extends JPanel {
    public AddRoomPanel(List<Soba> sobe, List<TipSobe> tipoviSobe){
        setLayout(null);

        JLabel lblNewLabel_9 = new JLabel("Broj sobe");
        lblNewLabel_9.setBounds(70, 23, 84, 20);
        add(lblNewLabel_9);

        final JTextField textField_10 = new JTextField();
        textField_10.setBounds(145, 23, 86, 20);
        add(textField_10);
        textField_10.setColumns(10);

        JLabel lblNewLabel_10 = new JLabel("Tip sobe");
        lblNewLabel_10.setBounds(70, 64, 46, 14);
        add(lblNewLabel_10);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(145, 60, 84, 22);
        add(comboBox);

        for (TipSobe tip : tipoviSobe) {
            comboBox.addItem(tip.naziv);
        }

        JButton btnNewButton_1 = new JButton("Dodaj sobu");
        btnNewButton_1.setBounds(77, 134, 154, 36);
        add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean is = false;
                String broj = textField_10.getText();
                String tip = (String) comboBox.getSelectedItem();

                for (Soba soba : sobe) {
                    if (soba.brojSobe.equals(broj)) {
                        is = true;
                    }
                }

                if (is == true) {
                    System.out.println("vec postoji soba s tim brojem");
                } else {
                    sobe.add(new Soba(broj, tip, "SLOBODNO"));
                    System.out.println("kreiramo novu sobu: " + broj + " " + tip);
                }
                DataManager.upisiSobe(sobe);
            }
        });
    }
}
