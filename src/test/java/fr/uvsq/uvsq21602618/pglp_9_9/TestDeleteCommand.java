package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

/**
 * Classe de tests pour la classe DeleteCommand
 * @author Nathalie
 *
 */
public class TestDeleteCommand {
    /**
     * Test de la fonction execute.
     */
    @Test
    public void testExecute() {
        LinkedList<Forme> formes = new LinkedList<Forme>();
        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);
        str = "delete(c1)";
        DeleteCommand del = new DeleteCommand(str.toLowerCase(), formes);
        del.execute();
        
        assertTrue(formes.isEmpty());
    }
}
