package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande de suppression du dessin.
 * @author Nathalie
 *
 */
public class DeleteCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * Nom de la forme a supprimer.
     */
    private String nom;
    /**
     * La ligne de commande.
     */
    private String suppression;
    /**
     * Constructeur de DeleteCommand.
     * @param ligne la commande de l'utilisateur
     * @param liste la liste des formes dessinees
     */
    public DeleteCommand(final String ligne, final List<Forme> liste) {
        this.suppression = ligne;
        recuperation();
        this.formes = liste;
    }
    /**
     * Execution de la commande de suppression.
     */
    @Override
    public void execute() {
        boolean flag = false;
        for (Forme f: this.formes) {
            if (f.getNom().trim().equals(nom)) {
                System.out.println("Suppression du " + f.getNomForme()
                + " " + f.getNom() + "!");
                this.formes.remove(f);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Le nom de cette forme n'existe pas!");
        }
    }
    /**
     * Fonction pour recuperer le nom de la forme a supprimer.
     */
    public void recuperation() {
        String str = this.suppression;
        str = str.replaceAll("delete\\(", "");
        str = str.replaceAll("\\)", "");
        str = str.trim();

        this.nom = str;
    }
}
