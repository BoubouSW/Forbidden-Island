package models;
//le push marche il ?

public class ForbiddenIsland {

    public static void main(String[] args) {
        int nb = 10;//Integer.parseInt(args[0]);
        // Création d'une fenêtre graphique, d'un échiquiers
        // et de deux boutons.
        IG.Fenetre fenetre = new IG.Fenetre("test");
        Plateau plateau = new Plateau(nb);
        Validation validation = new Validation(plateau);
        fenetre.ajouteElement(plateau);
        fenetre.ajouteElement(validation);
        fenetre.dessineFenetre();
    }
}