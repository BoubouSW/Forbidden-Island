package models;
import models.Playercontroller;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;


public class Controllers {
    private Plateau plateau;

    Controllers(Plateau plat) {
        this.plateau = plat;
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
        Frame f = new Frame("Demo");
        Playercontroller pc = new Playercontroller(p);
        f.addKeyListener(pc);
    }

}