package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;
import java.util.List;

/**
 * Super classe pour les formes dessinables.
 * @author Nathalie
 *
 */
public abstract class Forme {
    /**
     * Le nom de la forme dessinée.
     */
    protected String nom;
    /**
     * La forme dessinée.
     */
    protected String nomForme;
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
