package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;
/**
 * Le composite derive de Forme.
 * @author Nathalie
 *
 */
public class ComposantDessin {
    /**
     * Liste de composants. 
     */
    private ArrayList<Dessin> dessinFils = new ArrayList<Dessin>();
    /**
     * Methode pour afficher les informations du composant.
     */
    public void affiche() {
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
}
