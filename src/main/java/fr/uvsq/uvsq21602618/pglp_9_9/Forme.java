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
    private String nom;
    /**
     * Definit le nom de la forme.
     * @param nom2 de la forme
     */
    public void setNom(final String nom2) {
        this.nom = nom2;
    }
    /**
     * Definit la forme.
     * @param nomForme2 la forme
     */
    public void setNomForme(final String nomForme2) {
        this.nomForme = nomForme2;
    }
    /**
     * La forme dessinée.
     */
    private String nomForme;
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
    public String getNomForme() {
        return this.nomForme;
    }
    /**
     * Methode pour recuperer le nom.
     * @return le nom de la forme
     */
    public String getNom() {
        return this.nom;
    }
    /**
     * Methode pour afficher les informations d'une forme.
     */
    public abstract void affiche();
}
