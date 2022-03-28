package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.lang.RuntimeException;

class Case {

    public enum Etat {NORMALE,INONDEE,SUBMERGEE}
    public enum Dir {HAUT, BAS, GAUCHE, DROITE};

    // Attributs
    private Plateau plateau;
    private Etat etat;
    private int x, y;
    private Set<Player> players;
    private Set<Objet> objets;
    private models.CaseController controller;

    // Constructeur
    public Case(Plateau p, int x, int y) {
        this.plateau = p;
        this.etat = Etat.NORMALE;
        this.x = x;
        this.y = y;
        this.players = new HashSet<Player>();
        this.objets = new HashSet<Objet>();
        // invariant : au plus un artefact par case
    }

    // getters
    public Etat getEtat() { return this.etat;}
    public boolean isSubmergee(){return this.etat == Etat.SUBMERGEE;}
    public models.CaseController getController(){return this.controller;}

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public int[] getCoord(){
        int[] res = {this.getX(), this.getY()};
        return res;
    }
    public Set<Player> getPlayers() { return this.players;}
    public Set<Objet> getObjets() {return this.objets;}

    public Player getPlayerById(int id) {
        for (Player p : this.players) {
            if (p.getIdentifier() == id)
                return p;
        }
        if(true)
            throw new RuntimeException("Identifiant non valide");
        return null; //faire exception
    }

    public boolean hasPlayer() { return ! this.players.isEmpty();}

    /**
    public boolean hasArtefact() {
        for(Objet o: objets){
            if(o.getClass() == Artefact)

        }
    }
     **/

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

    public void set_controller(models.CaseController c) {this.controller = c;}

    public Case adjacente(Dir d){
        int x = this.getX();
        int y = this.getY();
        switch(d) {
            case GAUCHE:
                return this.plateau.getCase(x, y - 1);
            case DROITE:
                return this.plateau.getCase(x, y + 1);
            case HAUT:
                return this.plateau.getCase(x - 1, y);
            case BAS:
                return this.plateau.getCase(x + 1, y);
        }
        if(true)
            throw new RuntimeException("On ne devrait pas arriver ici");
        return null; // impossible d'arriver ici
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void removePlayer(Player p) { this.players.remove(p); }

    public void addObject(Objet objet){ this.objets.add(objet);}

};