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
     * La liste des noms des formes dessinees.
     */
    private List<String> noms;
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
     * @param listeNoms de la liste des noms deja utilise pour un dessin ou une
     * forme.
     */
    public DeleteCommand(final String ligne, final List<Forme> liste,
            final List<String> listeNoms) {
        this.suppression = ligne;
        recuperation();
        this.formes = liste;
        this.noms = listeNoms;
    }
    /**
     * Execution de la commande de suppression.
     */
    @Override
    public void execute() {
        boolean flag = false;
        flag = suppression(this.formes);
        if (!flag) {
            System.out.println("Le nom de cette forme n'existe pas!");
        } else {
            System.out.println("Suppression de"
                    + " " + nom + "!");
            this.noms.remove(nom);
        }
    }

    public boolean suppression(final List<Forme> liste) {
        boolean flag = false;
        Forme remove = null;
        ComposantDessin cd = null;
        for (Forme f: liste) {
            if (f.getNom().trim().equals(nom)) {
                remove = f; 
                flag = true;
            } else {
                if (f instanceof ComposantDessin) {
                    cd = (ComposantDessin) f;
                    flag = suppression(cd.getDessinFilsFormes());
                }
            }
        }
        if(remove != null) {
            liste.remove(remove);
        }
        if(cd != null) {
            cd.deleteFils(nom);
        }
        return flag;
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
