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
     */
    public void run() throws IOException,
    SQLException, ClassNotFoundException {
        
        DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
        Point p1 = new Point(15, 20);
        Point p2 = new Point(25, 10);
        Carre c = new Carre("c1", p1, 5);        
        //carreDAO.create(c);
        
        DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
        Cercle c2 = new Cercle("c2", p1, 5);
        //cercleDAO.create(c2);
        
        
        DAO<Rectangle> rectangleDAO = new DAOFactory().getRectangleDAO();
        Rectangle r = new Rectangle("r1", p1, p2);
        //rectangleDAO.create(r);
        
        DAO<Triangle> triangleDAO = new DAOFactory().getTriangleDAO();
        Point p3 = new Point(10, 10);
        Triangle t = new Triangle("t1", p1, p2, p3);
        triangleDAO.create(t);
        
        DAO<ComposantDessin> composantDessinDAO = new DAOFactory().getComposantDessinDAO();
        ComposantDessin dessin1 = new ComposantDessin("dessin1");
        ComposantDessin dessin2 = new ComposantDessin("dessin2");
        dessin1.ajoute(r);
        dessin1.ajoute(c);
        dessin2.ajoute(dessin1);
        dessin2.ajoute(c2);
        composantDessinDAO.create(dessin2);
        carreDAO.delete(c);
        cercleDAO.delete(c2);
        rectangleDAO.delete(r);
        triangleDAO.delete(t);
        
        CercleDAO dao = new CercleDAO();
        dao.affichageTable();
        
        TriangleDAO dao2 = new TriangleDAO();
        dao2.affichageTable();
        
        Carre c3 = new Carre("c1", p2, 5);   
        carreDAO.create(c);
        carreDAO.update(c3);
        
        
        
    }
    /**
     * Main.
     * @param args pour le main
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     * @throws IOException liee aux entreés/sorties
     */
    public static void main(final String[] args) throws SQLException,
    ClassNotFoundException, IOException {
        ENVIRONNEMENT.run();
    }
}
