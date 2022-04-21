package models;
import models.PlayerController;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;


public class Controllers {
    private Plateau plateau;
    private JFrame window;
    private models.Views view;
    private models.ValidationController validationController;

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

    public boolean allDead() {
        Set<Player> pc = this.getPlayersController();
        for (Player p : pc)
            if (p.isAlive()) {
                return false;
            }
        return true;
    }

    public boolean heliportDead(){
        return this.plateau.getHeliport().isSubmergee();
    }

    public void play(PlayerController[] pcBanque) {
        System.out.println("Niveau de l'eau : " + this.validationController.getWaterLevel().getCurrentLvl());
        int n = this.getPlayersController().size();
        int c;
        int whoShouldPlay = 0;
        boolean gameOver = false;
        PaquetCarte<CarteTresor> paquetCarteTresor = this.plateau.getPaquetCarteTresor();
        PlayerController pc;
        while(! gameOver) {
            if(this.allDead() || this.heliportDead()) {
                System.out.println("Perdu !");
                gameOver = true;
            }
            c = 3;
            this.validationController.setEndFalse();
            pc = pcBanque[whoShouldPlay];
            //for (Player player : this.getPlayersController())
             //   System.out.println(player.getName() + player.isAlive());
            if (pc.getPlayer().isAlive()) {
                pc.StartReply();
                while (c != 0 && !this.validationController.getEnd()) {
                    c = pc.getCount();
                    this.view.encadreTour.setPlayerName(pc.getPlayer().getName());
                    this.view.encadreTour.setNbrCoup(c);
                }
                // pioche des cartes tresors
                CarteTresor carte;
                for(int i = 0; i < 2; i++){
                    carte = paquetCarteTresor.pioche();

                    if(carte.getValeurCarte() == CarteTresor.TYPE_CARTE_TRESOR.MONTEE_DES_EAUX) {
                        this.validationController.getWaterLevel().augmenteLvl();
                        System.out.println(carte.getValeurCarte().name());
                        paquetCarteTresor.Defausse(carte);
                        paquetCarteTresor.melangeDefausse();
                        paquetCarteTresor.retourneDefausse();
                        System.out.println(paquetCarteTresor.Str());
                    }else{
                        pc.getPlayer().addCarteTresor(carte);
                        System.out.println("Le joueur pioche : " + carte.getValeurCarte().name());
                    }
                }
                this.view.allInventoryView.inventoriesViews[pc.getPlayer().getIdentifier()].setTexteKey(pc.getPlayer().getCarteTresors());
                pc.StopReply();
                //System.out.println(pc.getPlayer().inventory());
                synchronized (this) {
                    while (!this.validationController.getEnd()) {
                        try {
                            this.validationController.wait();
                        } catch (Exception e) {
                            //System.out.println("Exception in wait " + e); // bizarre
                        }
                    }
                }
                // while(!this.validationController.getEnd()) {
                // System.out.println(""); // trouver comment attendre ?!
                //}
            }

            whoShouldPlay = (whoShouldPlay + 1) % n;
            int taille = this.plateau.getTaille();
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    this.plateau.getCase(i,j).getController().repaint();
                }
            }
            if(Player.hasAllArtefact() && this.plateau.allPlayersOnHeliport()){
                System.out.println("Gagne !");
                gameOver = true;
            }
        }
        //this.window.setVisible(false);
    }
}