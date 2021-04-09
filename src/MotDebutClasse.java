/**
 * La commande unaire debutClasse est composée du nom de la commande, et du nom de la classe
 */
public class MotDebutClasse extends Commande {

    public String nomClasse;

    /**
     * Constructeur de la commande MotDebutClasse
     *
     * @param nomClasse nom de la classe
     */
    public MotDebutClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    /**
     * Appel la méthode genDebutClasse implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genDebutClasse(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotDebutClasse{}";
    }
}
