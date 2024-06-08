package logic;

public class Cenovnik {
    public String cena1;
    public String cena2;
    public String cena3;
    public String cena4;
    public String cena5;
    public String cena6;
    public String cena7;
    public String cena8;
    public String cena9;
    public String cena10;

    public String datumPocetka;
    public String datumZavrsetka;

    public Cenovnik(String cena1, String cena2, String cena3, String cena4, String cena5, String cena6, String cena7,
            String cena8, String cena9, String cena10, String datumPocetka, String datumZavrsetka) {
        this.cena1 = cena1;
        this.cena2 = cena2;
        this.cena3 = cena3;
        this.cena4 = cena4;
        this.cena5 = cena5;
        this.cena6 = cena6;
        this.cena7 = cena7;
        this.cena8 = cena8;
        this.cena9 = cena9;
        this.cena10 = cena10;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
    }

    public String toString() {
        return cena1 + "," + cena2 + "," + cena3 + "," + cena4 + "," + cena5 + "," + cena6 + "," + cena7 + "," + cena8
                + "," + cena9 + "," + cena10 + "," + datumPocetka + "," + datumZavrsetka;
    }

}
