package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;
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
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    public ComposantDessin(final String nom2) throws NameAlreadyExistsException {
        if (listeNoms.indexOf(nom2) == -1) {
            listeNoms.add(nom2);
            this.nom = nom2;
            this.nomForme = "Composant du dessin";
        } else {
            throw new NameAlreadyExistsException("Ce nom existe dejà !");
        }
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
}
