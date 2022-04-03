package views;

import models.Player;
import models.Plateau;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class EncadreInventaireView extends JPanel  {
    private IG.Texte[] PlayersName;
    private IG.Texte[] textesArtefact;
    private IG.Texte[] textesClef;

    public EncadreInventaireView(Plateau plateau) {
        super(new GridBagLayout());
        this.setBackground(new Color(240,128,128));
        int nbJoueur = plateau.getPlayersPlateau().size();
        PlayersName = new IG.Texte[nbJoueur];
        textesArtefact = new IG.Texte[nbJoueur];
        textesClef = new IG.Texte[nbJoueur];
        for(Player p:plateau.getPlayersPlateau()){
            PlayersName[p.getIdentifier()] = new IG.Texte("Inventaire de " + p.getName(), 10);
            textesArtefact[p.getIdentifier()] = new IG.Texte("Artefact :  ", 10);
            textesClef[p.getIdentifier()] = new IG.Texte("Clefs :  ", 10);
        }

        //newPanel.setLayout(null);
        /**
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(this.texteJoueur, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(this.texteNbrCoup, constraints);

        // set border for the panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(0,0,0,0);
         **/
    }
}
