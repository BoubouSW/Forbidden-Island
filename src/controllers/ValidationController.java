package models;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

class ValidationController extends IG.ZoneCliquable {

    private Plateau plateau;
    private boolean end;

    public boolean getEnd() {return this.end;}
    public void setEndFalse() {this.end = false;}

    public ValidationController(Plateau p) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("Fin de tour", 100, 25,15);
        this.plateau = p;
        this.end = false;
    }

    public boolean checkAroundSub(Case c) {
        if (c.getEtat() != Case.Etat.SUBMERGEE)
            return false;
        for (Case.Dir d : Case.Dir.values()) {
            if (c.adjacente(d).getEtat() != Case.Etat.SUBMERGEE)
                return false;
        }
        return true;
    }

    public void killPlayers() {
        Player[] dead = new Player[4];
        int nb = 0;
        for (Player p:plateau.getPlayersPlateau()) { //pas beau mais sinon ca retournait une exception
            if (this.checkAroundSub(p.getCase())) {
                p.killPlayer();
                p.getCase().getController().changeTexte("");
                p.getCase().removePlayer(p);
                //current.removePlayer(p);
                dead[nb] = p;
                nb++;
            }
        }
        //for (Player p : dead)
          //  c.removePlayer(p);
    }

    public void clicGauche() {
        //this.setBackground(Color.GREEN);
        this.end = true;
        Set<Case> forFonc = new HashSet<Case>();
        for (int i = 0; i < 3; i++) {
            Case current;
            do {
                current = this.plateau.randomSecheOuInonde();
            }while(forFonc.contains(current));
            forFonc.add(current);
            if (current.getEtat() == Case.Etat.NORMALE)
                current.getController().setBackground(new Color(95, 158, 160));
            else if (current.getEtat() == Case.Etat.INONDEE) {
                current.getController().setBackground(new Color(30, 144, 255));
                /*current.getController().changeTexte("");
                Player[] dead = new Player[4];
                int nb = 0;
                for (Player p:current.getPlayers()) { //pas beau mais sinon ca retournait une exception
                    p.killPlayer();
                    //current.removePlayer(p);
                    dead[nb] = p;
                    nb++;
                }
                for (Player p : dead)
                    current.removePlayer(p);

                 */
            }
            this.plateau.InondeOuSubmerge(current);
        }
        killPlayers();
    }

    public void clicDroit() {}
    public void mouseEntered(MouseEvent e) {
        this.setBackground(new Color(0,128,255,75));
    }
    public void mouseExited(MouseEvent e) {
        this.setBackground(Color.WHITE);
    }
}