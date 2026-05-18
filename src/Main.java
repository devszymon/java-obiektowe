import core.Swiat;
import gui.OknoGry;
import organizmy.Antylopa;
import organizmy.BarszczSosnowskiego;
import organizmy.Czlowiek;
import organizmy.Guarana;
import organizmy.Lis;
import organizmy.Mlecz;
import organizmy.Owca;
import organizmy.Trawa;
import organizmy.WilczeJagody;
import organizmy.Wilk;
import organizmy.Zolw;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Swiat swiat = new Swiat(20, 20);

        swiat.dodajOrganizm(new Czlowiek(10, 10, swiat));
        swiat.dodajOrganizm(new Wilk(3, 3, swiat));
        swiat.dodajOrganizm(new Wilk(15, 14, swiat));
        swiat.dodajOrganizm(new Owca(4, 4, swiat));
        swiat.dodajOrganizm(new Owca(6, 5, swiat));
        swiat.dodajOrganizm(new Lis(8, 2, swiat));
        swiat.dodajOrganizm(new Lis(14, 7, swiat));
        swiat.dodajOrganizm(new Zolw(2, 15, swiat));
        swiat.dodajOrganizm(new Zolw(11, 17, swiat));
        swiat.dodajOrganizm(new Antylopa(16, 3, swiat));
        swiat.dodajOrganizm(new Antylopa(5, 13, swiat));
        swiat.dodajOrganizm(new Trawa(1, 1, swiat));
        swiat.dodajOrganizm(new Trawa(18, 18, swiat));
        swiat.dodajOrganizm(new Mlecz(7, 9, swiat));
        swiat.dodajOrganizm(new Mlecz(12, 11, swiat));
        swiat.dodajOrganizm(new Guarana(3, 12, swiat));
        swiat.dodajOrganizm(new Guarana(17, 6, swiat));
        swiat.dodajOrganizm(new WilczeJagody(9, 15, swiat));
        swiat.dodajOrganizm(new WilczeJagody(13, 4, swiat));
        swiat.dodajOrganizm(new BarszczSosnowskiego(6, 16, swiat));
        swiat.dodajOrganizm(new BarszczSosnowskiego(15, 9, swiat));

        SwingUtilities.invokeLater(() -> new OknoGry(swiat));
    }
}
