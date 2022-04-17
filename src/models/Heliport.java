package models;

import java.util.HashSet;

public class Heliport extends Case{
    private boolean alive = true;

    public Heliport(Plateau p, int x, int y) {
        super(p, x, y);
    }
    // inutile nonobstant
    public void killHeliport(){
        this.alive = false;
    }

}
