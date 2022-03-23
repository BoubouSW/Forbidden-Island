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
                if ((i == 0) || (i==7) || (j == 0) || (j == 7)
                        || ((i == 1) && ((j == 1) || (j == 2) || (j == 5) || (j == 6)))
                        || ((i == 6) && ((j == 1) || (j == 2) || (j == 5) || (j == 6)))
                        || ((j == 1) && ((i == 2) || (i == 5)))
                        || ((j == 6) && ((i == 2) || (i == 5))))
                    cas.set_submergee();
                plateau[i][j] = cas;
            }
        }
    }
}