package fr.uvsq.uvsq21602618.pglp_9_9.commandes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.uvsq.uvsq21602618.pglp_9_9.Forme;
import fr.uvsq.uvsq21602618.pglp_9_9.Point;
import fr.uvsq.uvsq21602618.pglp_9_9.Carre;
import fr.uvsq.uvsq21602618.pglp_9_9.Cercle;
import fr.uvsq.uvsq21602618.pglp_9_9.ComposantDessin;
import fr.uvsq.uvsq21602618.pglp_9_9.Rectangle;
import fr.uvsq.uvsq21602618.pglp_9_9.Triangle;
import fr.uvsq.uvsq21602618.pglp_9_9.DAO.DAO;
import fr.uvsq.uvsq21602618.pglp_9_9.DAO.DAOFactory;

/**
 * Classe pour la commande de creation du dessin.
 * @author Nathalie
 */
public class CreateCommand implements Command {
    /**
     * La liste des formes dessinees.
     */
    private List<Forme> formes;
    /**
     * La liste des noms des formes dessinees.
     */
    private List<String> noms;
    /**
     * La forme a dessiner.
     */
    private Forme forme;
    /**
     * La ligne de commande.
     */
    private String creation;
    /**
     * initialisation de la constante 3 pour eviter le "magic number".
     */
    static final int TROIS = 3;
    /**
     * initialisation de la constante 4 pour eviter le "magic number".
     */
    static final int QUATRE = 4;
    /**
     * initialisation de la constante 5 pour eviter le "magic number".
     */
    static final int CINQ = 5;
    /**
     * Constructeur de CreateCommand.
     * @param ligne la commande de l'utilisateur
     * @param liste liste de formes
     * @param listeNoms liste des noms des dessins en cours
     */
    public CreateCommand(final String ligne, final List<Forme> liste,
            final List<String> listeNoms) {
        this.creation = ligne;
        this.formes = liste;
        this.noms = listeNoms;
    }
    /**
     * Execution de la commande de creation.
     * throws SQLException Exception liee a la base de donnes
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    @Override
    public void execute() throws ClassNotFoundException, IOException,
    SQLException {
        try {
            if (this.creation.contains("carre")) {
                this.forme = createCarre();
            } else if (this.creation.contains("cercle")) {
                this.forme = createCercle();
            } else if (this.creation.contains("rectangle")) {
                this.forme = createRectangle();
            } else if (this.creation.contains("triangle")) {
                this.forme = createTriangle();
            } else {
                this.forme = createComposantDessin();
            }
            DAO.setConnect();
            try {
                if (verification(this.forme)) {
                    this.formes.add(this.forme);
                    this.noms.add(this.forme.getNom());
                }
            } catch (IOException | SQLException  | ClassNotFoundException e) {
                DAO.disconnect();
                throw e;
            }
        } catch (ImpossibleValueException | ArrayIndexOutOfBoundsException
                | NumberFormatException e) {
            System.out.println("La commande de creation est incorrecte!\n");
            DAO.disconnect();
        }
        DAO.disconnect();
    }
    /**
     * Methode pour creer une instance de Carre a partir d'une ligne
     * de l'utilisateur.
     * @return un carre
     * @throws ImpossibleValueException Exception si la longueur ou le rayon est
     *  negatif
     */
    private Carre createCarre() throws ImpossibleValueException {
        String[] tab = this.creation.split("=");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("carre\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)", "");
        String l = tab2[2].trim();
        l = l.replaceAll("\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        if (Integer.parseInt(l) < 1) {
            throw new ImpossibleValueException();
        }
        Carre c = new Carre(nom, p, Integer.parseInt(l));
        return c;
    }
    /**
     * Methode pour creer une instance de Cercle a partir d'une ligne
     * de l'utilisateur.
     * @return un cercle
     * @throws ImpossibleValueException Exception si la longueur ou le rayon est
     *  negatif
     */
    private Cercle createCercle() throws ImpossibleValueException {
        String[] tab = this.creation.split("=");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("cercle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)", "");
        String r = tab2[2].trim();
        r = r.replaceAll("\\)", "");


        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        if (Integer.parseInt(r) < 1) {
            throw new ImpossibleValueException();
        }
        Cercle c = new Cercle(nom, p, Integer.parseInt(r));
        return c;
    }
    /**
     * Methode pour creer une instance de Rectangle a partir d'une ligne
     * de l'utilisateur.
     * @return un rectangle
     */
    private Rectangle createRectangle() {
        String[] tab = this.creation.split("=");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("rectangle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)", "");
        String x2 = tab2[2].trim();
        x2 = x2.replaceAll("\\(", "");
        String y2 = tab2[TROIS].trim();
        y2 = y2.replaceAll("\\)\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Point p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));
        Rectangle r = new Rectangle(nom, p, p2);
        return r;
    }
    /**
     * Methode pour creer une instance de Triangle a partir d'une ligne
     * de l'utilisateur.
     * @return un triangle
     */
    private Triangle createTriangle() {
        String[] tab = this.creation.split("=");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("triangle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)", "");
        String x2 = tab2[2].trim();
        x2 = x2.replaceAll("\\(", "");
        String y2 = tab2[TROIS].trim();
        y2 = y2.replaceAll("\\)", "");
        String x3 = tab2[QUATRE].trim();
        x3 = x3.replaceAll("\\(", "");
        String y3 = tab2[CINQ].trim();
        y3 = y3.replaceAll("\\)\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Point p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));
        Point p3 = new Point(Integer.parseInt(x3), Integer.parseInt(y3));
        Triangle t = new Triangle(nom, p, p2, p3);
        return t;
    }
    /**
     * Methode pour creer une instance de ComposantDessin a partir d'une ligne
     * de l'utilisateur.
     * @return un composant du dessin
     */
    private ComposantDessin createComposantDessin() {
        String[] tab = this.creation.split("=");
        String nom = tab[0].trim();

        ComposantDessin cd = new ComposantDessin(nom);
        //cd.affiche();
        return cd;
    }
    /**
     * Retourne la forme creee par la commande.
     * @return la forme creee
     */
    public Forme getForme() {
        return this.forme;
    }

    /**
     * Fonction pour verifier si le nom a deja ete utilise.
     * @param forme2 qui va être creee
     * @return true si le nom est disponible, false sinon
     * @throws SQLException Exception liee a la base de donnes
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception liee a une classe non trouvee
     */
    public boolean verification(final Forme forme2) throws IOException,
    SQLException, ClassNotFoundException {
        if (!this.noms.isEmpty()) {
            for (String s : this.noms) {
                if (s.equals(forme2.getNom())) {
                    System.out.println("Ce nom a deja ete utilise!");
                    return false;
                } else {
                    DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                            .getComposantDessinDAO();
                    if (composantDessinDAO.find(forme2.getNom()) != null) {
                        System.out.println("Ce nom a deja ete"
                                + " utilise dans la base"
                                + "de donnees pour un composant du dessin!");
                        return false;
                    } else {
                        DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
                        if (carreDAO.find(forme2.getNom()) != null) {
                            System.out.println("Ce nom a deja ete"
                                    + " utilise dans la base"
                                    + "de donnees pour un carré!");
                            return false;
                        } else {
                            DAO<Cercle> cercleDAO = new DAOFactory()
                                    .getCercleDAO();
                            if (cercleDAO.find(forme2.getNom()) != null) {
                                System.out.println("Ce nom a deja ete"
                                        + " utilise dans la base"
                                        + "de donnees pour un cercle!");
                                return false;
                            } else {
                                DAO<Rectangle> rectangleDAO = new DAOFactory()
                                        .getRectangleDAO();
                                if (rectangleDAO
                                        .find(forme2.getNom()) != null) {
                                    System.out.println("Ce nom a deja"
                                            + " ete utilise dans la base"
                                            + "de donnees pour un rectangle!");
                                    return false;
                                } else {
                                    DAO<Triangle> triangleDAO = new DAOFactory()
                                            .getTriangleDAO();
                                    if (triangleDAO
                                            .find(forme2.getNom()) != null) {

                                        System.out.println("Ce nom a deja"
                                                + " ete utilise dans la base"
                                                + "de donnees pour un"
                                                + " triangle!");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
