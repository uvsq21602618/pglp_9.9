package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe du DAO de ComposantDessin.
 * @author Nathalie
 *
 */
public class ComposantDessinDAO extends DAO<ComposantDessin> {
    /**
     * initialisation de la constante 3 pour eviter le "magic number".
     */
    static final int TROIS = 3;
    /**
     * Le DAO de Carre.
     */
    private DAO<Carre> carreDAO;
    /**
     * Le DAO de Cercle.
     */
    private DAO<Cercle> cercleDAO;
    /**
     * Le DAO de Rectangle.
     */
    private DAO<Rectangle> rectangleDAO;
    /**
     * Le DAO de Triangle.
     */
    private DAO<Triangle> triangleDAO;
    /**
     * Constructeur de ComposantDessinDAO.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public ComposantDessinDAO() throws SQLException, IOException {
        super();
        carreDAO = new DAOFactory().getCarreDAO();
        cercleDAO = new DAOFactory().getCercleDAO();
        rectangleDAO = new DAOFactory().getRectangleDAO();
        triangleDAO = new DAOFactory().getTriangleDAO();
    }
    /**
     * Méthode de création.
     * @param obj L'objet à créer
     * @return obj qui vient d'etre cree
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public ComposantDessin create(final ComposantDessin obj)
            throws SQLException, IOException {
        try {
            this.setConnect();
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
                    rs.close();
                    creation.close();
                }
    
                rs = dbmd.getTables(null, null,
                        "composants_dessin".toUpperCase(), null);
    
                try (Statement creation2 = getConnect().createStatement()) {
                    if (!rs.next()) {
                        creation2.executeUpdate("Create table composants_dessin"
                                + "(nom varchar(30), type_composant varchar(30),"
                                + " nom_composant varchar(30),"
                                + "primary key (nom, nom_composant), "
                                + "foreign key (nom) references formes(nom),"
                                + "foreign key (nom_composant)"
                                + " references formes(nom))");
                    }
                    try {
                        String updateString = ("insert into composants_dessin"
                                + " values ("
                                + "?, ?, ? )");
                        PreparedStatement update;
                        Forme f;
                        ComposantDessin cd;
                        for (Dessin dessin : obj.getDessinFils()) {
                            update = getConnect().prepareStatement(updateString);
                            update.setString(1, obj.getNom().toLowerCase());
                            f = (Forme) dessin;
                            update.setString(2, f.getNomForme().toLowerCase());
                            update.setString(TROIS, f.getNom().toLowerCase());
    
                            if (dessin instanceof Carre) {
                                Carre c = (Carre) dessin;
                                carreDAO.create(c);
                            } else if (dessin instanceof Cercle) {
                                Cercle c = (Cercle) dessin;
                                cercleDAO.create(c);
                            } else if (dessin instanceof Rectangle) {
                                Rectangle r = (Rectangle) dessin;
                                rectangleDAO.create(r);
                            } else if (dessin instanceof Triangle) {
                                Triangle t = (Triangle) dessin;
                                triangleDAO.create(t);
                            } else if (dessin instanceof ComposantDessin) {
                                cd = (ComposantDessin) dessin;
                                this.create(cd);
                            }
                            update.executeUpdate();
                            update.close();
                        }
                        rs = creation2.executeQuery("SELECT * FROM"
                                + " composants_dessin");
    
                        System.out.println("-------Table"
                                + " composants_dessin:--------\n");
                        System.out.println("nom\t\t type_composant\t\t"
                                + " nom_composant");
                        while (rs.next()) {
                            System.out.printf("%s\t\t%s\t\t%s%n",
                                    rs.getString("nom"),
                                    rs.getString("type_composant"),
                                    rs.getString("nom_composant"));
                        }
                        System.out.println("----------------------------"
                                + "-----------------\n");
                        rs.close();
    
                        System.out.println("L'objet " + obj.getNom()
                        + " a bien été enregistré!\n\n");
                    }  catch (org.apache.derby.shared.common.error
                            .DerbySQLIntegrityConstraintViolationException e) {
                        System.out.println("Ce nom a deja été utilisé"
                                + " dans composants_dessin!\n");
                        creation.close();
                        rs.close();
                    }
                    this.affichageTable();
                    creation2.close();
    
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                    creation.close();
                    rs.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.disconnect();
        }
        this.disconnect();
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table composants_dessin.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTable() throws SQLException {
        this.setConnect();
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "composants_dessin".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM composants_dessin")) {
                        System.out.println("--------Table"
                                + " composants_dessin:--------\n");
                        System.out.println("nom\t\t type_composant\t\t"
                                + " nom_composant");
                        while (rs.next()) {
                            System.out.printf("%s\t\t%s\t\t%s%n",
                                    rs.getString("nom"),
                                    rs.getString("type_composant"),
                                    rs.getString("nom_composant"));
                        }
                        System.out.println("------------------------------"
                                + "---------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de dessins"
                        + " dans la base de données!\n");
            }
        }
        this.disconnect();
    }
    /**
     * Méthode pour effacer.
     * @param obj L'objet à effacer
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    @Override
    public void delete(final ComposantDessin obj) throws SQLException {
        this.setConnect();
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (ResultSet rs = dbmd.getTables(null, null,
                "composants_dessin".toUpperCase(), null)) {
            if (rs.next()) {
                for (Dessin dessin : obj.getDessinFils()) {
                    if (dessin instanceof Carre) {
                        Carre c = (Carre) dessin;
                        carreDAO.delete(c);
                    } else if (dessin instanceof Cercle) {
                        Cercle c = (Cercle) dessin;
                        cercleDAO.delete(c);
                    } else if (dessin instanceof Rectangle) {
                        Rectangle r = (Rectangle) dessin;
                        rectangleDAO.delete(r);
                    } else if (dessin instanceof Triangle) {
                        Triangle t = (Triangle) dessin;
                        triangleDAO.delete(t);
                    } else if (dessin instanceof ComposantDessin) {
                        ComposantDessin cd = (ComposantDessin) dessin;
                        this.delete(cd);
                    }
                }
                String updateString = "delete from composants_dessin"
                        + " where nom = ?";
                try (PreparedStatement update =
                        getConnect().prepareStatement(updateString)) {
                    update.setString(1, obj.getNom().toLowerCase());
                    update.executeUpdate();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                }
                System.out.println("Le dessin avec le nom " + obj.getNom()
                + " a bien été supprimé!\n");
            }
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        this.disconnect();
    }

    /**
     * Méthode de mise à jour.
     * @param obj L'objet à mettre à jour
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return obj L'objet à mettre à jour
     */
    @Override
    public ComposantDessin update(final ComposantDessin obj)
            throws IOException, SQLException {
        this.setConnect();
        String updateString = "select * from formes where nom= ?";
        try (PreparedStatement update =
                getConnect().prepareStatement(updateString)) {
            update.setString(1, obj.getNom().toLowerCase());
            update.execute();
            ResultSet res = update.getResultSet();
            if (!res.next()) {
                System.out.println("Ce nom n'a pas"
                        + " encore été utilisé pour un dessin,"
                        + "il n'y a donc pas de mise a jour possible.");
                this.create(obj);
            } else {
                this.delete(obj);
                this.create(obj);
                System.out.println("La mise à jour du dessin "
                        + obj.getNom()
                        + " a été effectué!\n");
            }
            res.close();
            update.close();
        } catch (org.apache.derby.shared.common.error
                .DerbySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        this.disconnect();
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
    public ComposantDessin find(final String nom2)
            throws FileNotFoundException, ClassNotFoundException, IOException,
            SQLException {
        this.setConnect();
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "composants_dessin".toUpperCase(), null);
        try {
            try (Statement creation = getConnect().createStatement()) {
                if (!rs.next()) {
                    this.disconnect();
                    return null;
                }
            }
            String nom = nom2.toLowerCase();
            String updateString = "select * from composants_dessin where nom = ?";
            try (PreparedStatement update =
                    getConnect().prepareStatement(updateString)) {
                update.setString(1, nom);
                update.execute();
                ResultSet res = update.getResultSet();
                boolean flag = res.next();
                if (!flag) {
                    System.out.println("Il n'y a pas de dessin de nom "
                            + nom + " dans la base de données!\n");
                    this.disconnect();
                    return null;
                } else {
                    ComposantDessin cd = new ComposantDessin(nom);
                    while (flag) {
                        String type = res.getString("type_composant");
                        if (type.equals("Carré")) {
                            Carre c = carreDAO.
                                    find(res.getString("nom_composant"));
                            cd.ajoute(c);
                        } else if (type.equals("Cercle")) {
                            Cercle c = cercleDAO.
                                    find(res.getString("nom_composant"));
                            cd.ajoute(c);
                        } else if (type.contentEquals("Rectangle")) {
                            Rectangle r =
                                    rectangleDAO.
                                    find(res.getString("nom_composant"));
                            cd.ajoute(r);
                        } else if (type.equals("Triangle")) {
                            Triangle c =
                                    triangleDAO.
                                    find(res.getString("nom_composant"));
                            cd.ajoute(c);
                        } else if (type.equals("Composant du dessin")) {
                            ComposantDessin cd2 =
                                    this.find(res.getString("nom_composant"));
                            cd.ajoute(cd2);
                        }
                        flag = res.next();
                    }
                    System.out.println("Un dessin de nom "
                            + nom + " a été trouvé dans la base de données!\n");
                    this.disconnect();
                    return cd;
                }
    
            } catch (org.apache.derby.shared.common.error
                    .DerbySQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            } 
        } catch (Exception e) {
            e.printStackTrace();
            this.disconnect();
        }
        this.disconnect();
        return null;
    }
}
