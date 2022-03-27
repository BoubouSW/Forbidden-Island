package models;
import models.PlayerController;
import models.Views;
import java.lang.RuntimeException;
import java.util.Scanner;

public class Start {
    Start() {

        //création plateau taille 8x8
        int nb = 8;
        Plateau plateau = new Plateau(nb);

        //ajout des joueurs
        Scanner sc = new Scanner(System.in);
        int playersnumber = 0;
        boolean error = true;
        do {
            try {
                playersnumber = this.getNumberPlayer();
                error = false;
            }catch (Exception e) {
                System.out.println("Ce n'est pas un entier");
            }
        }while (error);
        this.addPlayerGame(playersnumber,plateau);

        //initialisation affichage du plateau
        models.Views views = new models.Views(plateau);

        //initialisation des playerscontroller
        int size = plateau.getPlayersPlateau().size();
        PlayerController[] pc = new PlayerController[size];
        for(Player p:plateau.getPlayersPlateau()){
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre);
        }

        //initialisation controller
        models.Controllers cont = new models.Controllers(plateau,views.fenetre);

        //affichage plateau
        views.display();

        //debut du jeu
        cont.play(pc);
    }

    public int getNumberPlayer() {
        int playersnumber = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Nombre de joueurs (2-4) :");
            playersnumber = sc.nextInt();
            if (playersnumber < 2 || playersnumber > 4)
                System.out.println("Nombre de joueurs doit etre compris entre 2 et 4");
        }while (playersnumber < 2 || playersnumber > 4);
        return playersnumber;
    }

    public void addPlayerGame(int nb, Plateau plateau) {
        Scanner sn = new Scanner(System.in);
        String name;
        int[] spawn;
        for (int i = 0; i < nb; i++) {
            System.out.println("Nom joueur " + (i+1) + " :");
            name = sn.nextLine();
            spawn = plateau.randomSpawn();
            plateau.addPlayerPlateau(i,name,spawn[0],spawn[1]);
        }
    }

}