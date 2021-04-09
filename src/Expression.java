/**
 * Cette interface décrit le fonctionnement minimal qu'une {@code Commande} doit avoir pour faire
 * appel à l'interpréteur.
 *
 * La signature de cette opération uniformise l'opérateur d'interprétation des {@code Expression}.
 * La méthode d'interprétation recoit en argument une instance d'une classe implémentant l'interface
 * {@code ContexteInterprétation}.  Cette instance va maintenir l'information utile pour l'interprétation de toutes
 * les {@code Commande} (l'état de l'interpréteur).
 *
 * Intention :
 * Cela va nous permettre d'avoir une classe différente pour chaque type de {@code Commande} tout en uniformisant leurs
 * utilisation dans l'interpréteur.
 *
 * Rédigée par Mr. Bruno Malenfant
 */
public interface Expression {
    void interprete(ContexteInterpretation contexte);
}
