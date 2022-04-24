import junit.framework.TestCase;
import models.CarteTresor;
import models.Plateau;
import models.Player;

public class CarteTresorTest extends TestCase {
    public void test() throws Exception {
        CarteTresor carte = new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU);
        assertEquals(carte.getValeurCarte(), CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU);
        assertEquals(carte.getId(), 0);
        CarteTresor carteBis = new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE);
        assertEquals(carteBis.getValeurCarte(), CarteTresor.TYPE_CARTE_TRESOR.CARTE_DE_SABLE);
        assertEquals(carteBis.getId(), 1);
    }
}
