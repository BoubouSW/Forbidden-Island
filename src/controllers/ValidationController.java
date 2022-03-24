package models;

import java.awt.Color;

class ValidationController extends IG.ZoneCliquable {

    private Plateau plateau;

    public ValidationController(Plateau p) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("Fin de tour", 100, 25);
        this.plateau = p;
    }

    public void clicGauche() {
        this.setBackground(Color.GREEN);
        int[] coordInonde = this.plateau.randomIndonde();
        System.out.println(coordInonde[0]);
        System.out.println(coordInonde[1]);
    }

    public void clicDroit() {}
}