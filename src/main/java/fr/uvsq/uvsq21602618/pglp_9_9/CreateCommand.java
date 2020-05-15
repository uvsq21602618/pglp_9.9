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
        if (this.creation.contains("Carre")) {
            Carre c = createCarre(this.creation);
            
        } else if (this.creation.contains("Cercle")) {
            Cercle c = createCercle(this.creation);
        } else if (this.creation.contains("Rectangle")) {
            Rectangle r = createRectangle(this.creation);
        } else if (this.creation.contains("Triangle")) {
            Triangle t = createTriangle(this.creation);
        } else {
            ComposantDessin = createComposantDessin(this.creation);
        }
    }
    
    private Carre createCarre(String str) {
        String[] tab = this.creation.split(" = ");
        String nom = tab[0];
        Carre c;
        
        
        return c;
    }

}
