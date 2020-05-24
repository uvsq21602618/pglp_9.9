package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import fr.uvsq.uvsq21602618.pglp_9_9.DAO.DAO;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.Command;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.CreateCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.DeleteAllCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.DeleteBackUpCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.DeleteCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.GetCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.MoveAllCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.MoveCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.PutCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.SaveCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.ShowAllCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.ShowCommand;
import fr.uvsq.uvsq21602618.pglp_9_9.commandes.UpdateCommand;

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

        while (!scanner.hasNext("quit") && scanner.hasNext()) {
            String ligne = scanner.nextLine().toLowerCase();
            if (ligne.contains("delete")) {
                if (ligne.contains("deleteall")) {
                    commande = moteur.nextCommand("deleteall",
                            new DeleteAllCommand(moteur.getFormes(),
                                    moteur.getNoms()));
                    commande.execute();
                } else if (ligne.contains("deletebackup")) {
                    commande = moteur.nextCommand("deletebackup",
                            new DeleteBackUpCommand(ligne));
                    commande.execute();
                } else {
                    commande = moteur.nextCommand("delete",
                            new DeleteCommand(ligne, moteur.getFormes(),
                                    moteur.getNoms()));
                    commande.execute();
                }
            } else if (ligne.contains("put")) {
                commande = moteur.nextCommand("put",
                        new PutCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("move")) {
                if (ligne.contains("moveall")) {
                    commande = moteur.nextCommand("moveall",
                            new MoveAllCommand(ligne, moteur.getFormes()));
                    commande.execute();
                } else {
                    commande = moteur.nextCommand("move",
                            new MoveCommand(ligne, moteur.getFormes()));
                    commande.execute();
                }

            } else if (ligne.contains("show")) {
                if (ligne.contains("showall")) {
                    commande = moteur.nextCommand("showall",
                            new ShowAllCommand(moteur.getFormes()));
                    commande.execute();
                } else {
                    commande = moteur.nextCommand("show",
                            new ShowCommand(ligne, moteur.getFormes()));
                    commande.execute();
                }

            } else if (ligne.contains("save")) {
                commande = moteur.nextCommand("save",
                        new SaveCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("get")) {
                commande = moteur.nextCommand("get",
                        new GetCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("update")) {
                commande = moteur.nextCommand("update",
                        new UpdateCommand(ligne, moteur.getFormes()));
                commande.execute();
            } else if (ligne.contains(("rectangle")) || (ligne.contains("cercle"))
                    || (ligne.contains("carre")) || (ligne.contains("triangle"))
                    || (ligne.contains("composant du dessin"))) {
                System.out.println(ligne);
                DAO.disconnect();

                commande = moteur.nextCommand("create",
                        new CreateCommand(ligne, moteur.getFormes(),
                                moteur.getNoms()));
                commande.execute();
            } else {
                System.out.println("La commande tapée n'est pas connue,"
                        + " veuillez réessayer!\n");
            }
        }
        System.out.println("Fin de la saisie!");
        scanner.close();
        DAO.disconnect();
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
        DAO.disconnect();
        ENVIRONNEMENT.run();
    }
}
