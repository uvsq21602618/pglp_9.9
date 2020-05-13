package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Super classe pour les formes dessinables.
 * @author Nathalie
 *
 */
public abstract class Forme {
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
     */
    public abstract void deplace(final int valx, final int valy) ;
    /**
     * Methode pour recuperer le nom de la forme.
     */
    public abstract String getNomForme();
    /**
     * Methode pour recuperer le nom.
     */
    public abstract String getNom();
   
}
