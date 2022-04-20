package models;

public class Navigateur extends Player{
    /*
    Le Navigateur peut déplacer d’autres joueurs d’1 ou 2 cases adjacentes par action.
     */

    public Navigateur(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
