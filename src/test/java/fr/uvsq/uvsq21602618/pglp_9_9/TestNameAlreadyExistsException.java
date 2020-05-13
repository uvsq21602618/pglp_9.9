package fr.uvsq.uvsq21602618.pglp_9_9;

import org.junit.Test;

/**
 * Classe de tests pour l'exception NameAlreadyExistsException.
 * @author Nathalie
 *
 */
public class TestNameAlreadyExistsException {
    /**
     * Test pour le lancement d'une exception si 
     * le nom entre en argument pour creer un carre
     * existe deja
     * @throws NameAlreadyExistsException Exception lorque 
     * le nom est deja utilise
     */
    @SuppressWarnings("unused")
    @Test(expected = NameAlreadyExistsException.class)
    public void testNomExisteDejaCarre() throws NameAlreadyExistsException{
        Point p1 = new Point(15, 20);
        Carre c = new Carre("c1", p1, 5);
        Carre copy = new Carre("c1", p1, 5);        
    }
    /**
     * Test pour le lancement d'une exception si 
     * le nom entre en argument pour creer un cercle
     * existe deja
     * @throws NameAlreadyExistsException Exception lorque 
     * le nom est deja utilise
     */
    @SuppressWarnings("unused")
    @Test(expected = NameAlreadyExistsException.class)
    public void testNomExisteDejaCercle() throws NameAlreadyExistsException{
        Point p1 = new Point(15, 20);
        Cercle c = new Cercle("c1", p1, 5);
        Cercle copy = new Cercle("c1", p1, 5);
    }
    /**
     * Test pour le lancement d'une exception si 
     * le nom entre en argument pour creer un rectangle
     * existe deja
     * @throws NameAlreadyExistsException Exception lorque 
     * le nom est deja utilise
     */
    @SuppressWarnings("unused")
    @Test(expected = NameAlreadyExistsException.class)
    public void testNomExisteDejaRectangle() throws NameAlreadyExistsException{
        Point p1 = new Point(15, 20);
        Point p2 = new Point(25, 10);
        Rectangle r = new Rectangle("r1", p1, p2);
        Rectangle copy = new Rectangle("r1", p1, p2);
    }
    /**
     * Test pour le lancement d'une exception si 
     * le nom entre en argument pour creer un rectangle
     * existe deja
     * @throws NameAlreadyExistsException Exception lorque 
     * le nom est deja utilise
     */
    @SuppressWarnings("unused")
    @Test(expected = NameAlreadyExistsException.class)
    public void testNomExisteDejaTriangle() throws NameAlreadyExistsException{
        Point p1 = new Point(15, 20);
        Point p2 = new Point(20, 30);
        Point p3 = new Point(10, 10);
        Triangle t = new Triangle("t1", p1, p2, p3);
        Triangle copy = new Triangle("t1", p1, p2, p3);
    }
    
    

}
