package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;

/**
 * Super classe pour les formes dessinables.
 * @author Nathalie
 *
 */
public abstract class Forme {
    /**
     * Liste des noms deja existants.
     */
    public static ArrayList<String> listeNoms = new ArrayList<String>();
    /**
     * Le nom de la forme dessinée.
     */
    public String nom;
    /**
     * La forme dessinée.
     */
    public String nomForme;
    /**
     * Méthode pour deplacer une forme.
     * @param valx valeur a ajoute a x
     * @param valy valeur a ajoute a y
     */
    public abstract void deplace(int valx, int valy);
    /**
     * Methode pour recuperer le nom de la forme.
     * @return le nom
     */
    public abstract String getNomForme();
    /**
     * Methode pour recuperer le nom.
     * @return le nom de la forme
     */
    public abstract String getNom();
}
