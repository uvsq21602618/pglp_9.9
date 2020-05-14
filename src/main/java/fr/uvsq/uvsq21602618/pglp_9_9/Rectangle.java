package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du rectangle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Rectangle extends Forme implements Dessin {
    /**
     * Le point en haut a gauche du rectangle.
     */
    private Point hg;
    /**
     * Le point en bas a droite du ractangle.
     */
    private Point bd;
    /**
     * Constructeur du rectangle.
     * @param nom2 le nom du rectangle
     * @param p1 le point en haut a gauche
     * @param p2 le point en bas a droite
     */
    public Rectangle(final String nom2, final Point p1, final Point p2) {
        this.nom = nom2;
        this.nomForme = "Rectangle";
        this.hg = p1;
        this.bd = p2;
    }
    /**
     * Methode de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bd == null) ? 0 : bd.hashCode());
        result = prime * result + ((hg == null) ? 0 : hg.hashCode());
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
        Rectangle other = (Rectangle) obj;
        if (bd == null) {
            if (other.bd != null) {
                return false;
            }
        } else if (!bd.equals(other.bd)) {
            return false;
        }
        if (hg == null) {
            if (other.hg != null) {
                return false;
            }
        } else if (!hg.equals(other.hg)) {
            return false;
        }
        return true;
    }
    /**
     * Methode pour deplacer le rectangle.
     * @param valx la valeur a ajouter au x d'un point
     * @param valy la valeur a ajouter au y d'un point
     */
    @Override
    public void deplace(final int valx, final int valy) {
        this.hg.deplace(valx, valy);
        this.bd.deplace(valx, valy);
    }
    /**
     * Methode pour recuperer le nom du rectangle.
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }
    /**
     * Methode pour recuperer le nom de la forme.
     * @return le nom de la forme
     */
    public String getNomForme() {
        return this.nomForme;
    }
    /**
     * Methode pour recupere le point en haut a gauche du rectangle.
     * @return hg
     */
    public Point getPointHG() {
        return this.hg;
    }
    /**
     * Methode pour recupere le point en bas a droite du rectangle.
     * @return bd
     */
    public Point getPointBD() {
        return this.bd;
    }
    /**
     * Affiche les informations du rectangle.
     */
    public void affiche() {
        String s = this.nom + " = " + this.nomForme
                + "((" + this.hg.getX() + ", " + this.hg.getY()
                + "), (" + this.bd.getX() + ", " + this.bd.getY()
                + "))\n";
        System.out.println(s);
    }
}
