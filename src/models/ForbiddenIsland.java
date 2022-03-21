package models;

public class ForbiddenIsland {

    public static void main(String[] args) {
        int nb = 8;//Integer.parseInt(args[0]);
        // Création d'une fenêtre graphique, d'un échiquiers
        // et de deux boutons.
        IG.Fenetre fenetre = new IG.Fenetre("test");
        Plateau plateau = new Plateau(nb);
        Validation validation = new Validation(plateau);
        //Indice indice = new Indice(plateau);
        // On précise que l'échiquier et les boutons doivent
        // être affichés dans la fenêtre graphique.
        fenetre.ajouteElement(plateau);
        fenetre.ajouteElement(validation);
        fenetre.dessineFenetre();
    }
}