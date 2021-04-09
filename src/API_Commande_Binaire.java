import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Contante qui decrie les differentes commande binaire
 */
public enum API_Commande_Binaire {

    methodeDebut("methodeDebut"),
    attribut("attribut"),
    parametre("parametre");

    /**
     * Contient toutes les constantes de la classe dans l'ordre des déclarations.
     */
    private static final API_Commande_Binaire[] TOUS = API_Commande_Binaire.class.getEnumConstants();

    /**
     * Contient le nom de chaque symbole de l'API delimitée par un '|'
     *
     * Sert de sous chaine de caractere dans S_TOUS pour former une expression reguliere
     */
    private static final String S_TOUS =
            Arrays.stream( TOUS )
                    .map( API_Commande_Binaire::toString )
                    .collect( Collectors.joining("|"));

    /**
     * Contient un {@code Pattern} de reconnaissance pour identifier si une ligne du fichier contient une commande
     * binaire
     */
    public static final Pattern PATRON_CHOIX_TOUS =
            Pattern.compile( "\\s*(" + S_TOUS + ")\\s*\\(\\s*[a-zA-Z0-9]+\\s*,\\s*[a-zA-Z0-9]+\\s*\\)");

    /**
     * Constructeur pour les commandes binaire
     *
     * @param nom la syntaxe exacte de la commande
     */
    API_Commande_Binaire(String nom) {
        this.nom = nom;
    }

    private String nom;
    private String identificateur1;
    private String identificateur2;

    /**
     * Lire une commande binaire dans le {@code Scanner}.
     *
     * Vérifie si le prochain symbole du {@code scanner} représente une commande.  Si oui, alors le symbole est lu et
     * la constante représentant cette commande est retournée.
     *
     * @param scanner le {@code Scanner} dans lequel la lecture est effectué.
     * @return la constante représenté par le symbole.
     * @exception NoSuchElementException s'il n'y a pas de {@code API_Commande } valide.
     * @exception IllegalStateException si le {@code Scanner} est fermé.
     */
    public static API_Commande_Binaire lire( Scanner scanner) {

        API_Commande_Binaire commande = null;

        String nomCommande = "";
        String ArgumentAvecParenthese = "";
        String argument1 = "";
        String argument2 = "";

        String suivant = scanner.next();

        //Recuperation du nom de la commande dans la chaine retournée par Scanner
        Pattern patternNomCommande = Pattern.compile("("+ S_TOUS +")");
        Matcher matcher = patternNomCommande.matcher(suivant);

        if(matcher.find())
            nomCommande = matcher.group();

        for(int i = 0 ; i < TOUS.length && commande == null ; i++){

            if(nomCommande.equals(TOUS[i].toString()))
                commande = TOUS[i];
        }

        //Recuperation des deux parenthése ainsi que leur contenue
        Pattern patternArgumentAvecParenthese = Pattern.compile("\\(.*\\)");
        matcher = patternArgumentAvecParenthese.matcher(suivant);

        if(matcher.find())
            ArgumentAvecParenthese = matcher.group();

        //Recuperation 1 a 1 des arguments entre parenthése
        Pattern patternArgument = Pattern.compile("[a-zA-Z0-9_]+");
        matcher = patternArgument.matcher(ArgumentAvecParenthese);

        //Recuperation argument 1
        if(matcher.find())
            argument1 = matcher.group();

        //Recuperation argument 2
        if(matcher.find())
            argument2 = matcher.group();

        commande.setIdentificateur1(argument1);
        commande.setIdentificateur2(argument2);

        return commande;
    }

    /**
     * Recupereur la valeur de l'identificateur 1
     *
     * @return identificateur1
     */
    public String getIdentificateur1() {
        return identificateur1;
    }

    /**
     * Recupereur la valeur de l'identificateur 2
     *
     * @return identificateur2
     */
    public String getIdentificateur2() {
        return identificateur2;
    }

    /**
     * Définir la valeur de l'identificateur 1
     *
     * @param identificateur1 valeur a enregistrer dans identificateur 1
     */
    public void setIdentificateur1(String identificateur1) {
        this.identificateur1 = identificateur1;
    }

    /**
     * Définir la valeur de l'identificateur 2
     *
     * @param identificateur2 valeur a enregistrer dans identificateur 2
     */
    public void setIdentificateur2(String identificateur2) {
        this.identificateur2 = identificateur2;
    }

    /**
     * Retourne une chaîne de caractère contenant le symbole de l'API représentant la commande.
     *
     * @return la chaîne contenant le nom de la commande
     */
    @Override
    public String toString() {
        return nom;
    }
}
