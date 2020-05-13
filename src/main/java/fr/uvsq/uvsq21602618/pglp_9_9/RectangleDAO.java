package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe pour le DAO de rectangle.
 * @author Nathalie
 *
 */
public class RectangleDAO extends DAO<Rectangle>{
    /**
     * initialisation de la constante 3 pour eviter le "magic number".
     */
    static final int TROIS = 3;
    /**
     * initialisation de la constante 4 pour eviter le "magic number".
     */
    static final int QUATRE = 4;
    /**
     * initialisation de la constante 5 pour eviter le "magic number".
     */
    static final int CINQ = 5;
    /**
     * Constructeur de RectangleDAO.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public RectangleDAO() throws SQLException, IOException {
        super();
    }
    /**
     * Méthode de création.
     * @param obj L'objet à créer
     * @return obj qui vient d'etre cree
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public Rectangle create(final Rectangle obj)
            throws SQLException, IOException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "rectangles".toUpperCase(), null);

        try (Statement creation = getConnect().createStatement()) {
            if (!rs.next()) {
                creation.executeUpdate("Create table rectangles"
                        + " (nom varchar(30) primary key, hg_x int not null,"
                        + " hg_y int not null, bd_x int not null, bd_y int not null)");
            }
            try {
                String updateString = ("insert into rectangles values ("
                        + "?, ?, ?, ?, ? )");
                PreparedStatement update =
                        getConnect().prepareStatement(updateString);
                update.setString(1, obj.getNom());
                update.setInt(2, obj.getPointHG().getX());
                update.setInt(TROIS, obj.getPointHG().getY());
                update.setInt(QUATRE, obj.getPointBD().getX());
                update.setInt(CINQ, obj.getPointBD().getY());
                update.executeUpdate();
                update.close();
                rs = creation.executeQuery("SELECT * FROM rectangles");

                System.out.println("---Table rectangles:---\n");
                System.out.println("nom\t hg_x\t hg_y\t bd_x\t bd_y");
                while (rs.next()) {
                    System.out.printf("%s\t%d\t%d\t%d\t%d%n", rs.getString("nom"),
                            rs.getInt("hg_x"), rs.getInt("hg_y"),
                            rs.getInt("bd_x"), rs.getInt("bd_y"));
                }
                System.out.println("------------------------------------\n");
                System.out.println("L'objet " + obj.getNom()
                + " a bien été enregistré!\n");
                rs.close();
                creation.close();
            } catch (org.apache.derby.shared.common.error
                    .DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Ce nom a deja était utilisé!");
            }
        return obj;
        }
    }
    /**
     * Methode pour afficher le contenu de la table carres.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTableNumero() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "rectangles".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM rectangles")) {
                        System.out.println("---Table rectangles:---\n");
                        System.out.println("nom\t hg_x\t hg_y\t bd_x\t bd_y");
                        while (rs.next()) {
                            System.out.printf("%s\t%d\t%d\t%d\t%d%n", rs.getString("nom"),
                                    rs.getInt("hg_x"), rs.getInt("hg_y"),
                                    rs.getInt("bd_x"), rs.getInt("bd_y"));
                        }
                        System.out.println("----------------------------"
                                + "-------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de carres"
                        + " dans la base de données!\n");
            }
        }
    }
    @Override
    public void delete(Rectangle obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Rectangle update(Rectangle obj) throws IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Rectangle find(int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}