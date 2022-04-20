package models;

public class Ingenieur extends Player{
    /*
    L’Ingénieur peut assécher 2 tuiles pour 1 action.
     */

    public Ingenieur(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
