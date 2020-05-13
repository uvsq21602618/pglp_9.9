package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du carré qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Carre extends Forme {
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
        this.nom = nom2;
        this.nomForme = "Carré";
        this.hg = p;
        this.longueur = l;
    }
    @Override
    public void dessine() {
        // TODO Auto-generated method stub
        
    }

}
