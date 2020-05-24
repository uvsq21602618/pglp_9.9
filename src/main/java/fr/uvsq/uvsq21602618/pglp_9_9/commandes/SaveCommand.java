package fr.uvsq.uvsq21602618.pglp_9_9.commandes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.uvsq.uvsq21602618.pglp_9_9.Carre;
import fr.uvsq.uvsq21602618.pglp_9_9.Cercle;
import fr.uvsq.uvsq21602618.pglp_9_9.ComposantDessin;
import fr.uvsq.uvsq21602618.pglp_9_9.Dessin;
import fr.uvsq.uvsq21602618.pglp_9_9.Forme;
import fr.uvsq.uvsq21602618.pglp_9_9.Rectangle;
import fr.uvsq.uvsq21602618.pglp_9_9.Triangle;
import fr.uvsq.uvsq21602618.pglp_9_9.DAO.DAO;
import fr.uvsq.uvsq21602618.pglp_9_9.DAO.DAOFactory;

/**
 * Classe pour la commande de sauvegarde dans la base de donnee du dessin en
 * cours.
 * @author Nathalie
 *
 */
public class SaveCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La ligne de commande.
     */
    private String save;
    /**
     * Nom du dessin en cours et de la sauvegarde.
     */
    private String nom;
    /**
     * Le constructeur de la commande SaveCommand.
     * @param ligne de commande
     * @param liste des formes dessinees
     */
    public SaveCommand(final String ligne, final List<Forme> liste) {
        this.save = ligne;
        this.formes = liste;
        this.nom = null;
    }
    /**
     * Execution de la commande de sauvegarde dans le base de donnees.
     * @throws SQLException Exception liee a la base de donnees
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    @Override
    public void execute() throws IOException, SQLException,
    ClassNotFoundException {
        recuperation();
        if (this.formes.isEmpty()) {
            System.out.println("Le dessin en cours est vide, il n'y a rien à"
                    + " sauvegarder!");
        } else if (this.nom == null) {
            System.out.println("Il faut indiquer un nom pour le dessin que l'on"
                    + " souhaite sauvegarder!");
        } else {
            DAO.setConnect();
            try {
                DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                        .getComposantDessinDAO();
                ComposantDessin dessin = new ComposantDessin(this.nom);
                for (Forme f : formes) {
                    Dessin d = (Dessin) f;
                    dessin.ajoute(d);
                }
                if (!verification(this.nom)) {
                    System.out.println("Le nom: " + this.nom
                            + " est deja utilise "
                            + "dans la base de donnees!\n"
                            + "Il faut en choisir un autre!\n");
                } else {
                    composantDessinDAO.create(dessin);
                    System.out.println("Le dessin en cours a été"
                            + " sauvegardé sous le"
                            + " nom: " + this.nom
                            + " dans la base de données!");
                }
            } catch (IOException | SQLException | ClassNotFoundException e) {
                DAO.disconnect();
                throw e;
            }
            DAO.disconnect();
        }
    }
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin a sauvegarder.
     */
    private void recuperation() {
        String str = this.save;
        str = str.replaceAll("save\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
    /**
     * Fonction pour verifier si le nom a deja ete utilise.
     * @param nom2 qui va être creee
     * @return true si le nom est disponible, false sinon
     * @throws SQLException Exception liee a la base de donnes
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public boolean verification(final String nom2) throws IOException,
    SQLException, ClassNotFoundException {

        DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                .getComposantDessinDAO();
        if (composantDessinDAO.find(nom2) != null) {
            return false;
        } else {
            DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
            if (carreDAO.find(nom2) != null) {
                return false;
            } else {
                DAO<Cercle> cercleDAO = new DAOFactory()
                        .getCercleDAO();
                if (cercleDAO.find(nom2) != null) {
                    return false;
                } else {
                    DAO<Rectangle> rectangleDAO = new DAOFactory()
                            .getRectangleDAO();
                    if (rectangleDAO
                            .find(nom2) != null) {
                        return false;
                    } else {
                        DAO<Triangle> triangleDAO = new DAOFactory()
                                .getTriangleDAO();
                        if (triangleDAO
                                .find(nom2) != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
