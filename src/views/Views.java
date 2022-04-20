package models;

import views.AllInventoryView;
import views.EncadreInventaireView;
import views.EncadreView;
import views.InitView;

import javax.swing.*;
import java.awt.*;

public class Views {

    JFrame fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;
    EncadreView encadreTour;
    //EncadreInventaireView[] encadreInventaire;
    AllInventoryView allInventoryView;

    public Views(Plateau p) {
        int nbrJoueur = p.getPlayersPlateau().size();
        this.fenetre = new JFrame("ForbiddenIsland");
        this.fenetre.setSize(1500,830);
        this.fenetre.setLayout(null);
        this.fenetre.setLocationRelativeTo(null);
        System.out.println(this.fenetre.getLayout().toString());
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p,null);
        this.encadreTour = new EncadreView();
        encadreTour.setLocation((this.fenetre.getWidth() - encadreTour.getWidth())/2, 10);
        this.fenetre.add(encadreTour);
        EncadreInventaireView[] encadreInventaire = new EncadreInventaireView[nbrJoueur];
        for(int i = 0; i < nbrJoueur; i++) {
            encadreInventaire[i] = new EncadreInventaireView(p, i);
        }
        this.allInventoryView = new AllInventoryView(encadreInventaire);
        this.allInventoryView.setLocation(100, 100);
        this.fenetre.add(this.allInventoryView);
        this.plateauView.setLocation((this.fenetre.getWidth() - plateauView.getWidth())/2, 70);
        this.fenetre.add(plateauView);
        this.validationController.setLocation(4*this.fenetre.getWidth()/5, this.fenetre.getHeight()/2);
        this.fenetre.add(validationController, BorderLayout.EAST);
    }

    public void display() {
        this.fenetre.pack();
        this.fenetre.setSize(1500,830);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setVisible(true);
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Plateau getRelatedPlateau() {return this.plateauView.getPlateau();}
}