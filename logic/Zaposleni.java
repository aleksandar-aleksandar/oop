package logic;

public abstract class Zaposleni extends Osoba {

    public String godineStaza;
    public String nivoStrucneSpreme;
    public String plata;

    public Zaposleni(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon, String adresa,
            String korisnickoIme, String lozinka, String godineStaza, String nivoStrucneSpreme) {
        super(ime, prezime, pol, datumRodjenja, brojTelefon, adresa, korisnickoIme, lozinka);
        this.godineStaza = godineStaza;
        this.nivoStrucneSpreme = nivoStrucneSpreme;
    }

    public String toString() {
        return super.toString() + "," + godineStaza + "," + nivoStrucneSpreme;

    }

}
