package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du cercle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Cercle extends Forme implements Dessin {
    /**
     * Le centre du cercle.
     */
    private Point centre;
    /**
     * Le rayon du cercle.
     */
    private int rayon;
    /**
     * Constructeur d'un cercle.
     * @param nom2 le nom du cercle
     * @param p le centre du cercle
     * @param ray le rayon du cercle
     */
    public Cercle(final String nom2, final Point p, final int ray) {
        this.setNom(nom2);
        this.setNomForme("Cercle");
        this.centre = p;
        this.rayon = ray;
    }
    /**
     * Methode de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((centre == null) ? 0 : centre.hashCode());
        result = prime * result + rayon;
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
        Cercle other = (Cercle) obj;
        if (centre == null) {
            if (other.centre != null) {
                return false;
            }
        } else if (!centre.equals(other.centre)) {
            return false;
        }
        if (rayon != other.rayon) {
            return false;
        }
        return true;
    }
    /**
     * Methode pour deplacer le cercle.
     * @param valx la valeur a ajouter au x d'un point
     * @param valy la valeur a ajouter au y d'un point
     */
    @Override
    public void deplace(final int valx, final int valy) {
        this.centre.deplace(valx, valy);
    }
    /**
     * Retourne les coordonées du centre du cercle.
     * @return centre
     */
    public Point getCentre() {
        return this.centre;
    }
    /**
     * Retourne le rayon du cercle.
     * @return rayon
     */
    public int getRayon() {
        return this.rayon;
    }
    /**
     * Affiche les informations du cercle.
     */
    @Override
    public void affiche() {
        String s = this.getNom() + " = " + this.getNomForme()
                + "((" + this.centre.getX() + ", " + this.centre.getY()
                + "), " + this.rayon + ")";
        System.out.println(s);
    }
}
