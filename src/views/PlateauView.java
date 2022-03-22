package models;

import models.CaseController;

import java.awt.Color;

class PlateauView extends IG.Grille {

    Plateau p;

    public PlateauView(Plateau plat) {
        super(plat.getTaille(), plat.getTaille());
        this.setBackground(Color.BLUE);
        this.p = plat;
        int taille = p.getTaille();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                CaseController cas = new CaseController(plat.getCase(i,j));
                if (plat.getCase(i,j).getEtat() == Case.Etat.INONDEE)
                    cas.setBackground(Color.MAGENTA);
                else if (plat.getCase(i,j).getEtat() == Case.Etat.SUBMERGEE)
                    cas.setBackground(Color.BLUE);
                else
                    cas.setBackground(Color.GREEN);
                this.ajouteElement(cas);
            }
        }
    }
}