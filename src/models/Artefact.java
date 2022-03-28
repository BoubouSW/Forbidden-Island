package models;

public class Artefact extends Objet{
    public enum Element {EAU, TERRE, FEU, VENT};

    // attributs
    private Element element;
    private Case position;

    public Artefact(Element e, Case c){
        this.element = e;
        this.position = c;
    }
}
