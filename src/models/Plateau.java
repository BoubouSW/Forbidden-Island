package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Plateau {

    final private int taille;

    private Case[][] plateau;
    private Set<Player> players;

    public Case getCase(int i, int j) { return plateau[i][j]; }
    public int getTaille() { return this.taille; }

    // Constructeur
    public Plateau(int taille) {
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        this.players = new HashSet<Player>();
        Player p1 = new Player(this, 0, "Clement", taille/2, taille/2);
        this.players.add(p1);
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                Case cas = new Case(this, i, j);
                if ((i == 0) || (i==7) || (j == 0) || (j == 7)
                        || ((i == 1) && ((j == 1) || (j == 2) || (j == 5) || (j == 6)))
                        || ((i == 6) && ((j == 1) || (j == 2) || (j == 5) || (j == 6)))
                        || ((j == 1) && ((i == 2) || (i == 5)))
                        || ((j == 6) && ((i == 2) || (i == 5))))
                    cas.set_submergee();
                plateau[i][j] = cas;
            }
        }
    }
}