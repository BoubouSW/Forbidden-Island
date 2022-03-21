package models;

class Plateau extends IG.Grille {

    // Attributs statiques
    private int taille;

    // Tableau contenant les cases
    private Case[][] plateau;
    public Case getCase(int i, int j) {
        return plateau[i][j];
    }

    // Constructeur
    public Plateau(int taille) {
        super(taille, taille);
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                Case cas = new Case(this);
                plateau[i][j] = cas;
                this.ajouteElement(cas);
            }
        }
    }
}