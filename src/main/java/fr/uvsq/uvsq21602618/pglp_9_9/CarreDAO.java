package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe Pour le DAO de Carre.
 * @author Nathalie
 *
 */
public class CarreDAO extends DAO<Carre>{
    /**
     * initialisation de la constante 3 pour eviter le "magic number".
     */
    static final int TROIS = 3;
    /**
     * initialisation de la constante 4 pour eviter le "magic number".
     */
    static final int QUATRE = 4;
    /**
     * Constructeur de CarreDAO.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public CarreDAO() throws SQLException, IOException {
        super();
    }
    /**
     * Méthode de création.
     * @param obj L'objet à créer
     * @return obj qui vient d'etre cree
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    public Carre create(final Carre obj)
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
                    "carres".toUpperCase(), null);
            try (Statement creation2 = getConnect().createStatement()) {
                if (!rs.next()) {
                    creation2.executeUpdate("Create table carres"
                            + " (nom varchar(30) primary key, hg_x int not null,"
                            + " hg_y int not null, longueur int not null, "
                            + "foreign key (nom) references formes(nom))");
                }
                try {
                    String updateString = ("insert into carres values ("
                            + "?, ?, ?, ? )");
                    PreparedStatement update =
                            getConnect().prepareStatement(updateString);
                    update.setString(1, obj.getNom());
                    update.setInt(2, obj.getPointHG().getX());
                    update.setInt(TROIS, obj.getPointHG().getY());
                    update.setInt(QUATRE, obj.getLongueur());
                    update.executeUpdate();
                    update.close();
                    rs = creation2.executeQuery("SELECT * FROM carres");
    
                    System.out.println("---Table carres:---\n");
                    System.out.println("nom\t hg_x\t hg_y\t longueur");
                    while (rs.next()) {
                        System.out.printf("%s\t%d\t%d\t%d%n", rs.getString("nom"),
                                rs.getInt("hg_x"), rs.getInt("hg_y"),
                                rs.getInt("longueur"));
                    }
                    System.out.println("-----------------------"
                            + "-----------------------\n");
                    System.out.println("L'objet " + obj.getNom()
                    + " a bien été enregistré dans carres!\n");
                    rs.close();
                    creation2.close();
                } catch (org.apache.derby.shared.common.error
                        .DerbySQLIntegrityConstraintViolationException e) {
                    System.out.println("Ce nom a deja été utilisé dans carres!");
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
                    "carres".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM carres")) {
                        System.out.println("---Table carres:---\n");
                        System.out.println("nom\t hg_x\t hg_y\t longueur");
                        while (rs.next()) {
                            System.out.printf("%s\t%d\t%d\t%d%n", rs.getString("nom"),
                                    rs.getInt("hg_x"), rs.getInt("hg_y"),
                                    rs.getInt("longueur"));
                        }
                        System.out.println("-----------------------"
                                + "-----------------------\n");
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
    public void delete(Carre obj) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Carre update(Carre obj) throws IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Carre find(int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}
    /**
     * Méthode pour effacer.
     * @param obj L'objet à effacer
     * @throws SQLException Exception liee a l'acces a la base de donnees
     *
    public void delete(final GroupePersonnels obj) throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();

        try (Statement stmt = getConnect().createStatement()) {
            int idGroupe;
            idGroupe = obj.getId();
            String sql;
            try (Statement exist = getConnect()
                    .createStatement()) {
                ResultSet rs2 = dbmd.getTables(null, null,
                        "appartenance_personnel".toUpperCase(), null);
                if (rs2.next()) {
                    sql = "delete from appartenance_personnel where id_groupe="
                            + idGroupe;
                    stmt.executeUpdate(sql);
                }
                rs2.close();
            }

            try (Statement exist = getConnect().createStatement()) {
                ResultSet rs3 = dbmd.getTables(null, null,
                        "appartenance_sous_groupe".toUpperCase(), null);
                if (rs3.next()) {
                    sql = "delete from appartenance_sous_groupe"
                            + " where id_groupe="
                            + idGroupe;
                    stmt.executeUpdate(sql);

                    sql = "delete from appartenance_sous_groupe"
                            + " where id_sousGroupe=" + idGroupe;
                    stmt.executeUpdate(sql);

                    sql = "delete from groupe_personnels where id="
                            + idGroupe;
                    stmt.executeUpdate(sql);

                    exist.close();
                }
                rs3.close();
            }
            stmt.close();

            System.out.printf("Le groupe avec l'id " + obj.getId()
            + " a bien été supprimé!\n");

        }

    }
    /**
     * Méthode de mise à jour.
     * @param obj L'objet à mettre à jour
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @return obj L'objet à mettre à jour
     *
    public GroupePersonnels update(final GroupePersonnels obj)
            throws SQLException,
            IOException {
        try (Statement stmt = getConnect().createStatement()) {
            try (ResultSet result = stmt.executeQuery("select *"
                    + "from groupe_personnels where id="
                    + obj.getId())) {
                if (!result.next()) {
                    System.out.println("Cet identifiant pour groupe n'a pas"
                            + " encore été utilisé,"
                            + "il n'y a donc pas de mise a jour possible.");
                    this.create(obj);
                } else {
                    this.delete(obj);
                    this.create(obj);
                    System.out.println("La mise à jour du groupe d'id "
                            + obj.getId()
                            + " dans la table groupe_personnels"
                            + " a été effectué!\n");
                }
                stmt.close();
                return obj;
            }
        }
    }
    /**
     * Méthode de recherche des informations.
     * @param id de l'information
     * @return gp le GroupePersonnel du fichier, null sinon
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws FileNotFoundException liee au fichier non trouve
     * @throws IOException liee aux entreés/sorties
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     *
    public GroupePersonnels find(final int id) throws SQLException,
    FileNotFoundException, ClassNotFoundException, IOException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        GroupePersonnels search = null;
        try (Statement stmt = getConnect().createStatement()) {
            try (ResultSet rs = stmt.executeQuery("select *"
                    + "from groupe_personnels"
                    + " where id=" + id)) {

                if (!rs.next()) {
                    System.out.println("Il n'y a pas de groupe de"
                            + " personnels correspondant a l'id"
                            + id + " dans la table groupe_personnels!\n");
                    return null;
                }
                String nomGroupe = rs.getString("nom_groupe");
                search = new GroupePersonnels(nomGroupe, id);
                Personnel p;
                GroupePersonnels gp;
                int idComp;

                try (Statement exist = getConnect().createStatement()) {
                    ResultSet rsEx = dbmd.getTables(null, null,
                            "appartenance_personnel".toUpperCase(),
                            null);
                    if (rsEx.next()) {
                        try (ResultSet rs1 = stmt.executeQuery("select * from"
                                + " appartenance_personnel"
                                + " where id_groupe= " + id)) {
                            while (rs1.next()) {
                                idComp = rs1.getInt("id_personnel");
                                p = persoJDBC.find(idComp);
                                if (p != null) {
                                    search.add(p);
                                }
                                rs1.close();
                            }
                        }
                    } else {
                        System.out.println("Il n'y a pas encore de personnels"
                                + " dans un groupe!\n");
                    }
                    try (Statement exist2 = getConnect().createStatement()) {
                        ResultSet rsEx2 = dbmd.getTables(null, null,
                                "appartenance_sous_groupe".toUpperCase(),
                                null);
                        if (rsEx2.next()) {
                            try (ResultSet rs2 = stmt.executeQuery("select"
                                    + " * from"
                                    + " appartenance_sous_groupe"
                                    + " where id_groupe= " + id)) {
                                while (rs2.next()) {
                                    idComp = rs2.getInt("id_sousGroupe");
                                    gp = this.find(idComp);
                                    if (gp != null) {
                                        search.add(gp);
                                    }
                                }
                                System.out.println("Le groupe suivant"
                                        + " a ete trouve"
                                        + " avec l'identifiant " + id + ":");
                                System.out.println(search.toString() + "\n");
                                rs.close();
                                rs2.close();
                                stmt.close();
                                return search;
                            }
                        } else {
                            System.out.println("Il n'y a pas encore de groupes"
                                    + " dans un groupe!\n");
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * Methode pour creer la table qui associe le composant de classe Personnel
     * avec le groupe de Personnels auquel il appartient.
     * @param idGroupe identifiant du groupe
     * @param idPerso identifiant du personnel
     * @throws SQLException Exception liee a l'acces a la base de donnees
     *
    private void appartientPersonnel(final int idGroupe, final int idPerso)
            throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "appartenance_personnel".toUpperCase(), null);

        try (Statement stmt = getConnect().createStatement()) {
            if (!rs.next()) {
                stmt.executeUpdate("Create table"
                        + " appartenance_personnel"
                        + " (id_groupe int NOT NULL, id_personnel"
                        + " int NOT NULL, "
                        + "primary key (id_groupe, id_personnel),"
                        + "foreign key (id_groupe) references"
                        + " groupe_personnels(id), "
                        + "foreign key (id_personnel)"
                        + " references personnel(id))");
            }

            try {
                stmt.executeUpdate("insert into"
                        + " appartenance_personnel values ("
                        + idGroupe + "," + idPerso + ")");
                try (ResultSet rs1 = stmt.executeQuery("SELECT * FROM"
                        + " appartenance_personnel")) {
                    System.out.println("---Table appartenance_personnel:---\n");
                    System.out.println("id_groupe\t id_personnel");
                    while (rs1.next()) {
                        System.out.printf("%d\t\t%d%n",
                                rs1.getInt("id_groupe"),
                                rs1.getInt("id_personnel"));
                    }
                    System.out.println("--------------------"
                            + "----------------\n");
                    rs1.close();
                    rs.close();
                    stmt.close();
                }
            }  catch (org.apache.derby.shared.common
                    .error.DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Cet id a deja était utilisé pour la "
                        + "table appartenance_personnel!\n");
            }
        }
    }
    /**
     * Methode pour creer la table qui associe le composant de classe
     * GroupePersonnels avec le groupe de Personnels auquel il appartient.
     * @param idGroupe identifiant du groupe
     * @param idSousGr identifiant du sous groupe
     * @throws SQLException Exception liee a l'acces a la base de donnees
     *
    private void appartientGroupe(final int idGroupe, final int idSousGr)
            throws SQLException {

        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,
                "appartenance_sous_groupe".toUpperCase(), null);

        try (Statement stmt =
                getConnect().createStatement()) {
            if (!rs.next()) {
                stmt.executeUpdate("Create table appartenance_sous_groupe"
                        + " (id_groupe int NOT NULL, id_sousGroupe"
                        + " int NOT NULL, "
                        + "primary key (id_groupe, id_sousGroupe),"
                        + "foreign key (id_groupe) references"
                        + " groupe_personnels(id), "
                        + "foreign key (id_sousGroupe) references"
                        + " groupe_personnels(id))");
            }

            try {
                stmt.executeUpdate("insert into appartenance_sous_groupe"
                        + " values ("
                        + idGroupe + "," + idSousGr + ")");
                try (ResultSet rs1 = stmt.executeQuery("SELECT * FROM"
                        + " appartenance_sous_groupe")) {
                    System.out.println("---Table"
                            + " appartenance_sous_groupe:---\n");
                    System.out.println("id_groupe\t id_sousGroupe");
                    while (rs1.next()) {
                        System.out.printf("%d\t\t%d%n", rs1.getInt("id_groupe"),
                                rs1.getInt("id_sousGroupe"));
                    }
                    System.out.println("---------------------------"
                            + "---------\n");
                    rs1.close();
                    rs.close();
                    stmt.close();
                }
            }  catch (org.apache.derby.shared.common
                    .error.DerbySQLIntegrityConstraintViolationException e) {
                System.out.println("Cet id a deja était utilisé pour la "
                        + "table appartenance_sous_groupe!\n");
            }
        }
    }
    /**
     * Methode pour afficher le contenu de la table groupe_personnels.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     *
    public void affichageTableGroupePersonnels() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "groupe_personnels".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM groupe_personnels")) {
                        System.out.println("---Table groupe_personnels:---\n");
                        System.out.println("id\t nom_groupe\t");
                        while (rs.next()) {
                            System.out.printf("%d\t%s%n", rs.getInt("id"),
                                    rs.getString("nom_groupe"));
                        }
                        System.out.println("-----------------------"
                                + "-------------\n");

                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de"
                        + " groupe de personnels!\n");
            }
        }
    }

    /**
     * Methode pour afficher le contenu de la table appartenance_personnel.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     *
    public void affichageTableAppartenancePersonnel() throws SQLException {
        DatabaseMetaData dbmd = getConnect().getMetaData();
        try (Statement exist = getConnect().createStatement()) {
            ResultSet rsEx = dbmd.getTables(null, null,
                    "appartenance_personnel".toUpperCase(),
                    null);
            if (rsEx.next()) {
                try (Statement stmt = getConnect().createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT *"
                            + " FROM appartenance_personnel")) {
                        System.out.println("---Table appartenance"
                                + "_personnels:---\n");
                        System.out.println("id_groupe\t id_personnel\t");
                        while (rs.next()) {
                            System.out.printf("%d\t\t%d%n",
                                    rs.getInt("id_groupe"),
                                    rs.getInt("id_personnel"));
                        }
                        System.out.println("-----------------------"
                                + "-------------\n");
                        rs.close();
                    }
                }
            } else {
                System.out.println("Il n'y a pas encore de groupes ayant"
                        + " des personnels!\n");
            }
        }
    }
}*/
