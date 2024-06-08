package logic;

public class Recepcionar extends Zaposleni {

    public Recepcionar(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon, String adresa,
            String korisnickoIme, String lozinka, String godineStaza, String nivoStrucneSpreme) {
        super(ime, prezime, pol, datumRodjenja, brojTelefon, adresa, korisnickoIme, lozinka, godineStaza,
                nivoStrucneSpreme);
    }

    public String toString() {
        return super.toString() + ",recepcionar";
    }

}
