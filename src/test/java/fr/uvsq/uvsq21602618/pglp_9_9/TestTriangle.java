package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Classe de tests de la fonction Triangle.
 * @author Nathalie
 *
 */
public class TestTriangle {
    /**
     * Test de la fonction deplace de Triangle.
     */
    @Test
    public void testDeplace() {
        Point p1 = new Point(15, 20);
        Point p2 = new Point(20, 30);
        Point p3 = new Point(10, 10);
        Triangle t = new Triangle("t1", p1, p2, p3);
        t.deplace(5, -5);
        
        Point p4 = new Point(20, 15);
        Point p5 = new Point(25, 25);
        Point p6 = new Point(15, 5);
        
        assertEquals(p4, t.getPoint1());
        assertEquals(p5, t.getPoint2());
        assertEquals(p6, t.getPoint3());
        assertEquals("t1", t.getNom());
        assertEquals("Triangle", t.getNomForme());
    }
    /**
     * Test de la fonction affichage dans Triangle.
     */
    @Test
    public void testAffichage() {
        Point p1 = new Point(15, 20);
        Point p2 = new Point(20, 30);
        Point p3 = new Point(10, 10);
        Triangle t = new Triangle("t1", p1, p2, p3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        t.affiche();

        String expected = "t1 = Triangle((15, 20), (20, 30), (10, 10))";

        assertEquals(expected, outContent.toString().trim());
    }
}
