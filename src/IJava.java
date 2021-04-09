import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * IJava est un interpreteur de commande qui va rediger un code Java dans un fichier .java
 *
 * A partir de {@code Programme} qui contient une liste de commande
 */
public class IJava implements ContexteInterpretation{

    public Deque<PrintWriter> pileFichier;
    public Deque<String> pileNom;
    boolean estAbstrait;
    boolean estPremierParametre;

    /**
     * Constructeur de l'interpreteur IJava
     */
    public IJava() {
        this.pileFichier = new ArrayDeque<>();
        this.pileNom = new ArrayDeque<>();
        this.estAbstrait = false;
        this.estPremierParametre = false;
    }

    /**
     * Majuscule sur le premier caractere
     *
     * @param nom une chaine de caracteres
     * @return la chaine de caractere en argument avec comme premiere lettre une majuscule
     */
    private String formatNomVariable(String nom){
        return nom.substring(0,1).toUpperCase()+nom.substring(1,nom.length());
    }

    /**
     * Créer un fichier .java avec les informations dans motDebutClasse
     *
     * @param motDebutClasse la commande motDebutClasse dans {@code Programme}
     */
    @Override
    public void genDebutClasse(MotDebutClasse motDebutClasse) {

        String nomFichier = motDebutClasse.nomClasse + ".java";

        try {
            PrintWriter fichierEcriture = new PrintWriter(nomFichier);
            pileFichier.push(fichierEcriture);
            pileNom.push(motDebutClasse.nomClasse);
            fichierEcriture.print("public ");

            if(estAbstrait)
                fichierEcriture.print("abstract ");

            fichierEcriture.print("class "+motDebutClasse.nomClasse);

            if(pileNom.size() > 1) {
                fichierEcriture.print(" extends ");
                Iterator<String> it = pileNom.iterator();
                String classeMere;
                it.next();
                classeMere = it.next();
                fichierEcriture.print(classeMere);
            }

            fichierEcriture.println(" {");

            estAbstrait = false;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecrire la guillemet fermante pour la classe en tete de pile et fermer le fichier.
     *
     * @param motFinClasse la commande motFinClasse dans {@code Programme}
     */
    @Override
    public void genFinClasse(MotFinClasse motFinClasse) {

        PrintWriter fichierEcriture = pileFichier.getFirst();
        fichierEcriture.print("}");
        pileFichier.pop();
        pileNom.pop();
        fichierEcriture.close();
    }

    /**
     * Ecrire la declaration d'une méthode
     *
     * @param motDebutMethode la commande motDebutMethode dans {@code Programme}
     */
    @Override
    public void genDebutMethode(MotDebutMethode motDebutMethode) {

        PrintWriter fichierEcriture = pileFichier.getFirst();
        fichierEcriture.print("\tpublic ");

        if(estAbstrait)
            fichierEcriture.print("abstract ");

        fichierEcriture.print(motDebutMethode.nomMethode+" "+motDebutMethode.retourMethode+" ( ");

        estPremierParametre = true;
    }

    /**
     * Ecrire la declaration d'un attribut de classe et ces getter et setter.
     *
     * @param motAttribut la commande motAttribut dans {@code Programme}
     */
    @Override
    public void genAttribut(MotAttribut motAttribut) {

        PrintWriter fichierEcriture = pileFichier.getFirst();

        fichierEcriture.println("\tprivate "+motAttribut.typeAttribut+" "+motAttribut.nomAttribut+";\n");

        //methode get
        fichierEcriture.print("\tpublic "+motAttribut.typeAttribut+" get"+
                formatNomVariable(motAttribut.nomAttribut)+"() {\n");

        fichierEcriture.print("\t\treturn "+motAttribut.nomAttribut+";\n\t}\n");

        //methode set
        fichierEcriture.print("\tpublic "+motAttribut.typeAttribut+" set"+
                formatNomVariable(motAttribut.nomAttribut)+
                "( "+ motAttribut.typeAttribut + " "+ motAttribut.nomAttribut +" ) {\n");

        fichierEcriture.print("\t\tthis."+motAttribut.nomAttribut+ " = "+motAttribut.nomAttribut+";\n\t}\n");
    }

    /**
     * Modifier la variable d'etat estAbstrait de l'interpreteur Ijava a vrai
     *
     * @param motAbstrait la commande motAbstrait dans {@code Programme}
     */
    @Override
    public void genAbstrait(MotAbstrait motAbstrait) {
        estAbstrait = true;
    }

    /**
     * Ecrire un parametre apres la declaration d'une méthode
     *
     * @param motParametre la commande motParametre dans {@code Programme}
     */
    @Override
    public void genParametre(MotParametre motParametre) {

        PrintWriter fichierEcriture = pileFichier.getFirst();

        if(!estPremierParametre)
            fichierEcriture.print(" , ");

        fichierEcriture.print(motParametre.typeParametre+" "+motParametre.nomParametre);

        estPremierParametre = false;
    }

    /**
     * Ecrire la fin d'une méthode
     *
     * @param motFinMethode la commande motFinMethode dans {@code Programme}
     */
    @Override
    public void genFinMethode(MotFinMethode motFinMethode) {

        PrintWriter fichierEcriture = pileFichier.getFirst();

        fichierEcriture.print(") ");

        if(estAbstrait)
            fichierEcriture.print(";\n");
        else
            fichierEcriture.print("{ \n\t}\n");

        estAbstrait = false;
    }

    /**
     * Parcour un {@code Programme} et appele la methode de la classe courante appropriée
     *
     * @param programme la liste de toute les commandes {@code Commande} validée au préalable
     */
    @Override
    public void interpreteProgramme(Programme programme) { programme.forEach( ( e ) -> e.interprete( this ) ); }
}
