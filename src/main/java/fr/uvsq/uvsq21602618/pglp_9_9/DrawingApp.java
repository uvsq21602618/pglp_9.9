package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import fr.uvsq.uvsq21602618.pglp_9_9.commandes.Command;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.ShowAllCommand;

/**
 * Singleton contenant le main.
 * @author Nathalie
 */
public enum DrawingApp {
    /**
     * L'enum qui contient le code du main.
     */
    ENVIRONNEMENT;
    /**
     * Execution du programme.
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws IOException Exceptions liees aux entrees/sorties
     * @throws ClassNotFoundException Exceptions liees a une classe inexistante
     */
    public void run() throws IOException,
    SQLException, ClassNotFoundException {
        DrawingTUI moteur = new DrawingTUI();
        Scanner scanner = moteur.getScanner();
        Command commande;
        String ligne = scanner.nextLine().toLowerCase();

        while (!ligne.contains("quit")) {
            commande = moteur.nextCommand(ligne);
            if (commande == null) {
                System.out.println("La commande tapée n'est pas connue,"
                        + " veuillez réessayer!\n");
            } else {
               commande.execute();
               if(!(commande instanceof ShowAllCommand)) {
                   moteur.nextCommand("showall").execute();
               }
            }
            ligne = scanner.nextLine().toLowerCase();
        }
        System.out.println("Fin de la saisie!");
        scanner.close();
    }
    /**
     * Main.
     * @param args pour le main
     * @throws SQLException Exception liee a l'acces a la base de donnees
     * @throws ClassNotFoundException Exception lié à une classe inexistante
     * @throws IOException liee aux entreés/sorties
     */
    public static void main(final String[] args) throws SQLException,
    ClassNotFoundException, IOException {
        ENVIRONNEMENT.run();
    }
}
