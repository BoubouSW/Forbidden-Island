package models;
import controllers.BoutonValiderController;
import views.BoutonSelection;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Player {

    public enum ROLE {EXPLORATEUR,INGENIEUR,MESSAGER,NAVIGATEUR,PILOTE,PLONGEUR};

    private Plateau plateau;
    private int identifier;
    private String name;
    private Case position;
    //private Set<Clef> listeClef;
    protected static Set<Artefact> artefactRamasse;
    private Set<CarteTresor> carteTresors;
    private boolean alive = true;
    private ROLE role;
    private Image image;
    private boolean flightMode = false;
    private boolean dryMode = false;

    // Constructeur
    public Player(Plateau p, int identifier, String name, Case spawn){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = spawn;
        //this.listeClef = new HashSet<Clef>();
        this.carteTresors = new HashSet<CarteTresor>();
    }

    public Player(Plateau p, int identifier, String name, int[] coord){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(coord[0], coord[1]);
        //this.listeClef = new HashSet<Clef>();
        this.carteTresors = new HashSet<CarteTresor>();
    }

    public Player(Plateau p, int identifier, String name, int x, int y, ROLE r){
        this.plateau = p;
        this.identifier = identifier;
        this.name = name;
        this.position = this.plateau.getCase(x, y);
        //this.listeClef = new HashSet<Clef>();
        this.carteTresors = new HashSet<CarteTresor>();
        this.role = r;
        Image img = new ImageIcon("resources/images/cowboy2.png").getImage();
        switch (r) {
            case EXPLORATEUR: img = new ImageIcon("resources/images/explorateur2.png").getImage(); break;
            case PLONGEUR: img = new ImageIcon("resources/images/plongeur2.png").getImage(); break;
            case INGENIEUR: img = new ImageIcon("resources/images/ingenieur2.png").getImage(); break;
            case PILOTE: img = new ImageIcon("resources/images/pilote2.png").getImage(); break;
            case NAVIGATEUR: img = new ImageIcon("resources/images/navigateur2.png").getImage(); break;
            case MESSAGER: img = new ImageIcon("resources/images/messager2.png").getImage(); break;
        }
        if (this.getName().equals("fred"))
            img = new ImageIcon("resources/images/gruau2.png").getImage();
        this.image = img;
    }


    // Getters
    public int getIdentifier(){return this.identifier;}
    public String getName(){return this.name;}
    //public Set<Clef> getKeyInventory(){return this.listeClef;}
    public Case getCase(){return this.position;}
    public String inventory(){
        String str = "Inventaire de " + this.getName() + "\nArtefact (commun a tous) : ";
        for(Artefact a: Player.artefactRamasse){
            str += a.StringForInventory() + " ";
        }
        str += "\nClef (Propre a chaque joueur) : ";
        for(CarteTresor c: this.carteTresors){
            str += c.getValeurCarte().name() + " ";
        }
        str += "\n";
        return str;
    }
    public static Set<Artefact> getArtefact(){return artefactRamasse;}
    public boolean isAlive() {return this.alive;}
    public Set<CarteTresor> getCarteTresors() {return this.carteTresors;}
    public ROLE getRole() {return this.role;}
    public Image getImage() { return this.image;}
    public boolean isFlightMode() {return flightMode;}
    public boolean isDryMode() {return dryMode;}

    //Setters
    public static void setEmptyArtefactList(){artefactRamasse = new HashSet<Artefact>();}
    public void killPlayer() {this.alive = false;}
    public void setCarteTresorsSet(Set<CarteTresor> c){ this.carteTresors = c;}
    public void enableFlight() {this.flightMode = true;}
    public void disableFlight() {this.flightMode = false;}
    public void enableDry() {this.dryMode = true;}
    public void disableDry() {this.dryMode = false;}

    // methodes

    public void moveCase(Case cas) {
        this.getCase().removePlayer(this);
        cas.addPlayer(this);
        this.position = cas;
        // boolean b = this.ramasseRandomClef();
    }

    public boolean moveDir(models.Case.Dir direction) {
        Case cas = this.getCase().adjacente(direction);
        if (this.getRole() == ROLE.PLONGEUR || this.flightMode) {
            this.moveCase(cas);
            return true;
        }
        if (cas.getEtat() != Case.Etat.SUBMERGEE) {
            this.moveCase(cas);
            return true;
        }
        return false;
    }

    public void moveDirDry(Case.Dir direction) {
        Case cas = this.getCase().adjacente(direction);
        if (cas.getEtat() != Case.Etat.SUBMERGEE) {
            this.moveCase(cas);
        }
    }

    public boolean assecheCase() {
        Case cas = this.getCase();
        if(cas.getEtat() == Case.Etat.INONDEE && this.dryMode) {
            cas.set_normale();
            cas.getController().setBackground(new Color(74, 160, 44));
            return true;
        }
        return false;
    }

    public boolean hasSand() {
        for (CarteTresor c : this.carteTresors) {
            if (c.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE)
                return true;
        }
        return false;
    }

    public boolean hasHelico() {
        for (CarteTresor c : this.carteTresors) {
            if (c.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.HELICOPTERE)
                return true;
        }
        return false;
    }

    public boolean has4KeyOfElement(Objet.Element elem){
        CarteTresor.TYPE_CARTE_TRESOR typeCarte = CarteTresor.TYPE_CARTE_TRESOR.values()[elem.ordinal()];
        return this.nombreCarteElement(typeCarte) >= 4;
    }

    public Set<Player> choosePlayers(Set<Player> players){
        Set<Player> res = new HashSet<Player>();
        JFrame fenetre = new JFrame("");
        fenetre.setSize(200,(players.size())*50 + 30);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.PAGE_AXIS));
        //fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int size = players.size();
        BoutonSelection.isSelectionned = new boolean[size];
        int i = 0;
        Player[] pArray = new Player[size];
        for(Player p: players){
            System.out.println(p.getName());
            panel.add(new BoutonSelection(p.getName(), 80, 50, p.getIdentifier()));
        }
        boolean b = false;
        BoutonValiderController bvc = new BoutonValiderController("Valider",50, 20, 13);
        JPanel sub = new JPanel();
        sub.add(bvc);
        panel.add(sub);
        fenetre.add(panel);

        do{
            b = bvc.hasBeenClicked();
            System.out.print("");
        }while(!b);
