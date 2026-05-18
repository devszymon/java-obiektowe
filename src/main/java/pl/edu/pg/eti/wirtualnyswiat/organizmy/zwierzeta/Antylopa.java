package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;
import java.util.Random;

public class Antylopa extends Zwierze {
    public Antylopa(int x, int y, Swiat s) { super(4, 4, x, y, s); }
    @Override
    public void akcja() {
        Random rand = new Random();
        int kierunek = rand.nextInt(4);
        int krok = 2;
        int new_x = x, new_y = y;

        if (kierunek == 0 && y - krok >= 0) new_y -= krok;
        else if (kierunek == 1 && y + krok < swiat.getWysokosc()) new_y += krok;
        else if (kierunek == 2 && x - krok >= 0) new_x -= krok;
        else if (kierunek == 3 && x + krok < swiat.getSzerokosc()) new_x += krok;

        if (new_x != x || new_y != y) {
            Organizm inne = swiat.coZajmujePole(new_x, new_y);
            if (inne != null) {
                kolizja(inne);
            } else {
                x = new_x; y = new_y;
            }
        }
    }
    public boolean wykonajSkokUcieczki() {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < swiat.getSzerokosc() && ny >= 0 && ny < swiat.getWysokosc()) {
                    if (swiat.coZajmujePole(nx, ny) == null) {
                        this.x = nx; this.y = ny;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean czyUcieka() {
        Random rand = new Random();
        if (rand.nextInt(100) < 50) return wykonajSkokUcieczki();
        return false;
    }
    @Override
    public void kolizja(Organizm inny) {
        if (!this.getNazwa().equals(inny.getNazwa())) {
            Random rand = new Random();
            if (rand.nextInt(100) < 50) {
                if (wykonajSkokUcieczki()) {
                    swiat.dodajLog("Antylopa w locie nie atakuje " + inny.getNazwa());
                    return;
                }
            }
        }
        super.kolizja(inny);
    }
    @Override
    public String getNazwa() { return "Antylopa"; }
    @Override
    public char rysowanie() { return 'A'; }
    @Override
    public Color getKolor() { return new Color(210, 180, 140); }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Antylopa(nx, ny, swiat); }
}