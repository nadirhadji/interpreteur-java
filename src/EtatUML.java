/**
 * Classe servant a suivre l'etat d'une classe dans son contexte d'execution
 */
public class EtatUML {

    private boolean premierAttribut;
    private boolean premiereMethode;
    private boolean premiereClasse;

    /**
     * Constructeur d'un EtatUML
     */
    public EtatUML() {
        this.premierAttribut = true;
        this.premiereMethode = true;
        this.premiereClasse = true;
    }

    /**
     * Enregistrer la valeur de l'attribut premierAttribut
     *
     * @param premierAttribut true ou false
     */
    public void setPremierAttribut(boolean premierAttribut) {
        this.premierAttribut = premierAttribut;
    }

    /**
     * Enregistrer la valeur de l'attribut premiereMethode
     *
     * @param premiereMethode true ou false
     */
    public void setPremiereMethode(boolean premiereMethode) {
        this.premiereMethode = premiereMethode;
    }

    /**
     * Enregistrer la valeur de l'attribut premiereClasse
     *
     * @param premiereClasse true ou false
     */
    public void setPremiereClasse(boolean premiereClasse) {
        this.premiereClasse = premiereClasse;
    }

    /**
     * Savoir si l'etatUML courant en est a son premier attribut.
     *
     * @return true ou false
     */
    public boolean isPremierAttribut() {
        return premierAttribut;
    }

    /**
     * Savoir si l'etatUML courant en est a sa premiere methode.
     *
     * @return true ou false
     */
    public boolean isPremiereMethode() {
        return premiereMethode;
    }

    /**
     * Savoir si l'etatUML courant en est a sa premiere methode.
     *
     * @return true ou false
     */
    public boolean isPremiereClasse() {
        return premiereClasse;
    }
}
