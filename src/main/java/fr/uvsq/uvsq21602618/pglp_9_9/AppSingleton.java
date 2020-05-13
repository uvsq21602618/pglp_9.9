package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Singleton contenant le main.
 * @author Nathalie
 */
public enum AppSingleton {
    /**
     * L'enum qui contient le code du main.
     */
    ENVIRONNEMENT;
    /**
     * Execution du programme.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws ClassNotFoundException Exceptions liees a une classe inexistante
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    public void run() throws IOException,
    SQLException, ClassNotFoundException, NameAlreadyExistsException {
        
        DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
        Point p1 = new Point(15, 20);
        Point p2 = new Point(25, 10);
        Carre c = new Carre("c1", p1, 5);        
        carreDAO.create(c);
        
        DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
        Cercle c2 = new Cercle("c2", p1, 5);
        cercleDAO.create(c2);
        
    }
    /**
     * Main.
     * @param args pour le main
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     * @throws IOException liee aux entreés/sorties
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    public static void main(final String[] args) throws SQLException,
    ClassNotFoundException, IOException, NameAlreadyExistsException {
        ENVIRONNEMENT.run();
    }
}
