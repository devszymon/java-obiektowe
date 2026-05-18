package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Wilk extends Zwierze {
    public Wilk(int x, int y, Swiat s) { super(9, 5, x, y, s); }
    @Override
    public String getNazwa() { return "Wilk"; }
    @Override
    public char rysowanie() { return 'W'; }
    @Override
    public Color getKolor() { return Color.DARK_GRAY; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Wilk(nx, ny, swiat); }
}