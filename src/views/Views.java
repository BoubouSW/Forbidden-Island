package models;

import views.EncadreInventaireView;
import views.EncadreView;
import views.InitView;

import javax.swing.*;
import java.awt.*;

public class Views {

    IG.Fenetre fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;
    EncadreView encadreTour;
    EncadreInventaireView[] encadreInventaire;

    public Views(Plateau p) {
        int nbrJoueur = p.getPlayersPlateau().size();
        this.fenetre = new IG.Fenetre("ForbiddenIsland");
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p,null);
        this.encadreTour = new EncadreView();
        this.fenetre.ajouteElement(encadreTour);
        this.encadreInventaire = new EncadreInventaireView[nbrJoueur];
        for(int i = 0; i < nbrJoueur; i++) {
            this.encadreInventaire[i] = new EncadreInventaireView(p, i);
            this.fenetre.ajouteElement(this.encadreInventaire[i]);
        }
        this.fenetre.ajouteElement(plateauView);
        this.fenetre.ajouteElement(validationController);
    }

    public void display() {
        this.fenetre.dessineFenetre();
    }

    private Plateau getRelatedPlateau() {return this.plateauView.getPlateau();}
}