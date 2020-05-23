package fr.uvsq.uvsq21602618.pglp_9_9.commandes;

import java.util.List;

import fr.uvsq.uvsq21602618.pglp_9_9.Forme;

/**
 * Classe pour la commande d'affichage des informations sur tout le contenu
 * du dessin.
 * @author Nathalie
 *
 */
public class ShowAllCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * Constructeur  ShowCommand.
     * @param liste la liste des formes dessinees
     */
    public ShowAllCommand(final List<Forme> liste) {
        this.formes = liste;
    }
    /**
     * Execution de la commande d'affichage de tout le dessin.
     */
    @Override
    public void execute() {
        if (this.formes.isEmpty()) {
            System.out.println("Il n'y a rien a afficher!");
        } else {
            for (Forme f : formes) {
                f.affiche();
            }
        }
    }
}
