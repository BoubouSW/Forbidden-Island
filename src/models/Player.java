package models;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player {
    private Plateau plateau;
    private int identifier;
    private String name;
    private Case position;
    private Set<Clef> listeClef;
    protected static Set<Artefact> artefactRamasse;
    // skin ??

    // Constructeur
    public Player(Plateau p, int identifier, String name, Case spawn){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = spawn;
        this.listeClef = new HashSet<Clef>();
    }

    public Player(Plateau p, int identifier, String name, int[] coord){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(coord[0], coord[1]);
        this.listeClef = new HashSet<Clef>();
    }

    public Player(Plateau p, int identifier, String name, int x, int y){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(x, y);
        this.listeClef = new HashSet<Clef>();
    }


    // Getters
    public int getIdentifier(){return this.identifier;}
    public String getName(){return this.name;}
    public Set<Clef> getKeyInventory(){return this.listeClef;}
    public Case getCase(){return this.position;}
    public String inventory(){
        String str = "Inventaire de " + this.getName() + "\nArtefact (commun a tous) : ";
        for(Artefact a: Player.artefactRamasse){
            str += a.StringForInventory() + " ";
        }
        str += "\nClef (Propre a chaque joueur) : ";
        for(Clef k: this.listeClef){
            str += k.StringForInventory() + " ";
        }
        str += "\n";
        return str;
    }
    public static Set<Artefact> getArtefact(){return artefactRamasse;}

    //Setters
    public static void setEmptyArtefactList(){artefactRamasse = new HashSet<Artefact>();}

    // methodes

    public void moveCase(Case cas) {
        this.getCase().removePlayer(this);
        cas.addPlayer(this);
        this.position = cas;
        boolean b = this.ramasseRandomClef();
    }

    public boolean moveDir(models.Case.Dir direction) {
        Case cas = this.getCase().adjacente(direction);
        if (cas.getEtat() != Case.Etat.SUBMERGEE) {
            this.moveCase(cas);
            return true;
        }
        return false;
    }

    public boolean assecheCase() {
        Case cas = this.getCase();
        if(cas.getEtat() == Case.Etat.INONDEE) {
            cas.set_normale();
            cas.getController().setBackground(new Color(74, 160, 44));
            return true;
        }
        return false;
    }

    public boolean hasKeyOfElement(Objet.Element e){
        for(Clef k: this.listeClef){
            if(k.getElement() == e)
                return true;
        }
        return false;
    }

    public boolean ramasseArtefact() {
        Case cas = this.getCase();
        if(cas.hasArtefact() && this.hasKeyOfElement(cas.getArtefact().getElement())){
            Player.artefactRamasse.add(cas.getArtefact());
            cas.removeArtefact();
            return true;
        }
        return false;
    }

    public boolean ramasseRandomClef() {
        // le player ramasse une clef aleatoire se trouvant sur sa case
        // renvoi vrai s il a ramasse une clef faux sinon
        if(Math.random() < 0.5){
            return false;
        }
        double a = Math.random();
        if(a < 0.25){
            this.listeClef.add(new Clef(Objet.Element.EAU, this, null));
        }else if(a < 0.5){
            this.listeClef.add(new Clef(Objet.Element.TERRE, this, null));
        }else if(a < 0.75){
            this.listeClef.add(new Clef(Objet.Element.FEU, this, null));
        }else{
            this.listeClef.add(new Clef(Objet.Element.VENT, this, null));
        }
        /**
        Case cas = this.getCase();
        if(cas.hasKey()){
            Clef k = cas.getRandomKey();
            cas.removeKey(k);
            this.listeClef.add(k);
            return true;
        }**/
        return true;
    }

    public static boolean hasAllArtefact(){
        return Player.artefactRamasse.size() == 4;
    }
}