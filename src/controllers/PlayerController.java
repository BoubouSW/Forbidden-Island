package models;
import java.awt.*;

import models.Controllers;
import models.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Set;

public class PlayerController extends IG.Touche{
    private Player player;
    private Set<Player> otherPlayers;
    private boolean shouldReply;
    private int count;
    private boolean ingenieurDry;
    private boolean piloteFlight;
    private Case saveCase;

    public PlayerController(Player p, JFrame fenetre, Set<Player> players){
        fenetre.addKeyListener(this);
        this.player = p;
        this.otherPlayers = players;
        this.shouldReply = false;
        this.ingenieurDry = false;
        this.piloteFlight = false;
    }

    //getters
    public boolean ShouldReply(){
        return this.shouldReply;
    }

    public int getCount(){
        return this.count;
    }

    public Player getPlayer(){return this.player;}

    //setters
    public void StartReply(){
        this.shouldReply = true;
        this.count = 3;
        if (this.player.getRole() == Player.ROLE.INGENIEUR)
            this.ingenieurDry = true;
        if (this.player.getRole() == Player.ROLE.PILOTE)
            this.piloteFlight = true;
    }

    public void StopReply(){
        this.shouldReply = false;
    }

    public void keyPressed(KeyEvent e){
    };
    public void keyReleased(KeyEvent e){
    };

    public void keyTyped(KeyEvent e) {
        if (this.shouldReply) {
            Player moi = this.player;
            //god mod
            if (moi.getName().equals("fred"))
                this.count = -100;
            moi.getCase().getController().repaint();
            /*
            String name = "";
            for (Player player : moi.getCase().getPlayers()) {
                if (player.getIdentifier() != moi.getIdentifier() && player.isAlive())
                    name = name + " " + player.getName();
            }
            if(moi.getCase().hasArtefact()){
                Objet a = moi.getCase().getArtefact();
                name = name + " " + a.getElement().name();
            }
            */
            moi.getCase().getController().changeTexte(" ");
            boolean b = false;
            switch (e.getKeyChar()) {
                case 'd':
                    if (moi.isDryMode())
                        moi.moveDirDry(Case.Dir.DROITE);
                    else
                        b = moi.moveDir(Case.Dir.DROITE);
                    break;
                case 'q':
                    if (moi.isDryMode())
                        moi.moveDirDry(Case.Dir.GAUCHE);
                    else
                        b = moi.moveDir(Case.Dir.GAUCHE);
                    break;
                case 'z':
                    if (moi.isDryMode())
                        moi.moveDirDry(Case.Dir.HAUT);
                    else
                        b = moi.moveDir(Case.Dir.HAUT);
                    break;
                case 's':
                    if (moi.isDryMode())
                        moi.moveDirDry(Case.Dir.BAS);
                    else
                        b = moi.moveDir(Case.Dir.BAS);
                    break;
                case 'a':
                    if (moi.getRole() == Player.ROLE.EXPLORATEUR)
                        b = moi.moveDir(Case.Dir.NW);
                    break;
                case 'e':
                    if (moi.getRole() == Player.ROLE.EXPLORATEUR)
                        b = moi.moveDir(Case.Dir.NE);
                    break;
                case 'w':
                    if (moi.getRole() == Player.ROLE.EXPLORATEUR)
                        b = moi.moveDir(Case.Dir.SW);
                    break;
                case 'c':
                    if (moi.getRole() == Player.ROLE.EXPLORATEUR)
                        b = moi.moveDir(Case.Dir.SE);
                    break;
                case 'f':
                    if (! moi.isFlightMode() && ! moi.isDryMode()) {
                        moi.enableDry();
                        this.saveCase = moi.getCase();
                    }else {
                        if (moi.isDryMode()) {
                            b = moi.assecheCase();
                            moi.disableDry();
                            moi.moveCase(this.saveCase);
                            if (b && this.ingenieurDry) {
                                this.ingenieurDry = false;
                                this.count++;
                            }
                        }
                    }
                    break;
                case 'l':
                    if (! moi.isFlightMode() && ! moi.isDryMode() && moi.hasSand()) {
                        moi.enableDry();
                        this.saveCase = moi.getCase();
                    }else {
                        if (moi.isDryMode()) {
                            b = moi.assecheCase();
                            if(b) {
                                moi.useSand();
                            }
                            moi.disableDry();
                            moi.moveCase(this.saveCase);
                        }
                    }
                    break;
                case 'r':
                    if (! moi.isFlightMode() && ! moi.isDryMode()) {
                        b = moi.ramasseArtefact();
                        Controllers theController = moi.getCase().getPlateau().getTheController();
                        theController.getView().allInventoryView.inventoriesViews[getPlayer().getIdentifier()].setTexteKey(getPlayer().getCarteTresors());
                    }
                    break;
                case 'i':
                    System.out.println(this.getPlayer().inventory());
                    break;
                case 'v':
                    if (!moi.isFlightMode() && this.piloteFlight && ! moi.isDryMode()) {
                        moi.enableFlight();
                        this.piloteFlight = false;
                        System.out.println(moi.isFlightMode());
                    }
                    else {
                        if (moi.getRole() == Player.ROLE.PILOTE && moi.isFlightMode()) {
                            moi.disableFlight();
                            b = true;
                        }
                    }
                    break;
                case 'h':
                    if (!moi.isFlightMode() && ! moi.isDryMode() && moi.hasHelico()) {
                        moi.enableFlight();
                    }
                    else {
                        if (moi.isFlightMode()) {
                            moi.disableFlight();
                            moi.useHelico();
                            b = true;
                        }
                    }
                    break;
                case 'n':
                    System.out.println(this.otherPlayers);
                    Set<Player> playerSet = moi.choosePlayers(this.otherPlayers,2);
            }
            //System.out.println(moi.isFlightMode());
            if (moi.isFlightMode())
                b = false;
            if(! b)
                this.count++;
            /*
            name = "";
            moi.getCase().getController().add(new IG.Texte("",24));

            for (Player player : moi.getCase().getPlayers()) {
                if(player.isAlive())
                    name = name + " " + player.getName();
            }

            if(moi.getCase().hasArtefact()){
                Objet a = moi.getCase().getArtefact();
                name = name + " " + a.getElement().name();
            }

             */
            moi.getCase().getController().changeTexte(" ");

            moi.getCase().getController().repaint();
            this.count--;
        }
    }
}
