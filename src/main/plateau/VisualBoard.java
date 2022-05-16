package main.plateau;

import main.plateau.Plateau;

public class VisualBoard {

    private char[] visualBoardFinal;
    private char[] visualBoardCache;

    public VisualBoard(Plateau plateau) {
        int nombreCasesPlateau = plateau.getPlateau().length;
        this.visualBoardFinal = new char[nombreCasesPlateau];
        this.visualBoardCache = new char[nombreCasesPlateau];
        for (int i = 0; i < nombreCasesPlateau; i++) {
            visualBoardFinal[i] = '\u2219';
            String stringContent = plateau.getContenuPlateau(i).getStringContent();
            if (stringContent.equals("monstre")) {
                visualBoardCache[i] = 'X';
            } else if (stringContent.equals("equipement") || stringContent.equals("potion")) {
                visualBoardCache[i] = '\u2665'; //image coeur
            } else {
                visualBoardCache[i] = '\u2219'; //point
            }
        }
    }

    public void afficherPlateauCache() {
        System.out.println(visualBoardCache);
    }

    public void afficherPlateauFinal(int position, int previousPosition) {
        visualBoardFinal[previousPosition] = visualBoardCache[previousPosition];
        visualBoardFinal[position] = 'O'; //curseur localisation
        System.out.println(visualBoardFinal);
    }

}
