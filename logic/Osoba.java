package logic;

public class Osoba {

    // O svim korisnicima se evidentiraju sledeći podaci: ime, prezime, pol, datum
    // rođenja,telefon, adresa, korisničko ime i lozinka.
    public String ime;
    public String prezime;
    public String pol;
    public String datumRodjenja;
    public String brojTelefon;
    public String adresa;
    public String korisnickoIme;
    public String lozinka;

    public Osoba(String ime, String prezime, String pol, String datumRodjenja, String brojTelefon, String adresa,
            String korisnickoIme, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefon = brojTelefon;
        this.adresa = adresa;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String toString() {
        return ime + "," + prezime + "," + pol + "," + datumRodjenja + "," + brojTelefon + "," + adresa + ","
                + korisnickoIme + "," + lozinka;
    }
}
