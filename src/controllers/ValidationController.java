package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

class ValidationController extends IG.ZoneCliquable {

    private Plateau plateau;
    private WaterLevel waterLevel;
    private boolean end;

    public boolean getEnd() {return this.end;}
    public void setEndFalse() {this.end = false;}

    public ValidationController(Plateau p, WaterLevel w) {
        // Création d'une zone cliquable de dimensions 80*25 pixels,
        // et contenant le texte "Valider".
        super("Fin de tour", 100, 25,15);
        this.setLayout(new GridBagLayout());
        this.setSize(100, 25);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(new Color(30,144,255));
        this.plateau = p;
        this.waterLevel = w;
        this.end = false;
    }

    public WaterLevel getWaterLevel() { return this.waterLevel; }

    public void setWaterLevel(WaterLevel waterLevel) {
        this.waterLevel = waterLevel;
    }

    public boolean checkAroundSub(Case c) {
        if (c.getEtat() != Case.Etat.SUBMERGEE)
            return false;
        for (Case.Dir d : Case.Dir.values()) {
            if (d == Case.Dir.SE || d == Case.Dir.SW || d == Case.Dir.NE || d == Case.Dir.NW)
                break;
            if (c.adjacente(d).getEtat() != Case.Etat.SUBMERGEE)
                return false;
        }
        return true;
    }

    public void killPlayers() {
        for (Player p:plateau.getPlayersPlateau()) {
            if (this.checkAroundSub(p.getCase())) {
                p.killPlayer();
                p.getCase().getController().changeTexte("");
                p.getCase().removePlayer(p);
            }
        }
    }

    public void killArtefact(Case c) {
        if (c.hasArtefact()) {
            c.getArtefact().killArtefact();
            c.removeArtefact();
        }
    }

    public void killHeliport() {
        if(this.plateau.getHeliport().isSubmergee()){
            ((Heliport) this.plateau.getHeliport()).killHeliport();
        }
    }

    public void drowning(int n) {
        int borne;
        CarteInnonde carte;
        borne = Math.min(n, this.plateau.getNbCaseNonSubmergee());
        for (int i = 0; i < borne; i++) {
            Case current;
            current = this.plateau.tireSecheouInonde();
            if (current.getEtat() == Case.Etat.NORMALE)
                current.getController().setBackground(new Color(95, 158, 160));
            else if (current.getEtat() == Case.Etat.INONDEE) {
                try {
                    Icon imgIcon = new ImageIcon("resources/images/waves2.gif");
                    JLabel label = new JLabel(imgIcon);
                    models.CaseController controller = current.getController();
                    label.setBounds(0, 0, controller.getWidth(), controller.getHeight());
                    controller.add(label);
                }catch (Exception e) {System.out.println("bug");}
                current.getController().setBackground(new Color(30, 144, 255));
                this.killArtefact(current);
            }
            this.plateau.InondeOuSubmerge(current);
        }
        killPlayers();
    }

    public void clicGauche() {
        this.end = true;
        drowning(this.waterLevel.nbCaseInonde());
    }

    public void clicDroit() {}
    public void mouseEntered(MouseEvent e) {
        this.setBackground(new Color(0,128,200,75));
    }
    public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(30,144,255));
    }
}