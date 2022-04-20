package models;

public class Messager extends Player{
    /*
    Le Messager peut donner des cartes Trésor sans être sur la même tuile.
     */

    public Messager(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
