package models;

import javax.swing.*;
import java.awt.*;

public class Views {

    IG.Fenetre fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;

    public Views(Plateau p) {
        this.fenetre = new IG.Fenetre("ForbiddenIsland");
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p);
        this.tourDisplay();
        this.fenetre.ajouteElement(plateauView);
        this.fenetre.ajouteElement(validationController);

    }

    public void display() {
        this.fenetre.dessineFenetre();
    }

    public void tourDisplay() {
        JLabel labelUsername = new JLabel("Tour de : ");
        JLabel labelPassword = new JLabel("Nombre de coup restant : ");
        JPanel newPanel = new JPanel(new GridBagLayout());

        //newPanel.setLayout(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelPassword, constraints);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPanel.setBounds(0,0,0,0);

        // add the panel to this frame
        this.fenetre.ajouteElement(newPanel);
    }
}