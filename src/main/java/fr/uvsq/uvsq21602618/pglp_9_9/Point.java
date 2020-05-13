package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour un point
 * @author Nathalie
 */
public class Point {
    /**
     * La coordonnée en abscisse du point.
     */
    private int x;
    /**
     * La coordonnée en ordonnée du point.
     */
    private int y;
    /**
     * Constructeur de point.
     * @param x2 abscisse du point
     * @param y2 ordonnée du point
     */
    public Point(final int x2, final int y2) {
        this.x = x2;
        this.y= y2;
    }
    /**
     * Methode pour retourner la coordonnee x du point.
     * @return x
     */
    public int getX() {
        return this.getX();
    }
    /**
     * Méthode pour retourner la coordonnee y du point.
     * @return y
     */
    public int getY() {
        return this.getY();
    }
    /**
     * Methode qui deplace un point.
     * @param valx la valeur a ajoute a x
     * @param valy la valeur a ajoute a y
     */
    public void deplace(final int valx, final int valy) {
        this.x += valx;
        this.y += valy;
    }
}
