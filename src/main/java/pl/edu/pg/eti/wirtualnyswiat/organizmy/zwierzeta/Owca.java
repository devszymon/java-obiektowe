package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Owca extends Zwierze {
    public Owca(int x, int y, Swiat s) { super(4, 4, x, y, s); }
    @Override
    public String getNazwa() { return "Owca"; }
    @Override
    public char rysowanie() { return 'O'; }
    @Override
    public Color getKolor() { return Color.LIGHT_GRAY; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Owca(nx, ny, swiat); }
}