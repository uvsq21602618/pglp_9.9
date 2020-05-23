package fr.uvsq.uvsq21602618.pglp_9_9;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe pour la commande de creation du dessin.
 * @author Nathalie
 *
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
    public void execute() throws ClassNotFoundException, IOException, SQLException {
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
            /*if (verification(this.forme)) {*/
                this.formes.add(this.forme);
                this.noms.add(this.forme.getNom());
            //}

        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            System.out.println("La commande de creation est incorrecte!\n");
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            System.out.println("La commande de creation est incorrecte!!\n");
        }
    }
    /**
     * Methode pour creer une instance de Carre a partir d'une ligne
     * de l'utilisateur.
     * @return un carre
     */
    private Carre createCarre() {
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
        Carre c = new Carre(nom, p, Integer.parseInt(l));
        //c.affiche();
        return c;
    }
    /**
     * Methode pour creer une instance de Cercle a partir d'une ligne
     * de l'utilisateur.
     * @return un cercle
     */
    private Cercle createCercle() {
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
        Cercle c = new Cercle(nom, p, Integer.parseInt(r));
        //c.affiche();
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
        //r.affiche();
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
        //t.affiche();
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

        if(!this.noms.isEmpty()) {
            for (String s : this.noms) {
                if (s.equals(forme.getNom())) {
                    System.out.println("Ce nom a deja ete utilise!");
                    composantDessinDAO.disconnect();
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                } else if (composantDessinDAO.find(forme.getNom()) != null) {
                    System.out.println("Ce nom a deja ete utilise dans la base"
                            + "de donnees pour un composant du dessin!");
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                } else if (carreDAO.find(forme.getNom()) != null) {
                    System.out.println("Ce nom a deja ete utilise dans la base"
                            + "de donnees pour un carré!");
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                } else if (cercleDAO.find(forme.getNom()) != null) {
                    System.out.println("Ce nom a deja ete utilise dans la base"
                            + "de donnees pour un cercle!");
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                } else if (rectangleDAO.find(forme.getNom()) != null) {
                    System.out.println("Ce nom a deja ete utilise dans la base"
                            + "de donnees pour un rectangle!");
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                } else if (triangleDAO.find(forme.getNom()) != null) {
                    System.out.println("Ce nom a deja ete utilise dans la base"
                            + "de donnees pour un triangle!");
                    carreDAO.disconnect();
                    cercleDAO.disconnect();
                    rectangleDAO.disconnect();
                    triangleDAO.disconnect();
                    return false;
                }
            }
            carreDAO.disconnect();
            cercleDAO.disconnect();
            rectangleDAO.disconnect();
            triangleDAO.disconnect();
            return true;
        }
        carreDAO.disconnect();
        cercleDAO.disconnect();
        rectangleDAO.disconnect();
        triangleDAO.disconnect();
        return true;
    }
}
