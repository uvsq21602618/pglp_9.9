package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;

/**
 * La fabrique de DAO pour JDBC.
 * @author Nathalie
 *
 */
public class DAOFactory {
    /**
     * Constructeur.
     */
    public DAOFactory() {
    }
    /**
     * Méthode pour récuperer le DAO de Carre.
     * @return le DAO correspondant
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public DAO<Carre> getCarreDAO()
            throws IOException, SQLException {
        return new CarreDAO();
    }
    /**
     * Méthode pour récuperer le DAO de Cercle.
     * @return le DAO correspondant
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public DAO<Cercle> getCercleDAO()
            throws IOException, SQLException {
        return new CercleDAO();
    }
    /**
     * Méthode pour récuperer le DAO de Rectangle.
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return le DAO correspondant
     */
    public DAO<Rectangle> getRectangleDAO()
            throws IOException, SQLException {
        return new RectangleDAO();
    }
    /**
     * Méthode pour récuperer le DAO de Triangle.
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return le DAO correspondant
     */
    public DAO<Triangle> getTriangleDAO()
            throws IOException, SQLException {
        return new TriangleDAO();
    }
    /**
     * Méthode pour récuperer le DAO de ComposantDessin.
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return le DAO correspondant
     */
    public DAO<ComposantDessin> getComposantDessinDAO()
            throws IOException, SQLException {
        return new ComposantDessinDAO();
    }
}
