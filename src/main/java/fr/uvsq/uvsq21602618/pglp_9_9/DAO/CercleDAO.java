package fr.uvsq.uvsq21602618.pglp_9_9.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.uvsq.uvsq21602618.pglp_9_9.Cercle;
import fr.uvsq.uvsq21602618.pglp_9_9.Point;

/**
 * Classe pour le DAO de cercle.
 * @author Nathalie
 *
 */
public class CercleDAO extends DAO<Cercle> {
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
    @Override
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
                update.setString(1, obj.getNom().toLowerCase());
                update.setString(2, obj.getNomForme().toLowerCase());
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
                creation.close();
                rs.close();
            }
            rs = dbmd.getTables(null, null,
                    "cercles".toUpperCase(), null);

            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table cercles"
                            + " (nom varchar(30) primary key,"
                            + " centre_x int not null,"
                            + " centre_y int not null, rayon int not null, "
                            + "foreign key (nom) references formes(nom))");
                }
                try {
                    String updateString = ("insert into cercles values ("
                            + "?, ?, ?, ? )");
                    PreparedStatement update =
                            getConnect().prepareStatement(updateString);
                    update.setString(1, obj.getNom().toLowerCase());
                    update.setInt(2, obj.getCentre().getX());
                    update.setInt(TROIS, obj.getCentre().getY());
                    update.setInt(QUATRE, obj.getRayon());
                    update.executeUpdate();
                    update.close();
                    rs = creation2.executeQuery("SELECT * FROM cercles");

                    System.out.println("---Table cercles:---\n");
                    System.out.println("nom\t centre_x\t centre_y\t rayon");
                    while (rs.next()) {
                        System.out.printf("%s\t\t%d\t\t%d\t\t%d%n",
                                rs.getString("nom"),
                                rs.getInt("centre_x"), rs.getInt("centre_y"),
                                rs.getInt("rayon"));
                    }
                    System.out.println("----------------------------------\n");
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré!\n");
                    rs.close();
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé"
                            + " dans cercles!\n");
                    creation.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return obj;
    }

    /**
     * Méthode pour effacer.
     * @param obj L'objet à effacer
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    @Override
    public void delete(final Cercle obj) throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (ResultSet rs = dbmd.getTables(null, null,
                "composants_dessin".toUpperCase(), null)) {
            if (rs.next()) {
                String updateString = "delete from composants_dessin"
                        + " where nom_composant= ?";
                try (PreparedStatement update =
                        getConnect().prepareStatement(updateString)) {
                    update.setString(1, obj.getNom().toLowerCase());
                    update.executeUpdate();
                }
            }
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = dbmd.getTables(null, null,
                "cercles".toUpperCase(), null)) {
            if (rs.next()) {
                String updateString = "delete from cercles"
                        + " where nom= ?";
                try (PreparedStatement update =
                        getConnect().prepareStatement(updateString)) {
                    update.setString(1, obj.getNom().toLowerCase());
                    update.executeUpdate();

                    try (ResultSet rs2 = dbmd.getTables(null, null,
                            "formes".toUpperCase(), null)) {
                        if (rs2.next()) {
                            updateString = "delete from formes"
                                    + " where nom= ?";
                            try (PreparedStatement update2 =
                                 getConnect().prepareStatement(updateString)) {
                                update2.setString(1, obj.getNom()
                                        .toLowerCase());
                                update2.executeUpdate();

                                System.out.printf("Le cercle avec le nom "
                                        + obj.getNom()
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
                }
            }
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode pour afficher le contenu de la table cercles.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    @Override
    public void affichageTable() throws SQLException {
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
                            System.out.printf("%s\t\t%d\t\t%d\t\t%d%n",
                                    rs.getString("nom"),
                                    rs.getInt("centre_x"),
                                    rs.getInt("centre_y"),
                                    rs.getInt("rayon"));
                        }
                        System.out.println("-------------------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de cercles"
                        + " dans la base de données!\n");
            }
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
    public Cercle update(final Cercle obj)
            throws SQLException, IOException {
        String updateString = "select * from formes where nom= ?";
        try (PreparedStatement update =
                getConnect().prepareStatement(updateString)) {
            update.setString(1, obj.getNom().toLowerCase());
            update.execute();
            ResultSet res = update.getResultSet();
            if (!res.next()) {
                System.out.println("Ce nom n'a pas"
                        + " encore été utilisé pour un cercle,"
                        + "il n'y a donc pas de mise a jour possible.\n");
                this.create(obj);
            } else {
                this.delete(obj);
                this.create(obj);
                System.out.println("La mise à jour du cercle "
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
     * @param nom2 de l'information
     * @return gp le GroupePersonnel du fichier, null sinon
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws FileNotFoundException liee au fichier non trouve
     * @throws IOException liee aux entreés/sorties
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     */
    @Override
    public Cercle find(final String nom2) throws SQLException,
    FileNotFoundException, ClassNotFoundException, IOException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "cercles".toUpperCase(), null);
        try (Statement creation = getConnect().createStatement()) {
            if (!rs.next()) {
                return null;
            }
        }
        String nom = nom2.toLowerCase();
        String updateString = "select * from cercles where nom = ?";
        try (PreparedStatement update =
                getConnect().prepareStatement(updateString)) {
            update.setString(1, nom);
            update.execute();
            ResultSet res = update.getResultSet();

            if (!res.next()) {
                System.out.println("Il n'y a pas de cercle de nom "
                        + nom + " dans la base de données!\n");
                return null;
            } else {
                Point p = new Point(res.getInt("centre_x"),
                        res.getInt("centre_y"));
                int r = res.getInt("rayon");
                Cercle c = new Cercle(nom, p, r);
                System.out.println("Un cercle de nom "
                        + nom + " a été trouvé dans la base de données!\n");
                return c;
            }

        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
