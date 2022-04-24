package models;
import junit.framework.TestCase;

public class CaseTest extends TestCase {
    public void test() throws Exception {
        Plateau plateau = new Plateau(8);
        Case cas = plateau.getCase(4,4);
        assertEquals(cas.adjacente(Case.Dir.HAUT),plateau.getCase(3,4));
        assertEquals(cas.adjacente(Case.Dir.BAS),plateau.getCase(5,4));
        assertEquals(cas.adjacente(Case.Dir.GAUCHE),plateau.getCase(4,3));
        assertEquals(cas.adjacente(Case.Dir.DROITE),plateau.getCase(4,5));
        assertFalse(cas.hasArtefact());
        assertFalse(cas.hasPlayer());
        Player p = new Player(plateau,0,"test",cas);
        cas.addPlayer(p);
        assertTrue(cas.hasPlayer());
        cas.set_inondee();
        assertEquals(cas.getEtat(), Case.Etat.INONDEE);
        cas.set_submergee();
        assertEquals(cas.getEtat(), Case.Etat.SUBMERGEE);
    }
}