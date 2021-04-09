/**
 * La commande binaire attribut est composée du nom de la commande, son nom et son type.
 */
public class MotAttribut extends Commande {

    public String typeAttribut;
    public String nomAttribut;

    /**
     * Constructeur de la commande MotAttribut
     *
     * @param typeAttribut type de l'attribut
     * @param nomAttribut nom  de l'attribut
     */
    public MotAttribut(String typeAttribut, String nomAttribut) {
        this.typeAttribut = typeAttribut;
        this.nomAttribut = nomAttribut;
    }

    /**
     * Appel la méthode genAttribut implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genAttribut(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotAttribut{}";
    }
}
