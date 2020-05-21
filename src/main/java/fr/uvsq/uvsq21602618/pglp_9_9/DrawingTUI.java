package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Classe qui se charge des interactions avec l'utilisateur.
 * @author Nathalie
 *
 */
public class DrawingTUI {
    /*
     * Cette classe fournira une méthode `nextCommand` qui analysera le texte saisi
     *  par l’utilisateur et retournera un objet implémentant l’interface Commande (cf. question suivante).
Elle proposera également une méthode permettant d'afficher un dessin.
1. Les commandes seront implémentées à l’aide du modèle de conception _Commande_.
   1. créer l’interface `Command` comportant la méthode `execute`,
   1. créer une classe implémentant cette interface pour chaque action.
1. Réaliser la classe principale `DrawingApp`.
     */
    /**
     * La table de hachage qui associe une chaine
     * de caractere a une commande.
     */
    private final HashMap<String, Command> commandes;
    /**
     * Le scanner pour la console.
     */
    private Scanner scanner;
    /**
     * Liste des formes dessinées.
     */
    private LinkedList<Forme> formes;
    /**
     * Liste des noms utilisés pour les dessins.
     */
    private LinkedList<String> noms;
    /**
     * Le constructeur de la classe.
     */
    public DrawingTUI() {
        this.commandes = new HashMap<String, Command>();
        this.scanner = new Scanner(System.in, "UTF-8");
        this.formes = new LinkedList<Forme>();
        this.noms = new LinkedList<String>();
    }
    /**
     * Méthode qui analysera le texte saisi
     * par l’utilisateur et retournera un objet implémentant l’interface
     *  Commande.
     * @param nom de la commande
     * @param commande l'instance
     * @return la commande à faire
     */
    public Command nextCommand(final String nom,
            final Command commande) {
        Command com = null;

        while (!scanner.hasNext("quit") && scanner.hasNext()) {
            String ligne = scanner.next().toLowerCase();
            if (ligne.contains(("rectangle")) || (ligne.contains("cercle"))
                    || (ligne.contains("carre")) || (ligne.contains("triangle"))
                    || (ligne.contains("dessin"))) {
                com = new CreateCommand(ligne);
                CreateCommand create = (CreateCommand) com;
                if (verification(create.getForme())) {
                    this.formes.add(create.getForme());
                    this.noms.add(create.getForme().getNom());
                    return com;
                }
            } else if (ligne.contains("delete")) {
                com = new DeleteCommand(ligne, this.formes, this.noms);
                return com;
            } else if (ligne.contains("put")) {
                com = new PutCommand(ligne, this.formes);
                return com;
            } else if (ligne.contains("move")) {
                if(ligne.contains("moveall")) {
                    com = new MoveCommand(ligne, this.formes);
                    return com;
                } else {
                    com = new MoveAllCommand(ligne, this.formes);
                    return com;
                }
            } else if (ligne.contains("show")) {
                com = new MoveCommand(ligne, this.formes);
                return com;
            }
        }

        return com;
    }
    /**
     * Fonction pour verifier si le nom a deja ete utilise.
     * @param forme qui va être creee
     * @return true si le nom est disponible, false sinon
     */
    public boolean verification(final Forme forme) {
        for (String s : this.noms) {
            if (s.equals(forme.getNom())) {
                System.out.println("Ce nom a deja ete utilise!");
                return false;
            }
        }
        return true;
    }
}
