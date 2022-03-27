package models;
import java.awt.Frame;

import models.Player;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class PlayerController extends IG.Touche{
    private Player player;
    private boolean shouldReply;
    private int count;

    public PlayerController(Player p, IG.Fenetre fenetre){
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
            String name = "";
            for (Player player : moi.getCase().getPlayers()) {
                if (player.getIdentifier() != moi.getIdentifier())
                    name = name + " " + player.getName();
            }
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
            }
            if(! b)
                this.count++;

            name = "";
            for (Player player : moi.getCase().getPlayers()) {
                name = name + " " + player.getName();
            }
            moi.getCase().getController().changeTexte(name);
            this.count--;
        }
    }
}