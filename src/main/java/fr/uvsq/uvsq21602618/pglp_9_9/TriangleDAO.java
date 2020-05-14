package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe du DAO de triangle.
 * @author Nathalie
 *
 */
public class TriangleDAO extends DAO<Triangle> {
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
     * initialisation de la constante 6 pour eviter le "magic number".
     */
    static final int SIX = 6;
    /**
     * initialisation de la constante 7 pour eviter le "magic number".
     */
    static final int SEPT = 7;
    /**
     * Constructeur de TriangleDAO.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public TriangleDAO() throws SQLException, IOException {
        super();
    }
    /**
     * Méthode de création.
     * @param obj L'objet à créer
     * @return obj qui vient d'etre cree
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public Triangle create(final Triangle obj)
            throws SQLException, IOException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "formes".toUpperCase(), null);
        try (Statement creation = getConnect().createStatement()) {
            if (!rs.next()) {
                creation.executeUpdate("Create table formes"
                        + " (nom varchar(30) primary key, type varchar(30))");
            }
            try {
                String updateString = ("insert into formes values ("
                        + "?, ? )");
                PreparedStatement update =
                        getConnect().prepareStatement(updateString);
                update.setString(1, obj.getNom());
                update.setString(2, obj.getNomForme());
                update.executeUpdate();
                update.close();
                rs = creation.executeQuery("SELECT * FROM formes");

                System.out.println("---Table formes:---\n");
                System.out.println("nom\t type");
                while (rs.next()) {
                    System.out.printf("%s\t%s%n", rs.getString("nom"),
                            rs.getString("type"));
                }
                System.out.println("-----------------------"
                        + "-----------------------\n");
                System.out.println("L'objet " + obj.getNom()
                + " a bien été enregistré dans formes!\n");
                rs.close();
                creation.close();
            } catch (org.apache.derby.shared.common.error
                    .DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Ce nom a deja été utilisé dans formes!");
            }
            rs = dbmd.getTables(null, null,
                    "triangles".toUpperCase(), null);
    
            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table triangles"
                            + " (nom varchar(30) primary key, p1_x int not null,"
                            + " p1_y int not null, p2_x int not null, p2_y int not null,"
                            + " p3_x int not null, p3_y int not null, "
                            + " foreign key (nom) references formes(nom))");
                }
                try {
                    String updateString = ("insert into triangles values ("
                            + "?, ?, ?, ?, ?, ?, ? )");
                    PreparedStatement update =
                            getConnect().prepareStatement(updateString);
                    update.setString(1, obj.getNom());
                    update.setInt(2, obj.getPoint1().getX());
                    update.setInt(TROIS, obj.getPoint1().getY());
                    update.setInt(QUATRE, obj.getPoint2().getX());
                    update.setInt(CINQ, obj.getPoint2().getY());
                    update.setInt(SIX, obj.getPoint3().getX());
                    update.setInt(SEPT, obj.getPoint3().getY());
                    update.executeUpdate();
                    update.close();
                    rs = creation2.executeQuery("SELECT * FROM triangles");
    
                    System.out.println("---Table triangles:---\n");
                    System.out.println("nom\t p1_x\t p1_y\t p2_x\t p2_y\t"
                            + " p3_x\t p3_y");
                    while (rs.next()) {
                        System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%n", rs.getString("nom"),
                                rs.getInt("p1_x"), rs.getInt("p1_y"),
                                rs.getInt("p2_x"), rs.getInt("p2_y"),
                                rs.getInt("p3_x"), rs.getInt("p3_y"));
                    }
                    System.out.println("------------------------------------\n");
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré!\n");
                    rs.close();
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé!");
                }
            }
        }
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table carres.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTable() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "triangles".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM triangles")) {
                        System.out.println("---Table triangles:---\n");
                        System.out.println("nom\t p1_x\t p1_y\t p2_x\t p2_y\t"
                                + " p3_x\t p3_y");
                        while (rs.next()) {
                            System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%n", rs.getString("nom"),
                                    rs.getInt("p1_x"), rs.getInt("p1_y"),
                                    rs.getInt("p2_x"), rs.getInt("p2_y"),
                                    rs.getInt("p3_x"), rs.getInt("p3_y"));
                        }
                        System.out.println("------------------------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de triangles"
                        + " dans la base de données!\n");
            }
        }
    }
    @Override
    public void delete(Triangle obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Triangle update(Triangle obj) throws IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Triangle find(int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}
