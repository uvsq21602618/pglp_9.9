package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                .getComposantDessinDAO();
        recuperation();
        if (this.nom == null) {
            System.out.println("Il faut indiquer un nom pour le dessin que l'on"
                    + " souhaite sauvegarder!");
        } else {
            ComposantDessin dessin = new ComposantDessin(this.nom);
            for (Forme f : formes) {
                Dessin d = (Dessin) f;
                dessin.ajoute(d);
            }

            if (composantDessinDAO.find(this.nom) != null) {
                System.out.println("Le nom: " + this.nom + " est deja utilise "
                        + "dans la base de donnees!");
            } else {
                composantDessinDAO.create(dessin);
                composantDessinDAO.affichageTable();
                System.out.println("Le dessin en cours a été sauvegardé sous le"
                        + " nom: " + this.nom + " dans la base de données!");
            }
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
}
