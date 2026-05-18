package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Trawa extends Roslina {
    public Trawa(int x, int y, Swiat s) { super(0, x, y, s); }
    @Override
    public String getNazwa() { return "Trawa"; }
    @Override
    public char rysowanie() { return 't'; }
    @Override
    public Color getKolor() { return Color.GREEN; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Trawa(nx, ny, swiat); }
}