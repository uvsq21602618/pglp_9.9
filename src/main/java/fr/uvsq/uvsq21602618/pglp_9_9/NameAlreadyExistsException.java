package fr.uvsq.uvsq21602618.pglp_9_9;
/**
 * Classe pour une exception.
 * @author Nathalie
 *
 */
public class NameAlreadyExistsException extends Exception {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Classe de l'exception lorque le nom pour
     * une forme a deja ete attribue.
     * @param message Le message d'erreur
     */
    public NameAlreadyExistsException(final String message) {
        super(message);
    }
}
