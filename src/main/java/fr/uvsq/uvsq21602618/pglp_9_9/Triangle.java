package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du triangle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Triangle extends Forme implements Dessin{
    /**
     * Premier point du triangle.
     */
    private Point p1;
    /**
     * Deuxieme point du triangle.
     */
    private Point p2;
    /**
     * Troisieme point du triangle.
     */
    private Point p3;
    /**
     * Constructeur d'un triangle.
     * @param nom2 Le nom du triangle
     * @param point1 Le premier point du triangle
     * @param point2 Le deuxieme point du triangle
     * @param point3 Le troisieme point du triangle
     */
    public Triangle(final String nom2, final Point point1,
            final Point point2, final Point point3) {
        this.nom = nom2;
        this.nomForme = "Triangle";
        this.p1 = point1;
        this.p2 = point2;
        this.p3 = point3;
    }
    /**
     * Methode pour deplacer le triangle.
     * @param valx la valeur a ajouter au x d'un point
     * @param valy la valeur a ajouter au y d'un point
     */
    @Override
    public void deplace(final int valx, final int valy) {
        this.p1.deplace(valx, valy);
        this.p2.deplace(valx, valy);
        this.p3.deplace(valx, valy);
    }
    /**
     * Methode qui retourne le nom du triangle.
     * @return le nom
     */
    @Override
    public String getNom() {
        return this.nom;
    }
    /**
     * Methode qui retourne le nom de la forme.
     * @return le nom de la forme
     */
    @Override
    public String getNomForme() {
        return this.nomForme;
    }
    /**
     * Retourne le premier point du triangle.
     * @return p1
     */
    public Point getPoint1() {
        return this.p1;
    }
    /**
     * Retourne le deuxieme point du triangle.
     * @return p2
     */
    public Point getPoint2() {
        return this.p2;
    }
    /**
     * Retourne le troisieme point du triangle.
     * @return p3
     */
    public Point getPoint3() {
        return this.p3;
    }
    /**
     * Affiche les informations du triangle.
     */
    public void affiche() {
        String s =this.nom + " = " + this.nomForme
                + "((" + this.p1.getX() + ", " + this.p1.getY()
                + "), (" + this.p2.getX() + ", " + this.p2.getY()
                + "), (" + this.p3.getX() + ", " + this.p3.getY()
                + "))\n";
        System.out.println(s);   
    }
    /**
     * Methode de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
        result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
        result = prime * result + ((p3 == null) ? 0 : p3.hashCode());
        return result;
    }
    /**
     * Methode de comparaison.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Triangle other = (Triangle) obj;
        if (p1 == null) {
            if (other.p1 != null) {
                return false;
            }
        } else if (!p1.equals(other.p1)) {
            return false;
        }
        if (p2 == null) {
            if (other.p2 != null) {
                return false;
            }
        } else if (!p2.equals(other.p2)) {
            return false;
        }
        if (p3 == null) {
            if (other.p3 != null) {
                return false;
            }
        } else if (!p3.equals(other.p3)) {
            return false;
        }
        return true;
    }

}
