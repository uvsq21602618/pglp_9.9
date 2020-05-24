package fr.uvsq.uvsq21602618.pglp_9_9.commandes;
/**
 * Classe de l'exception qui correspond a une entree de valeurs impossibles.
 * @author Nathalie
 *
 */
public class ImpossibleValueException extends Exception{
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Message de l'exception.
     * @param message a afficher.
     */
    public ImpossibleValueException()
    {
        System.out.println("La valeur entree pour le rayon ou la longueur"
                + " doivent être positive!");
    }
}
