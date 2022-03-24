package models;
import models.Views;
import java.lang.RuntimeException;

public class Start {
    Start() {
        int nb = 8;
        Plateau plateau = new Plateau(nb);
        plateau.addPlayerPlateau(0,"Clément",2,5);
        plateau.addPlayerPlateau(1,"LA",4,2);
        Player moi = plateau.getCase(2,5).getPlayerById(0);
        moi.moveDir(Case.Dir.BAS);
        moi.moveDir(Case.Dir.DROITE);
        moi.moveDir(Case.Dir.BAS);
        moi.moveDir(Case.Dir.HAUT);

        //models.ValidationController validation = new models.ValidationController(plateau);
        models.Views views = new models.Views(plateau);
        views.display();
    }
}