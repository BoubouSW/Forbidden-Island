package models;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    public void test() throws Exception {
        Plateau plateau = new Plateau(8);
        Player player1 = new Player(plateau,0,"player1",3,3, Player.ROLE.PILOTE);
        assertEquals(player1.getCase().getX(),3);
        assertNotSame(player1.getCase().getX(),2);
        player1.moveDir(Case.Dir.HAUT);
        assertEquals(player1.getCase().getX(),2);
        player1.getCase().set_inondee();
        assertEquals(player1.getCase().getEtat(), Case.Etat.INONDEE);
        assertFalse(player1.hasHelico());
        assertFalse(player1.hasSand());
        assertFalse(player1.has4KeyOfElement(Objet.Element.FEU));
        player1.addCarteTresor(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.HELICOPTERE));
        assertTrue(player1.hasHelico());
        player1.addCarteTresor(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
        player1.addCarteTresor(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
        player1.addCarteTresor(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
        player1.addCarteTresor(new CarteTresor(CarteTresor.TYPE_CARTE_TRESOR.ARTEF_FEU));
        assertTrue(player1.has4KeyOfElement(Objet.Element.FEU));
        player1.removed4CardsOfElement(Objet.Element.FEU);
        assertFalse(player1.has4KeyOfElement(Objet.Element.FEU));
    }
}
