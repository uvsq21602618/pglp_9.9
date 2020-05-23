package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande les deplacements du dessin.
 * @author Nathalie
 *
 */
public class MoveCommand implements Command {
    /**
     * La ligne de commande.
     */
    private String move;
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * Nom de la forme a deplacer.
     */
    private String nom;
    /**
     * Les valeurs a deplacer pour le dessin.
     */
    private Point deplacement;
    /**
     * Constructeur  moveCommand.
     * @param ligne la commande de l'utilisateur
     * @param liste la liste des formes dessinees
     */
    public MoveCommand(final String ligne, final List<Forme> liste) {
        this.move = ligne;
        this.formes = liste;
    }
    /**
     * Execution de la commande de deplacements.
     */
    @Override
    public void execute() {
        boolean flag = false;
        try {
            recuperation();
            flag = deplace(this.formes);
            if (!flag) {
                System.out.println("Le dessin a deplace n'a pas ete trouve!");
            } else {
                System.out.println("Le composant " + this.nom
                        + " a ete deplace de (" + this.deplacement.getX()
                        + ", " + this.deplacement.getY() + ")!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("La commande n'a pas été saisie correctement!");
        }
    }
    /**
     * Methode pour deplacer une forme dans une liste donnee.
     * @param liste ou se situe le composant a deplacer
     * @param flag vaut vrai si un composant correspond, false sinon
     * @return flag
     */
    private boolean deplace(final List<Forme> liste) {
        boolean flag = false;
        for (Forme f: liste) {
            if (f.getNom().equals(this.nom)) {
                flag = true;
                f.deplace(this.deplacement.getX(),
                        this.deplacement.getY());
            } else {
                if (f instanceof ComposantDessin) {
                    flag = ((ComposantDessin) f).deplaceFils(nom
                            , deplacement.getX(), deplacement.getY());
                }
            }
        }
        return flag;
    }
    /**
     * Fonction pour recuperer le nom de la forme ou du dessin a deplacer.
     */
    private void recuperation() {
        String str = this.move;
        str = str.replaceAll("move\\(", "");
        str = str.trim();
        String[] tab = str.split(",");

        String x = tab[1];
        x = x.replaceAll("\\(", "").trim();
        String y = tab[2];
        y = y.replaceAll("\\)\\)", "").trim();
        this.deplacement = new Point(Integer.parseInt(x), Integer.parseInt(y));
        this.nom = tab[0].trim();
    }
}
