package models;

import views.EncadreView;

import javax.swing.*;
import java.awt.*;

public class Views {

    IG.Fenetre fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;
    EncadreView encadreTour;

    public Views(Plateau p) {
        this.fenetre = new IG.Fenetre("ForbiddenIsland");
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p);
        this.encadreTour = new EncadreView();
        this.fenetre.ajouteElement(encadreTour);
        this.fenetre.ajouteElement(plateauView);
        this.fenetre.ajouteElement(validationController);

    }

    public void display() {
        this.fenetre.dessineFenetre();
    }

    private Plateau getRelatedPlateau() {return this.plateauView.getPlateau();}
}