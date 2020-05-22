package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

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
            String ligne = scanner.next().toLowerCase();
            if (ligne.contains(("rectangle")) || (ligne.contains("cercle"))
                    || (ligne.contains("carre")) || (ligne.contains("triangle"))
                    || (ligne.contains("dessin"))) {
                commande = moteur.nextCommand("create"
                        , new CreateCommand(ligne, moteur.getFormes()
                                , moteur.getNoms()));
                commande.execute();

            } else if (ligne.contains("delete")) {
                if (ligne.contains("deleteAll")) {
                    commande = moteur.nextCommand("deleteall"
                            , new DeleteAllCommand(moteur.getFormes()
                                    , moteur.getNoms()));
                    commande.execute();
                } else {
                    commande = moteur.nextCommand("delete"
                            , new DeleteCommand(ligne, moteur.getFormes()
                                    , moteur.getNoms()));
                    commande.execute();
                }
            } else if (ligne.contains("put")) {
                commande = moteur.nextCommand("put"
                        , new PutCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("move")) {
                if (ligne.contains("moveall")) {
                    commande = moteur.nextCommand("moveall"
                            , new MoveAllCommand(ligne, moteur.getFormes()));
                    commande.execute();
                } else {
                    commande = moteur.nextCommand("move"
                            , new MoveCommand(ligne, moteur.getFormes()));
                    commande.execute();
                }

            } else if (ligne.contains("show")) {
                if (ligne.contains("showall")) {
                    commande = moteur.nextCommand("showall"
                            , new ShowAllCommand(moteur.getFormes()));
                    commande.execute();
                }
                commande = moteur.nextCommand("show"
                        , new ShowCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("save")) {
                commande = moteur.nextCommand("save"
                        , new SaveCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("get")) {
                commande = moteur.nextCommand("get"
                        , new SaveCommand(ligne, moteur.getFormes()));
                commande.execute();

            } else if (ligne.contains("update")) {
                commande = moteur.nextCommand("update"
                        , new SaveCommand(ligne, moteur.getFormes()));
                commande.execute();
            }
        }
        scanner.close();

        /*
         * Gerer le deplacement de figures utilisant les memes points.
         *
        DAO<Carre> carreDAO = new DAOFactory().getCarreDAO();
        Point p1 = new Point(15, 20);
        Point p2 = new Point(25, 10);
        Carre c = new Carre("c1", p1, 5);        
        //carreDAO.create(c);

        DAO<Cercle> cercleDAO = new DAOFactory().getCercleDAO();
        Cercle c2 = new Cercle("c2", p1, 5);
        //cercleDAO.create(c2);


        DAO<Rectangle> rectangleDAO = new DAOFactory().getRectangleDAO();
        Rectangle r = new Rectangle("r1", p1, p2);
        //rectangleDAO.create(r);

        DAO<Triangle> triangleDAO = new DAOFactory().getTriangleDAO();
        Point p3 = new Point(10, 10);
        Triangle t = new Triangle("t1", p1, p2, p3);
        triangleDAO.create(t);

        DAO<ComposantDessin> composantDessinDAO = new DAOFactory().getComposantDessinDAO();
        ComposantDessin dessin1 = new ComposantDessin("dessin1");
        ComposantDessin dessin2 = new ComposantDessin("dessin2");
        dessin1.ajoute(r);
        dessin1.ajoute(c);
        dessin2.ajoute(dessin1);
        dessin2.ajoute(c2);
        composantDessinDAO.create(dessin2);
        carreDAO.delete(c);
        cercleDAO.delete(c2);
        rectangleDAO.delete(r);
        triangleDAO.delete(t);

        CercleDAO dao = new CercleDAO();
        dao.affichageTable();

        TriangleDAO dao2 = new TriangleDAO();
        dao2.affichageTable();

        Carre c3 = new Carre("c1", p2, 5);   
        carreDAO.create(c);
        carreDAO.update(c3);
        carreDAO.find("c1");

        Cercle c4 = new Cercle("c2", p2, 5);
        cercleDAO.create(c2);
        cercleDAO.update(c4);
        cercleDAO.find("c2");

        Rectangle r2 = new Rectangle("r1", p2, p3);
        rectangleDAO.create(r);
        rectangleDAO.update(r2);
        rectangleDAO.find("r1");

        Triangle t2 = new Triangle("t1", p2, p1, p3);
        triangleDAO.create(t);
        triangleDAO.update(t2);
        triangleDAO.find("t1");

        ComposantDessin dessin3 = new ComposantDessin("dessin2");
        dessin3.ajoute(t);
        //composantDessinDAO.update(dessin3);
        composantDessinDAO.find("dessin2");
        composantDessinDAO.delete(dessin2);   
         */
        /* 
        String str = "c1 = Carre((2, 3), 5)";
        Command com = new CreateCommand(str.toLowerCase());
        com.execute();


        String str2 = "c2 = Cercle((2, 3), 5)";
        Command com2 = new CreateCommand(str2.toLowerCase());
        com2.execute();

        String str3 = "r1 = Rectangle((5, 5), (3, 3))";
        Command com3 = new CreateCommand(str3.toLowerCase());
        com3.execute();

        String str4 = "t1 = triangle((5, 5), (3, 3), (2,2))";
        Command com4 = new CreateCommand(str4);
        com4.execute();

        String str5 = "dessin = composantdessin";
        Command com5 = new CreateCommand(str5);
        com5.execute();*/

        /*
        DAO<ComposantDessin> composantDessinDAO = new DAOFactory()
                .getComposantDessinDAO();
        LinkedList<Forme> formes = new LinkedList<Forme>();
        LinkedList<String> noms = new LinkedList<String>();

        String str = "c1 = Carre((2, 3), 5)";
        CreateCommand com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f = com.getForme();
        formes.add(f);
        noms.add(f.getNom());

        str = "petitDessin = composantdessin";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f2 = com.getForme();
        formes.add(f2);
        noms.add(f2.getNom());

        str = "t1 = triangle((5, 5), (3, 3), (2,2))";
        com = new CreateCommand(str.toLowerCase());
        com.execute();
        Forme f3 = com.getForme();
        formes.add(f3);
        noms.add(f3.getNom());

        str = "put(petitDessin, c1)";
        PutCommand put = new PutCommand(str.toLowerCase(), formes);
        put.execute();

        str = "show(petitDessin)";
        ShowCommand show = new ShowCommand(str.toLowerCase(), formes);
        show.execute();

        str = "update(dessin3)";
        UpdateCommand update = new UpdateCommand(str.toLowerCase(), formes);
        update.execute();

        /*str = "delete()";
        DeleteCommand delete = new DeleteCommand(str.toLowerCase(), formes
                , noms);
        delete.execute();*/

        /*str = "save(dessin)";
        SaveCommand save = new SaveCommand(str.toLowerCase(), formes);
        save.execute();

        composantDessinDAO.affichageTable();

        str = "get(dessin)";
        GetCommand get = new GetCommand(str.toLowerCase(), formes);
        get.execute();

        str = "deleteBackUp(petitDessin)";
        DeleteBackUpCommand DelBU = new DeleteBackUpCommand(str.toLowerCase()
                , formes);
        DelBU.execute();*/

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
