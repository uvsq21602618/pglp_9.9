package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Classe de tests pour Cercle.
 * @author Nathalie
 *
 */
public class TestCercle {
    /**
     * Test de la fonction deplace de Cercle.
     */
    @Test
    public void testDeplace() {
        Point p1 = new Point(15, 20);
        Cercle c = new Cercle("c1", p1, 5);
        c.deplace(5, -5);
        
        Point p2 = new Point(20, 15);
        int r = 5;
        
        assertEquals(p2, c.getCentre());
        assertEquals(r, c.getRayon());
        assertEquals("c1", c.getNom());
        assertEquals("Cercle", c.getNomForme());
    }
    /**
     * Test de la fonction affichage dans Cercle.
     */
    @Test
    public void testAffichage() {
        Point p1 = new Point(15, 20);
        Cercle c = new Cercle("c1", p1, 5);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        c.affiche();
        String expected = "c1 = Cercle((15, 20), 5)";

        assertEquals(expected, outContent.toString().trim());
    }
}
