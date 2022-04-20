package models;

public class Explorateur extends Player{
    /*
    L’Explorateur peut se déplacer et assécher diagonalement.
     */

    public Explorateur(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
