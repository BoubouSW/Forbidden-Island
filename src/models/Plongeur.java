package models;

public class Plongeur extends Player{
    /*
    Le Plongeur peut se déplacer au travers d’une ou plusieurs tuiles adjacentes
    manquantes et/ou inondées pour 1 action (pas forcément en ligne droite).
     */

    public Plongeur(Plateau p, int identifier, String name, int x, int y) {
        super(p,identifier,name,x,y);
    }
}
