package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Case {

    public enum Etat {NORMALE,INONDEE,SUBMERGEE}

    // Attributs
    private Plateau plateau;
    private Etat etat;
    private int x, y;
    private Set<Player> players;

    // Constructeur
    public Case(Plateau p, int x, int y) {
        this.plateau = p;
        this.etat = Etat.NORMALE;
        this.x = x;
        this.y = y;
        this.players = new HashSet<Player>();
    }

    // getters
    public Etat getEtat() { return this.etat;}

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public int[] getCoord(){
        int[] res = {this.getX(), this.getY()};
        return res;
    }
    public Set<Player> getPlayers() { return this.players;}
    public boolean hasPlayer() { return ! this.players.isEmpty();}

    // setters
    public void set_normale() {
        etat = Etat.NORMALE;
    }

    public void set_inondee() {
        etat = Etat.INONDEE;
    }

    public void set_submergee() {
        etat = Etat.SUBMERGEE;
    }

    public Case adjacente(Dir d){
        int x = this.getX();
        int y = this.getY();
        switch(d) {
            case HAUT:
                return this.plateau.getCase(x, y - 1);
            case BAS:
                return this.plateau.getCase(x, y + 1);
            case GAUCHE:
                return this.plateau.getCase(x - 1, y);
            case DROITE:
                return this.plateau.getCase(x + 1, y);
        }
        return null;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

};

enum Dir {HAUT, BAS, GAUCHE, DROITE};