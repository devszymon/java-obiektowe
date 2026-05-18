package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Czlowiek extends Zwierze {
    private int cooldownUmiejetnosci = 0;

    public Czlowiek(int x, int y, Swiat s) {
        super(5, 4, x, y, s);
    }

    @Override
    public void akcja() {
        if (swiat.czyZazadanoUmiejetnosci()) {
            aktywujUmiejetnosc();
        }

        int new_x = x, new_y = y;
        int ruch = swiat.getKierunekCzlowieka();

        if (ruch == 0 && y > 0) new_y--;
        else if (ruch == 1 && y < swiat.getWysokosc() - 1) new_y++;
        else if (ruch == 2 && x > 0) new_x--;
        else if (ruch == 3 && x < swiat.getSzerokosc() - 1) new_x++;

        if (new_x != x || new_y != y) {
            Organizm inne = swiat.coZajmujePole(new_x, new_y);
            if (inne != null) kolizja(inne);
            else { x = new_x; y = new_y; }
        }
        obsluzUmiejetnosc();
    }

    public void aktywujUmiejetnosc() {
        if (cooldownUmiejetnosci == 0) {
            swiat.dodajLog("Człowiek używa umiejętności: CAŁOPALENIE");

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;

                    int new_x = x + dx;
                    int new_y = y + dy;
                    if (new_x >= 0 && new_x < swiat.getSzerokosc() && new_y >= 0 && new_y < swiat.getWysokosc()) {
                        Organizm obok = swiat.coZajmujePole(new_x, new_y);
                        if (obok != null) {
                            swiat.dodajLog("Całopalenie niszczy: " + obok.getNazwa());
                            obok.zabij();
                        }
                    }
                }
            }
            cooldownUmiejetnosci = 5;
        } else {
            swiat.dodajLog("Umiejętność niedostępna. Cooldown: " + cooldownUmiejetnosci);
        }
    }

    private void obsluzUmiejetnosc() {
        if (cooldownUmiejetnosci > 0) {
            cooldownUmiejetnosci--;
        }
    }

    @Override
    public void kolizja(Organizm inny) {
        if (this.getSila() < inny.getSila()) {
            swiat.dodajLog("Człowiek ginie! KONIEC GRY.");
            swiat.zakonczGre();
            this.zabij();
        } else {
            super.kolizja(inny);
        }
    }

    public int getCooldown() { return cooldownUmiejetnosci; }
    @Override
    public String getNazwa() { return "Człowiek"; }
    @Override
    public char rysowanie() { return 'C'; }
    @Override
    public Color getKolor() { return Color.BLUE; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return null; }
}