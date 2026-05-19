package core;

import core.Organizm;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Swiat implements Serializable {
    private static final long serialVersionUID = 1L;
    private int szerokosc, wysokosc;
    private List<Organizm> organizmy = new ArrayList<>();
    private List<Organizm> nowoNarodzone = new ArrayList<>();
    private List<String> logi = new ArrayList<>();
    private boolean koniecGry = false;
    private int kierunekCzlowieka = -1;
    private boolean zazadanoUmiejetnosci = false;

    public Swiat(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    public int getSzerokosc() { return szerokosc; }
    public int getWysokosc() { return wysokosc; }
    public boolean czyKoniec() { return koniecGry; }
    public void zakonczGre() { koniecGry = true; }

    public void dodajOrganizm(Organizm org) { organizmy.add(org); }
    public void dodajNowyOrganizm(Organizm org) { nowoNarodzone.add(org); }
    public void dodajLog(String wiadomosc) { logi.add(wiadomosc); }
    public List<String> getLogi() { return logi; }
    public List<Organizm> getOrganizmy() { return organizmy; }

    public void setKierunekCzlowieka(int k) { this.kierunekCzlowieka = k; }
    public int getKierunekCzlowieka() { return kierunekCzlowieka; }
    public void setZazadanoUmiejetnosci(boolean b) { this.zazadanoUmiejetnosci = b; }
    public boolean czyZazadanoUmiejetnosci() { return zazadanoUmiejetnosci; }

    public Organizm coZajmujePole(int x, int y) {
        for (Organizm org : organizmy) {
            if (org.czyZywy() && org.getX() == x && org.getY() == y) return org;
        }
        return null;
    }

    public void wykonajTure() {
        if (koniecGry) return;

        organizmy.sort((a, b) -> {
            if (a.getInicjatywa() == b.getInicjatywa()) {
                return Integer.compare(b.getWiek(), a.getWiek());
            }
            return Integer.compare(b.getInicjatywa(), a.getInicjatywa());
        });

        for (int i = 0; i < organizmy.size(); i++) {
            Organizm org = organizmy.get(i);
            if (org.czyZywy()) {
                org.akcja();
                org.zwiekszWiek();
            }
        }

        organizmy.addAll(nowoNarodzone);
        nowoNarodzone.clear();

        boolean czlowiekZyje = false;
        List<Organizm> doUsuniecia = new ArrayList<>();
        for (Organizm org : organizmy) {
            if (org.getNazwa().equals("Człowiek") && org.czyZywy()) {
                czlowiekZyje = true;
            }
            if (!org.czyZywy()) {
                doUsuniecia.add(org);
            }
        }
        organizmy.removeAll(doUsuniecia);

        if (!czlowiekZyje) {
            dodajLog("Człowiek umarł! KONIEC SYMULACJI.");
            zakonczGre();
        }

        kierunekCzlowieka = -1;
        zazadanoUmiejetnosci = false;
    }

    public void zapiszDoPliku(String sciezka) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sciezka))) {
            out.writeObject(this);
        }
    }

    public static Swiat wczytajZPliku(String sciezka) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(sciezka))) {
            return (Swiat) in.readObject();
        }
    }
}
