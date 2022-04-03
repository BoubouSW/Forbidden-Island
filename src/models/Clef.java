package models;

public class Clef extends Objet{
    public Clef(Element e, Player p, Case c){
        super(e, p, c);
    }
    public String StringForInventory(){return "Clef de " + this.getElement().name();}
}
