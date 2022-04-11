package views;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// un encadre par joueur
public class EncadreInventaireView extends JPanel  {
    private IG.Texte PlayersName;
    private IG.Texte textesArtefact;
    private IG.Texte textesClef;

    public void setTexteArtefact(Set<Artefact> A){
        String str = "Artefact :  ";
        for(Artefact a: A){
            str += a.StringForInventory() + " ";
        }
        this.textesArtefact.setText(str);
    }

    public void setTexteKey(Set<Clef> C){
        int[] forFonc = {0, 0, 0, 0};
        // eau : 0, terre : 1, feu : 2, vent : 3
        for(Clef c: C){
            forFonc[c.getElement().ordinal()]++;
        }
        String str = "Clefs :  ";
        for(int ci = 0; ci < 4; ci++) {
            if (forFonc[ci] >= 1) {
                str += Objet.Element.values()[ci].name();
                if (forFonc[ci] == 2)
                    str += "x2";
            }
            str += " ";
            this.textesClef.setText(str);
        }
    }

    public EncadreInventaireView(Plateau plateau, int joueur) {
        super(new GridBagLayout());
        this.setBackground(new Color(240,128,128));
        Player p = plateau.getPlayerById(joueur);
        this.PlayersName = new IG.Texte("Inventaire de " + p.getName(), 10);
        this.textesArtefact = new IG.Texte("Artefact :  ", 10);
        this.textesClef = new IG.Texte("Clefs :  ", 10);

        //newPanel.setLayout(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.add(this.PlayersName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(this.textesArtefact, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(this.textesClef, constraints);

        // set border for the panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(0,0,0,0);
    }
}
