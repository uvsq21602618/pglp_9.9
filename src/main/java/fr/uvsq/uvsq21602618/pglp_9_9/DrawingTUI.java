package fr.uvsq.uvsq21602618.pglp_9_9;

import java.util.HashMap;
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
     * Le constructeur de la classe.
     */
    public DrawingTUI() {
        this.commandes = new HashMap<String, Command>();
        this.scanner = new Scanner(System.in, "UTF-8");
    }
    /**
     * Méthode qui analysera le texte saisi
     * par l’utilisateur et retournera un objet implémentant l’interface Commande
     * @param nom de la commande
     * @param commande l'instance
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
                    }
        }
        
        return com;
    }
    
}
