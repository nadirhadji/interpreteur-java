/**
 * La commande unaire finMethode est seulement composée de son nom
 */
public class MotFinMethode extends Commande {

    /**
     * Appel la méthode genFinMethode implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genFinMethode(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotFinMethode{}";
    }
}
