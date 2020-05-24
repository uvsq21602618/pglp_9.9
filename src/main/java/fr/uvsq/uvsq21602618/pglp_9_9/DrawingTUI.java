package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
 * Classe qui se charge des interactions avec l'utilisateur.
 * @author Nathalie
 *
 */
public class DrawingTUI {
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
        this.scanner = new Scanner(System.in, "UTF-8");
        this.formes = new LinkedList<Forme>();
        this.noms = new LinkedList<String>();
    }
    /**
     * Méthode qui analysera le texte saisi
     * par l’utilisateur et retournera un objet implémentant l’interface
     *  Commande.
     * @param ligne de la commande
     * @return la commande à faire
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public Command nextCommand(final String ligne) throws
    ClassNotFoundException, IOException {
        Command commande = null;
        if (ligne.contains("delete")) {
            if (ligne.contains("deleteall")) {
                DeleteAllCommand deleteAll = new DeleteAllCommand(formes, noms);
                return deleteAll;
            } else if (ligne.contains("deletebackup")) {
                DeleteBackUpCommand delBackUp = new DeleteBackUpCommand(ligne);
                return delBackUp;
            } else {
                DeleteCommand delete = new DeleteCommand(ligne, formes, noms);
                return delete;
            }
        } else if (ligne.contains("put")) {
            PutCommand put = new PutCommand(ligne, formes);
            return put;

        } else if (ligne.contains("move")) {
            if (ligne.contains("moveall")) {
                MoveAllCommand moveAll = new MoveAllCommand(ligne, formes);
                return moveAll;
            } else {
                MoveCommand move = new MoveCommand(ligne, formes);
                return move;
            }

        } else if (ligne.contains("show")) {
            if (ligne.contains("showall")) {
                ShowAllCommand showAll = new ShowAllCommand(formes);
                return showAll;
            } else {
                ShowCommand show = new ShowCommand(ligne, formes);
                return show;
            }

        } else if (ligne.contains("save")) {
            SaveCommand save = new SaveCommand(ligne, formes);
            return save;

        } else if (ligne.contains("get")) {
            GetCommand get = new GetCommand(ligne, formes);
            return get;

        } else if (ligne.contains("update")) {
            UpdateCommand update = new UpdateCommand(ligne, formes);
            return update;
        } else if (ligne.contains(("rectangle")) || (ligne.contains("cercle"))
                || (ligne.contains("carre")) || (ligne.contains("triangle"))
                || (ligne.contains("composant du dessin"))) {
            CreateCommand create = new CreateCommand(ligne, formes, noms);
            return create;
        } else {
            return commande;
        }
    }
    /**
     * Methode pour retourner le scanner.
     * @return le scanner
     */
    public Scanner getScanner() {
        return this.scanner;
    }
    /**
     * Methode pour retourner la liste des formes.
     * @return la liste de formes
     */
    public List<Forme> getFormes() {
        return this.formes;
    }
    /**
     * Methode pour retourner la liste des noms.
     * @return la liste de formes
     */
    public List<String> getNoms() {
        return this.noms;
    }
}
