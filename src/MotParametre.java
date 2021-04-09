/**
 * La commande binaire parametre est composée du nom de la commande, son nom et son type.
 */
public class MotParametre extends Commande{

    public String typeParametre;
    public String nomParametre;

    /**
     * Constructeur de la commande MotParametre
     * @param typeParametre type du parametre
     * @param nomParametre nom du parametre
     */
    public MotParametre(String typeParametre, String nomParametre) {
        this.typeParametre = typeParametre;
        this.nomParametre = nomParametre;
    }

    /**
     * Appel la méthode genAbstrait implémentée par l'interpreteur en argument.
     *
     * @param contexte Un interpreteur parmis IJava , IOrdre, IUML
     */
    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.genParametre(this); }

    /**
     * Decrit la classe avec une chaine de caractére
     *
     * @return la chaine de caractere descriptive de la classe courante
     */
    @Override
    public String toString() {
        return "MotParametre{}";
    }
}
