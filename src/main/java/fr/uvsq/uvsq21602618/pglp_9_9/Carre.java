package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour le dessin du carré qui hérite de Forme.
 * @author Nathalie
 *
 */
public class Carre extends Forme implements Dessin {
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
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    public Carre(final String nom2, final Point p, final int l)
            throws NameAlreadyExistsException {
        if (listeNoms.indexOf(nom2) == -1) {
            listeNoms.add(nom2);
            this.nom = nom2;
            this.nomForme = "Carré";
            this.hg = p;
            this.longueur = l;
        } else {
            throw new NameAlreadyExistsException("Ce nom existe dejà !");
        }
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
    /**
     * Affiche les informations du carre.
     */
    public void affiche() {
        String s =this.nom + " = " + this.nomForme
                + "((" + this.hg.getX() + ", " + this.hg.getY()
                + "), " + this.longueur + ")\n";
        System.out.println(s);       
    }
}
