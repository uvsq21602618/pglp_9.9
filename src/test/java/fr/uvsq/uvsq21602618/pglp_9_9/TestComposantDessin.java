package fr.uvsq.uvsq21602618.pglp_9_9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
/**
 * Classe de tests pour ComposantDessin.
 * @author Nathalie
 *
 */
public class TestComposantDessin {
    /**
     * Test de la fonction affichage dans ComposantDessin.
     * @throws NameAlreadyExistsException Exception pour 
     * quand le nom utilise en argument existe deja
     */
    @Test
    public void testAffichage() throws NameAlreadyExistsException {
        Point p1 = new Point(15, 20);
        Point p2 = new Point(25, 10);
        Rectangle r = new Rectangle("r1", p1, p2);
        Cercle c = new Cercle("c1", p1, 5);
        
        ComposantDessin cd = new ComposantDessin("cd1");
        cd.ajoute(r);
        cd.ajoute(c);
        cd.affiche();
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        cd.affiche();

        String expected = "cd1 = Composant du dessin:";
        String[] actual = outContent.toString().split("\n");
        assertEquals(expected, actual[0]);
        expected = "r1 = Rectangle((15, 20), (25, 10))";
        assertEquals(expected, actual[2]);
        expected = "c1 = Cercle((15, 20), 5)";
        assertEquals(expected, actual[4]);
    } 
    /**Tester deplace**/
}
