package models;

import IG.Texte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
                if (player.isAlive())
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

            Image img = new ImageIcon("resources/images/joueur2.png").getImage();

            /* SI ON VEUT CHANGER DE COULEUR LE SPRITE
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            final int oldRGB = new Color(0,0,0).getRGB();
            final int newRGB = new Color(0,255,255).getRGB();
            for (int x = 0; x < bimage.getWidth(); x++) {
                for (int y = 0; y < bimage.getHeight(); y++) {
                    if (bimage.getRGB(x, y) == oldRGB)
                        bimage.setRGB(x, y, newRGB);
                    //else bimage.setRGB(x,y,this.getBackground().getRGB());
                }
            }
             */
            //int x = (this.getWidth() - img.getWidth(null)) / 2;
            //int y = (this.getHeight() - img.getHeight(null)) / 2;
            //g.drawImage(img,0,0,null);  //remplacer img par bimage
            int nombreJoueur = 0;
            for (Player p : this.cas.getPlayers()) {
                nombreJoueur += 1;
            }
            switch (nombreJoueur) {
                case 4:
                    g.drawImage(img,50,50,null);
                case 3:
                    g.drawImage(img,0,50,null);
                case 2:
                    g.drawImage(img,50,0,null);
                case 1:
                    g.drawImage(img,0,0,null);
            }
        }
        Image img;
        img = new ImageIcon("resources/images/feu2.png").getImage();
        if(this.cas.hasArtefact()) {
            switch (this.cas.getArtefact().getElement()) {
                case FEU:
                    img = new ImageIcon("resources/images/feu3.png").getImage();break;
                case EAU:
                    img = new ImageIcon("resources/images/eau4.png").getImage();break;
                case VENT:
                    img = new ImageIcon("resources/images/vent3.png").getImage();break;
                case TERRE:
                    img = new ImageIcon("resources/images/terre3.png").getImage();break;
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
            if (player.isAlive())
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