package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande d'affichage des informations sur le contenu
 * du dessin.
 * @author Nathalie
 *
 */
public class ShowCommand implements Command {
    /**
     * La ligne de commande.
     */
    private String show;
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * Nom de la forme a deplacer.
     */
    private String nom;
    /**
     * Constructeur  ShowCommand.
     * @param ligne la commande de l'utilisateur
     * @param liste la liste des formes dessinees
     */
    public ShowCommand(final String ligne, final List<Forme> liste) {
        this.show = ligne;
        this.formes = liste;
    }
    /**
     * Execution de la commande d'affichage.
     */
    @Override
    public void execute() {
        boolean flag = false;
        recuperation();
        if (this.formes.isEmpty()) {
            System.out.println("Il n'y a rien a afficher!");
            flag = true;
        } else {
            for (Forme f : formes) {
                if(f.getNom().equals(nom)) {
                    f.affiche();
                    flag = true;
                }
            }
        }
        if(!flag) {
            System.out.println("Le nom indiqué ne correspond a aucun dessin!");
        }
    }
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin dont on veut
     * afficher les informations.
     */
    private void recuperation() {
        String str = this.show;
        str = str.replaceAll("show\\(", "");
        str = str.replaceAll("\\)", "");

        this.nom = str.trim();
    }
}