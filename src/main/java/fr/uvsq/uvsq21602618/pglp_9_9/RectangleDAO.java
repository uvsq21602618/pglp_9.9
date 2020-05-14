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
                    "rectangles".toUpperCase(), null);
    
            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table rectangles"
                            + " (nom varchar(30) primary key, hg_x int not null,"
                            + " hg_y int not null, bd_x int not null,"
                            + " bd_y int not null,"
                            + " foreign key (nom) references formes(nom))");
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
                    rs = creation2.executeQuery("SELECT * FROM rectangles");
    
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
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja était utilisé!");
                }
            }
        }
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table rectangles.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTable() throws SQLException {
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
                System.out.println("Il n'y a pas encore de rectangles"
                        + " dans la base de données!\n");
            }
        }
    }
    /**
     * Méthode pour effacer.
     * @param obj L'objet à effacer
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    @Override
    public void delete(final Rectangle obj) throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (ResultSet rs = dbmd.getTables(null, null,
                "composants_dessin".toUpperCase(), null)) {
            if (rs.next()) {
                String updateString = "delete from composants_dessin"
                        + " where nom_composant= ?";
                try (PreparedStatement update =
                        getConnect().prepareStatement(updateString)) {
                    update.setString(1, obj.getNom());
                    update.executeUpdate();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                }
            } 
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }  
        try (ResultSet rs = dbmd.getTables(null, null,
                "rectangles".toUpperCase(), null)) {
            if (rs.next()) {
                String updateString = "delete from rectangles"
                        + " where nom= ?";
                try (PreparedStatement update =
                        getConnect().prepareStatement(updateString)) {
                    update.setString(1, obj.getNom());
                    update.executeUpdate();
                    
                    try(ResultSet rs2 = dbmd.getTables(null, null,
                            "formes".toUpperCase(), null)) {
                        if(rs2.next()) {
                            updateString = "delete from formes"
                                    + " where nom= ?";
                            try (PreparedStatement update2 =
                                    getConnect().prepareStatement(updateString)) {
                                update2.setString(1, obj.getNom());
                                update2.executeUpdate();
                                
                                System.out.printf("Le rectangle avec le nom " + obj.getNom()
                                + " a bien été supprimé!\n");
                            } catch (org.apache.derby.shared.common.error
                                    .DerbySQLIntegrityConstraintViolationException e) {
                                e.printStackTrace();
                            }
                        }  
                    } catch (org.apache.derby.shared.common.error
                            .DerbySQLIntegrityConstraintViolationException e) {
                        e.printStackTrace();
                    }
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                }
            } 
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
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
