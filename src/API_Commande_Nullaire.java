import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Contante qui decrie les differentes commandes nullaire
 */
public enum API_Commande_Nullaire {

    methodeFin("methodeFin"),
    classeFin("classeFin"),
    abstrait("abstrait");

    /**
     * Contient toutes les constantes de la classe dans l'ordre des déclarations.
     */
    private static final API_Commande_Nullaire [] TOUS = API_Commande_Nullaire.class.getEnumConstants();

    /**
     * Contient le nom de chaque symbole de l'API separée par un '|'
     *
     * Va servir de sous chaine dans la formation de l'expression reguliere PATRON_CHOIX_TOUS
     **/
    private static final String S_TOUS =
            Arrays.stream( TOUS )
                    .map( API_Commande_Nullaire::toString )
                    .collect( Collectors.joining("|"));

    /**
     * Contient un {@code Pattern} de reconnaissance pour identifier si une commande est un symbole valide de l'API.
     */
    public static final Pattern PATRON_CHOIX_TOUS = Pattern.compile( "\\s*(" + S_TOUS + ")\\s*");

    /**
     * Constructeur pour les commandes
     *
     * @param nom la syntaxe exacte de la commande
     */
    API_Commande_Nullaire(String nom) {
        this.nom = nom;
    }

    private String nom;

    /**
     * Lire une commande dans le {@code Scanner}.
     *
     * Vérifie si le prochain symbole du {@code scanner} représente une commande.  Si oui, alors le symbole est lu et
     * la constante représentant cette commande est retournée.
     *
     * @param scanner le {@code Scanner} dans lequel la lecture est effectué.
     * @return la constante représenté par le symbole.
     * @exception NoSuchElementException s'il n'y a pas de {@code API_Commande } valide.
     * @exception IllegalStateException si le {@code Scanner} est fermé.
     */
    public static API_Commande_Nullaire lire( Scanner scanner) {

        API_Commande_Nullaire commande = null;

        String suivant = scanner.next();

        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(suivant);

        if(matcher.find())
            suivant = matcher.group();

        for(int i = 0 ; i < TOUS.length && commande == null ; i++){

            if(suivant.equals(TOUS[i].toString()))
                commande = TOUS[i];
        }

        return commande;
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


