package models;
import models.PlayerController;
import models.Views;
import java.lang.RuntimeException;

public class Start {
    Start() {

        int nb = 8;
        Plateau plateau = new Plateau(nb);



        plateau.addPlayerPlateau(0,"Boubou",4,3);
        plateau.addPlayerPlateau(1,"ATP",4,2);
        models.Views views = new models.Views(plateau);
        int size = plateau.getPlayersPlateau().size();
        PlayerController[] pc = new PlayerController[size];
        for(Player p:plateau.getPlayersPlateau()){
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre);
        }

        models.Controllers cont = new models.Controllers(plateau,views.fenetre);
        views.display();
        cont.play(pc);
        //models.ValidationController validation = new models.ValidationController(plateau);

    }
}