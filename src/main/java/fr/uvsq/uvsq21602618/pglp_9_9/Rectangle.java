package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du rectangle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Rectangle extends Forme {
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
        this.nomForme ="Rectangle";
        this.hg = p1;
        this.bd = p2;
    }
    @Override
    public void dessine() {
        // TODO Auto-generated method stub
        
    }

}
