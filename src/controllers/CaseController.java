package models;

import IG.Texte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CaseController extends IG.ZoneCliquable {

    Case cas;

    CaseController(Case c) {
        super("",80,80,15);
        this.cas = c;
        c.set_controller(this);

        if (this.cas.hasPlayer()) {
            super.changeTexte(" ");
        }
        switch(this.cas.getEtat()) {
            case INONDEE:
                this.setBackground(Color.white); break;
            case SUBMERGEE:
                this.setBackground(new Color(30, 144, 255));
                try {
                    Icon imgIcon = new ImageIcon("resources/images/waves2.gif");
                    JLabel label = new JLabel(imgIcon);
                    label.setBounds(0, 0,getWidth(),getHeight());
                    this.add(label);
                }catch (Exception e) {System.out.println("bug");}
                break;
            case NORMALE:
                this.setBackground(new Color(74, 160, 44)); break;
        }

    }
    CaseController(Case c, String name) {
        super(name,100,100,24);
        this.cas = c;
    }

    public void clicGauche() {}

    public void clicDroit() {}

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            switch (this.cas.getEtat()) {
                case INONDEE:
                    g.setColor(new Color(61, 136, 200,100));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    break;
                case SUBMERGEE:
                    g.setColor(new Color(30, 144, 255));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    Image water = new ImageIcon("resources/images/waves2.gif").getImage();
                    int x = (this.getWidth() - water.getWidth(null)) / 2;
                    int y = (this.getHeight() - water.getHeight(null)) / 2;
                    g.drawImage(water,x,y,null);
                    break;
                case NORMALE:
                    g.setColor(new Color(74, 160, 44));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    break;
            }
        if(this.cas.getClass() == Heliport.class){
            Image img = new ImageIcon("resources/images/heliport3.png").getImage();
            int x = (this.getWidth() - img.getWidth(null)) / 2;
            int y = (this.getHeight() - img.getHeight(null)) / 2;
            g.drawImage(img, x, y, null);
        }
        if(this.cas.hasPlayer()) {
            int nb = this.cas.getPlayers().size();
            int k = 0;
            Image img = new ImageIcon("resources/images/sandbag.png").getImage();
            for (Player pi : this.cas.getPlayers()) {
                img = pi.getImage();
                if (pi.isFlightMode())
                    img = new ImageIcon("resources/images/helicopter2.png").getImage();
                if (pi.isDryMode())
                    img = new ImageIcon("resources/images/sandbag.png").getImage();
                switch (nb) {
                    case 1: g.drawImage(img,0,0,null); break;
                    case 2: if (k == 0) g.drawImage(img,0,0,null); else g.drawImage(img,50,0,null); break;
                    case 3: if (k == 0) g.drawImage(img,0,0,null); if (k==1) g.drawImage(img,50,0,null); else g.drawImage(img,0,50,null); break;
                    case 4: if (k == 0) g.drawImage(img,0,0,null); if (k==1) g.drawImage(img,50,0,null); if (k==2) g.drawImage(img,0,50,null); else g.drawImage(img,50,50,null); break;
                }
                k++;
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
            int x = (this.getWidth() - img.getWidth(null)) / 2;
            int y = (this.getHeight() - img.getHeight(null)) / 2;
            g.drawImage(img,x,y,null);
        }

    }


    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}