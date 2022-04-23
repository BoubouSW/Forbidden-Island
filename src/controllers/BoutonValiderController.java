package controllers;

import views.EncadreSelection;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class BoutonValiderController extends IG.ZoneCliquable {
    private boolean isClicked = false;
    EncadreSelection theEncadre;

    public BoutonValiderController(String texte, int x, int y, int texteSize, EncadreSelection ES){
        super(texte, x, y, texteSize);
        this.theEncadre = ES;
    }

    public void clicGauche(){
        isClicked = true;
        this.theEncadre.TheButtonHasBeenPRESSED();
    }

    public boolean hasBeenClicked(){
        return isClicked;
    }

    public void clicDroit(){};

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
