package models;

import models.CaseController;

import java.awt.Color;

class PlateauView extends IG.Grille {

    Plateau p;

    public PlateauView(Plateau plat) {
        super(plat.getTaille(), plat.getTaille());
        this.setBackground(new Color(30,144,255));
        this.p = plat;
        int taille = p.getTaille();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {

                if (plat.getCase(i,j).hasPlayer()) {
                    Case thiscase = plat.getCase(i,j);
                    String name = "";
                    for (Player player : thiscase.getPlayers()) {
                        name = name + " " + player.getName();
                    }
                    CaseController cas = new CaseController(thiscase,name);
                    cas.setBackground(new Color(177,21,38));
                    this.ajouteElement(cas);
                }else {
                    CaseController cas = new CaseController(plat.getCase(i,j));
                    if (plat.getCase(i,j).getEtat() == Case.Etat.INONDEE)
                        cas.setBackground(new Color(95,158,160));
                    else if (plat.getCase(i,j).getEtat() == Case.Etat.SUBMERGEE)
                        cas.setBackground(new Color(30,144,255));
                    else
                        cas.setBackground(new Color(74,160,44));
                    this.ajouteElement(cas);
                }
            }
        }
    }

    public void updatePlateauView() {
        int taille = this.p.getTaille();
        Plateau plat = this.p;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {

                if (plat.getCase(i,j).hasPlayer()) {
                    Case thiscase = plat.getCase(i,j);
                    String name = "";
                    for (Player player : thiscase.getPlayers()) {
                        name = name + " " + player.getName();
                    }
                    CaseController cas = new CaseController(thiscase,name);
                    cas.setBackground(new Color(177,21,38));
                    this.ajouteElement(cas);
                }else {
                    CaseController cas = new CaseController(plat.getCase(i,j));
                    if (plat.getCase(i,j).getEtat() == Case.Etat.INONDEE)
                        cas.setBackground(new Color(95,158,160));
                    else if (plat.getCase(i,j).getEtat() == Case.Etat.SUBMERGEE)
                        cas.setBackground(new Color(30,144,255));
                    else
                        cas.setBackground(new Color(74,160,44));
                    this.ajouteElement(cas);
                }
            }
        }
    }

}