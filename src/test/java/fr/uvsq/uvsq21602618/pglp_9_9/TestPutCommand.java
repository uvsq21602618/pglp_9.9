package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

import fr.uvsq.uvsq21602618.pglp_9_9.commandes.CreateCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.PutCommand;

/**
 * Classe de tests pour la classe PutCommand
 * @author Nathalie
 *
 */
public class TestPutCommand {
    /**
     * Test de la fonction execute. On verifie l'ajout d'un compose Forme dans
     * une instance de type ComposantDessin.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecute() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "dessin = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        Forme test = formes.get(0);
        ComposantDessin cd = (ComposantDessin) test;
        
        ComposantDessin expected = new ComposantDessin("dessin");
        Point p = new Point(2, 3);
        Carre c = new Carre("c1", p, 5);
        expected.ajoute(c);
        
        assertEquals(expected, cd);
        assertTrue(noms.contains("c1"));
    }
    /**
     * Test de la fonction execute lorsque le compose est une forme et non pas
     * un composantDessin.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteMauvaisCompose() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f = com.getForme();

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute(); 
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le composé indiqué: t1 ne peut pas contenir de composants!";

        str = "put(t1, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        
        assertEquals(expected, outContent.toString().trim());
        assertTrue(formes.contains(f));    

    }
    /**
     * Test de la fonction execute lorsque le compose est une forme et non pas
     * un composantDessin.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteComposantExistePas() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "dessin = composantdessin";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f2 = com.getForme();
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le composant indiqué: c1 n'a pas encore été dessiné!";

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        
        assertFalse(noms.contains("c1"));
        assertTrue(noms.contains("dessin"));
        assertEquals(expected, outContent.toString().trim());

    }
    /**
     * Test de la fonction execute lorsque le compose est une forme et non pas
     * un composantDessin.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteComposeExistePas() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f = com.getForme();
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Il semble que le composé : dessin n'a pas encore été dessiné!";

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();
        
        assertFalse(noms.contains("dessin"));
        assertTrue(noms.contains("c1"));
        assertEquals(expected, outContent.toString().trim());
    }
}
