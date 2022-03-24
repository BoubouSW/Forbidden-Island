package models;
import java.awt.Frame;

import models.Player;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Playercontroller extends IG.Touche{
    private Player player;

    public Playercontroller(Player p){
        this.player = p;
    }

    public void keyPressed(KeyEvent e){
        System.out.println("pp");
    };
    public void keyReleased(KeyEvent e){
        System.out.println("pp");
    };

    public void keyTyped(KeyEvent e){
        Player moi = this.player;
        moi.getCase().getController().changeTexte("");
        System.out.println("pp");
        System.out.println(e.getKeyChar());
        /*
        switch (e.getKeyChar()) {
            case 'd':
                moi.moveDir(Case.Dir.DROITE); break;
            case "q":
                moi.moveDir(Case.Dir.GAUCHE); break;
            case "z":
                moi.moveDir(Case.Dir.HAUT); break;
            case "s":
                moi.moveDir(Case.Dir.BAS); break;
        }
         */
        String name = "";
        for (Player player : moi.getCase().getPlayers()) {
            name = name + " " + player.getName();
        }
        moi.getCase().getController().changeTexte(name);
    }
}
