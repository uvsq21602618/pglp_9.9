package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

import fr.uvsq.uvsq21602618.pglp_9_9.commandes.CreateCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.PutCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.ShowCommand;

/**
 * Classe de tests pour la classe ShowCommand.
 * @author Nathalie
 *
 */
public class TestShowCommand {
    /**
     * Test de la fonction execute. On verifie l'affichage.
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

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "put(dessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "dessin = Composant du dessin";
        String expected2 = "\r\n" + 
                "c1 = Carré((2, 3), 5)";

        str = "show(dessin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        String ligne = outContent.toString().replace("-", "").trim();
        assertEquals(expected, ligne.split(":")[0]);
        assertEquals(expected2, ligne.split(":")[1]);
    }
    /**
     * Test de la fonction execute quand la liste est vide.
     */
    @Test
    public void testExecuteVide() {
        LinkedList<Forme> formes = new LinkedList<Forme>();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Il n'y a rien a afficher!";

        String str = "show(dessin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        assertEquals(expected, outContent.toString().trim());

    }
    /**
     * Test de la fonction execute quand le nom indique n'existe pas dans la
     * liste.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteExistePas() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le nom indiqué ne correspond a aucun dessin!";

        str = "show(des,sin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        assertEquals(expected, outContent.toString().trim());

    }
}
