package models;

public class Views {

    IG.Fenetre fenetre;
    models.PlateauView plateauView;
    models.ValidationController validationController;

    public Views(Plateau p) {
        this.fenetre = new IG.Fenetre("ForbiddenIsland");
        this.plateauView = new models.PlateauView(p);
        this.validationController = new models.ValidationController(p);
        this.fenetre.ajouteElement(plateauView);
        this.fenetre.ajouteElement(validationController);
    }

    public void display() {
        this.fenetre.dessineFenetre();
    }

}