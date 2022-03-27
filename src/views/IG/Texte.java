package IG;

import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        /*  SI ON VEUT UNE POLICE PERSO
        try {
            Font newFont = Font.createFont(Font.TRUETYPE_FONT,new File("resources/fonts/myfont.ttf")).deriveFont(new Integer(texteSize).floatValue());
            super.setFont(newFont);
        }catch (IOException | FontFormatException e) {}
        */
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
