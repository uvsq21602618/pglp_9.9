package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe MoveAllCommand.
 * @author Nathalie
 *
 */
public class TestMoveAllCommand {
    /**
     * Test de la fonction execute. On verifie le deplacement du composant
     * indique en argument.
     *
    @Test
    public void testExecute() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);

        str = "dessin = composantdessin";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        str = "moveAll(2, 2)";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();

        ComposantDessin cd = (ComposantDessin) formes.get(0);
        Triangle t1 = (Triangle) formes.get(1);        
        Point p = new Point(4, 5);
        Carre c1 = new Carre("c1", p, 5);
        ComposantDessin exp = new ComposantDessin("dessin");
        exp.ajoute(c1);

        Point p1 = new Point(7, 7);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(4, 4);
        Triangle exp2 = new Triangle("t1", p1, p2, p3);

        assertEquals(exp, cd);
        assertEquals(exp2, t1);
    }
    /**
     * Test de la fonction execute quand il n'y a rien a deplacer.
     *
    @Test
    public void testExecuteVide() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Il n'y a rien n'a déplacer!";
        
        String str = "moveAll(2, 2)";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();

        assertEquals(expected, outContent.toString().trim());
        assertTrue(formes.isEmpty());

    }
    /**
     * Test de la fonction execute. On verifie la detection d'un
     *  mauvais argument.
     */
    @Test
    public void testExecuteMauvaisArgument() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);

        str = "dessin = composantdessin";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "La commande n'a pas été saisie correctement!";

        str = "moveAll";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();
        
        assertEquals(expected, outContent.toString().trim());

    }
    
    /**
     * Test de la fonction execute. On verifie la detection d'un
     *  chiffre en trop.
     */
    @Test
    public void testExecuteArgumentEnTrop() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);

        str = "dessin = composantdessin";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "L'argument en trop '4' a été retiré";
        
        str = "moveAll(2,3,4)";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();
        
        assertEquals(expected, outContent.toString().trim().split("!")[0]);

    }
}