/**
        synchronized (this) {
            while (!b) {
                try {
                    b = button.getModel().isPressed();
                    button.wait();
                } catch (Exception e) {
                    //System.out.println("Exception in wait " + e); // bizarre
                }
            }
        }**/

        fenetre.setVisible(false); //you can't see me!
        fenetre.dispose(); //Destroy the JFrame object
        for(Player p: players){
            if(BoutonSelection.isSelectionned[p.getIdentifier()]){
                res.add(p);
            }
        }

        System.out.println(res);
        return res;
    }

    public boolean has4KeyOfElement(CarteTresor.TYPE_CARTE_TRESOR type){
        return this.nombreCarteElement(type) >= 4;
    }

    public CarteTresor[] removed4CardsOfElement(Objet.Element elem){
        // suppose qu'il y a eu moins 4 cartes de l'element elem
        // renvoi un array de 4 cartes tresor du bon type et les enleve
        CarteTresor.TYPE_CARTE_TRESOR typeCarte = CarteTresor.TYPE_CARTE_TRESOR.values()[elem.ordinal()];
        CarteTresor[] res = new CarteTresor[4];
        int i = 0;
        for(CarteTresor c: this.carteTresors){
            if(c.getValeurCarte() == typeCarte && i < 4){
                res[i] = c;
                i++;
            }
        }
        for(CarteTresor c: res){
            this.carteTresors.remove(c);
        }
        return res;
    }

    public boolean ramasseArtefact() {
        Case cas = this.getCase();
        if(cas.hasArtefact() && this.has4KeyOfElement(cas.getArtefact().getElement())){
            Objet.Element elem = cas.getArtefact().getElement();
            CarteTresor.TYPE_CARTE_TRESOR typeCarte = CarteTresor.TYPE_CARTE_TRESOR.values()[elem.ordinal()];
            Player.artefactRamasse.add(cas.getArtefact());
            cas.removeArtefact();
            CarteTresor[] carteArray = this.removed4CardsOfElement(elem);
            this.plateau.getTheController().getView().allInventoryView.playerHasPickedArtefact(Player.getArtefact());
            for(CarteTresor c: carteArray){
                this.plateau.getPaquetCarteTresor().Defausse(c);
            }
            return true;
        }
        return false;
    }

    public int nombreCarteElement(CarteTresor.TYPE_CARTE_TRESOR t){
        if(t.ordinal() > 3)
            throw new RuntimeException("Pas une carte artefact");
        int res = 0;
        for(CarteTresor c : this.carteTresors){
            if(c.getValeurCarte() == t)
                res++;
        }
        return res;
    }

    public void useSand() {
        CarteTresor carte = null;
        for(CarteTresor c:this.carteTresors) {
            if (c.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE) {
                carte = c;
            }

        }
        this.plateau.getPaquetCarteTresor().Defausse(carte);
        this.removeCarteTresor(carte);
    }

    public void useHelico() {
        CarteTresor carte = null;
        for(CarteTresor c:this.carteTresors) {
            if (c.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.HELICOPTERE) {
                carte = c;
            }

        }
        this.plateau.getPaquetCarteTresor().Defausse(carte);
        this.removeCarteTresor(carte);
    }

    /** FONCTIONS QUI MARCHAIENT AVEC LIMPLEMENTATION AVEC LE TYPE CLEF ET NON LES CARTES
    public void ramasseClefDepuisCarte(CarteTresor c){
        switch (c.getValeurCarte()){
            case ARTEF_EAU :
                this.listeClef.add(new Clef(Objet.Element.EAU, this, null)); break;
            case ARTEF_TERRE:
                this.listeClef.add(new Clef(Objet.Element.TERRE, this, null)); break;
            case ARTEF_FEU:
                this.listeClef.add(new Clef(Objet.Element.FEU, this, null)); break;
            case ARTEF_VENT:
                this.listeClef.add(new Clef(Objet.Element.VENT, this, null)); break;
        }
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
        return true;
    }
    **/
    public static boolean hasAllArtefact(){
        return Player.artefactRamasse.size() == 4;
    }

    public void addCarteTresor(CarteTresor c){ this.carteTresors.add(c); }

    public void removeCarteTresor(CarteTresor c){ this.carteTresors.remove(c); }

    public int numberOfCards() { return this.carteTresors.size(); }
}