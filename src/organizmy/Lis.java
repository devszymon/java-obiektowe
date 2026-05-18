package organizmy;

import core.Organizm;
import core.Swiat;
import java.awt.Color;
import java.util.Random;

public class Lis extends Zwierze {
    public Lis(int x, int y, Swiat s) { super(3, 7, x, y, s); }
    @Override
    public void akcja() {
        Random rand = new Random();
        int kierunek = rand.nextInt(4);
        int nowy_x = x, nowy_y = y;
        if (kierunek == 0 && y > 0) nowy_y--;
        else if (kierunek == 1 && y < swiat.getWysokosc() - 1) nowy_y++;
        else if (kierunek == 2 && x > 0) nowy_x--;
        else if (kierunek == 3 && x < swiat.getSzerokosc() - 1) nowy_x++;

        Organizm inne = swiat.coZajmujePole(nowy_x, nowy_y);
        if (inne == null || inne.getSila() <= this.getSila()) {
            if (inne != null) {
                kolizja(inne);
            } else {
                x = nowy_x; y = nowy_y;
            }
        } else {
            swiat.dodajLog("Lis wyczuł niebezpieczeństwo i zostaje w miejscu.");
        }
    }
    @Override
    public String getNazwa() { return "Lis"; }
    @Override
    public char rysowanie() { return 'L'; }
    @Override
    public Color getKolor() { return Color.ORANGE; }
    @Override
    public Organizm stworzPotomka(int nx, int ny) { return new Lis(nx, ny, swiat); }
}