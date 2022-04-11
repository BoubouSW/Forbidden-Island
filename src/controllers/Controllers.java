package models;
import models.PlayerController;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;


public class Controllers {
    private Plateau plateau;
    private IG.Fenetre window;
    private models.Views view;
    private models.ValidationController validationController;

    Controllers(Plateau plat, IG.Fenetre fenetre, models.Views view) {
        this.plateau = plat;
        this.window = fenetre;
        this.view = view;
        this.validationController = this.view.validationController;
    }

    public Set<Player> getPlayersController() { return this.plateau.getPlayersPlateau(); }

    public Player getPlayerByIdController(int id) {
        for (Player p : this.getPlayersController()) {
            if (p.getIdentifier() == id)
                return p;
        }
        throw new RuntimeException("Identifiant non valide");
    }

    public void play(PlayerController[] pcBanque) {
        int n = this.getPlayersController().size();
        int c;
        int whoShouldPlay = 0;
        boolean gameOver = false;
        PlayerController pc;
        while(! gameOver) {
            c = 3;
            this.validationController.setEndFalse();
            pc = pcBanque[whoShouldPlay];
            pc.StartReply();
            while(c != 0 && !this.validationController.getEnd()){
                c = pc.getCount();
                this.view.encadreTour.setPlayerName(pc.getPlayer().getName());
                this.view.encadreTour.setNbrCoup(c);
            }
            for(int p = 0; p < n; p++){
                this.view.encadreInventaire[pcBanque[p].getPlayer().getIdentifier()].setTexteArtefact(Player.getArtefact());
            }
            this.view.encadreInventaire[pc.getPlayer().getIdentifier()].setTexteKey(pc.getPlayer().getKeyInventory());
            pc.StopReply();
            System.out.println(pc.getPlayer().inventory());
            synchronized (this) {
                while(! this.validationController.getEnd()) {
                    try {
                        this.validationController.wait();
                    }catch (Exception e){
                        //System.out.println("Exception in wait " + e); // bizarre
                    }
                }
            }
            // while(!this.validationController.getEnd()) {
            // System.out.println(""); // trouver comment attendre ?!
            //}
            whoShouldPlay = (whoShouldPlay + 1) % n;
            int taille = this.plateau.getTaille();
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    this.plateau.getCase(i,j).getController().repaint();
                }
            }
            if(Player.hasAllArtefact()){
                System.out.println("Gagne !");
                gameOver = true;
            }
        }
    }
}