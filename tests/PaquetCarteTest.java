import junit.framework.TestCase;
import models.CarteTresor;
import models.PaquetCarte;
import models.Plateau;
import models.Player;

public class PaquetCarteTest extends TestCase {
    public void test() throws Exception {
        PaquetCarte<CarteTresor> paquetCarte = new PaquetCarte<CarteTresor>();
        for(int i = 0; i < 2; i++){
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_EAU));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_TERRE));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
            paquetCarte.addPile(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_VENT));
        }
        assertTrue(paquetCarte.pileVide());
        assertEquals(paquetCarte.sizeDefausse(), 0);
        CarteTresor carte = new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE);
        paquetCarte.addPile(c);
        CarteTresor cartePioche = paquetCarte.pioche();
        assertEquals(c, cartePioche);
        CarteTresor carte2 = new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.HELICOPTERE);
        paquetCarte.Defausse(c);
        paquetCarte.Defausse(carte2);
        paquetCarte.melangeDefausse();
        assertEquals(paquetCarte.sizeDefausse(), 2);
    }
}