package controllers;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class BoutonValiderController extends IG.ZoneCliquable {
    private boolean isClicked = false;

    public BoutonValiderController(String texte, int x, int y, int texteSize){
        super(texte, x, y, texteSize);
    }

    public void clicGauche(){
        isClicked = true;
    }

    public boolean hasBeenClicked(){
        return isClicked;
    }

    public void clicDroit(){};

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
