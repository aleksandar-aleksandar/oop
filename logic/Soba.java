package logic;

public class Soba {

    public String brojSobe;
    public String tip;
    public String stanje;

    public Soba(String brojSobe, String tip, String stanje) {
        this.brojSobe = brojSobe;
        this.tip = tip;
        this.stanje = stanje;
    }

    public String toString() {
        return brojSobe + "," + tip + "," + stanje;
    }
}
