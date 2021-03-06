package models;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Plateau {
    // attrib
    final private int taille;

    private Case[][] plateau;
    private Set<Player> players;
    private models.Controllers theController;
    private PaquetCarte<CarteTresor> paquetCarteTresor;
    private PaquetCarte<CarteInnonde> paquetCarteInnonde;
    private models.PlateauView plateauView;
    private int nbCaseNonSubmergee;
    private int[] coordHeliport;
    private Artefact[] artefacts;

    // Constructeur
    public Plateau(int taille) {
        this.paquetCarteTresor = new PaquetCarte<CarteTresor>();
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        this.players = new HashSet<Player>();
        this.nbCaseNonSubmergee = 24;
        this.theController = null;
        int xHeli, yHeli;
        do{
            xHeli = 1 + (int) (Math.random() * (this.getTaille() - 2));
            yHeli = 1 + (int) (Math.random() * (this.getTaille() - 2));
        }while(Math.abs(xHeli - (taille - 1) / 2.) + Math.abs(yHeli - (taille - 1) / 2.) >= taille / 2.);
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                if(xHeli == i && yHeli == j){
                    Heliport hel = new Heliport(this, i, j);
                    plateau[i][j] = hel;
                    int[] c = {i, j};
                    this.coordHeliport = c;
                }else {
                    Case cas = new Case(this, i, j);
                    if (Math.abs(i - (taille - 1) / 2.) + Math.abs(j - (taille - 1) / 2.) >= taille / 2.)
                        cas.set_submergee();
                    plateau[i][j] = cas;
                }
            }
        }
    }

    // getters
    public Case getCase(int i, int j) {
        return plateau[i][j];
    }
    public int getTaille() { return this.taille; }
    public Set<Player> getPlayersPlateau() { return this.players; }
    public Player getPlayerById(int i){
        for(Player p: this.players){
            if(p.getIdentifier() == i)
                return p;
        }
        throw new RuntimeException("Identifiant non valide");
    }
    public int getNbCaseNonSubmergee(){return this.nbCaseNonSubmergee;}
    public int getXHeliport(){
        return this.coordHeliport[0];
    }
    public int getYHeliport(){
        return this.coordHeliport[1];
    }
    public Case getHeliport(){
        return this.getCase(coordHeliport[0], coordHeliport[1]);
    }
    public PaquetCarte<CarteTresor> getPaquetCarteTresor(){ return this.paquetCarteTresor; }
    public PaquetCarte<CarteInnonde> getPaquetCarteInnonde() {return paquetCarteInnonde;}
    public models.PlateauView getPlateauView(){return this.plateauView;}
    public models.Controllers getTheController() { return theController; }
    public Artefact[] getArtefacts() {return artefacts;}

    //setters
    public void setPaquetCarteTresor(PaquetCarte<CarteTresor> p){
        this.paquetCarteTresor = p;
    }
    public void setPaquetCarteInnonde(PaquetCarte<CarteInnonde> p){
        this.paquetCarteInnonde = p;
    }
    public void setTheController(models.Controllers c){ this.theController = c;}
    public void setArtefacts(Artefact[] art){ this.artefacts = art;}

    // methodes
    public void addPlayerPlateau(int id,String name, int x, int y, Player.ROLE r) {
        Player p1 = new Player(this,id,name,x,y,r);
        this.players.add(p1);
        p1.getCase().addPlayer(p1);
    }

    // renvoie une case seche ou inondee au hasard
    public Case randomSecheOuInonde(){
        int x, y;
        do {
            x = 1 + (int) (Math.random() * (this.getTaille() - 2));
            y = 1 + (int) (Math.random() * (this.getTaille() - 2));
        }while(this.getCase(x,y).getEtat() == Case.Etat.SUBMERGEE);
        return this.getCase(x, y);
    }

    public Case tireSecheouInonde(){
        CarteInnonde carte;
        PaquetCarte<CarteInnonde> paquet = this.getPaquetCarteInnonde();
        if (paquet.pileVide()) {
            paquet.melangeDefausse();
            paquet.retourneDefausse();
        }
        carte = this.getPaquetCarteInnonde().pioche();
        int[] coord = carte.getCoord();
        this.getPaquetCarteInnonde().Defausse(carte);
        //this.getPaquetCarteInnonde().retourneDefausse();
        Case c = this.getCase(coord[0], coord[1]);
        if (c.getEtat() == Case.Etat.INONDEE)
            paquet.supprimeDefausse(carte);
        return c;
    }

    int[] randomIndondeSubmerge() {
        // renvoi les coordonnees de la case inonde
        Case c = randomSecheOuInonde();
        int x = c.getX();
        int y = c.getY();
        // attention si plus aucune case non SUBMERGEE boucle infini,
        //on ne sera pas confronte a ce pb car on aura perdu avant
        Case current = this.getCase(x, y);
        if(current.getEtat() == Case.Etat.NORMALE)
            current.set_inondee();
        else if(current.getEtat() == Case.Etat.INONDEE) {
            current.set_submergee();
            this.nbCaseNonSubmergee--;
        }
        int[] res = {x, y};
        return res;
    }

    void InondeOuSubmerge(Case c) {
        if(c.getEtat() == Case.Etat.NORMALE)
            c.set_inondee();
        else if(c.getEtat() == Case.Etat.INONDEE) {
            c.set_submergee();
            this.nbCaseNonSubmergee--;
        }
    }

    public int[] randomSpawn() {
        Case c = randomSecheOuInonde();
        return c.getCoord();
    }

    public boolean allPlayersOnHeliport(){
        return this.getCase(this.getXHeliport(), this.getYHeliport()).getPlayers().size() == this.getPlayersPlateau().size();
    }
}