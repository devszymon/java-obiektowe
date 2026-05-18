package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class Guarana extends Roslina {
    public Guarana(int x, int y, Swiat s) { super(0, x, y, s); }
    @Override
    public void kolizja(Organizm inny) {
        inny.setSila(inny.getSila() + 3);
        swiat.dodajLog(inny.getNazwa() + " zjadł guaranę. Siła wzrosła o 3.");
        this.zabij();
    }
    @Override
    public String getNazwa() { return "Guarana"; }
    @Override
    public char rysowanie() { return 'g'; }
    @Override
    public Color getKolor() { return Color.RED; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Guarana(nx, ny, swiat); }
}