package models;
import models.Views;
import java.lang.RuntimeException;

public class Start {
    Start() {
        int nb = 8;
        Plateau plateau = new Plateau(nb);
        plateau.addPlayerPlateau(0,"Clément",4,3);
        plateau.addPlayerPlateau(1,"LA",4,2);



        //models.ValidationController validation = new models.ValidationController(plateau);
        models.Views views = new models.Views(plateau);
        views.display();
        models.Controllers cont = new models.Controllers(plateau,views.fenetre);
        cont.play();

    }
}