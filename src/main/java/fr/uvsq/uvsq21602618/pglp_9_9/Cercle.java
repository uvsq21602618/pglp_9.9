package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du cercle qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Cercle extends Forme {
    /**
     * Le centre du cercle.
     */
    private Point centre;
    /**
     * Le rayon du cercle.
     */
    private int rayon;
    
    public Cercle (final String nom2, final Point p, final int ray) {
        this.nom = nom2;
        this.centre = p;
        this.rayon = ray;
    }
    @Override
    public void dessine() {
        // TODO Auto-generated method stub
        
    }

}
