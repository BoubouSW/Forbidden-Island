package models;

import IG.Texte;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CaseController extends IG.ZoneCliquable {

    Case cas;

    CaseController(Case c) {
        super("",100,100,15);
        //super(Integer.toString(c.getX())+" "+Integer.toString(c.getY()),100,100); // pour debugger
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
            case INONDEE:
                this.setBackground(new Color(95, 158, 160)); break;
            case SUBMERGEE:
                this.setBackground(new Color(30, 144, 255)); break;
            case NORMALE:
                this.setBackground(new Color(74, 160, 44)); break;
        }

    }
    CaseController(Case c, String name) {
        super(name,100,100,24);
        this.cas = c;
    }

    // Action à effectuer lors d'un clic gauche.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicGauche() {}

    // Action à effectuer lors d'un clic droit.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicDroit() {}

    public void mouseEntered(MouseEvent e) {
        if (this.cas.getEtat() != Case.Etat.SUBMERGEE) {
            this.setBackground(new Color(this.getBackground().getRed(), this.getBackground().getRed(), this.getBackground().getRed(), 30));
            //this.changeTexte(Integer.toString(this.cas.getX()) + " " + Integer.toString(this.cas.getY()));
        }
    }

    /*
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0,0,0,0);
    }
     */


    public void mouseExited(MouseEvent e) {
        String name = "";
        for (Player player : this.cas.getPlayers()) {
            name = name + " " + player.getName();
        }
        this.changeTexte(name);
        switch(this.cas.getEtat()) {
            case INONDEE:
                this.setBackground(new Color(95, 158, 160)); break;
            case SUBMERGEE:
                this.setBackground(new Color(30, 144, 255)); break;
            case NORMALE:
                this.setBackground(new Color(74, 160, 44)); break;
        }
    }
}