package organizmy;

import core.Organizm;
import core.Swiat;
import java.util.Random;

public abstract class Zwierze extends Organizm {
    public Zwierze(int sila, int inicjatywa, int x, int y, Swiat swiat) {
        super(sila, inicjatywa, x, y, swiat);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        int kierunek = rand.nextInt(4);
        int nowy_x = x, nowy_y = y;

        if (kierunek == 0 && y > 0) nowy_y--;
        else if (kierunek == 1 && y < swiat.getWysokosc() - 1) nowy_y++;
        else if (kierunek == 2 && x > 0) nowy_x--;
        else if (kierunek == 3 && x < swiat.getSzerokosc() - 1) nowy_x++;

        if (nowy_x != x || nowy_y != y) {
            Organizm inne = swiat.coZajmujePole(nowy_x, nowy_y);
            if (inne != null) {
                kolizja(inne);
            } else {
                x = nowy_x;
                y = nowy_y;
            }
        }
    }

    @Override
    public void kolizja(Organizm inny) {
        if (this.getNazwa().equals(inny.getNazwa())) {
            Random rand = new Random();
            if (rand.nextInt(100) >= 50) return;

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    int nowy_x = x + dx;
                    int nowy_y = y + dy;

                    if (nowy_x >= 0 && nowy_x < swiat.getSzerokosc() && nowy_y >= 0 && nowy_y < swiat.getWysokosc()) {
                        if (swiat.coZajmujePole(nowy_x, nowy_y) == null) {
                            swiat.dodajLog("Urodził się nowy osobnik: " + getNazwa());
                            swiat.dodajNowyOrganizm(stworzPotomka(nowy_x, nowy_y));
                            return;
                        }
                    }
                }
            }
            return;
        }

        swiat.dodajLog(getNazwa() + " atakuje " + inny.getNazwa());
        if (inny.czyOdbilAtak(this)) {
            swiat.dodajLog(inny.getNazwa() + " odpiera atak " + this.getNazwa());
            return;
        }

        if (this.getSila() >= inny.getSila()) {
            swiat.dodajLog(getNazwa() + " zjada " + inny.getNazwa());
            inny.zabij();
            this.x = inny.getX();
            this.y = inny.getY();
        } else {
            swiat.dodajLog(inny.getNazwa() + " broni się");
            this.zabij();
        }
    }

    @Override
    public boolean czyZwierze() { return true; }
}