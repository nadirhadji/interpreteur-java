/**
 * La commande binaire debutMethode est composée du nom de la commande, du nom et du type de retour de la methode
 */
public class MotDebutMethode extends Commande {

    public String nomMethode;
    public String retourMethode;

    /**
     * Constructeur de la commande MotDebutMethode
     *
     * @param nomMethode nom de la methode
     * @param retourMethode type de retour de la methode
     */
    public MotDebutMethode(String nomMethode, String retourMethode) {
        this.nomMethode = nomMethode;
        this.retourMethode = retourMethode;
    }

    /**
     * Appel la méthode genDebutMethode implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genDebutMethode(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotDebutMethode{}";
    }
}
