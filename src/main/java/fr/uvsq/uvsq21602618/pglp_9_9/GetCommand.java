package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe pour la commande qui recupere un dessin sauvegarde dans la
 *  base de donnees.
 * @author Nathalie
 *
 */
public class GetCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La ligne de commande.
     */
    private String get;
    /**
     * Nom du dessin a recuperer dans la base de donnees.
     */
    private String nom;
    /**
     * Le constructeur de la commande GetCommand.
     * @param ligne de commande
     * @param liste des formes dessinees
     */
    public GetCommand(final String ligne, final List<Forme> liste) {
        this.get = ligne;
        this.formes = liste;
        this.nom = null;
    }
    /**
     * Execution de la commande de recuperation d'une sauvegarde d'un dessin
     * dans une base de donnees.
     */
    @Override
    public void execute() throws IOException, SQLException, ClassNotFoundException {
        recuperation();
        if (this.nom == null) {
            System.out.println("Indiquer le nom du dessin a recuperer!\n");
        } else {
            if (!this.formes.isEmpty()) {
                System.out.println("Recuperer le dessin demandé remplacera "
                        + "le dessin actuel! Veuillez le sauvegarder  avec"
                        + " save(nom de la sauvegarde) puis deleteAll pour"
                        + " tout supprimer!\n");
            } else {
                DAO.setConnect();
                try {
                    DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                            .getComposantDessinDAO();
                    DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
                    DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
                    DAO<Rectangle> rectangleDAO = new DAOFactory()
                            .getRectangleDAO();
                    DAO<Triangle> triangleDAO = new DAOFactory().getTriangleDAO();

                    Object obj = null;
                    obj = composantDessinDAO.find(this.nom);

                    if (obj != null) {
                        Forme f = (Forme) obj;
                        formes.add(f);
                    } else {
                        obj = carreDAO.find(this.nom);
                        if (obj != null) {
                            Forme f = (Forme) obj;
                            formes.add(f);
                        } else {
                            obj = cercleDAO.find(this.nom);
                            if (obj != null) {
                                Forme f = (Forme) obj;
                                formes.add(f);
                            } else {
                                obj = rectangleDAO.find(this.nom);
                                if (obj != null) {
                                    Forme f = (Forme) obj;
                                    formes.add(f);
                                } else {
                                    obj = triangleDAO.find(this.nom);
                                    if (obj != null) {
                                        Forme f = (Forme) obj;
                                        formes.add(f);
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Le dessin: " + this.nom + " a été"
                            + " récupéré!\n");
                } catch (IOException | SQLException | ClassNotFoundException e) {
                    DAO.disconnect();
                    throw e;
                }
                DAO.disconnect();
            }
        }
    }
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin a recuperer.
     */
    private void recuperation() {
        String str = this.get;
        str = str.replaceAll("get\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
}
