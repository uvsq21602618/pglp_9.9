package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
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
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecute() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1test = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();    

        str = "dessintest = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "test = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "put(dessintest, c1test)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        str = "moveAll(2, 2)";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();

        ComposantDessin cd = null;
        Triangle t1 = null;

        for (Forme i : formes) {
            if(i instanceof ComposantDessin) {
                cd = (ComposantDessin) i;
            } else if (i instanceof Triangle) {
                t1 = (Triangle) i; 
            }
        }

        Point p = new Point(4, 5);
        Carre c1 = new Carre("c1test", p, 5);
        ComposantDessin exp = new ComposantDessin("dessintest");
        exp.ajoute(c1);

        Point p1 = new Point(7, 7);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(4, 4);
        Triangle exp2 = new Triangle("test", p1, p2, p3);

        assertEquals(exp2, t1);
        assertEquals(exp, cd);
    }
    /**
     * Test de la fonction execute quand il n'y a rien a deplacer.
     */
    @Test
    public void testExecuteVide() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Il n'y a rien a déplacer!";

        String str = "moveAll(2, 2)";
        MoveAllCommand moveAll = new MoveAllCommand(str.toLowerCase(), formes);
        moveAll.execute();

        assertEquals(expected, outContent.toString().trim());
        assertTrue(formes.isEmpty());

    }
    /**
     * Test de la fonction execute. On verifie la detection d'un
     *  mauvais argument.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteMauvaisArgument() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1test = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());

        str = "dessintest = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);
        noms.add(f2.getNom());

        str = "test = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);
        noms.add(f3.getNom());

        str = "put(dessintest, c1test)";
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
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteArgumentEnTrop() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1test = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());

        str = "dessintest = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);
        noms.add(f2.getNom());

        str = "test = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);
        noms.add(f3.getNom());

        str = "put(dessintest, c1test)";
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
