package models;
import views.EndView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;


public class Controllers {
    private Plateau plateau;
    private JFrame window;
    private models.Views view;
    private models.ValidationController validationController;
    private boolean win = false;

    Controllers(Plateau plat, JFrame fenetre, models.Views view) {
        plat.setTheController(this);
        this.plateau = plat;
        this.window = fenetre;
        this.view = view;
        this.validationController = this.view.validationController;
    }

    public models.Views getView(){return this.view;}

    public models.ValidationController getValidationController() {
        return validationController;
    }

    public Set<Player> getPlayersController() { return this.plateau.getPlayersPlateau(); }

    public Player getPlayerByIdController(int id) {
        for (Player p : this.getPlayersController()) {
            if (p.getIdentifier() == id)
                return p;
        }
        throw new RuntimeException("Identifiant non valide");
    }

    public boolean oneDead() {
        Set<Player> pc = this.getPlayersController();
        for (Player p : pc)
            if (!p.isAlive()) {
                return true;
            }
        return false;
    }

    public boolean twoArtefactDead() {
        int[] count = {0,0,0,0};
        for (Artefact a: this.plateau.getArtefacts()) {
            if (!a.alive)
                count[a.getElement().ordinal()] += 1;
        }
        for (int i :count) {
            if (i == 2)
                return true;
        }
        return false;
    }

    public boolean heliportDead(){
        return this.plateau.getHeliport().isSubmergee();
    }

    public void play(models.PlayerController[] pcBanque) {
        int n = this.getPlayersController().size();
        int c;
        int whoShouldPlay = 0;
        boolean gameOver = false;
        PaquetCarte<CarteTresor> paquetCarteTresor = this.plateau.getPaquetCarteTresor();
        models.PlayerController pc;
        while(! gameOver) {
            c = 3;
            this.validationController.setEndFalse();
            pc = pcBanque[whoShouldPlay];
            if (pc.getPlayer().isAlive()) {
                pc.StartReply();
                this.view.encadreTour.setPlayerName(pc.getPlayer().getName());
                while (c != 0 && !this.validationController.getEnd()) {
                    c = pc.getCount();
                    this.view.encadreTour.setNbrCoup(c);
                    this.view.allInventoryView.inventoriesViews[pc.getPlayer().getIdentifier()].setBackground(new Color(255,100,100));
                }
                pc.resetDemiCount();
                // pioche des cartes tresors
                CarteTresor carte;
                for(int i = 0; i < 2; i++){
                    if (pc.getPlayer().numberOfCards() < 4) {
                        carte = paquetCarteTresor.pioche();

                        if (carte.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.MONTEE_DES_EAUX) {
                            this.validationController.getWaterLevel().augmenteLvl();
                            if (this.plateau.getPaquetCarteInnonde().sizeDefausse() > 1)
                                this.plateau.getPaquetCarteInnonde().melangeDefausse();
                            this.plateau.getPaquetCarteInnonde().retourneDefausse();
                            this.getView().waterLevelView.repaint();
                            paquetCarteTresor.Defausse(carte);
                            paquetCarteTresor.melangeDefausse();
                            paquetCarteTresor.retourneDefausse();
                        } else {
                            pc.getPlayer().addCarteTresor(carte);
                            //System.out.println("Le joueur "+ (pc.getPlayer().getIdentifier()+1) + "  pioche : " + carte.getValeurCarte().name());
                        }
                    }
                }
                this.view.allInventoryView.inventoriesViews[pc.getPlayer().getIdentifier()].setTexteKey(pc.getPlayer().getCarteTresors());
                pc.StopReply();
                synchronized (this) {
                    while (!this.validationController.getEnd()) {
                        try {
                            this.validationController.wait();
                        } catch (Exception e) {
                            //System.out.println("Exception in wait " + e); // bizarre
                        }
                    }
                }
            }
            for (Player p: this.getPlayersController()) {
                p.disableFlight();
                p.disableDry();
            }
            this.view.allInventoryView.inventoriesViews[pc.getPlayer().getIdentifier()].setBackground(new Color(30,144,255));
            whoShouldPlay = (whoShouldPlay + 1) % n;
            int taille = this.plateau.getTaille();
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    this.plateau.getCase(i,j).getController().repaint();
                }
            }
            WaterLevel wl = this.validationController.getWaterLevel();
            if(this.oneDead() || this.heliportDead() || wl.getCurrentLvl() == wl.getMaxLvl() || this.twoArtefactDead()) {
                //System.out.println("Perdu !");
                gameOver = true;
                break;
            }
            if(Player.hasAllArtefact() && this.plateau.allPlayersOnHeliport()){
                //System.out.println("Gagne !");
                gameOver = true;
                this.win = true;
            }
        }
        //this.window.setVisible(false);
        this.window.setEnabled(false);
        EndView endWindow = new EndView(!this.win);
        endWindow.drawWin();
    }
}