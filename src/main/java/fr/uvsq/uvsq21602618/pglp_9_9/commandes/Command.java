package fr.uvsq.uvsq21602618.pglp_9_9.commandes;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Une interface commande.
 * @author Nathalie
 *
 */
public interface Command {
    /**
     * Methode pour executer une commande.
     * @throws SQLException Exception liee a la base de donnees
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    void execute() throws IOException, SQLException, ClassNotFoundException;
}
