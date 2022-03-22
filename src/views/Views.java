package models;

public class Views {

    IG.Fenetre fenetre;
    models.PlateauView plateauView;

    public Views(Plateau p) {
        this.fenetre = new IG.Fenetre("ForbiddenIsland");
        this.plateauView = new models.PlateauView(p);
        this.fenetre.ajouteElement(plateauView);
    }

    public void display() {
        this.fenetre.dessineFenetre();
    }

}