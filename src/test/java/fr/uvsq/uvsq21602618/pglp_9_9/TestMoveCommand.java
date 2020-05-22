package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe MoveCommand.
 * @author Nathalie
 *
 */
public class TestMoveCommand {
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
        
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "dessin = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        
        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        str = "move(dessin, (2, 2))";
        MoveCommand move = new MoveCommand(str.toLowerCase(), formes);
        move.execute();
        
        ComposantDessin cd = (ComposantDessin) formes.get(0);
        
        Point p = new Point(4, 5);
        Carre c1 = new Carre("c1", p, 5);
        ComposantDessin exp = new ComposantDessin("dessin");
        exp.ajoute(c1);
        
        assertEquals(exp, cd);
    }
    /**
     * Test de la fonction execute. On verifie la detection d'un mauvais 
     * argument.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteMauvaisArgument() throws ClassNotFoundException, IOException, SQLException {
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
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "La commande n'a pas été saisie correctement!";
        
        str = "move(dessin)";
        MoveCommand move = new MoveCommand(str.toLowerCase(), formes);
        move.execute();
        
        assertEquals(expected, outContent.toString().trim());
    }
    /**
     * Test de la fonction execute. On verifie la detection d'un nom de 
     * composant qui n'existe pas.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteNomExistePas() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le dessin a deplace n'a pas ete trouve!";
        
        str = "move(a, (2, 2))";
        MoveCommand move = new MoveCommand(str.toLowerCase(), formes);
        move.execute();
        
        assertEquals(expected, outContent.toString().trim());
    }
}
