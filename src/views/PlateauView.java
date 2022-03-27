package models;

import models.CaseController;

import java.awt.Color;

class PlateauView extends IG.Grille {

    private Plateau p;

    public Plateau getPlateau() {return this.p;}

    public PlateauView(Plateau plat) {
        super(plat.getTaille(), plat.getTaille());
        this.setBackground(new Color(30,144,255));
        this.p = plat;
        int taille = p.getTaille();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                    CaseController cas = new CaseController(plat.getCase(i,j));
                    this.ajouteElement(cas);
            }
        }
    }
}