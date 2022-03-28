package models;

public abstract class Objet {
    public enum Element {EAU, TERRE, FEU, VENT};

    // attributs
    private Element element;
    private Player Owner; // = null si n'appartient a aucun joueur
    private Case position; // = null si n'est sur aucune case

    // getter
    public Element getElement(){return this.element;}
    public Player getOwner(){return this.Owner;}
    public Case getCase(){return this.position;}

    // constructeur
    public Objet(Element e, Player p, Case c){
        this.element = e;
        this.Owner = p;
        this.position = c;
    }
}