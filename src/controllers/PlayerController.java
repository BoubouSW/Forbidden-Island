package models;
import java.awt.Frame;

import models.Player;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class PlayerController extends IG.Touche{
    private Player player;

    public PlayerController(Player p, IG.Fenetre fenetre){
        fenetre.addKeyListener(this);
        this.player = p;
    }

    public void keyPressed(KeyEvent e){
    };
    public void keyReleased(KeyEvent e){
    };

    public void keyTyped(KeyEvent e){
        Player moi = this.player;
        String name = "";
        for (Player player : moi.getCase().getPlayers()) {
            if (player.getIdentifier() != moi.getIdentifier())
                name = name + " " + player.getName();
        }
        moi.getCase().getController().changeTexte(name);

        switch (e.getKeyChar()) {
            case 'd':
                moi.moveDir(Case.Dir.DROITE); break;
            case 'q':
                moi.moveDir(Case.Dir.GAUCHE); break;
            case 'z':
                moi.moveDir(Case.Dir.HAUT); break;
            case 's':
                moi.moveDir(Case.Dir.BAS); break;
        }
        name = "";
        for (Player player : moi.getCase().getPlayers()) {
            name = name + " " + player.getName();
        }
        moi.getCase().getController().changeTexte(name);
    }
}
