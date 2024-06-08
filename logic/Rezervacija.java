package logic;

public class Rezervacija {
    public String id;
    public String gost;
    public String tipSobe;
    public String datumPocetka;
    public String datumZavrsetka;
    public String dodatneUsluge;
    public String stanje;
    public String cena;

    public Rezervacija(String id, String gost, String tipSobe, String datumPocetka, String datumZavrsetka,
            String dodatneUsluge,
            String stanje, String cena) {
        this.id = id;
        this.gost = gost;
        this.tipSobe = tipSobe;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.dodatneUsluge = dodatneUsluge;
        this.stanje = stanje;
        this.cena = cena;
    }

    public String toString() {
        return id + "," + gost + "," + tipSobe + "," + datumPocetka + "," + datumZavrsetka + "," + dodatneUsluge + ","
                + stanje + "," + cena;
    }

}
