package models;

public class Artefact extends Objet{
    public Artefact(Element e, Player p, Case c){
        super(e, p, c);
    }

    public String StringForInventory(){return "Artefact : " + this.getElement().name();}
}
