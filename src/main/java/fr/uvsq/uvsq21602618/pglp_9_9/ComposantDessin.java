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
     * @param nom2 le nom du composant
     */
    public ComposantDessin(final String nom2) {
        this.setNom(nom2);
        this.setNomForme("Composant du dessin");
    }
    /**
     * Methode pour afficher les informations du composant.
     */
    @Override
    public void affiche() {
        if (!dessinFils.isEmpty()) {
            System.out.println("----" + this.getNom() + " = "
        + this.getNomForme() + "-----:");
            for (Dessin dessin : dessinFils) {
                dessin.affiche();
            }
        }
        System.out.println("-------------------------------------\n");
    }
    /**
     * Ajouter un dessin a la liste.
     * @param dessin un dessin
     */
    public void ajoute(final Dessin dessin) {
        dessinFils.add(dessin);
    }
    /**
     * Retirer un dessin a la liste.
     * @param dessin un dessin
     */
    public void retire(final Dessin dessin) {
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
            if (dessin instanceof Carre) {
                Carre c = (Carre) dessin;
                c.deplace(valx, valy);
            } else if (dessin instanceof Cercle) {
                Cercle c = (Cercle) dessin;
                c.deplace(valx, valy);
            } else if (dessin instanceof Rectangle) {
                Rectangle r = (Rectangle) dessin;
                r.deplace(valx, valy);
            } else if (dessin instanceof Triangle) {
                Triangle t = (Triangle) dessin;
                t.deplace(valx, valy);
            } else if (dessin instanceof ComposantDessin) {
                ComposantDessin cd = (ComposantDessin) dessin;
                cd.deplace(valx, valy);
            }
        }
    }
    /**
     * Fonction de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dessinFils == null) ? 0 : dessinFils.hashCode());
        return result;
    }
    /**
     * Methode de comparaison.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComposantDessin other = (ComposantDessin) obj;
        if (dessinFils == null) {
            if (other.dessinFils != null) {
                return false;
            }
        } else if (!dessinFils.equals(other.dessinFils)) {
            return false;
        }
        return true;
    }
    /**
     * Retourne une liste non modifiable de dessinFils.
     * @return la liste de dessinFils
     */
    public List<Dessin> getDessinFils() {
        List<Dessin> liste = Collections.unmodifiableList(this.dessinFils);
        return liste;
    }
    /**
     * Retourne la liste de dessins sous forme de Formes.
     * @return liste de formes
     */
    public ArrayList<Forme> getDessinFilsFormes() {
        ArrayList<Forme> formes = new ArrayList<Forme>();
        for (Dessin d : this.dessinFils) {
            Forme f = (Forme) d;
            formes.add(f);
        }
        return formes;
    }
    /**
     * Methode pour supprimer un fils base sur son nom.
     * @param nom du dessinFils a supprimer
     */
    public void deleteFils(final String nom) {
        Dessin remove = null;
        for (Dessin d : this.dessinFils) {
            if ((((Forme) d).getNom()).equals(nom)) {
                remove = d;
            }
        }
        if (remove != null) {
            this.dessinFils.remove(remove);
        }
    }
    /**
     * Methode pour deplacer un fils base sur son nom.
     * @param nom du dessinFils a deplacer
     * @param valx valeur du deplacement
     * @param valy valeur du deplacement
     * @return flag
     */
    public boolean deplaceFils(final String nom, final int valx,
            final int valy) {
        boolean flag = false;
        for (Dessin d : this.dessinFils) {
            if ((((Forme) d).getNom()).equals(nom)) {
                ((Forme) d).deplace(valx, valy);
                flag = true;
            } else {
                if (d instanceof ComposantDessin) {
                    ((ComposantDessin) d).deplace(valx, valy);
                }
            }
        }
        return flag;
    }
    /**
     * Methode pour afficher un fils en fonction de son nom.
     * @param nom du fils a afficher
     * @return flag
     */
    public boolean afficheFils(final String nom) {
        Dessin show = null;
        boolean flag = false;
        for (Dessin d : this.dessinFils) {
            if ((((Forme) d).getNom()).equals(nom)) {
                show = d;
                show.affiche();
                flag = true;
            } else {
                if (d instanceof ComposantDessin) {
                    flag = ((ComposantDessin) d).afficheFils(nom);
                }
            }
        }
        return flag;
    }
}
