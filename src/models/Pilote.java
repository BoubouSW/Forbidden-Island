package models;

public class Pilote extends Player{
    /*
    Le Pilote peut aller une fois par tour sur n’importe quelle tuile pour 1 action.
     */

    public Pilote(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
