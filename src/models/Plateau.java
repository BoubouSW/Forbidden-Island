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
    public Set<Player> getPlayersPlateau() { return this.players; }

    // Constructeur
    public Plateau(int taille) {
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        this.players = new HashSet<Player>();
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                Case cas = new Case(this, i, j);
                if (Math.abs(i - (taille - 1) / 2.) + Math.abs(j - (taille - 1) / 2.) >= taille / 2.)
                    cas.set_submergee();
                plateau[i][j] = cas;
            }
        }
    }

    public void addPlayerPlateau(int id,String name, int x, int y) {
        Player p1 = new Player(this,id,name,x,y);
        this.players.add(p1);
        p1.getCase().addPlayer(p1);
    }

    int[] randomIndondeSubmerge() {
        // renvoi les coordonnees de la case inonde
        int x, y;
        do {
            x = 1 + (int) (Math.random() * (this.getTaille() - 2));
            y = 1 + (int) (Math.random() * (this.getTaille() - 2));
        }while(this.getCase(x,y).getEtat() == Case.Etat.SUBMERGEE);
        // attention si plus aucune case non SUBMERGEE boucle infini,
        //on ne sera pas confronte a ce pb car on aura perdu avant
        Case current = this.getCase(x, y);
        if(current.getEtat() == Case.Etat.NORMALE)
            current.set_inondee();
        else if(current.getEtat() == Case.Etat.INONDEE)
            current.set_submergee();
        int[] res = {x, y};
        return res;
    }

    public int[] randomSpawn() {
        int x, y;
        do {
            x = 1 + (int) (Math.random() * (this.getTaille() - 2));
            y = 1 + (int) (Math.random() * (this.getTaille() - 2));
        }while(this.getCase(x,y).getEtat() == Case.Etat.SUBMERGEE);
        int[] res = {x, y};
        return res;
    }
}