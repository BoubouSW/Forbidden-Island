package IG;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Classe pour une zone carrée pouvant recevoir des clics de souris.
 */
public abstract class ZoneCliquable extends JPanel implements MouseListener {

    private IG.Texte texte;

    /**
     * Deux constructeurs, incluant ou non la création d'une zone de texte.
     *
     * @param texte Texte à afficher
     * @param x Première dimension de la case
     * @param y Deuxième dimention de la case
     */
    public ZoneCliquable(String texte, int x, int y, int texteSize) {
	    this(x, y);
        //setLayout(new GridBagLayout());
	    this.texte = new IG.Texte(texte,texteSize);
	    this.add(this.texte);
    }

    public ZoneCliquable(int x, int y) {
	setPreferredSize(new Dimension(x, y));
	addMouseListener(this);
	setBackground(Color.WHITE);
    }
    
    /**
     * Change le texte affiché dans la zone. 
     *
     * @param texte Nouveau texte à afficher.
     */
    public void changeTexte(String texte) {
    	this.texte.changeTexte(texte);
    }
    
    /**
     * Méthodes abstraites à définir pour indiquer les actions à effectuer
     * lors d'un clic gauche ou droit.
     */
    public abstract void clicGauche();
    public abstract void clicDroit();
    
    /**
     * Interfaçage entre la bibliothèque standard et les méthodes [clicGauche]
     * et [clicDroit].
     */
    public void mouseClicked(MouseEvent e) {
	if (SwingUtilities.isRightMouseButton(e)) {
	    this.clicDroit();
	} else {
	    this.clicGauche();
	}
    }

    public abstract void mouseEntered(MouseEvent e);// {}
    public abstract void mouseExited(MouseEvent e); // {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
