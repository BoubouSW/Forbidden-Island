package models;

import views.EncadreInventaireView;

import javax.swing.*;
import java.util.Set;

public class CarteTresor extends Carte{
    private static int lastNewId = -1;
    private TYPE_CARTE_TRESOR valeurDeLaCarte;

    public enum TYPE_CARTE_TRESOR {ARTEF_EAU, ARTEF_TERRE, ARTEF_FEU, ARTEF_VENT,
                                MONTEE_DES_EAUX, HELICOPTERE, CARTE_DE_SABLE};

    public CarteTresor(TYPE_CARTE_TRESOR t){
        super(CarteTresor.lastNewId + 1);
        CarteTresor.lastNewId++;
        this.valeurDeLaCarte = t;
    }

    public void setValeurCarte(TYPE_CARTE_TRESOR t){ this.valeurDeLaCarte = t; }

    public TYPE_CARTE_TRESOR getValeurCarte(){ return this.valeurDeLaCarte; }
}
