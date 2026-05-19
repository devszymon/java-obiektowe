package core;

import java.awt.Color;
import java.io.Serializable;

public abstract class Organizm implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int sila;
    protected int inicjatywa;
    protected int x, y;
    protected int wiek;
    protected boolean zywy;
    protected Swiat swiat;

    public Organizm(int sila, int inicjatywa, int x, int y, Swiat swiat) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = x;
        this.y = y;
        this.swiat = swiat;
        this.wiek = 0;
        this.zywy = true;
    }

    public abstract void akcja();
    public abstract void kolizja(Organizm inny);
    public abstract String getNazwa();
    public abstract char rysowanie();
    public abstract Color getKolor();
    public abstract boolean czyZwierze();
    public abstract Organizm stworzPotomka(int nx, int ny);

    public int getSila() { return sila; }
    public void setSila(int s) { sila = s; }
    public int getInicjatywa() { return inicjatywa; }
    public int getWiek() { return wiek; }
    public void zwiekszWiek() { wiek++; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int nx) { x = nx; }
    public void setY(int ny) { y = ny; }
    public boolean czyZywy() { return zywy; }
    public void zabij() { zywy = false; }

    public boolean czyOdbilAtak(Organizm atakujacy) { return false; }
    public boolean czyUcieka() { return false; }
}
