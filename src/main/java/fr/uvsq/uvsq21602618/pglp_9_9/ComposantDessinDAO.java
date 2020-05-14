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
                "composants_dessin".toUpperCase(), null);

            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table composants_dessin"
                            + "(nom varchar(30), type_composant varchar(30),"
                            + " nom_composant varchar(30),"
                            + "primary key (nom, nom_composant), "
                            + "foreign key (nom) references formes(nom),"
                            + "foreign key (nom_composant) references formes(nom))");
                }
                try {
                    String updateString = ("insert into composants_dessin values ("
                            + "?, ?, ? )");
                    PreparedStatement update;
                    Forme f;
                    ComposantDessin cd;
                    for (Dessin dessin : obj.getDessinFils()) {
                        update = getConnect().prepareStatement(updateString);
                        update.setString(1, obj.getNom());
                        f = (Forme) dessin;
                        update.setString(2, f.getNomForme());
                        update.setString(TROIS, f.getNom());

                        if (dessin instanceof Carre) {
                            Carre c = (Carre) dessin;
                            carreDAO.create(c);
                        } else if (dessin instanceof Cercle) {
                            Cercle c = (Cercle) dessin;
                            cercleDAO.create(c);
                        } else if (dessin instanceof Rectangle) {
                            Rectangle r = (Rectangle) dessin;
                            rectangleDAO.create(r);
                        } else if (dessin instanceof Triangle){
                            Triangle t = (Triangle) dessin;
                            triangleDAO.create(t);
                        }else if (dessin instanceof ComposantDessin) {
                            cd = (ComposantDessin) dessin;
                            this.create(cd);
                        }
                        update.executeUpdate();
                        update.close();
                    }
                    rs = creation2.executeQuery("SELECT * FROM composants_dessin");
    
                    System.out.println("-------Table composants_dessin:--------\n");
                    System.out.println("nom\t\t type_composant\t\t nom_composant");
                    while (rs.next()) {
                        System.out.printf("%s\t\t%s\t\t%s%n", rs.getString("nom"),
                                rs.getString("type_composant"), rs.getString("nom_composant"));
                    }
                    System.out.println("---------------------------------------------\n");
                    rs.close();
    
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré!\n\n");
                }  catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé dans composants_dessin!");
                }
                this.affichageTable();
                creation2.close();
                
            }
        }
        return obj;
    }
    /**
     * Methode pour afficher le contenu de la table composants_dessin.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     */
    public void affichageTable() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "composants_dessin".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM composants_dessin")) {
                        System.out.println("--------Table composants_dessin:--------\n");
                        System.out.println("nom\t\t type_composant\t\t nom_composant");
                        while (rs.next()) {
                            System.out.printf("%s\t\t%s\t\t%s%n", rs.getString("nom"),
                                    rs.getString("type_composant"), rs.getString("nom_composant"));
                        }
                        System.out.println("---------------------------------------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de dessins"
                        + " dans la base de données!\n");
            }
        }
    }
    @Override
    public void delete(ComposantDessin obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public ComposantDessin update(ComposantDessin obj) throws IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ComposantDessin find(int id)
            throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}