package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(int x, int y, Swiat s) { super(2, 1, x, y, s); }
    @Override
    public void akcja() {
        Random rand = new Random();
        if (rand.nextInt(100) >= 75) super.akcja();
    }
    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) { return atakujacy.getSila() < 5; }
    @Override
    public String getNazwa() { return "Żółw"; }
    @Override
    public char rysowanie() { return 'Z'; }
    @Override
    public Color getKolor() { return new Color(34, 139, 34); }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Zolw(nx, ny, swiat); }
}