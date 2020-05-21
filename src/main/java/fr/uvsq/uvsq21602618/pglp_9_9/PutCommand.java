package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande d'ajout d'une forme dans un dessin ou un
 * composant du dessin.
 * @author Nathalie
 *
 */
public class PutCommand implements Command{
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
        for (Forme f: this.formes) {
            if (f.getNom().trim().equals(compose)) {
                for (Forme f2: this.formes) {
                    if (f2.getNom().trim().equals(composant)) {
                        if(f instanceof ComposantDessin) {
                            ComposantDessin cd = (ComposantDessin) f;
                            Dessin d = (Dessin) f2;
                            cd.ajoute(d);

                            formes.remove(f2);
                            System.out.println("Le composé " + compose
                                    + " a été ajouté dans le composant "
                                    + composant + "!");
                        } else {
                            System.out.println("Le compose indique ne peut pas"
                                    + "contenir de composants!");
                        }
                    } else {
                        System.out.println("Le composant indiqué n'a pas encore"
                                + " été dessiné!");
                    }
                }


            } else {
                System.out.println("Le composé indiqué n'a pas encore"
                        + " été dessiné!");
            }
        }    
    }
    /**
     * Recupere le nom du compose auquel on veut ajouter un composant.
     * @param nom1 le compose
     * @param nom2 le composant
     */
    public void recuperation(String nom1, String nom2) {
        String str = this.put;
        str.replaceAll("put\\(", "");
        str.replaceAll("\\)", "");
        String[] tab = str.split(",");

        this.compose = tab[0].trim();
        this.composant = tab[1].trim();
    }
}
