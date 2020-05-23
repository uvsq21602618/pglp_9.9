package fr.uvsq.uvsq21602618.pglp_9_9.commandes;

import java.util.List;

import fr.uvsq.uvsq21602618.pglp_9_9.Forme;

/**
 * Classe pour la commande de suppression du dessin en cours.
 * @author Nathalie
 *
 */
public class DeleteAllCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La liste des noms des formes dessinees.
     */
    private List<String> noms;
    /**
     * Le constructeur de la commande DeleteAllCommand.
     * @param liste des formes dessinees
     * @param listeNoms des noms des formes dessinees
     */
    public DeleteAllCommand(final List<Forme> liste,
            final List<String> listeNoms) {
        this.formes = liste;
        this.noms = listeNoms;
    }
    /**
     * Execution de la commande d'ajout de suppression du dessin en cours.
     */
    @Override
    public void execute() {
        this.formes.clear();
        this.noms.clear();
        System.out.println("Le dessin en cours a été supprimé!\n");
    }
}
