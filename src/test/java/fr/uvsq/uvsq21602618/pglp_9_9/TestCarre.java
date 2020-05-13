package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
/**
 * Classe de tests de la fonction Carre.
 * @author Nathalie
 *
 */
public class TestCarre {
    /**
     * Test de la fonction deplace de Carre.
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    @Test
    public void testDeplace() throws NameAlreadyExistsException {
        Point p1 = new Point(15, 20);
        Carre c = new Carre("c1", p1, 5);
        c.deplace(5, -5);
        
        Point p2 = new Point(20, 15);
        int l = 5;
        
        assertEquals(p2, c.getPointHG());
        assertEquals(l, c.getLongueur());
        assertEquals("c1", c.getNom());
        assertEquals("Carré", c.getNomForme());
    }
    /**
     * Test de la fonction affichage dans Carre.
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    @Test
    public void testAffichage() throws NameAlreadyExistsException {
        Point p1 = new Point(15, 20);
        Carre c = new Carre("c1", p1, 5);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        c.affiche(); 
        String expected = "c1 = Carré((15, 20), 5)";

        assertEquals(expected, outContent.toString().trim());
    }
}
