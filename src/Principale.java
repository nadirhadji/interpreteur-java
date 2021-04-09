import java.util.Scanner;

/**
 * Classe Principale du logiciel Interpreteur de pseudo language java
 *
 * Ce logiciel verifie la syntaxe ainsi que l'ordre de declaration du pseudo code.
 * Puis genere un vrai code java ainsi qu'un shema UML en Latex.
 *
 * @author Nadir Hadji (HADN08069703)
 * @author Saliou Cissé (CISS20129907)
 */
public class Principale {

    public static Scanner clavier;

    /**
     * Demande le nom du fichier dans lequel la suite de syllabe sera lu.
     *
     * @param scanner Indique l'endroit où le nom du fichier sera lu.
     * @return une chaîne de caractères contenant le nom du fichier.
     */
    public static String demanderNomFichier( Scanner scanner ) {
        String resultat = "";

        System.out.print( Texte.MSSG_DEMANDE_NOMFICHIER );
        resultat = scanner.nextLine();

        return resultat;
    }

    public static void main(String[] args) {

        clavier = new Scanner( System.in );

        String nomFichier = demanderNomFichier(clavier);

        Programme programme = new Programme(nomFichier);

        ContexteInterpretation etatOrdre = new IOrdre();
        programme.interprete(etatOrdre);

        ContexteInterpretation etatJava = new IJava();
        programme.interprete(etatJava);

        ContexteInterpretation etatUml = new IUML();
        programme.interprete(etatUml);
    }
}
