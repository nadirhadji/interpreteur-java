/**
 * Interface decrivant toute les actions qu'un interpreteur doit pouvoir faire
 *
 * Chaque méthode devra etre rendu concrete pour les classe :
 *          {@code IJava}
 *          {@code IOrdre}
 *          {@code IUML}
 */
public interface ContexteInterpretation {
    void genDebutClasse( MotDebutClasse motDebutClasse );
    void genFinClasse( MotFinClasse motFinClasse);
    void genDebutMethode( MotDebutMethode motDebutMethode);
    void genAttribut( MotAttribut motAttribut);
    void genAbstrait( MotAbstrait motAbstrait);
    void genParametre( MotParametre motParametre);
    void genFinMethode( MotFinMethode motFinMethode);
    void interpreteProgramme( Programme programme);
}
