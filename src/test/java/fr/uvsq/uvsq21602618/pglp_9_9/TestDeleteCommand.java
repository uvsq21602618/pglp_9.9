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
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.DeleteCommand;

/**
 * Classe de tests pour la classe DeleteCommand.
 * @author Nathalie
 *
 */
public class TestDeleteCommand {
    /**
     * Test de la fonction execute quand le nom existe dans la liste.
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testExecuteNomExiste() throws ClassNotFoundException, IOException, SQLException {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute(); 

        str = "delete(c1)";
        DeleteCommand del = new DeleteCommand(str.toLowerCase(), formes, noms);
        del.execute();
        
        assertTrue(formes.isEmpty());
        assertTrue(noms.isEmpty());
    }
    /**
     * Test de la fonction execute quand le nom ne correspond a aucune forme
     * de la liste.
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
        str = "delete(c2)";
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "Le nom de cette forme n'existe pas!";
        
        DeleteCommand del = new DeleteCommand(str.toLowerCase(), formes, noms);
        del.execute();
        
        assertFalse(formes.isEmpty());
        assertFalse(noms.isEmpty());
        assertEquals(expected, outContent.toString().trim());
    }
}
