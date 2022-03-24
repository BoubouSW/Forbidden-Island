package models;

import java.awt.*;

public class CaseController extends IG.ZoneCliquable {

    Case cas;

    CaseController(Case c) {
        //super("",100,100);
        super(Integer.toString(c.getX())+" "+Integer.toString(c.getY()),100,100); // pour debugger
        this.cas = c;
        c.set_controller(this);
        if (this.cas.hasPlayer()) {
            String name = "";
            for (Player player : this.cas.getPlayers()) {
                name = name + " " + player.getName();
            }
            super.changeTexte(name);
            //this.setBackground(new Color(177,21,38));
        }
        switch(this.cas.getEtat()) {
            case INONDEE -> this.setBackground(new Color(95, 158, 160));
            case SUBMERGEE -> this.setBackground(new Color(30, 144, 255));
            case NORMALE -> this.setBackground(new Color(74, 160, 44));
        }

    }
    CaseController(Case c, String name) {
        super(name,100,100);
        this.cas = c;
    }

    // Action à effectuer lors d'un clic gauche.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicGauche() {}

    // Action à effectuer lors d'un clic droit.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicDroit() {}
}