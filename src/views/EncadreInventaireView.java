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

    // methode privee pour les affichages
    private String stringForInventory(CarteTresor.TYPE_CARTE_TRESOR tc){
        if(tc.ordinal() <= 3){
            return Objet.Element.values()[tc.ordinal()].name();
        }else{
            switch(tc){
                case HELICOPTERE : return "HELICO";
                case CARTE_DE_SABLE : return "SABLE";
                default: return null;
            }
        }
    }

    public void setTexteKey(Set<CarteTresor> C){
        int[] forFonc = {0, 0, 0, 0, 0, 0, 0};
        // eau : 0, terre : 1, feu : 2, vent : 3, mde : 4
        // helico : 5, sable : 6
        for(CarteTresor c: C){
            if(c.getValeurCarte().ordinal() != 4)
                forFonc[c.getValeurCarte().ordinal()]++;
        }
        String str = "Cartes :  ";
        for(int ci = 0; ci < 7; ci++) {
            if (forFonc[ci] >= 1) {
                str += stringForInventory(CarteTresor.TYPE_CARTE_TRESOR.values()[ci]);
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
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,Color.BLACK));
        this.setBounds(0,0,0,0);
    }
}
