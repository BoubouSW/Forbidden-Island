package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.lang.RuntimeException;

class Case {

    public enum Etat {NORMALE,INONDEE,SUBMERGEE}
    public enum Dir {HAUT, BAS, GAUCHE, DROITE,NW,NE,SE,SW};

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
    public Plateau getPlateau(){return this.plateau;}

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
        throw new RuntimeException("Identifiant non valide");
    }

    public boolean hasPlayer() { return ! this.players.isEmpty();}

    public boolean hasArtefact() {
        for(Objet o: objets){
            if(o.getClass() == Artefact.class)
                return true;
        }
        return false;
    }

    public Artefact getArtefact() {
        // suppose qu'il y a un artefact
        for(Objet o: objets){
            if(o.getClass() == Artefact.class)
                return (Artefact) o;
        }
        throw new RuntimeException("Pas d'artefact");
    }

    public void removeArtefact() {
        // suppose qu'il y a un artefact
        objets.remove(this.getArtefact());
    }

    public boolean hasKey(){
        for(Objet o: objets){
            if(o.getClass() == Clef.class)
                return true;
        }
        return false;
    }

    public Clef getRandomKey() {
        // suppose qu'il y a une clef
        // renvoi une clef aleatoire de la case
        for(Objet o: objets){
            if(o.getClass() == Clef.class)
                return (Clef) o;
        }
        throw new RuntimeException("Pas d'artefact");
    }

    public void removeKey(Clef clef) {
        if(this.objets.contains(clef))
            this.objets.remove(clef);
    }

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
            case NW:
                return this.plateau.getCase(x-1,y-1);
            case NE:
                return this.plateau.getCase(x-1,y+1);
            case SE:
                return this.plateau.getCase(x+1,y+1);
            case SW:
                return this.plateau.getCase(x+1,y-1);
        }
        throw new RuntimeException("On ne devrait pas arriver ici");
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void removePlayer(Player p) { this.players.remove(p); }

    public void addObject(Objet objet){ this.objets.add(objet); }

    public boolean isAdjacenteOrEqual(Case other, boolean diag){
        // return true if and only if other has the same
        // coordonate than this, or if this is adjacente
        int x = this.getX();
        int y = this.getY();
        int xOther = other.getX();
        int yOther = other.getY();
        if(xOther == x)
            return yOther == y || yOther == y-1 || yOther == y+1;
        if(yOther == y)
            return xOther == x-1 || xOther == x+1;
        if(diag){
            if(xOther == x + 1 || xOther == x - 1)
                return yOther == y+1 || yOther == y-1;
            if(yOther == y + 1 || yOther == y - 1)
                return xOther == x+1 || xOther == x-1;
        }
        return false;
    }
};