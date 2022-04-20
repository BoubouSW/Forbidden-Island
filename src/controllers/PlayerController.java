package models;
import java.awt.*;

import models.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class PlayerController extends IG.Touche{
    private Player player;
    private boolean shouldReply;
    private int count;

    public PlayerController(Player p, JFrame fenetre){
        fenetre.addKeyListener(this);
        this.player = p;
        shouldReply = false;
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
            moi.getCase().getController().repaint();
            String name = "";
            for (Player player : moi.getCase().getPlayers()) {
                if (player.getIdentifier() != moi.getIdentifier() && player.isAlive())
                    name = name + " " + player.getName();
            }
            /*
            if(moi.getCase().hasArtefact()){
                Objet a = moi.getCase().getArtefact();
                name = name + " " + a.getElement().name();
            }
             */
            moi.getCase().getController().changeTexte(name);
            boolean b = false;
            switch (e.getKeyChar()) {
                case 'd':
                    b = moi.moveDir(Case.Dir.DROITE);
                    break;
                case 'q':
                    b = moi.moveDir(Case.Dir.GAUCHE);
                    break;
                case 'z':
                    b = moi.moveDir(Case.Dir.HAUT);
                    break;
                case 's':
                    b = moi.moveDir(Case.Dir.BAS);
                    break;
                case 'a':
                    b = moi.assecheCase();
                    break;
                case 'r':
                    b = moi.ramasseArtefact();
                    break;
                case 'i':
                    System.out.println(this.getPlayer().inventory());
                    break;
            }
            if(! b)
                this.count++;
            name = "";
            moi.getCase().getController().add(new IG.Texte("",24));
            for (Player player : moi.getCase().getPlayers()) {
                if(player.isAlive())
                    name = name + " " + player.getName();
            }
            /*
            if(moi.getCase().hasArtefact()){
                Objet a = moi.getCase().getArtefact();
                name = name + " " + a.getElement().name();
            }
             */
            moi.getCase().getController().changeTexte(name);
            moi.getCase().getController().repaint();
            this.count--;
        }
    }
}
