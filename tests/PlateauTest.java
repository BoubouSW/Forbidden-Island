package models;
import junit.framework.TestCase;

import java.util.HashSet;

public class PlateauTest extends TestCase {
    public void test() throws Exception {
        Plateau plateau = new Plateau(8);
        plateau.addPlayerPlateau(0,"test",4,4, Player.ROLE.EXPLORATEUR);
        assertEquals(plateau.getPlayersPlateau().size(),1);
        assertEquals(plateau.getPlayerById(0).getName(),"test");
    }
}