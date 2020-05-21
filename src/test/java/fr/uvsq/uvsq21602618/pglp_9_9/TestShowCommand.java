package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe ShowCommand.
 * @author Nathalie
 *
 */
public class TestShowCommand {
    /**
     * Test de la fonction execute. On verifie l'affichage.
     */
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

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "dessin = Composant du dessin";
        String expected2 = "c1 = Carré((2, 3), 5)";

        str = "show(dessin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        assertEquals(expected, outContent.toString().split(":")[0]);
        assertEquals(expected2, outContent.toString().split(":")[1].trim());
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
     */
    @Test
    public void testExecuteExistePas() {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le nom indiqué ne correspond a aucun dessin!";

        str = "show(des,sin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        assertEquals(expected, outContent.toString().trim());

    }
}
