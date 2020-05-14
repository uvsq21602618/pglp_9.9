package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Le composite derive de Forme.
 * @author Nathalie
 *
 */
public class ComposantDessin extends Forme implements Dessin { 
    /**
     * Liste de composants. 
     */
    private ArrayList<Dessin> dessinFils = new ArrayList<Dessin>();
    /**
     * Constructeur de ComposantDessin.
     * @param nom2
     */
    public ComposantDessin(final String nom2) {
        this.nom = nom2;
        this.nomForme = "Composant du dessin";
    }
    /**
     * Methode pour afficher les informations du composant.
     */
    public void affiche() {
        System.out.println(this.nom + " = " + this.nomForme + ":\n");
        for (Dessin dessin : dessinFils) {
            dessin.affiche();
        }
    }
    /**
     * Ajouter un dessin a la liste.
     * @param dessin un dessin
     */
    public void ajoute(Dessin dessin) {
        dessinFils.add(dessin);
    }
    /**
     * Retirer un dessin a la liste.
     * @param dessin un dessin
     */
    public void retire(Dessin dessin) {
        dessinFils.remove(dessin);
    }
    /**
     * Deplace tous les composants du dessin.
     * @param valx ajoute cette valeur a tous les x des formes
     * @param valy ajoute cette valeur a tous les y des formes
     */
    @Override
    public void deplace(final int valx, final int valy) {
        for (Dessin dessin : dessinFils) {
            ((ComposantDessin) dessin).deplace(valx, valy);
        }
    }
    /**
     * Retourne le type du composant.
     * @retourn le type du composant
     */
    @Override
    public String getNomForme() {
        return this.nomForme;
    }
    /**
     * Retourne le nom du composant.
     * @return le nom du composant
     */
    @Override
    public String getNom() {
        return this.nom;
    }
    /**
     * Retourne une liste non modifiable de dessinFils.
     * @return la liste de dessinFils
     */
    public List<Dessin> getDessinFils() {
        List<Dessin> liste = Collections.unmodifiableList(this.dessinFils);
        return liste;
    }
}
