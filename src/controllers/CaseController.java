package models;

public class CaseController extends IG.ZoneCliquable {

    Case cas;

    CaseController(Case c) {
        super(100,100);
        this.cas = c;
    }

    // Action à effectuer lors d'un clic gauche.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicGauche() {}

    // Action à effectuer lors d'un clic droit.
    // Ceci utilise [IG.ZoneCliquable].
    public void clicDroit() {}
}