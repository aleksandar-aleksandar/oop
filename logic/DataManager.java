package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static List<Zaposleni> ucitajZaposlene() {
        List<Zaposleni> zaposleni = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/zaposleni.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 12) {
                    System.out.println("Desila se greska u citanju podataka!  zaposlenii");
                    break;
                }

                if (podaci[11].equals("administrator")) {
                    zaposleni.add(new Administrator(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5],
                            podaci[6],
                            podaci[7], podaci[8], podaci[9], podaci[10]));
                } else if (podaci[11].equals("recepcionar")) {
                    zaposleni.add(
                            new Recepcionar(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5], podaci[6],
                                    podaci[7], podaci[8], podaci[9], podaci[10]));
                } else if (podaci[11].equals("sobarica")) {
                    zaposleni.add(
                            new Sobarica(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5], podaci[6],
                                    podaci[7], podaci[8], podaci[9], "1000"));
                }
            }
            System.out.println("Zaposleni uspesno ucitani!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zaposleni;
    }

    public static List<Rezervacija> ucitajRezervacije() {
        List<Rezervacija> rezervacije = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/rezervacije.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 8) {
                    System.out.println("Desila se greska u citanju podataka! rez");
                    break;
                }

                rezervacije.add(
                        new Rezervacija(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5], podaci[6],
                                podaci[7]));

            }
            System.out.println("Rezervacije uspesno ucitane!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rezervacije;
    }

    public static List<Soba> ucitajSobe() {
        List<Soba> sobe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/sobe.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 3) {
                    System.out.println("Desila se greska u citanju podataka! sobe");
                    break;
                }
                sobe.add(new Soba(podaci[0], podaci[1], podaci[2]));
            }
            System.out.println("Sobe uspesno ucitane!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sobe;

    }

    public static List<Cenovnik> ucitajCene() {
        List<Cenovnik> cene = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/cenovnik.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 12) {
                    System.out.println("Desila se greska u citanju podataka!cene");
                    break;
                }
                cene.add(new Cenovnik(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5], podaci[6],
                        podaci[7], podaci[8], podaci[9], podaci[10], podaci[11]));
            }
            System.out.println("Cene uspesno ucitane!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cene;
    }

    public static List<TipSobe> ucitajTipoveSobe() {
        List<TipSobe> tipoviSobe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/tipsobe.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 1) {
                    System.out.println("Desila se greska u citanju podataka! tipovi");
                    break;
                }
                tipoviSobe.add(new TipSobe(podaci[0]));
            }
            System.out.println("Tipovi sobe uspesno ucitani!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tipoviSobe;
    }

    public static List<Gost> ucitajGoste() {
        // read form file gosti.txt
        List<Gost> gosti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/gosti.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 8) {
                    System.out.println("Desila se greska u citanju podataka! gosti");
                    break;
                }
                gosti.add(new Gost(podaci[0], podaci[1], podaci[2], podaci[3], podaci[4], podaci[5],
                        podaci[6],
                        podaci[7]));
            }
            System.out.println("Gosti uspesno ucitani!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gosti;
    }

    public static List<Usluga> ucitajUsluge() {
        List<Usluga> usluge = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/usluge.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] podaci = line.split(",");
                if (podaci.length != 1) {
                    System.out.println("Desila se greska u citanju podataka! usluge");
                    break;
                }
                usluge.add(new Usluga(podaci[0]));
            }
            System.out.println("Usluge uspesno ucitane!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usluge;
    }

    public static void upisiZaposlene(List<Zaposleni> zaposleni) {
        // write zaposlene into file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/zaposleni.txt"))) {
            for (Zaposleni radnik : zaposleni) {
                bw.write(radnik.toString());
                bw.newLine();
            }
            System.out.println("Zaposleni uspesno upisani!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upisiRezervacije(List<Rezervacija> rezervacije) {
        // write rezervacij into file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/rezervacije.txt"))) {
            for (Rezervacija rezervacija : rezervacije) {
                bw.write(rezervacija.toString());
                bw.newLine();
            }
            System.out.println("Rezervacije uspesno upisane!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upisiSobe(List<Soba> sobe) {
        // WRITE SOBA IN FILE
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/sobe.txt"))) {
            for (Soba soba : sobe) {
                bw.write(soba.toString());
                bw.newLine();
            }
            System.out.println("Sobe uspesno upisane!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upisiCene(List<Cenovnik> cene) {
        // WRITE CENE IN FILE
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/cenovnik.txt"))) {
            for (Cenovnik cena : cene) {
                bw.write(cena.toString());
                bw.newLine();
            }
            System.out.println("Cene uspesno upisane!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void upisiGoste(List<Gost> gosti) {
        // WRITE GUESTS IN FILE
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/gosti.txt"))) {
            for (Gost gost : gosti) {
                bw.write(gost.toString());
                bw.newLine();
            }
            System.out.println("Gosti uspesno upisani!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void upisiTipoveSobe(List<TipSobe> tipoviSobe) {
        // WRITE TIP SOBE IN FILE
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/tipsobe.txt"))) {
            for (TipSobe tipSobe : tipoviSobe) {
                bw.write(tipSobe.toString());
                bw.newLine();
            }
            System.out.println("Tipovi sobe uspesno upisani!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
