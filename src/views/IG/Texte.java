package IG;

import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.*;


/**
 * Classe pour une zone de texte modifiable.
 */
public class Texte extends JLabel {

    
    /**
     * Constructeur paramétré par un texte initial.
     *
     * @param texte Texte initial
     */
    public Texte(String texte, int texteSize) {

        super(texte);
        super.setFont(new Font("Roboto", Font.PLAIN, texteSize));
        //super.setForeground(Color.RED);
    }

    /**
     * Méthode pour la modification du texte.
     *
     * @param texte Nouveau texte
     */
    public void changeTexte(String texte) {
	this.setText(texte);
	this.repaint();
    }
}
