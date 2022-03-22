package models;

import models.CaseController;

class PlateauView extends IG.Grille {

    Plateau p;

    public PlateauView(Plateau plat) {
        super(plat.getTaille(), plat.getTaille());
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