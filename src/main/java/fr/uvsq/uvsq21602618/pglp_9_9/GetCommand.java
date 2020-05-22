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
            System.out.println("Indiquer le nom du dessin a recuperer!");
        } else {
            if (!this.formes.isEmpty()) {
                System.out.println("Recuperer le dessin demandé remplacera "
                        + "le dessin actuel! Veuillez le sauvegarder  avec"
                        + " save(nom de la sauvegarde) puis deleteAll pour "
                        + " tout supprimer!");
            } else {
                DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                        .getComposantDessinDAO();
                DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
                DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
                DAO<Rectangle> rectangleDAO = new DAOFactory().getRectangleDAO();
                DAO<Triangle> triangleDAO = new DAOFactory().getTriangleDAO();

                if (composantDessinDAO.find(this.nom) != null) {
                    Forme f = (Forme) composantDessinDAO.find(this.nom);
                    formes.add(f);
                } else if (carreDAO.find(this.nom) != null) {
                    Forme f = (Forme) carreDAO.find(this.nom);
                    formes.add(f);
                } else if (cercleDAO.find(this.nom) != null) {
                    Forme f = (Forme) cercleDAO.find(this.nom);
                    formes.add(f);
                } else if (rectangleDAO.find(this.nom) != null) {
                    Forme f = (Forme) rectangleDAO.find(this.nom);
                    formes.add(f);
                } else if (triangleDAO.find(this.nom) != null) {
                    Forme f = (Forme) triangleDAO.find(this.nom);
                    formes.add(f);
                }
                System.out.println("Le dessin: " + this.nom + " a été"
                        + " récupéré!");
            }
        }     
    } 
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin a recuper.
     */
    private void recuperation() {
        String str = this.get;
        str = str.replaceAll("get\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
}
