import java.util.List;
import design.Login;
import logic.*;

public class Main {
    public static void main(String[] args) {
        List<Zaposleni> zaposleni = DataManager.ucitajZaposlene();
        List<Rezervacija> rezervacije = DataManager.ucitajRezervacije();
        List<Soba> sobe = DataManager.ucitajSobe();
        List<Cenovnik> cene = DataManager.ucitajCene();
        List<Gost> gosti = DataManager.ucitajGoste();
        List<TipSobe> tipoviSobe = DataManager.ucitajTipoveSobe();
        List<Usluga> usluge = DataManager.ucitajUsluge();

        new Login(zaposleni, rezervacije, sobe, cene, gosti, tipoviSobe, usluge);
    }
}