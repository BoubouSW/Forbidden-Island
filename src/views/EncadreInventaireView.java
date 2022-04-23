package views;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// un encadre par joueur
public class EncadreInventaireView extends JPanel  {
    private IG.Texte PlayersName;
    private IG.Texte textesSkin;
    private IG.Texte textesClef;

    public void setTexteSkin(/** type enum skin **/){
        String str = "Role :  ";
        this.textesSkin.setText(str);
    }

    public void setTexteKey(Set<CarteTresor> C){
        int[] forFonc = {0, 0, 0, 0};
        // eau : 0, terre : 1, feu : 2, vent : 3
        for(CarteTresor c: C){
            if(c.getValeurCarte().ordinal() <= 3)
                forFonc[c.getValeurCarte().ordinal()]++;
        }
        String str = "Cartes :  ";
        for(int ci = 0; ci < 4; ci++) {
            if (forFonc[ci] >= 1) {
                str += Objet.Element.values()[ci].name();
                if (forFonc[ci] >= 2)
                    str += "x" + Integer.toString(forFonc[ci]);
            }
            str += " ";
            this.textesClef.setText(str);
        }
    }

    public EncadreInventaireView(Plateau plateau, int joueur) {
        super(new GridBagLayout());
        this.setBackground(new Color(30,144,255));
        Player p = plateau.getPlayerById(joueur);
        this.PlayersName = new IG.Texte("Inventaire de " + p.getName(), 16);
        this.textesSkin = new IG.Texte("Role :  "+p.getRole().toString(), 13);
        this.textesClef = new IG.Texte("Cartes :  ", 13);

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
        this.add(new JLabel(new ImageIcon(p.getImage())));
        this.add(this.textesSkin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(this.textesClef, constraints);

        // set border for the panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(0,0,0,0);
    }
}
