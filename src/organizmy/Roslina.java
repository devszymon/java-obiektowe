package organizmy;

import core.Organizm;
import core.Swiat;
import java.util.Random;

public abstract class Roslina extends Organizm {
    public Roslina(int sila, int x, int y, Swiat swiat) {
        super(sila, 0, x, y, swiat);
    }

    @Override
    public void akcja() {
        Random rand = new Random();
        if (rand.nextInt(100) < 10) {
            int nx = x, ny = y;
            int kierunek = rand.nextInt(4);
            if (kierunek == 0 && y > 0) ny--;
            else if (kierunek == 1 && y < swiat.getWysokosc() - 1) ny++;
            else if (kierunek == 2 && x > 0) nx--;
            else if (kierunek == 3 && x < swiat.getSzerokosc() - 1) nx++;

            if (swiat.coZajmujePole(nx, ny) == null) {
                swiat.dodajLog("Wyrasta nowa roślina: " + getNazwa());
                swiat.dodajNowyOrganizm(stworzPotomka(nx, ny));
            }
        }
    }

    @Override
    public void kolizja(Organizm inny) {}

    @Override
    public boolean czyZwierze() { return false; }
}