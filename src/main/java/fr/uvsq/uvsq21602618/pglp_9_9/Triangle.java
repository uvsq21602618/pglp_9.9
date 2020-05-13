package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du triangle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Triangle extends Forme {
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
    public Triangle (final String nom2, final Point point1, final Point point2, final Point point3) {
        this.nom = nom2;
        this.nomForme = "Triangle";
        this.p1 = point1;
        this.p2 = point2;
        this.p3 = point3;
    }
    
    @Override
    public void dessine() {
        // TODO Auto-generated method stub
        
    }

}
