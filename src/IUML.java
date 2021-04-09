import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * IUML est un interpreteur de commande qui va rediger un diagramme UML dans un fichier .tex
 */
public class IUML implements ContexteInterpretation {

    public int nbrClasse;
    public Deque<EtatUML> pileEtat;
    public boolean estAbstrait;
    public boolean estPremierParametre;
    private PrintWriter fichierEcriture;

    public IUML() {
        this.nbrClasse = 0;
        this.pileEtat = new ArrayDeque<>();
        this.estAbstrait = false;
        this.estPremierParametre = false;
    }

    /**
     * Initialiser le fichier latex si besoin et declarer une classe
     *
     * @param motDebutClasse la commande motDebutClasse dans {@code Programme}
     */
    @Override
    public void genDebutClasse(MotDebutClasse motDebutClasse) {

            if(nbrClasse == 0)
                fichierEcriture.print(DescriptionLatex8.PAGE_DEBUT);
            else {

                if(pileEtat.getFirst().isPremiereClasse()) {
                    fichierEcriture.print(DescriptionLatex8.CLASSE_FIN);
                    fichierEcriture.print(DescriptionLatex8.LISTE_CLASSE_DEBUT);
                    pileEtat.getFirst().setPremiereClasse(false);
                }

                fichierEcriture.print(DescriptionLatex8.CLASSE_INTERNE_PREFIX);
            }

            pileEtat.push(new EtatUML());
            fichierEcriture.print(DescriptionLatex8.CLASSE_DEBUT);

            if(estAbstrait)
                fichierEcriture.print(DescriptionLatex8.ABSTRAIT_DEBUT);

            fichierEcriture.print(motDebutClasse.nomClasse+"\n");

            if(estAbstrait) {
                fichierEcriture.print(DescriptionLatex8.ABSTRAIT_FIN);
                estAbstrait = false;
            }

            nbrClasse = nbrClasse + 1;
    }

    /**
     * Ecrire le code latex indiquant la fin d'une classe dans le fichier uml.tex
     *
     * @param motFinClasse la commande motFinClasse dans {@code Programme}
     */
    @Override
    public void genFinClasse(MotFinClasse motFinClasse) {

        nbrClasse = nbrClasse - 1;

        if(pileEtat.getFirst().isPremiereClasse())
            fichierEcriture.print(DescriptionLatex8.CLASSE_FIN);
        else
            fichierEcriture.print(DescriptionLatex8.LISTE_CLASSE_FIN);

        if(nbrClasse == 0)
            fichierEcriture.print(DescriptionLatex8.PAGE_FIN);
        else
            fichierEcriture.print(DescriptionLatex8.CLASSE_INTERNE_SUFFIX);

        pileEtat.pop();
    }

    /**
     * Ecrire le code latex indiquant le debut d'une methode dans une classe
     *
     * @param motDebutMethode la commande motDebutMethode dans {@code Programme}
     */
    @Override
    public void genDebutMethode(MotDebutMethode motDebutMethode) {

        if(pileEtat.getFirst().isPremiereMethode()) {
            fichierEcriture.print(DescriptionLatex8.LISTE_METHODE_DEBUT);
            pileEtat.getFirst().setPremiereMethode(false);
        }
        else
            fichierEcriture.print(DescriptionLatex8.LISTE_METHODE_SEP);

        if(estAbstrait)
            fichierEcriture.print(DescriptionLatex8.ABSTRAIT_DEBUT);

        if(!motDebutMethode.nomMethode.equals("void"))
            fichierEcriture.print(motDebutMethode.nomMethode+" ");

        fichierEcriture.print(motDebutMethode.retourMethode);
        fichierEcriture.print(DescriptionLatex8.PARAMETRE_DEBUT);
        estPremierParametre = true;
    }

    /**
     * Ecrire le code latex indiquant un attribut a une classe dans le fichier uml.tex
     *
     * @param motAttribut la commande motAttribut dans {@code Programme}
     */
    @Override
    public void genAttribut(MotAttribut motAttribut) {

        if(pileEtat.getFirst().isPremierAttribut()) {
            fichierEcriture.print(DescriptionLatex8.LISTE_ATTRIBUT_DEBUT);
            pileEtat.getFirst().setPremierAttribut(false);
        }
        else
            fichierEcriture.print(DescriptionLatex8.LISTE_ATTRIBUT_SEP);

        fichierEcriture.print(motAttribut.nomAttribut+":"+motAttribut.typeAttribut);
    }

    /**
     * Modifier l'etat de la variable d'état 'estAbstrait' a vrai
     *
     * @param motAbstrait la commande motAbstrait
     */
    @Override
    public void genAbstrait(MotAbstrait motAbstrait) {
        estAbstrait = true;
    }

    /**
     * Ecrire le code latex indiquant un parametre dans le fichier uml.tex
     *
     * @param motParametre la commande motParametre dans {@code Programme}
     */
    @Override
    public void genParametre(MotParametre motParametre) {

        if(estPremierParametre)
            estPremierParametre = false;
        else
            fichierEcriture.print(DescriptionLatex8.PARAMETRE_SEP);

        fichierEcriture.print(motParametre.typeParametre);
    }

    /**
     * Ecrire le code latex indiquant une fin de classe dans le fichier uml.tex
     *
     * @param motFinMethode la commande motFinMethode dans {@code Programme}
     */
    @Override
    public void genFinMethode(MotFinMethode motFinMethode) {

        fichierEcriture.print(DescriptionLatex8.PARAMETRE_FIN);

        if(estAbstrait) {
            fichierEcriture.print(DescriptionLatex8.ABSTRAIT_FIN);
            estAbstrait = false;
        }
    }

    /**
     * Iniliaser , ecrire et cloturer le fichier uml.tex en fonction du programme
     *
     * @param programme la liste de Commande a interpreter en latex
     */
    @Override
    public void interpreteProgramme(Programme programme) {

        //initialisation du fichier uml.tex
        try {
            fichierEcriture = new PrintWriter("uml.tex");
            fichierEcriture.print(DescriptionLatex8.FICHIER_DEBUT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //traitement a appliquer pour chaque commande
        programme.forEach( ( e ) -> e.interprete( this ) );

        //cloture du fichier
        fichierEcriture.print(DescriptionLatex8.FICHIER_FIN);
        fichierEcriture.close();

    }
}
