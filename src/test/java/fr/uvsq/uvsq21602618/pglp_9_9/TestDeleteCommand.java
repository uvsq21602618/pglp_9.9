package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe DeleteCommand.
 * @author Nathalie
 *
 */
public class TestDeleteCommand {
    /**
     * Test de la fonction execute quand le nom existe dans la liste.
     */
    @Test
    public void testExecuteNomExiste() {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute(); 
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());
        str = "delete(c1)";
        DeleteCommand del = new DeleteCommand(str.toLowerCase(), formes, noms);
        del.execute();
        
        assertTrue(formes.isEmpty());
        assertTrue(noms.isEmpty());
    }
    /**
     * Test de la fonction execute quand le nom ne correspond a aucune forme
     * de la liste.
     */
    @Test
    public void testExecuteNomExistePas() {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute(); 
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());
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
