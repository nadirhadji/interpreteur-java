/**
 * Constante utile pour l'ecriture d'un fichier .tex
 *
 * Classe redigée par Mr. Bruno Malenfant
 */
public class DescriptionLatex8 {
    public static final String FICHIER_DEBUT =
            "\\documentclass[landscape]{article}\n" +
                    "\\usepackage[T1]{fontenc}\n" +
                    "\\usepackage[utf8]{inputenc}\n\n" +
                    "\\usepackage{tikz}\n\n"+
                    "\\usetikzlibrary{shapes.multipart, shapes.geometric}\n\n" +
                    "\\tikzset{\n" +
                    "    edge from parent path={(\\tikzparentnode.south) -| (\\tikzchildnode.north)},\n"+
                    "    every one node part/.style={font=\\bfseries},\n" +
                    "    classeNode/.style = {\n" +
                    "        draw,\n" +
                    "        rectangle split,\n" +
                    "        rectangle split parts=3,\n" +
                    "        align=left\n" +
                    "    },\n" +
                    "    abstract/.style = {\n" +
                    "        font=\\itshape\n" +
                    "    },\n" +
                    "    extends/.style = {draw, regular polygon, regular polygon sides=3}\n" +
                    "}\n\n" +
                    "\\newcommand{\\abstrait}[1]{\\textit{#1}}\n\n" +
                    "\\begin{document}\n";

    public static final String FICHIER_FIN = "\\end{document}\n";


    public static final String PAGE_DEBUT =
            "\\centering{\n" +
                    "\\begin{figure}\n" +
                    "    \\begin{tikzpicture}\n" +
                    "    \\path\n";

    public static final String PAGE_FIN =
            "    ;\n" +
                    "    \\end{tikzpicture}\n" +
                    "\\end{figure}\n" +
                    "}\n\n" +
                    "\\clearpage\n";

    public static final String CLASSE_INTERNE_PREFIX = " child{\n";

    public static final String CLASSE_DEBUT =
            "node[classeNode]\n" +
                    "{\n" +
                    "    \\nodepart{one}\n";

    public static final String CLASSE_FIN = "}\n";
    public static final String CLASSE_INTERNE_SUFFIX = "}\n";

    public static final String LISTE_CLASSE_DEBUT =
            "[level distance=20mm]\n" +
                    "child{\n" +
                    "    node[extends] {}\n" +
                    "    [level distance=20mm, sibling distance=70mm]\n";

    public static final String LISTE_CLASSE_FIN = "}\n";
    public static final String LISTE_ATTRIBUT_DEBUT = "\\nodepart{two}\n";
    public static final String LISTE_ATTRIBUT_SEP = "\\\\\n";
    public static final String LISTE_METHODE_DEBUT = "\\nodepart{three}\n";
    public static final String LISTE_METHODE_SEP = "\\\\\n";
    public static final String ABSTRAIT_DEBUT = "\\abstrait{\n";
    public static final String ABSTRAIT_FIN = "}\n";
    public static final String PARAMETRE_DEBUT = "(";
    public static final String PARAMETRE_SEP = ", ";
    public static final String PARAMETRE_FIN = ")";
}