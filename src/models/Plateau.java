package models;

class Plateau {

    final private int taille;

    private Case[][] plateau;

    public Case getCase(int i, int j) { return plateau[i][j]; }
    public int getTaille() { return this.taille; }

    // Constructeur
    public Plateau(int taille) {
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {
                Case cas = new Case(this);
                plateau[i][j] = cas;
            }
        }
    }
}