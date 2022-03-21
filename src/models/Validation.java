package models;

class Validation extends IG.ZoneCliquable {

    private Plateau plateau;

    public Validation(Plateau p) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("Valider", 80, 25);
        this.plateau = p;
    }

    public void clicGauche() {}

    public void clicDroit() {}
}
