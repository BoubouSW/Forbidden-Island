package models;
import models.InitStart;
import models.PlayerController;
import models.Views;
import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Start {

    Start(ArrayList<String> nomsJoueurs) {

        //création plateau taille 8x8
        int nb = 8;
        Plateau plateau = new Plateau(nb);
        //InitStart initStart = new InitStart();
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
        for (int i = 0; i < nomsJoueurs.size(); i++) {
            int[] spawn = plateau.randomSpawn();
            plateau.addPlayerPlateau(i, nomsJoueurs.get(i), spawn[0], spawn[1]);
        }
        Player.setEmptyArtefactList();
        //plateau.addPlayerPlateau(0,"Boubou",4,5);
        //plateau.addPlayerPlateau(1,"ATP",2,3);

        //initialisation affichage du plateau
        models.Views views = new models.Views(plateau);

        Artefact[] aTab = { new Artefact(Objet.Element.FEU, null, null),
                            new Artefact(Objet.Element.EAU, null, null),
                            new Artefact(Objet.Element.TERRE, null, null),
                            new Artefact(Objet.Element.VENT, null, null),
                            new Artefact(Objet.Element.FEU, null, null),
                            new Artefact(Objet.Element.EAU, null, null),
                            new Artefact(Objet.Element.TERRE, null, null),
                            new Artefact(Objet.Element.VENT, null, null)};

        for(Artefact a: aTab){
            Case c;
            do{
                c = plateau.randomSecheOuInonde();
            }while(c.hasArtefact() || c.getClass() == Heliport.class);
            c.addObject(a);
            //c.getController().changeTexte(a.getElement().name());
        }

        Clef[] cTab = { new Clef(Objet.Element.FEU, null, null),
                        new Clef(Objet.Element.EAU, null, null),
                        new Clef(Objet.Element.TERRE, null, null),
                        new Clef(Objet.Element.VENT, null, null),
                        new Clef(Objet.Element.FEU, null, null),
                        new Clef(Objet.Element.EAU, null, null),
                        new Clef(Objet.Element.TERRE, null, null),
                        new Clef(Objet.Element.VENT, null, null)};

        for(Clef k: cTab){
            Case c;
            do {
                c = plateau.randomSecheOuInonde();
            }while(c.hasPlayer());
            c.addObject(k);
            //c.getController().changeTexte("Cle " + k.getElement().name());
        }



        //initialisation des playerscontroller
        int size = plateau.getPlayersPlateau().size();
        PlayerController[] pc = new PlayerController[size];
        for(Player p:plateau.getPlayersPlateau()){
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre);
        }

        //initialisation des paquets de carte
        PaquetCarte paquetCarte = new PaquetCarte();
        CarteTresor[] ctArray = new CarteTresor[28];
        for(int i = 0; i < 5; i++){
            CarteTresor[i] = new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_EAU);
        }

        //initialisation controller
        models.Controllers cont = new models.Controllers(plateau,views.fenetre, views);

        //affichage plateau
        views.display();

        //debut du jeu
        cont.play(pc);
    }

    /*
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
     */

}