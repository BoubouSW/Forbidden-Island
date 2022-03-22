package models;

class Case {

    public enum Etat {NORMALE,INONDEE,SUBMERGEE}

    // Attributs
    private Plateau plateau;
    private Etat etat;

    // Constructeur
    public Case(Plateau p) {
        this.plateau = p;
        this.etat = Etat.NORMALE;
    }

    public Etat getEtat() { return this.etat;}

    public void set_normale() {
        etat = Etat.NORMALE;
    }

    public void set_inondee() {
        etat = Etat.INONDEE;
    }

    public void set_submergee() {
        etat = Etat.SUBMERGEE;
    }
}