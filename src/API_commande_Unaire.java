import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Contante qui decrie les differentes commandes unaire
 */
public enum API_commande_Unaire {

    classeDebut("classeDebut");

    /**
     * Contient un {@code Pattern} de reconnaissance pour identifier si une commande est un symbole valide de l'API.
     */
    public static final Pattern PATRON_CHOIX_TOUS =
            Pattern.compile( "\\s*classeDebut\\s*\\(\\s*[a-zA-Z0-9]+\\s*\\)");

    /**
     * Constructeur pour les commandes
     *
     * @param nom la syntaxe exacte de la commande
     */
    API_commande_Unaire(String nom) {
        this.nom = nom;
    }

    private String nom;
    private String identificateur;

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
    public static API_commande_Unaire lire( Scanner scanner) {

        API_commande_Unaire commande = classeDebut;
        String argBuff = "";
        String arg = "";

        String suivant = scanner.next();

        Pattern pattern = Pattern.compile("\\(.*\\)");
        Pattern pattern2 = Pattern.compile("[a-zA-Z0-9_]+");

        Matcher matcher = pattern.matcher(suivant);

        if( matcher.find() )
            argBuff = matcher.group();

        matcher = pattern2.matcher(argBuff);

        if(matcher.find())
            arg = matcher.group();

        commande.setIdentificateur(arg);

        return commande;
    }

    /**
     * Recuperer l'identificateur
     *
     * @return la valeur de l'identificateur detectée
     */
    public String getIdentificateur() {
        return identificateur;
    }

    /**
     * Enregistrer l'identificateur
     *
     * @param identificateur une chaine de caractere de type String
     */
    public void setIdentificateur(String identificateur) {
        this.identificateur = identificateur;
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
