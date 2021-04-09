import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Cette classe englode plusieurs {@link Commande} dans une liste
 */
public class Programme extends ArrayList<Commande> implements Expression {

    /**
     * Le caractère utilisé pour séparé les commandes lors de la lecture .
     */
    public static final String SEPARATEUR = "\r\n";

    /**
     * Construit un programme sans commande
     */
    public Programme() {
    }

    /**
     * Construit un programme a partir des commandes dans le fichier
     *
     * @param nomFichier le nom du fichier
     */
    public Programme(String nomFichier) {

        File fichier = new File( nomFichier );
        Scanner scanner = null;

        try{
            scanner = new Scanner( fichier );
        } catch( FileNotFoundException e ) {
            Erreur.FICHIER_INEXISTANT.lancer( "\"" + nomFichier +"\"" );
        }

        scanner.useDelimiter(SEPARATEUR);

        while (scanner.hasNextLine()){

            //Essayer de tout stocker dans variable et de tester une chaine de carac a la place de passer en srgument scanner

            if(scanner.hasNext(API_Commande_Nullaire.PATRON_CHOIX_TOUS))
                lireNullaire( scanner);

            else if(scanner.hasNext(API_commande_Unaire.PATRON_CHOIX_TOUS))
                lireUnaire( scanner);

            else if(scanner.hasNext(API_Commande_Binaire.PATRON_CHOIX_TOUS))
                lireBinaire( scanner);

            else if(scanner.hasNextLine())
                scanner.nextLine();

            else
                Erreur.ERREUR_SYNTAXE_COMMANDE.lancer();
        }

        scanner.close();
    }

    /**
     *Lit une suite de commande de type nullaire dans le {@code Scanner}
     *
     * Consulte le {@code Scanner} pour lire une suite de commande nullaire séparé par le caractère {@code SEPARATEUR}.
     */
    private void lireNullaire(Scanner scanner){
        try{
            addCommandeNullaire(scanner);
        } catch( NoSuchElementException e ) {
            Erreur.ERREUR_SYNTAXE_NULLAIRE.lancer();
        }
    }

    /**
     *Lit une suite de commande de type unaire dans le {@code Scanner}
     *
     * Consulte le {@code Scanner} pour lire une suite de commande unaire séparé par le caractère {@code SEPARATEUR}.
     */
    private void lireUnaire(Scanner scanner){
        try{
            addCommandeUnaire(scanner);
        } catch( NoSuchElementException e ) {
            Erreur.ERREUR_SYNTAXE_UNAIRE.lancer();
        }
    }

    /**
     *Lit une suite de commande de type binaire dans le {@code Scanner}
     *
     * Consulte le {@code Scanner} pour lire une suite de commande binaire séparé par le caractère {@code SEPARATEUR}.
     */
    private void lireBinaire(Scanner scanner){
        try{
            addCommandeBinaire(scanner);
        } catch( NoSuchElementException e ) {
            Erreur.ERREUR_SYNTAXE_BINAIRE.lancer();
        }
    }

    /**
     * Ajouter la commande nullaire appropriée dans la liste courante
     *
     * @param scanner
     */
    private void addCommandeNullaire(Scanner scanner) {

        API_Commande_Nullaire commande = API_Commande_Nullaire.lire(scanner);

        switch (commande) {
            case abstrait -> addAbstrait();
            case methodeFin -> addMethodeFin();
            case classeFin -> addClasseFin();
        }
    }

    /**
     * Ajouter la commande unaire appropriée dans la liste courante
     *
     * @param scanner
     */
    private void addCommandeUnaire(Scanner scanner) {
        API_commande_Unaire commande = API_commande_Unaire.lire(scanner);
        addClasseDebut(commande);
    }

    /**
     * Ajouter la commande binaire appropriée dans la liste courante
     *
     * @param scanner
     */
    private void addCommandeBinaire(Scanner scanner) {

        API_Commande_Binaire commande = API_Commande_Binaire.lire(scanner);

        switch (commande) {
            case parametre -> addParametre(commande);
            case methodeDebut -> addMethodeDebut(commande);
            case attribut -> addAttribut(commande);
        }
    }

    /**
     * Ajouter une commande de type MotAbstrait au programme courant
     */
    private void addAbstrait() {
        this.add(new MotAbstrait());
    }

    /**
     * Ajouter une commande de type MotAttribut au programme courant
     */
    private void addAttribut(API_Commande_Binaire commande) {
        this.add(new MotAttribut(commande.getIdentificateur1(), commande.getIdentificateur2()));
    }

    /**
     * Ajouter une commande de type MotClasseDebut au programme courant
     */
    private void addClasseDebut(API_commande_Unaire commande){
        this.add(new MotDebutClasse(commande.getIdentificateur()));
    }

    /**
     * Ajouter une commande de type MotClasseFin au programme courant
     */
    private void addClasseFin(){
        this.add(new MotFinClasse());
    }

    /**
     * Ajouter une commande de type MotMethodeDebut au programme courant
     */
    private void addMethodeDebut(API_Commande_Binaire commande){
        this.add(new MotDebutMethode(commande.getIdentificateur1(), commande.getIdentificateur2()));
    }

    /**
     * Ajouter une commande de type MotMethodeDebut au programme courant
     */
    private void addMethodeFin(){
        this.add(new MotFinMethode());
    }

    /**
     * Ajouter une commande de type MotParametre au programme courant
     */
    private void addParametre(API_Commande_Binaire commande) {
        this.add(new MotParametre(commande.getIdentificateur1(), commande.getIdentificateur2()));
    }

    @Override
    public void interprete(ContexteInterpretation contexte) { contexte.interpreteProgramme(this); }
}
