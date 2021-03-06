package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

import fr.uvsq.uvsq21602618.pglp_9_9.commandes.CreateCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.DeleteAllCommand;

/**
 * Classe de tests pour la classe DeleteAllCommand.
 * @author Nathalie
 *
 */
public class TestDeleteAllCommand {
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
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());
        
        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase(), formes, noms);
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);
        noms.add(f3.getNom());
        
        str = "deleteAll()";
        DeleteAllCommand del = new DeleteAllCommand(formes, noms);
        del.execute();
        
        assertTrue(formes.isEmpty());
        assertTrue(noms.isEmpty());
    }
}
