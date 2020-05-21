package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;



/**
 * Classe de tests pour la classe CreateCommand
 * @author Nathalie
 *
 */
public class TestCreateCommand {
    /**
     * Test de la fonction execute dans le cas du carre.
     */
    @Test
    public void testExecuteCarre() {
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        Carre c1 = (Carre) f;
        
        Point p = new Point(2, 3);
        Carre expected = new Carre("c1", p, 5);
        
        assertEquals(expected, c1);
    }
    /**
     * Test de la fonction execute dans le cas du cercle.
     */
    @Test
    public void testExecuteCercle() {
        String str = "c2 = Cercle((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        Cercle c2 = (Cercle) f;
        
        Point p = new Point(2, 3);
        Cercle expected = new Cercle("c2", p, 5);
        
        assertEquals(expected, c2);
    }
    /**
     * Test de la fonction execute dans le cas du rectangle.
     */
    @Test
    public void testExecuteRectangle() {
        String str = "r1 = Rectangle((3, 5), (5, 3))";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        Rectangle r1 = (Rectangle) f;
        
        Point p = new Point(3, 5);
        Point p2 = new Point(5, 3);
        Rectangle expected = new Rectangle("r1", p, p2);
        
        assertEquals(expected, r1);
    }
    /**
     * Test de la fonction execute dans le cas du triangle.
     */
    @Test
    public void testExecuteTriangle() {
        String str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        Triangle t1 = (Triangle) f;
        
        Point p = new Point(5, 5);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(2, 2);
        Triangle expected = new Triangle("t1", p, p2, p3);
        
        assertEquals(expected, t1);
    } 
    /**
     * Test de la fonction execute dans le cas d'un composant du dessin.
     */
    @Test
    public void testExecuteComposantDessin() {
        String str = "dessin = composantdessin";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        ComposantDessin dessin = (ComposantDessin) f;
        
        ComposantDessin expected = new ComposantDessin("dessin");
        
        assertEquals(expected, dessin);
    }
    /**
     * Test de la détection d'une mauvaise commande avec les mauvais arguments.
     */
    @Test
    public void testMauvaisArgument() {
        String str = "t1 = Cercle((5, 3))";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        com.execute();
        String expected = "La commande de creation est incorrecte!";
        assertEquals(expected, outContent.toString().trim());
    }
    /**
     * Test de la détection d'une mauvaise commande contenant l'appel de 
     * plusieurs formes valides.
     */
    @Test
    public void testMauvaisesFormes() {
        String str = "c2 = CarreCercle((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        com.execute();
        String expected = "La commande de creation est incorrecte!!";
        assertEquals(expected, outContent.toString().trim());
    }
}
