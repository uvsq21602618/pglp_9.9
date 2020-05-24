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
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.ShowAllCommand;

/**
 * Classe de tests pour la classe ShowAllCommand.
 * @author Nathalie
 *
 */
public class TestShowAllCommand {
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

        String str = "ctest = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "dessintest = composantdessin";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "tri = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();

        str = "put(dessintest, ctest)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "----dessintest = Composant du dessin-----";
        String expected2 = "ctest = Carré((2, 3), 5)";
        String expected3 = "tri = Triangle((5, 5), (3, 3), (2, 2))";

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
