package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(int x, int y, Swiat s) { super(10, x, y, s); }
    @Override
    public void akcja() {
        super.akcja();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                Organizm obok = swiat.coZajmujePole(x + dx, y + dy);
                if (obok != null && obok.czyZwierze()) {
                    swiat.dodajLog("Barszcz Sosnowskiego zabija: " + obok.getNazwa());
                    obok.zabij();
                }
            }
        }
    }
    @Override
    public void kolizja(Organizm inny) {
        swiat.dodajLog(inny.getNazwa() + " zjada Barszcz i GINIE!");
        inny.zabij();
        this.zabij();
    }
    @Override
    public String getNazwa() { return "Barszcz Sosnowskiego"; }
    @Override
    public char rysowanie() { return 'b'; }
    @Override
    public Color getKolor() { return Color.CYAN; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new BarszczSosnowskiego(nx, ny, swiat); }
}