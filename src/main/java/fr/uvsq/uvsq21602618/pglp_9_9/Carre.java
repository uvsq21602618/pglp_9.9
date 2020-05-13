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
     * Methode pour retourner le nom du carre.
     */
    @Override
    public String getNom() {
        return this.nom;
    }
    /**
     * Methode pour retourner le nom de la forme.
     */
    @Override
    public String getNomForme() {
        return this.nomForme;
    }
    

}
