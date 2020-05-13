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
        this.nom = nom2;
        this.nomForme = "Cercle";
        this.centre = p;
        this.rayon = ray;
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
     * Retourne le nom de la forme.
     * @return le nom de la forme.
     */
    @Override
    public String getNomForme() {
        return this.nomForme;
    }
    /**
     * Retourne le nom du cercle.
     * @return nom
     */
    @Override
    public String getNom() {
        return this.nom;
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
    public void affiche() {
        String s =this.nom + " = " + this.nomForme
                + "((" + this.centre.getX() + ", " + this.centre.getY()
                + "), " + this.rayon + ")\n";
        System.out.println(s);       
    }
}
