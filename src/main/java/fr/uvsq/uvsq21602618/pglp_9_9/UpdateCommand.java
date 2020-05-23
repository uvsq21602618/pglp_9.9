package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe pour la commande de mise à jour d'un dessin dans la base de donnees.
 * @author Nathalie
 *
 */
public class UpdateCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La ligne de commande.
     */
    private String update;
    /**
     * Nom du dessin a mettre a jour.
     */
    private String nom;
    /**
     * Le constructeur de la commande SaveCommand.
     * @param ligne de commande
     * @param liste des formes dessinees
     */
    public UpdateCommand(final String ligne, final List<Forme> liste) {
        this.update = ligne;
        this.formes = liste;
        this.nom = null;
    }
    /**
     * Execution de la commande de mise a jour dans le base de donnees.
     * @throws SQLException Exception liee a la base de donnees
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    @Override
    public void execute() throws SQLException, IOException, ClassNotFoundException {
        recuperation();
        boolean flag = false;
        if (this.nom == null) {
            System.out.println("Il faut indiquer un nom pour le dessin que l'on"
                    + " souhaite mettre a jour!");
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
                Forme up = null;
                obj = composantDessinDAO.find(this.nom);
                for (Forme f : this.formes) {
                    if (f.getNom().equals(this.nom)) {
                        up = f;
                    }
                }

                if (obj != null && up == null) {
                    ComposantDessin cd = new ComposantDessin(this.nom);
                    for (Forme f : this.formes) {
                        cd.ajoute((Dessin) f);
                    }
                    composantDessinDAO.update(cd);
                    flag = true;          
                } else if (obj != null && up instanceof ComposantDessin) {
                    composantDessinDAO.update((ComposantDessin) up);
                    flag = true;
                } else {
                    obj = carreDAO.find(this.nom);
                    if (obj != null && up instanceof Carre) {
                        carreDAO.update((Carre) up);
                        flag = true;
                    } else {
                        obj = cercleDAO.find(this.nom);
                        if (obj != null && up instanceof Cercle) {
                            cercleDAO.update((Cercle) up);
                            flag = true;
                        } else {
                            obj = rectangleDAO.find(this.nom);
                            if (obj != null && up instanceof Rectangle) {
                                rectangleDAO.update((Rectangle) up);
                                flag = true;
                            } else {
                                obj = triangleDAO.find(this.nom);
                                if (obj != null && up instanceof Triangle) {
                                    triangleDAO.update((Triangle) up);
                                    flag = true;
                                }
                            }
                        }
                        if (flag) {
                            System.out.println("Le dessin : " + this.nom 
                                    + " a été mis "
                                    + "a jour!\n");
                        }
                        else {
                            System.out.println("Le dessin : " + this.nom 
                                    + " a mettre a jour n'est "
                                    + "pas dans la base de donnée!\n");
                        }
                    }
                }
            } catch (IOException | SQLException  | ClassNotFoundException e) {
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
        String str = this.update;
        str = str.replaceAll("update\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
}
