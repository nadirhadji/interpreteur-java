/**
 * La commande unaire finClasse est seulement composée de son nom
 */
public class MotFinClasse extends Commande {

    /**
     * Appel la méthode genFinClasse implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genFinClasse(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotFinClasse{}";
    }
}
