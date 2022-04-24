package models;

import models.PlayerController;
import views.*;

import javax.swing.*;
import java.awt.*;

public class Views {

    JFrame fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;
    EncadreView encadreTour;
    AllInventoryView allInventoryView;
    ControlView controlView;
    WaterLevelView waterLevelView;

    public Views(Plateau p) {
        int nbrJoueur = p.getPlayersPlateau().size();
        this.fenetre = new JFrame("ForbiddenIsland");
        this.fenetre.getContentPane().setBackground(new Color(100,149,237));
        this.fenetre.setSize(1500,830);
        this.fenetre.setLayout(null);
        this.fenetre.setLocationRelativeTo(null);
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p,null);
        this.encadreTour = new EncadreView();
        encadreTour.setLocation((this.fenetre.getWidth() - encadreTour.getWidth())/2, 10);
        this.fenetre.add(encadreTour);

        this.controlView = new ControlView();
        this.controlView.setLocation(this.fenetre.getWidth()-controlView.getWidth() - 180, controlView.getHeight());
        this.fenetre.add(this.controlView);

        this.waterLevelView = new WaterLevelView();
        this.waterLevelView.setLocation(this.fenetre.getWidth()-waterLevelView.getWidth() - 180, this.fenetre.getHeight() - waterLevelView.getHeight() - 60);
        this.fenetre.add(this.waterLevelView);


        EncadreInventaireView[] encadreInventaire = new EncadreInventaireView[nbrJoueur];
        for(int i = 0; i < nbrJoueur; i++) {
            encadreInventaire[i] = new EncadreInventaireView(p, i);
        }
        this.allInventoryView = new AllInventoryView(encadreInventaire);
        this.allInventoryView.setLocation(50, 100);
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