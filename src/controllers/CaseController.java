package models;

import IG.Texte;

import javax.swing.*;
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
            this.repaint();
            //this.changeTexte(Integer.toString(this.cas.getX()) + " " + Integer.toString(this.cas.getY()));
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public void paintComponent(Graphics g) {
            switch (this.cas.getEtat()) {
                case INONDEE:
                    //this.setBackground(new Color(95, 158, 160));
                    g.setColor(new Color(95, 158, 160));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    break;
                case SUBMERGEE:
                    //this.setBackground(new Color(30, 144, 255));
                    g.setColor(new Color(30, 144, 255));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    break;
                case NORMALE:
                    //this.setBackground(new Color(74, 160, 44));
                    g.setColor(new Color(74, 160, 44));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    break;
            }
        if(this.cas.hasPlayer()) {
            //g.setColor(new Color(220,20,60));
            Image img = new ImageIcon("resources/images/joueur2.png").getImage();
            //int x = (this.getWidth() - img.getWidth(null)) / 2;
            //int y = (this.getHeight() - img.getHeight(null)) / 2;
            g.drawImage(img,0,0,null);
            //g.fillOval(getWidth() / 6, getWidth() / 6, getWidth() / 6, getWidth() / 6);
        }
        Image img;
        img = new ImageIcon("resources/images/feu2.png").getImage();
        if(this.cas.hasArtefact()) {
            switch (this.cas.getArtefact().getElement()) {
                case FEU:
                    img = new ImageIcon("resources/images/feu2.png").getImage();break;
                case EAU:
                    img = new ImageIcon("resources/images/eau2.png").getImage();break;
                case VENT:
                    img = new ImageIcon("resources/images/vent2.png").getImage();break;
                case TERRE:
                    img = new ImageIcon("resources/images/terre2.png").getImage();break;
            }
            //g.setColor(Color.BLACK);
            int x = (this.getWidth() - img.getWidth(null)) / 2;
            int y = (this.getHeight() - img.getHeight(null)) / 2;
            g.drawImage(img,x,y,null);
            //g.fillRect(getWidth() / 6, getWidth() / 6, getWidth() / 6, getWidth() / 6);
        }
    }



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