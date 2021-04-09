/**
 * IOrdre est un interpreteur de commande qui va valider la syntaxe de chaque ligne du fichier a lire.
 */
public class IOrdre implements ContexteInterpretation {

    public Mode mode;
    public boolean estAbstrait;
    public int nbrOuverture;

    /**
     * Contructeur de l'interpreteur IOrdre
     */
    public IOrdre() {
        this.mode = Mode.FClasse;
        this.estAbstrait = false;
        this.nbrOuverture = 0;
    }

    /**
     * Verifier si le mode pour la methode genClasseDebut et genClasseFin est valide
     *
     * Les modes valides sont :
     *          {@code Mode}   DClasse
     *          {@code Mode}   FClasse
     *          {@code Mode}   DAttribut
     *          {@code Mode}   FMethode
     *
     * @return vrai si valide sinon faux
     */
    public boolean verifierModeClasse() {

        boolean resultat = false;

        if (mode == Mode.DClasse || mode == Mode.FClasse || mode == Mode.DAttribut || mode == Mode.FMethode)
            resultat = true;

        return resultat;
    }

    /**
     * Verifie si la commande debutClasse peut etre interpreté sinon interuption du logiciel
     *
     * @param motDebutClasse
     */
    @Override
    public void genDebutClasse(MotDebutClasse motDebutClasse) {

        if(verifierModeClasse()){
            nbrOuverture = nbrOuverture + 1;
            mode = Mode.DClasse;
            estAbstrait = false;
        }
        else
            Erreur.ERREUR_CLASSE_DEBUT.lancer();
    }

    /**
     *Verifie si la commande finClasse peut etre interpreté sinon interuption du logiciel
     * @param motFinClasse
     */
    @Override
    public void genFinClasse(MotFinClasse motFinClasse) {

        if(verifierModeClasse() && !estAbstrait && (nbrOuverture > 0) ){
            nbrOuverture = nbrOuverture - 1;
            mode = Mode.FClasse;
        }
        else
            Erreur.ERREUR_CLASSE_FIN.lancer();
    }

    /**
     * Verifie si la commande debutMethode peut etre interpreté sinon interuption du logiciel
     * @param motDebutMethode
     */
    @Override
    public void genDebutMethode(MotDebutMethode motDebutMethode) {

        if( mode == Mode.DClasse || mode == Mode.DAttribut || mode == Mode.FMethode) {
            mode = Mode.DMethode;
            estAbstrait = false;
        }
        else
            Erreur.ERREUR_METHODE_DEBUT.lancer();
    }

    /**
     * Verifie si la commande attribut peut etre interpreté sinon interuption du logiciel
     * @param motAttribut
     */
    @Override
    public void genAttribut(MotAttribut motAttribut) {

        if( (mode == Mode.DClasse || mode == Mode.DAttribut) && !estAbstrait ) {
            mode = Mode.DAttribut;
        }
        else
            Erreur.ERREUR_ATTRIBUT.lancer();
    }

    /**
     * Verifie si la commande abstrait peut etre interpreté sinon interuption du logiciel
     * @param motAbstrait
     */
    @Override
    public void genAbstrait(MotAbstrait motAbstrait) {

        if(!estAbstrait)
            estAbstrait = true;
        else
            Erreur.ERREUR_ABSTRAIT.lancer();
    }

    /**
     * Verifie si la commande parametre peut etre interpreté sinon interuption du logiciel
     * @param motParametre
     */
    @Override
    public void genParametre(MotParametre motParametre) {

        if( (mode == Mode.DMethode || mode == Mode.DParametre) && !estAbstrait)
            mode = Mode.DParametre;
        else
            Erreur.ERREUR_PARAM.lancer();
    }

    /**
     * Verifie si la commande finMethode peut etre interpreté sinon interuption du logiciel
     * @param motFinMethode
     */
    @Override
    public void genFinMethode(MotFinMethode motFinMethode) {

        if( (mode == Mode.DMethode || mode == Mode.DParametre) && !estAbstrait)
            mode = Mode.FMethode;
        else
            Erreur.ERREUR_METHODE_FIN.lancer();
    }

    @Override
    public void interpreteProgramme(Programme programme) { programme.forEach( ( e ) -> e.interprete( this ) ); }
}
