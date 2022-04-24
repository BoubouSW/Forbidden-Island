package models;
import views.EncadreInventaireView;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
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

    public void moveDirDry(Case.Dir direction, int xRef, int yRef, boolean sand) {
        Case ref = this.plateau.getCase(xRef, yRef);
        Case cas = this.getCase().adjacente(direction);
        boolean diag = (this.role == ROLE.EXPLORATEUR);
        if (cas.getEtat() != Case.Etat.SUBMERGEE && sand)
            this.moveCase(cas);
        else {
            if (cas.getEtat() != Case.Etat.SUBMERGEE && ref.isAdjacenteOrEqual(cas, diag)) {
                this.moveCase(cas);
            }
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

    public Player choosePlayer(Set<Player> players, boolean fenetreConfirmation, String msg){
        // choisi une carte parmi son inventaire
        int nbjoueur = players.size();
        Player[] id = new Player[nbjoueur];
        String[] options2 = new String[nbjoueur];
        int k = 0;
        for (Player p: players) {
            id[k] = p;
            options2[k] = id[k].getName();
            k++;
        }

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel(msg));

        int result = JOptionPane.showOptionDialog(null, panel2, "",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(this.image),
                options2, null);
        if (result >= 0 && fenetreConfirmation){
            //il faut changer ici fqojgqjdfhpj
            JOptionPane.showMessageDialog(null,"Vous avez choisi le joueur " + options2[result]);
        }
        if (result < 0)
            return this;
        return id[result];
    }

    public int chooseNumberPlayer(int nb, boolean fenetreConfirmation, String msg){
        // choisi une carte parmi son inventaire
        String[] options2 = new String[nb];
        for (int k = 0; k < nb; k++) {
            options2[k] = String.valueOf(k);
        }

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel(msg));

        int result = JOptionPane.showOptionDialog(null, panel2, "",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(this.image),
                options2, null);
        if (result >= 0 && fenetreConfirmation){
            //il faut changer ici fqojgqjdfhpj
            JOptionPane.showMessageDialog(null,"Vous avez choisi le joueur " + options2[result]);
        }
        if (result < 0)
            return 0;
        return result;
    }

    public Player choosePlayerOnMyCaseWithLessThan4Cards(boolean fenetreConfirmation){
        Set<Player> set = new HashSet<Player>();
        for(Player p: this.getCase().getPlayers()){
            if(p != this && p.getCarteTresors().size() < 4)
                set.add(p);
        }
        if(set.size() == 0)
            return this;
        return choosePlayer(set, fenetreConfirmation,"Choisissez un joueur pour l'echange :");
    }

    public Player choosePlayerOnMyCase(Set<Player> players, boolean fenetreConfirmation){
        Set<Player> set = new HashSet<Player>();
        for(Player p: this.getCase().getPlayers()){
            if(p != this && players.contains(p))
                set.add(p);
        }
        //if(set.size() == 0)
        //    return this;
        return choosePlayer(set, fenetreConfirmation,"Choisissez un joueur à embarquer :");
    }

    public Set<Player> chooseHelico(Set<Player> other) {
        Set<Player> res = new HashSet<Player>();
        int players = this.getCase().getPlayers().size();
        if (players == 1) {
            res.add(this);
            return res;
        }
        int nb = this.chooseNumberPlayer(players,false,"Choisissez le nombre de joueur :");
        if (nb == this.plateau.getPlayersPlateau().size() - 1)
            return this.plateau.getPlayersPlateau();
        if (nb == this.getCase().getPlayers().size() - 1)
            return this.getCase().getPlayers();
        Player p;
        for (int i = 0; i < nb; i++) {
            p = this.choosePlayerOnMyCase(other,true);
            res.add(p);
            other.remove(p);
        }
        res.add(this);
        return res;
    }

    public Player choosePlayerAllPlateau(boolean fenetreConfirmation){
        Set<Player> set = new HashSet<Player>();
        for(Player p: this.getCase().getPlateau().getPlayersPlateau()){
            if(p != this && p.getCarteTresors().size() < 4)
                set.add(p);
        }
        if(set.size() == 0)
            return this;
        return choosePlayer(set, fenetreConfirmation,"Choisissez un joueur pour l'echange :");
    }

    public CarteTresor chooseCarte(String str, boolean fenetreConfirmation){
        // choisi une carte parmi son inventaire
        Set<CarteTresor> cartesTresor = new HashSet<CarteTresor>();
        for(CarteTresor c: this.carteTresors){
            if(c.getValeurCarte().ordinal() <= 4)
                cartesTresor.add(c);
        }

        CarteTresor[] id = new CarteTresor[4];
        int k = 0;
        for (CarteTresor c: cartesTresor) {
            id[k] = c;
            k++;
        }
        System.out.println(id[2] + " " + id[1] + " " + id[0]);
        //Object[] options2 = { "Joueur 4", "Joueur 3", "Joueur 2", "Joueur 1"};
        int nbjoueur = cartesTresor.size();
        String[] options2 = new String[nbjoueur];
        for(int i = 0; i < nbjoueur; i++){
            options2[i] = EncadreInventaireView.stringForInventory(id[i].getValeurCarte());
        }

        JPanel panel2 = new JPanel();
        if(str == "")
            str = "Choisissez une carte :";
        panel2.add(new JLabel(str));

        int result = JOptionPane.showOptionDialog(null, panel2, "",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("resources/images/gruau2.png"),
                options2, null);
        if (result >= 0 && fenetreConfirmation){
            //il faut changer ici fqojgqjdfhpj
            JOptionPane.showMessageDialog(null,"Vous avez choisi la carte " + options2[result]);
        }
        if (result < 0)
            return null;
        return id[result];
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