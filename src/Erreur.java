/**
 * Contient et gére les erreur du logiciel
 */
public enum Erreur {
    FICHIER_INEXISTANT( -101, Texte.MSSG_ERREUR_FICHIER_INEXISTANT ),
    ERREUR_ABSTRAIT(-102,Texte.MSG_ERR_ABSTRAIT),
    ERREUR_ATTRIBUT(-102,Texte.MSG_ERR_ATTRIBUT),
    ERREUR_PARAM(-102,Texte.MSG_ERR_PARAM),
    ERREUR_CLASSE_DEBUT(-102,Texte.MSG_ERR_CLASSE_DEBUT),
    ERREUR_CLASSE_FIN(-102,Texte.MSG_ERR_CLASSE_FIN),
    ERREUR_METHODE_DEBUT(-102,Texte.MSG_ERR_METHODE_DEBUT),
    ERREUR_METHODE_FIN(-102,Texte.MSG_ERR_METHODE_FIN),
    ERREUR_SYNTAXE_COMMANDE(-103,Texte.MSSG_ERREUR_SYNTAXE_COMMANDE),
    ERREUR_SYNTAXE_NULLAIRE(-103,Texte.MSSG_ERREUR_SYNTAXE_NULLAIRE),
    ERREUR_SYNTAXE_UNAIRE(-103,Texte.MSSG_ERREUR_SYNTAXE_UNAIRE),
    ERREUR_SYNTAXE_BINAIRE(-103,Texte.MSSG_ERREUR_SYNTAXE_BINAIRE),
    ;

    /**
     * contient le message d'erreur qui sera affiché.
     */
    private String message;

    /**
     * contient le code d'erreur retourné par le {@code exit}.
     */
    private int numero;

    /**
     * Construit une erreur.
     *
     * @param no le code d'erreur, devrait être négatif.
     * @param mssg le message d'erreur.  Ne doit pas être {@code null}.
     */
    private Erreur( int no, String mssg ) {
        numero = no;
        message = mssg;
    }

    /**
     * affiche le message d'erreur sur la canal d'erreur.
     */
    public void afficher() {
        afficher( "" );
    }


    /**
     * affiche le message d'erreur suivit d'un message complémentaire sur le canal d'erreur.
     *
     * @param complement un message complémentaire au message d'erreur.  Ne doit pas être {@code null}.
     */
    public void afficher( String complement ) {
        System.out.println( Texte.MSSG_ERREUR + message + "  " + complement );
    }


    /**
     * Affiche le message d'erreur sur le canal d'erreur et termine abruptement l'exécution de l'application en
     * retournant le code de l'erreur.
     */
    public void lancer() {
        lancer( "" );
    }

    /**
     * affiche le message d'erreur suivit d'un message complémentaire sur le canal d'erreur et termine abruptement
     * l'exécution de l'application en retournant le code de l'erreur.
     *
     * @param complement un message complémentaire au message d'erreur.  Ne doit pas être {@code null}.
     */
    public void lancer( String complement ) {
        System.err.println( Texte.MSSG_ERREUR + message + "  " + complement );
        if(numero == -102)
            System.err.println(Texte.MSG_ERR_AUCUN_FICHER);
        System.exit( numero );
    }
}
