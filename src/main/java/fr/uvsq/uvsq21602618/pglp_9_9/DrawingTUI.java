package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
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
     * @throws SQLException Exception liee a la base de donnees
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public Command nextCommand(final String nom,
            final Command commande) throws ClassNotFoundException, IOException, SQLException {
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
                if (ligne.contains("moveall")) {
                    com = new MoveCommand(ligne, this.formes);
                    return com;
                } else {
                    com = new MoveAllCommand(ligne, this.formes);
                    return com;
                }
            } else if (ligne.contains("show")) {
                if (ligne.contains("showall")) {
                    com = new ShowAllCommand(this.formes);
                    return com;
                }
                com = new ShowCommand(ligne, this.formes);
                return com;
            } else if (ligne.contains("save")) {
                com = new SaveCommand(ligne, this.formes);
                return com;
            } else if (ligne.contains("get")) {
                com = new GetCommand(ligne, this.formes);
                return com;
            }
        }

        return com;
    }
    /**
     * Fonction pour verifier si le nom a deja ete utilise.
     * @param forme qui va être creee
     * @return true si le nom est disponible, false sinon
     * @throws SQLException Exception liee a la base de donnes
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public boolean verification(final Forme forme) throws IOException, SQLException, ClassNotFoundException {
        DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                .getComposantDessinDAO();
        DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
        DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
        DAO<Rectangle> rectangleDAO = new DAOFactory().getRectangleDAO();
        DAO<Triangle> triangleDAO = new DAOFactory().getTriangleDAO();

        for (String s : this.noms) {
            if (s.equals(forme.getNom())) {
                System.out.println("Ce nom a deja ete utilise!");
                return false;
            } else if (composantDessinDAO.find(forme.getNom()) != null) {
                System.out.println("Ce nom a deja ete utilise dans la base"
                        + "de donnees pour un composant du dessin!");
                return false;
            } else if (carreDAO.find(forme.getNom()) != null) {
                System.out.println("Ce nom a deja ete utilise dans la base"
                        + "de donnees pour un carré!");
                return false;
            } else if (cercleDAO.find(forme.getNom()) != null) {
                System.out.println("Ce nom a deja ete utilise dans la base"
                        + "de donnees pour un cercle!");
                return false;
            } else if (rectangleDAO.find(forme.getNom()) != null) {
                System.out.println("Ce nom a deja ete utilise dans la base"
                        + "de donnees pour un rectangle!");
                return false;
            } else if (triangleDAO.find(forme.getNom()) != null) {
                System.out.println("Ce nom a deja ete utilise dans la base"
                        + "de donnees pour un triangle!");
                return false;
            }
        }
        return true;
    }
}
