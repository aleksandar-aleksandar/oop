package logic;

public class Administrator extends Zaposleni {

    public Administrator(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon,
            String adresa, String korisnickoIme, String lozinka, String godineStaza, String nivoStrucneSpreme) {
        super(ime, prezime, pol, datumRodjenja, brojTelefon, adresa, korisnickoIme, lozinka, godineStaza,
                nivoStrucneSpreme);
    }

    @Override
    public String toString() {
        return super.toString() + ",administrator";
    }
}