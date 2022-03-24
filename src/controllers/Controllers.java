package models;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    /*
    public void play() {
        do {

        } while()
    }
     */
}