package gui;

import core.Swiat;
import core.Organizm;
import organizmy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OknoGry extends JFrame {
    private Swiat swiat;
    private JPanel panelPlanszy;
    private JTextArea obszarLogow;
    private JButton[][] polaSiatki;
    private JLabel labelStatusu;
    private JComboBox<String> listaOrganizmow;

    public OknoGry(Swiat swiat) {
        this.swiat = swiat;
        setTitle("Wirtualny Świat - Szymon Jankowski 208489");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelGorny = new JPanel(new GridLayout(2, 1));
        JLabel labelAutora = new JLabel("Autor: Szymon Jankowski, Indeks: 208489", SwingConstants.CENTER);
        labelAutora.setFont(new Font("Arial", Font.BOLD, 14));
        labelStatusu = new JLabel("Sterowanie: Strzałki lub WASD. U - Umiejętność.", SwingConstants.CENTER);
        panelGorny.add(labelAutora);
        panelGorny.add(labelStatusu);
        add(panelGorny, BorderLayout.NORTH);

        panelPlanszy = new JPanel();
        panelPlanszy.setLayout(new GridLayout(swiat.getWysokosc(), swiat.getSzerokosc()));
        polaSiatki = new JButton[swiat.getWysokosc()][swiat.getSzerokosc()];

        for (int y = 0; y < swiat.getWysokosc(); y++) {
            for (int x = 0; x < swiat.getSzerokosc(); x++) {
                JButton przycisk = new JButton();
                przycisk.setFont(new Font("Arial", Font.BOLD, 12));
                przycisk.setMargin(new Insets(0, 0, 0, 0));
                przycisk.setBackground(Color.WHITE);
                przycisk.setFocusable(false);
                final int fx = x, fy = y;
                przycisk.addActionListener(e -> obsluzKliecieWPole(fx, fy));
                polaSiatki[y][x] = przycisk;
                panelPlanszy.add(przycisk);
            }
        }
        add(panelPlanszy, BorderLayout.CENTER);

        obszarLogow = new JTextArea(20, 45);
        obszarLogow.setEditable(false);
        obszarLogow.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLogow = new JScrollPane(obszarLogow);
        add(scrollLogow, BorderLayout.EAST);

        JPanel panelDolny = new JPanel();
        JButton btnNowaTura = new JButton("Następna Tura");
        btnNowaTura.addActionListener(e -> wykonajKrok());

        JButton btnUmiejetnosc = new JButton("Użyj Całopalenia (U)");
        btnUmiejetnosc.addActionListener(e -> {
            swiat.setZazadanoUmiejetnosci(true);
            labelStatusu.setText("Zażądano użycia Całopalenia!");
            requestFocusInWindow();
        });

        // Lista rozwijana do ręcznego umieszczania organizmów na planszy
        String[] opcjeOrganizmow = {
            "-- wybierz organizm --",
            "Wilk", "Owca", "Lis", "Żółw", "Antylopa", "Człowiek",
            "Trawa", "Mlecz", "Guarana", "Wilcze Jagody", "Barszcz Sosnowskiego"
        };
        listaOrganizmow = new JComboBox<>(opcjeOrganizmow);
        listaOrganizmow.setFocusable(false);

        panelDolny.add(btnNowaTura);
        panelDolny.add(btnUmiejetnosc);
        panelDolny.add(new JLabel("Dodaj organizm:"));
        panelDolny.add(listaOrganizmow);
        add(panelDolny, BorderLayout.SOUTH);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean podanoRuch = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        swiat.setKierunekCzlowieka(0);
                        podanoRuch = true;
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        swiat.setKierunekCzlowieka(1);
                        podanoRuch = true;
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        swiat.setKierunekCzlowieka(2);
                        podanoRuch = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        swiat.setKierunekCzlowieka(3);
                        podanoRuch = true;
                        break;
                    case KeyEvent.VK_U:
                        swiat.setZazadanoUmiejetnosci(true);
                        labelStatusu.setText("Zażądano użycia Całopalenia!");
                        break;
                }
                if (podanoRuch) {
                    wykonajKrok();
                }
            }
        });

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        odswiezPlansze();
    }

    private void wykonajKrok() {
        if (swiat.czyKoniec()) {
            JOptionPane.showMessageDialog(this, "Symulacja została zakończona.");
            return;
        }
        swiat.wykonajTure();
        odswiezPlansze();
        requestFocusInWindow();
    }

    private void odswiezPlansze() {
        for (int y = 0; y < swiat.getWysokosc(); y++) {
            for (int x = 0; x < swiat.getSzerokosc(); x++) {
                polaSiatki[y][x].setBackground(Color.WHITE);
                polaSiatki[y][x].setText("");
            }
        }

        for (int y = 0; y < swiat.getWysokosc(); y++) {
            for (int x = 0; x < swiat.getSzerokosc(); x++) {
                Organizm org = swiat.coZajmujePole(x, y);
                if (org != null) {
                    polaSiatki[y][x].setBackground(org.getKolor());
                    polaSiatki[y][x].setText(String.valueOf(org.rysowanie()));
                }
            }
        }

        obszarLogow.setText("");
        for (String log : swiat.getLogi()) {
            obszarLogow.append(log + "\n");
        }

        for (Organizm o : swiat.getOrganizmy()) {
            if (o instanceof Czlowiek) {
                Czlowiek c = (Czlowiek) o;
                labelStatusu.setText("Człowiek żyje. Cooldown Całopalenia: " + c.getCooldown());
            }
        }

        if (swiat.getLogi().size() > 0) {
            swiat.getLogi().clear();
        }
    }

    private void obsluzKliecieWPole(int x, int y) {
        // Ignoruj kliknięcie jeśli nie wybrano żadnego organizmu
        int wybranyIndeks = listaOrganizmow.getSelectedIndex();
        if (wybranyIndeks == 0) return;

        // Zabezpieczenie: jeśli pole jest już zajęte, zablokuj możliwość postawienia.
        // Aby zamiast blokady nastąpiło zastąpienie organizmu, usuń poniższy blok if
        // i odkomentuj linię zabijającą istniejący organizm w sekcji poniżej.
        if (swiat.coZajmujePole(x, y) != null) {
            labelStatusu.setText("Pole (" + x + ", " + y + ") jest zajęte!");
            return;
        }

        // Gdyby zastąpienie było pożądane, zamiast blokady powyżej można użyć:
        // Organizm istniejacy = swiat.coZajmujePole(x, y);
        // if (istniejacy != null) istniejacy.zabij();

        Organizm nowyOrganizm = stworzOrganizm(wybranyIndeks, x, y);
        if (nowyOrganizm != null) {
            swiat.dodajOrganizm(nowyOrganizm);
            odswiezPlansze();
            requestFocusInWindow();
        }
    }

    private Organizm stworzOrganizm(int indeks, int x, int y) {
        switch (indeks) {
            case 1:  return new Wilk(x, y, swiat);
            case 2:  return new Owca(x, y, swiat);
            case 3:  return new Lis(x, y, swiat);
            case 4:  return new Zolw(x, y, swiat);
            case 5:  return new Antylopa(x, y, swiat);
            case 6:  return new Czlowiek(x, y, swiat);
            case 7:  return new Trawa(x, y, swiat);
            case 8:  return new Mlecz(x, y, swiat);
            case 9:  return new Guarana(x, y, swiat);
            case 10: return new WilczeJagody(x, y, swiat);
            case 11: return new BarszczSosnowskiego(x, y, swiat);
            default: return null;
        }
    }
}