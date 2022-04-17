package models;

public class Artefact extends Objet{

    boolean alive = true;

    public Artefact(Element e, Player p, Case c){
        super(e, p, c);
    }

    public String StringForInventory(){return "Artefact : " + this.getElement().name();}

    public void killArtefact() {this.alive = false;}
}
