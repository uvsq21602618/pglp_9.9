package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public Command nextCommand(final String nom,
            final Command commande) throws ClassNotFoundException, IOException {
        Command com = commande;
        if (nom.equals("create")) {
            CreateCommand create = (CreateCommand) commande;
            return create;
        } else if (nom.equals("deleteall")) {
            DeleteAllCommand deleteAll = (DeleteAllCommand) commande;
            return deleteAll;
        } else if (nom.equals("delete")) {
            DeleteCommand delete = (DeleteCommand) commande;
            return delete;
        } else if (nom.equals("put")) {
            PutCommand put = (PutCommand) commande;
            return put;
        } else if (nom.equals("moveall")) {
            MoveAllCommand moveAll = (MoveAllCommand) commande;
            return moveAll;
        } else if (nom.equals("move")) {
            MoveCommand move = (MoveCommand) commande;
            return move;
        } else if (nom.equals("showall")) {
            ShowAllCommand showAll = (ShowAllCommand) commande;
            return showAll;
        } else if (nom.equals("show")) {
            ShowCommand show = (ShowCommand) commande;
            return show;
        } else if (nom.equals("save")) {
            SaveCommand save = (SaveCommand) commande;
            return save;
        } else if (nom.equals("get")) {
            GetCommand get = (GetCommand) commande;
            return get;
        } else if (nom.equals("update")) {
            UpdateCommand update = (UpdateCommand) commande;
            return update;
        } else if (nom.equals("deletebackup")) {
            DeleteBackUpCommand delBackUp = (DeleteBackUpCommand) commande;
            return delBackUp;
        }
        return com;
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
    public List<Forme> getFormes(){
        return this.formes;
    }
    /**
     * Methode pour retourner la liste des noms.
     * @return la liste de formes
     */
    public List<String> getNoms(){
        return this.noms;
    }
    
}
