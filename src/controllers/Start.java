package models;
import models.InitStart;
import models.PlayerController;
import models.Views;
import java.lang.RuntimeException;
import java.util.*;

public class Start {

    Start(ArrayList<String> nomsJoueurs, int startwaterlevel) {

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
        Player.ROLE[] roles = new Player.ROLE[] {
            Player.ROLE.EXPLORATEUR, Player.ROLE.INGENIEUR, Player.ROLE.MESSAGER, Player.ROLE.PLONGEUR, Player.ROLE.PILOTE, Player.ROLE.NAVIGATEUR};
        int i1,i2;
        Player.ROLE temp;
        for (int i = 0; i < 100; i++) {
            i1 = (int) (Math.random() * (Player.ROLE.values().length));
            i2 = (int) (Math.random() * (Player.ROLE.values().length));
            temp = roles[i1];
            roles[i1] = roles[i2];
            roles[i2] = temp;
        }

        for (int i = 0; i < nomsJoueurs.size(); i++) {
            int[] spawn = plateau.randomSpawn();
            Player.ROLE role;
            plateau.addPlayerPlateau(i, nomsJoueurs.get(i), spawn[0], spawn[1],roles[i]);
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
        plateau.setArtefacts(aTab);

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
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre,plateau.getPlayersPlateau());
        }

        //initialisation des paquets de carte
        PaquetCarte<CarteTresor> paquetCarte = new PaquetCarte<CarteTresor>();
        for(int i = 0; i < 5; i++){
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_EAU));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_TERRE));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_VENT));
        }
        for(int i = 0; i < 3; i++) {
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.MONTEE_DES_EAUX));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.HELICOPTERE));
        }

        paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE));
        paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE));
        paquetCarte.melangePile();

        plateau.setPaquetCarteTresor(paquetCarte);

        //
        PaquetCarte<CarteInnonde> innondePaquetCarte = new PaquetCarte<>();
        for (int i = 0; i < plateau.getTaille(); i++) {
            for (int j = 0; j < plateau.getTaille(); j++) {
                if (Math.abs(i - (plateau.getTaille() - 1) / 2.) + Math.abs(j - (plateau.getTaille() - 1) / 2.) < plateau.getTaille() / 2.)
                    innondePaquetCarte.addPile(new CarteInnonde(i,j));
            }
        }
        innondePaquetCarte.melangePile();
        plateau.setPaquetCarteInnonde(innondePaquetCarte);

        //initialisation controller
        models.Controllers cont = new models.Controllers(plateau, views.fenetre, views, views.encadreSelection);

        //set niveau de l'eau
        WaterLevel waterLevel = new WaterLevel(startwaterlevel);
        cont.getValidationController().setWaterLevel(waterLevel);
        cont.getValidationController().drowning(6);

        cont.getView().waterLevelView.setWaterLevel(waterLevel);
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