package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour la commande de creation du dessin.
 * @author Nathalie
 *
 */

public class CreateCommand implements Command {
    /**
     * La forme a dessiner.
     */
    private Forme forme;
    /**
     * La ligne de commande.
     */
    private String creation;
    /**
     * Constructeur de CreateCommand.
     * @param ligne la commande de l'utilisateur
     */
    public CreateCommand(String ligne) {
        this.creation = ligne;
    }
    /**
     * Execution de la commande de creation.
     */
    @Override
    public void execute() {
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
    }
    /**
     * Methode pour creer une instance de Carre a partir d'une ligne
     * de l'utilisateur.
     * @param str ligne tapee par l'utilisateur
     * @return un carre 
     */
    private Carre createCarre() {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("carre\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)","");
        String l = tab2[2].trim();
        l = l.replaceAll("\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Carre c = new Carre(nom, p, Integer.parseInt(l));
        c.affiche();
        return c;
    }
    /**
     * Methode pour creer une instance de Cercle a partir d'une ligne
     * de l'utilisateur.
     * @param str ligne tapee par l'utilisateur
     * @return un cercle 
     */
    private Cercle createCercle() {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("cercle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)","");
        String r = tab2[2].trim();
        r = r.replaceAll("\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Cercle c = new Cercle(nom, p, Integer.parseInt(r));
        c.affiche();
        return c;
    }
    /**
     * Methode pour creer une instance de Rectangle a partir d'une ligne
     * de l'utilisateur.
     * @param str ligne tapee par l'utilisateur
     * @return un rectangle
     */
    private Rectangle createRectangle() {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("rectangle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)","");
        String x2 = tab2[2].trim();
        x2 = x2.replaceAll("\\(", "");
        String y2 = tab2[3].trim();
        y2 = y2.replaceAll("\\)\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Point p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));
        Rectangle r = new Rectangle(nom, p, p2);
        r.affiche();
        return r;
    }
    /**
     * Methode pour creer une instance de Triangle a partir d'une ligne
     * de l'utilisateur.
     * @param str ligne tapee par l'utilisateur
     * @return un triangle
     */
    private Triangle createTriangle() {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0].trim();
        String[] tab2 = tab[1].split(",");

        String x = tab2[0].trim();
        x = x.replaceAll("triangle\\(\\(", "");
        String y = tab2[1].trim();
        y = y.replaceAll("\\)","");
        String x2 = tab2[2].trim();
        x2 = x2.replaceAll("\\(", "");
        String y2 = tab2[3].trim();
        y2 = y2.replaceAll("\\)", "");
        String x3 = tab2[4].trim();
        x3 = x3.replaceAll("\\(", "");
        String y3 = tab2[5].trim();
        y3 = y3.replaceAll("\\)\\)", "");

        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        Point p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));
        Point p3 = new Point(Integer.parseInt(x3), Integer.parseInt(y3));
        Triangle t = new Triangle(nom, p, p2, p3);
        t.affiche();
        return t;
    }
    /**
     * Methode pour creer une instance de ComposantDessin a partir d'une ligne
     * de l'utilisateur.
     * @param str ligne tapee par l'utilisateur
     * @return un composant du dessin
     */
    private ComposantDessin createComposantDessin() {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0].trim();

        ComposantDessin cd = new ComposantDessin(nom);
        cd.affiche();
        return cd;
    }

}
