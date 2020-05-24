package fr.uvsq.uvsq21602618.pglp_9_9;

/**
 * Classe pour le dessin du carré qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Carre extends Forme implements Dessin {
    /**
     * Le point en haut à gauche du carré.
     */
    private Point hg;
    /**
     * La longueur de chaque côté du carré.
     */
    private int longueur;
    /**
     * Constructeur du carre.
     * @param nom2 nom du carre
     * @param p point en haut a gauche du carre
     * @param l longueur du carre
     */
    public Carre(final String nom2, final Point p, final int l) {
            this.setNom(nom2);
            this.setNomForme("Carré");
            this.hg = p;
            this.longueur = l;
    }
    /**
     * Methode pour deplacer le carre.
     * @param valx la valeur a ajouter au x d'un point
     * @param valy la valeur a ajouter au y d'un point
     */
    @Override
    public void deplace(final int valx, final int valy) {
        this.hg.deplace(valx, valy);
    }
    /**
     * Methode pour retourner le point en haut a gauche du carre.
     * @return hg
     */
    public Point getPointHG() {
        return this.hg;
    }
    /**
     * Methode pour recuperer la longueur des cotes.
     * @return longueur
     */
    public int getLongueur() {
        return longueur;
    }
    /**
     * Affiche les informations du carre.
     */
    @Override
    public void affiche() {
        String s = this.getNom() + " = " + this.getNomForme()
                + "((" + this.hg.getX() + ", " + this.hg.getY()
                + "), " + this.longueur + ")";
        System.out.println(s);
    }
    /**
     * Methode de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hg == null) ? 0 : hg.hashCode());
        result = prime * result + longueur;
        return result;
    }
    /**
     * Methode de comparaison.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Carre other = (Carre) obj;
        if (hg == null) {
            if (other.hg != null) {
                return false;
            }
        } else if (!hg.equals(other.hg)) {
            return false;
        }
        if (longueur != other.longueur) {
            return false;
        }
        return true;
    }
}
