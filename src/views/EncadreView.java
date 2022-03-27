package views;

import IG.Texte;

import javax.swing.*;
import java.awt.*;

public class EncadreView extends JPanel {
    private IG.Texte texteJoeur;
    private IG.Texte texteNbrCoup;

    public void setPlayerName(String name){
        this.texteJoeur.setText("Tour de : " + name);
    }

    public void setNbrCoup(int nbr){
        this.texteNbrCoup.setText("Nombre de coup restant : " + Integer.toString(nbr));
    }

    public EncadreView() {
        super(new GridBagLayout());
        this.texteJoeur = new Texte("Tour de : ", 12);
        this.texteNbrCoup = new Texte("Nombre de coup restant : ", 12);


        //newPanel.setLayout(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(this.texteJoeur, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(this.texteNbrCoup, constraints);

        // set border for the panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(0,0,0,0);
    }
}
