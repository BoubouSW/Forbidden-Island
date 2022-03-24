package models;

import java.awt.Color;
import java.awt.event.MouseEvent;

class ValidationController extends IG.ZoneCliquable {

    private Plateau plateau;

    public ValidationController(Plateau p) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("Fin de tour", 100, 25,15);
        this.plateau = p;
    }

    public void clicGauche() {
        //this.setBackground(Color.GREEN);
        for (int i = 0; i < 3; i++) {
            int[] inond = this.plateau.randomIndondeSubmerge();
            Case current = this.plateau.getCase(inond[0], inond[1]);
            if (current.getEtat() == Case.Etat.INONDEE)
                current.getController().setBackground(new Color(95, 158, 160));
            else if (current.getEtat() == Case.Etat.SUBMERGEE)
                current.getController().setBackground(new Color(30, 144, 255));
        }
    }

    public void clicDroit() {}
    public void mouseEntered(MouseEvent e) {
        this.setBackground(new Color(0,128,255,75));
    }
    public void mouseExited(MouseEvent e) {
        this.setBackground(Color.WHITE);
    }
}