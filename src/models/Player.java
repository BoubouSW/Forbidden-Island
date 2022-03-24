package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player {
    private Plateau plateau;
    private int identifier;
    private String name;
    private Case position;
    private Set<Objet> objets;
    // skin ??

    // Constructeur
    public Player(Plateau p, int identifier, String name, Case spawn){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = spawn;
        this.objets = new HashSet<Objet>();
    }

    public Player(Plateau p, int identifier, String name, int[] coord){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(coord[0], coord[1]);
        this.objets = new HashSet<Objet>();
    }

    public Player(Plateau p, int identifier, String name, int x, int y){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(x, y);
        this.objets = new HashSet<Objet>();
    }


    // Getters
    public int getIdentifier(){return this.identifier;}
    public String getName(){return this.name;}
    public Set<Objet> getInventory(){return this.objets;}
    public Case getCase(){return this.position;}

    public void moveCase(Case cas) {
        this.getCase().removePlayer(this);
        cas.addPlayer(this);
        this.position = cas;
    }

    public void moveDir(models.Case.Dir direction) {
        Case cas = this.getCase().adjacente(direction);
        if (cas.getEtat() != Case.Etat.SUBMERGEE)
            this.moveCase(cas);
    }
}