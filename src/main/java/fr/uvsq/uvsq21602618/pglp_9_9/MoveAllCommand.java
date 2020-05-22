package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.List;

/**
 * Classe pour la commande le deplacement de tout le dessin en cours.
 * @author Nathalie
 *
 */
public class MoveAllCommand implements Command {
    /**
     * La ligne de commande.
     */
    private String moveAll;
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * Les valeurs a deplacer pour le dessin.
     */
    private Point deplacement;
    /**
     * Constructeur  moveCommand.
     * @param ligne la commande de l'utilisateur
     * @param liste la liste des formes dessinees
     */
    public MoveAllCommand(final String ligne, final List<Forme> liste) {
        this.moveAll = ligne;
        this.formes = liste;
    }
    /**
     * Execution de la commande de deplacements.
     */
    @Override
    public void execute() {
        try {
            recuperation();
            deplace(this.formes);
            if (this.formes.isEmpty()) {
                System.out.println("Il n'y a rien a déplacer!");
            } else {
                System.out.println("Tout le dessin"
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
     */
    private void deplace(final List<Forme> liste) {
        for (Forme f: liste) {
            f.deplace(this.deplacement.getX(),
                    this.deplacement.getY());
            f.affiche();
        }
    }
    /**
     * Fonction pour recuperer les valeurs du deplacement.
     */
    private void recuperation() {
        String str = this.moveAll;
        str = str.replaceAll("moveall\\(", "");
        str = str.trim();
        String[] tab = str.split(",");

        String x = tab[0];
        x = x.replaceAll("\\(", "").trim();
        String y = tab[1];
        y = y.replaceAll("\\)", "").trim();
        if (tab.length > 2) {
            tab[2] = tab[2].replaceAll("\\)", "");
            System.out.println("L'argument en trop \'" + tab[2]
                    + "\' a été retiré!");
        }
        this.deplacement = new Point(Integer.parseInt(x), Integer.parseInt(y));
    }
}
