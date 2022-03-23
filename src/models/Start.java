package models;
import models.Views;

public class Start {
    Start() {
        int nb = 8;
        Plateau plateau = new Plateau(nb);
        plateau.addPlayerPlateau(0,"Clément",3,5);
        plateau.addPlayerPlateau(0,"LA",4,2);
        models.ValidationController validation = new models.ValidationController(plateau);
        models.Views views = new models.Views(plateau);
        views.display();
    }
}