package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;

public class WilczeJagody extends Roslina {
    public WilczeJagody(int x, int y, Swiat s) { super(99, x, y, s); }
    @Override
    public void kolizja(Organizm inny) {
        swiat.dodajLog(inny.getNazwa() + " zjadł wilcze jagody i umarł.");
        inny.zabij();
        this.zabij();
    }
    @Override
    public String getNazwa() { return "Wilcze Jagody"; }
    @Override
    public char rysowanie() { return 'j'; }
    @Override
    public Color getKolor() { return new Color(128, 0, 128); }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new WilczeJagody(nx, ny, swiat); }
}