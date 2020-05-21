package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe ShowAllCommand.
 * @author Nathalie
 *
 */
public class TestShowAllCommand {
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
        String expected3 = "t1 = Triangle((5, 5), (3, 3), (2, 2))";

        str = "showall";
        ShowAllCommand show = new ShowAllCommand(formes);
        show.execute();

        assertEquals(expected, outContent.toString().split(":")[0]);
        assertEquals(expected2, outContent.toString().split(":")[1]
                .split("-------------------------------------")[0].trim());
        assertEquals(expected3, outContent.toString().split(":")[1]
                .split("-------------------------------------")[1].trim());
        
    }
}
