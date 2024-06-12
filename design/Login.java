package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import logic.*;

public class Login extends JFrame {

    public Login(List<Zaposleni> zaposleni, List<Rezervacija> rezervacije, List<Soba> sobe, List<Cenovnik> cene,
            List<Gost> gosti, List<TipSobe> tipoviSobe, List<Usluga> usluge) {

        setTitle("Login");
        setResizable(false);
        setBounds(100, 100, 800, 450);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(290, 125, 220, 35);
        add(usernameField);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(290, 190, 220, 35);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(289, 255, 220, 35);
        add(loginButton);

        JLabel lblNewLabel = new JLabel("Korisnicko ime");
        lblNewLabel.setBounds(289, 106, 113, 14);
        add(lblNewLabel);

        JLabel lblLozinka = new JLabel("Lozinka");
        lblLozinka.setBounds(290, 171, 113, 14);
        add(lblLozinka);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println(username + password);
                for (Zaposleni radnik : zaposleni) {
                    System.out.println(radnik);
                    if (radnik.korisnickoIme.equals(username)) {
                        if (radnik.lozinka.equals(password)) {
                            dispose();
                            if (radnik instanceof Administrator) {
                                new AdminTabPane(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge);
                            } else if (radnik instanceof Recepcionar) {
                                new RecepcionarTabPane(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge);
                            } else if (radnik instanceof Sobarica) {
                                new SobaricaTabPane(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge, username);
                            }
                        }
                    }
                }

                for (Gost gost : gosti) {
                    if (gost.korisnickoIme.equals(username)) {
                        if (gost.lozinka.equals(password)) {
                            dispose();
                            new GostTabPane(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, gost, usluge);
                        }
                    }
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {

    }

}
