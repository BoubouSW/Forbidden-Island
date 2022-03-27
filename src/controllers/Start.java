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
        /*  POUR LE MOMENT ON LAISSE COMMENTER CAR SINON HORRIBLE POUR TESTER
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

         */

        plateau.addPlayerPlateau(0,"Boubou",4,5);
        plateau.addPlayerPlateau(1,"ATP",2,3);

        //initialisation affichage du plateau
        models.Views views = new models.Views(plateau);

        //initialisation des playerscontroller
        int size = plateau.getPlayersPlateau().size();
        PlayerController[] pc = new PlayerController[size];
        for(Player p:plateau.getPlayersPlateau()){
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre);
        }

        //initialisation controller
        models.Controllers cont = new models.Controllers(plateau,views.fenetre, views);

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