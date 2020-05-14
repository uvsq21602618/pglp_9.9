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
                System.out.println("Ce nom a deja été utilisé dans formes!\n");
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
                        System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%d%n", rs.getString("nom"),
                                rs.getInt("p1_x"), rs.getInt("p1_y"),
                                rs.getInt("p2_x"), rs.getInt("p2_y"),
                                rs.getInt("p3_x"), rs.getInt("p3_y"));
                    }
                    System.out.println("----------------------------------------"
                            + "-----------------------\n");
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré!\n");
                    rs.close();
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé dans"
                            + " triangles!\n");
                }
            }
        }
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table triangles.
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
                            System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%d%n", rs.getString("nom"),
                                    rs.getInt("p1_x"), rs.getInt("p1_y"),
                                    rs.getInt("p2_x"), rs.getInt("p2_y"),
                                    rs.getInt("p3_x"), rs.getInt("p3_y"));
                        }
                        System.out.println("-------------------------------------"
                                + "------------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de triangles"
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
    public void delete(final Triangle obj) throws SQLException {
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
                "triangles".toUpperCase(), null)) {
            if (rs.next()) {
                String updateString = "delete from triangles"
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
                                
                                System.out.printf("Le triangle avec le nom " + obj.getNom()
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
    /**
     * Méthode de mise à jour.
     * @param obj L'objet à mettre à jour
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return obj L'objet à mettre à jour
     */
    @Override
    public Triangle update(final Triangle obj)
            throws SQLException, IOException {
        
        String updateString = "select * from formes where nom= ?";
        try (PreparedStatement update =
                getConnect().prepareStatement(updateString)) {
            update.setString(1, obj.getNom());
            update.execute();
            ResultSet res = update.getResultSet();
            if (!res.next()) {
                System.out.println("Ce nom n'a pas"
                        + " encore été utilisé pour un triangle,"
                        + "il n'y a donc pas de mise a jour possible.\n");
                this.create(obj);
            } else {
                this.delete(obj);
                this.create(obj);
                System.out.println("La mise à jour du triangle "
                        + obj.getNom()
                        + " a été effectué!\n");
            }
            res.close();
            update.close();
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * Méthode de recherche des informations.
     * @param id de l'information
     * @return gp le GroupePersonnel du fichier, null sinon
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws FileNotFoundException liee au fichier non trouve
     * @throws IOException liee aux entreés/sorties
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     */
    @Override
    public Triangle find(final String nom) throws SQLException,
    FileNotFoundException, ClassNotFoundException, IOException {
        String updateString = "select * from triangles where nom = ?";
        try (PreparedStatement update =
                getConnect().prepareStatement(updateString)) {
            update.setString(1, nom);
            update.execute();
            ResultSet res = update.getResultSet();
            
            if (!res.next()) {
                System.out.println("Il n'y a pas de triangle de nom "
                        + nom + " dans la base de données!\n");
                return null;
            } else {
                Point p = new Point(res.getInt("p1_x"), res.getInt("p1_y"));
                Point p2 = new Point(res.getInt("p2_x"), res.getInt("p2_y"));
                Point p3 = new Point(res.getInt("p3_x"), res.getInt("p3_y"));
                Triangle t = new Triangle(nom, p, p2, p3);
                System.out.println("Un triangle de nom "
                        + nom + " a été trouvé dans la base de données!\n");
                return t;
            }

        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
