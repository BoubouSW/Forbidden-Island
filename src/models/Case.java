package models;

class Case extends IG.ZoneCliquable {

    // Attributs
    private boolean occupee;
    private Plateau plateau;

    // Constructeur
    public Case(Plateau p) {
        // Initialisation d'une case cliquable, de dimensions 40*40 pixels.
        super(40, 40);
        // Initialisation des attributs
        this.occupee = false;
        this.plateau = p;
    }

    // Pour permettre à un objet [Plateau] de consulter l'état d'une case.
    public boolean estOccupee() {return this.occupee;}

    // Méthodes pour occuper ou libérer une case.
    public void occupe() { this.occupee = true; }
    public void libere() { this.occupee = false; }

    // Action à effectuer lors d'un clic gauche.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicGauche() { }

    // Action à effectuer lors d'un clic droit.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicDroit() { }
}