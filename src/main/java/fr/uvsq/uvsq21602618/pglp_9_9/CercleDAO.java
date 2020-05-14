package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe pour le DAO de cercle.
 * @author Nathalie
 *
 */
public class CercleDAO extends DAO<Cercle>{
    /**
     * initialisation de la constante 3 pour eviter le "magic number".
     */
    static final int TROIS = 3;
    /**
     * initialisation de la constante 4 pour eviter le "magic number".
     */
    static final int QUATRE = 4;
    /**
     * Constructeur de CercleDAO.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public CercleDAO() throws SQLException, IOException {
        super();
    }
    /**
     * Méthode de création.
     * @param obj L'objet à créer
     * @return obj qui vient d'etre cree
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public Cercle create(final Cercle obj)
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
                    "cercles".toUpperCase(), null);
    
            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table cercles"
                            + " (nom varchar(30) primary key, centre_x int not null,"
                            + " centre_y int not null, rayon int not null, "
                            + "foreign key (nom) references formes(nom))");
                }
                try {
                    String updateString = ("insert into cercles values ("
                            + "?, ?, ?, ? )");
                    PreparedStatement update =
                            getConnect().prepareStatement(updateString);
                    update.setString(1, obj.getNom());
                    update.setInt(2, obj.getCentre().getX());
                    update.setInt(TROIS, obj.getCentre().getY());
                    update.setInt(QUATRE, obj.getRayon());
                    update.executeUpdate();
                    update.close();
                    rs = creation2.executeQuery("SELECT * FROM cercles");
    
                    System.out.println("---Table cercles:---\n");
                    System.out.println("nom\t centre_x\t centre_y\t rayon");
                    while (rs.next()) {
                        System.out.printf("%s\t\t%d\t\t%d\t\t%d%n", rs.getString("nom"),
                                rs.getInt("centre_x"), rs.getInt("centre_y"),
                                rs.getInt("rayon"));
                    }
                    System.out.println("------------------------------------\n");
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré!\n");
                    rs.close();
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé dans cercles!");
                }
            
            }
        }
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table cercles.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTableNumero() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "cercles".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM cercles")) {
                        System.out.println("---Table cercles:---\n");
                        System.out.println("nom\t centre_x\t centre_y\t rayon");
                        while (rs.next()) {
                            System.out.printf("%s\t\t%d\t\t%d\t\t%d%n", rs.getString("nom"),
                                    rs.getInt("centre_x"), rs.getInt("centre_y"),
                                    rs.getInt("rayon"));
                        }
                        System.out.println("------------------------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de cercles"
                        + " dans la base de données!\n");
            }
        }
    }
    @Override
    public void delete(Cercle obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Cercle update(Cercle obj) throws IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Cercle find(int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}
