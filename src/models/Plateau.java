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
                if ((i == 0 || i == 5) && (j == 0 || j == 1 || j == 4 || j == 5)
                    || (i == 1 || i == 4) && (j == 0 || j == 5))
                    cas.set_submergee();
                plateau[i][j] = cas;
            }
        }
    }
}