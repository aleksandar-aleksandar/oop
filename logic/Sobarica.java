package logic;

public class Sobarica extends Zaposleni {

    public Sobarica(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon, String adresa,
            String korisnickoIme, String lozinka, String godineStaza, String nivoStrucneSpreme, int plata) {
        super(ime, prezime, pol, datumRodjenja, brojTelefon, adresa, korisnickoIme, lozinka, godineStaza,
                nivoStrucneSpreme,plata);
    }

    public String toString() {
        return super.toString() + ",sobarica";
    }

}
