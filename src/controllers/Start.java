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

        //ajout des joueurs
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
        }



        //initialisation des playerscontroller
        int size = plateau.getPlayersPlateau().size();
        PlayerController[] pc = new PlayerController[size];
        for(Player p:plateau.getPlayersPlateau()){
            Set<Player> other = new HashSet<Player>(plateau.getPlayersPlateau());
            other.remove(p);
            pc[p.getIdentifier()] = new PlayerController(p, views.fenetre,other);
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
        models.Controllers cont = new models.Controllers(plateau, views.fenetre, views);

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
}