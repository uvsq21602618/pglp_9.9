package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe pour la commande qui supprime un dessin dans la base de donnees.
 * @author Nathalie
 *
 */
public class DeleteBackUpCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La ligne de commande.
     */
    private String delBackUp;
    /**
     * Nom du dessin a supprimer dans la base de donnees.
     */
    private String nom;
    /**
     * Le constructeur de la commande DeleteBackUpCommand.
     * @param ligne de commande
     * @param liste des formes dessinees
     */
    public DeleteBackUpCommand(final String ligne, final List<Forme> liste) {
        this.delBackUp = ligne;
        this.formes = liste;
        this.nom = null;
    }
    /**
     * Execution de la commande de suppression d'un dessin dans la base de
     *  donnee.
     */
    @Override
    public void execute() throws IOException, SQLException, ClassNotFoundException {
        recuperation();
        if (this.nom == null) {
            System.out.println("Indiquer le nom du dessin a supprimer!");
        } else {
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
                composantDessinDAO.delete((ComposantDessin) obj);
            } else {
                obj = carreDAO.find(this.nom);
                if (obj != null) {
                    carreDAO.delete((Carre) obj);
                } else {
                    obj = cercleDAO.find(this.nom);
                    if (obj != null) {
                        cercleDAO.delete((Cercle) obj);
                    } else {
                        obj = rectangleDAO.find(this.nom);
                        if (obj != null) {
                            rectangleDAO.delete((Rectangle) obj);
                        } else {
                            obj = triangleDAO.find(this.nom);
                            if (obj != null) {
                                triangleDAO.delete((Triangle) obj);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin a supprimer.
     */
    private void recuperation() {
        String str = this.delBackUp;
        str = str.replaceAll("deletebackup\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
}
