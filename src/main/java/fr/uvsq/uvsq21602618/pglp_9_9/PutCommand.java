package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande d'ajout d'une forme dans un dessin ou un
 * composant du dessin.
 * @author Nathalie
 *
 */
public class PutCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La ligne de commande.
     */
    private String put;
    /**
     * Nom du compose.
     */
    private String compose;
    /**
     * Nom de la forme ou du composant a ajoute dans un dessin.
     */
    private String composant;
    /**
     * Le constructeur de la commande PutCommand.
     * @param ligne de commande
     * @param liste des formes dessinees
     */
    public PutCommand(final String ligne, final List<Forme> liste) {
        this.put = ligne;
        this.formes = liste;
        recuperation(this.compose, this.composant);
    }
    /**
     * Execution de la commande d'ajout d'un composant dans un autre.
     */
    @Override
    public void execute() {
        int flag = 0;
        flag = ajouteComposant(this.formes, flag);

        if (flag == 0) {
            System.out.println("Il semble que le composé : " + compose
                    + " n'a pas encore été dessiné!");
        } else if (flag == 1) {
            System.out.println("Le composant indiqué: " + composant
                    + " n'a pas encore"
                    + " été dessiné!");
        }
    }
    /**
     * Recupere le nom du compose auquel on veut ajouter un composant.
     * @param nom1 le compose
     * @param nom2 le composant
     */
    private void recuperation(final String nom1, final String nom2) {
        String str = this.put;
        str = str.replaceAll("put\\(", "");
        str = str.replaceAll("\\)", "");
        String[] tab = str.split(",");

        this.compose = tab[0].trim();
        this.composant = tab[1].trim();
    }
    /**
     * Methode pour ajouter un composant dans le compose d'une liste
     *  en argument.
     *  @param liste de formes
     *  @param flag vaut 1 si le compose est trouvé, vaut 2 si le
     *  composant est trouve.
     *  @return le flag
     */
    private int ajouteComposant(final List<Forme> liste, int flag) {
        for (Forme f: liste) {
            if (f.getNom().trim().equals(compose)) {
                flag = 1;
                for (Forme f2: liste) {
                    if (f2.getNom().trim().equals(composant)) {
                        flag = 2;
                        if (f instanceof ComposantDessin) {
                            ComposantDessin cd = (ComposantDessin) f;
                            Dessin d = (Dessin) f2;
                            cd.ajoute(d);
                            ajouteComposant(cd.getDessinFilsFormes(),
                                    flag);

                            formes.remove(f2);
                            System.out.println("Le composant " + composant
                                    + " a été ajouté dans le composé "
                                    + compose + "!");
                        } else {
                            System.out.println("Le composé indiqué: " + compose
                                    + " ne peut pas "
                                    + "contenir de composants!");
                        }
                    }
                }
            }
        }
        return flag;
    }
}