package models;
import models.Playercontroller;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;


public class Controllers {
    private Plateau plateau;
    private IG.Fenetre window;

    Controllers(Plateau plat, IG.Fenetre fenetre) {
        this.plateau = plat;
        this.window = fenetre;
    }

    public Set<Player> getPlayersController() { return this.plateau.getPlayersPlateau(); }

    public Player getPlayerByIdController(int id) {
        for (Player p : this.getPlayersController()) {
            if (p.getIdentifier() == id)
                return p;
        }
        if(true)
            throw new RuntimeException("Identifiant non valide");
        return null;
    }

    public void play() {
        Player p = this.getPlayerByIdController(0);
        //Player p2 = this.getPlayerByIdController(1);
        Playercontroller pc = new Playercontroller(p,this.window);
        //Playercontroller pc1 = new Playercontroller(p2,this.window);
    }

}