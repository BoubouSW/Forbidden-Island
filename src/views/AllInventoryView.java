package views;

import models.Artefact;
import models.Objet;
import models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class AllInventoryView extends JPanel {
    public EncadreInventaireView[] inventoriesViews;
    IG.Texte textTitreArt;
    IG.Texte textArt;
    public AllInventoryView(EncadreInventaireView[] invArray){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(300, 600);
        this.setBackground(new Color(100,149,237));
        this.inventoriesViews = invArray;
        for(EncadreInventaireView EIV: invArray){
            this.add(EIV);
        }
        JPanel inventaireArtefact = new JPanel();
        inventaireArtefact.setLayout(new BoxLayout(inventaireArtefact, BoxLayout.PAGE_AXIS));

        JPanel sub = new JPanel(new GridBagLayout());
        sub.setBackground(new Color(30,144,255));
        this.textTitreArt = new IG.Texte("Artefact Ramasses : ", 16);
        this.textArt = new IG.Texte("", 13);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;

        sub.add(textTitreArt, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        sub.add(textArt, constraints);

        // set border for the panel
        sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
        sub.setBounds(0,0,0,0);
        this.add(sub);
    }

    public void playerHasPickedArtefact(Set<Artefact> A){
        String str = "";
        for(Artefact a: A){
            str += a.getElement().name() + " ";
        }
        System.out.println(str);
        this.textArt.setText(str);
    }
}
