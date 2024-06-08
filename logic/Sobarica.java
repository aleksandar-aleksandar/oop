package logic;

import java.util.List;
import java.util.Scanner;

public class Sobarica extends Zaposleni {

    public Sobarica(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon, String adresa,
            String korisnickoIme, String lozinka, String godineStaza, String nivoStrucneSpreme) {
        super(ime, prezime, pol, datumRodjenja, brojTelefon, adresa, korisnickoIme, lozinka, godineStaza,
                nivoStrucneSpreme);
    }

    public String toString() {
        return super.toString() + ",sobarica";
    }

    public static void sobaricaMeni(List<Soba> sobe) {
        Scanner s = new Scanner(System.in);
        System.out.println("1.Raspremi sobe");
        int izbor = s.nextInt();
        if (izbor == 1) {
            Sobarica.raspremiSobe(sobe);
        }
        s.close();
    }

    public static void raspremiSobe(List<Soba> sobe) {
        System.out.println("Sobe za ciscenje");
        for (Soba soba : sobe) {
            if (soba.stanje.equals("ZA CISCENJE")) {
                System.out.println(soba.brojSobe);
            }
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Unesite broj sobe koju zelite da ocistite:");
        String sobaZaCiscenje = s.nextLine();
        for (Soba soba : sobe) {
            if (soba.brojSobe.equals(sobaZaCiscenje)) {
                soba.stanje = "SLOBODNO";
                System.out.println("Uspesno ste ocistili sobu " + soba.brojSobe + "!");
            }
        }
        s.close();
    }

}
