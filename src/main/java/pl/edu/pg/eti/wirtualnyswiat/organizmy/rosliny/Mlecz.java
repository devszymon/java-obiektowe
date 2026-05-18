package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Mlecz extends Roslina {
    public Mlecz(int x, int y, Swiat s) { super(0, x, y, s); }
    @Override
    public void akcja() {
        for (int i = 0; i < 3; i++) super.akcja();
    }
    @Override
    public String getNazwa() { return "Mlecz"; }
    @Override
    public char rysowanie() { return 'm'; }
    @Override
    public Color getKolor() { return Color.YELLOW; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Mlecz(nx, ny, swiat); }
}