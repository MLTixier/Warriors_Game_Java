package main;

import characters.*;
import exceptions.*;
import main.BDD.DB;
import main.des.De6Faces;
import main.des.De8Faces;
import main.plateau.FakePlateau;
import main.plateau.Plateau;
import main.plateau.RandomPlateau;
import main.plateau.VisualBoard;
import main.des.FakeDe;
import main.des.RandomDe;

import java.util.Scanner;

public class Game {

    public Scanner scanner;
    private Hero hero;
    private Plateau plateau;
    private VisualBoard visualBoard;
    private RandomDe de;
    private DB db;
    private MenuDemarrage menuDemarrage;

    /**
     * constructeur de la classe Game pour récupérer le scanner et le héros créés au menu démarrage, et instancier un plateau, un dé, une position du héros, etc.
     *
     * @param scanner            scanner
     * @param hero               héros
     * @param db                 bdd
     * @param difficulte         difficulté souhaitée du jeu : "dur" ou plus facile
     * @param nombreCasesPlateau nombre de cases souhaitées pour le plateau
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public Game(Scanner scanner, Hero hero, String gameDifficulty, int nombreCasesPlateau, DB db, MenuDemarrage menuDemarrage, String diceChoice) throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        this.menuDemarrage = menuDemarrage;
        this.scanner = scanner;
        this.hero = hero;
        this.db = db;
       if (gameDifficulty.equals("fake")){
            this.plateau = new FakePlateau(nombreCasesPlateau, this);
        } else if  (gameDifficulty.equals("dur")) {
            this.plateau = new RandomPlateau(nombreCasesPlateau, gameDifficulty, this);
        }
        this.visualBoard = new VisualBoard(plateau);
       if (diceChoice.equals("fake")){
        this.de = new FakeDe();
       } else if (diceChoice.equals("de8Faces")){
           this.de = new De8Faces();
       } else {
           this.de = new De6Faces();
       }
        System.out.println("Plateau de jeu créé !");
        visualBoard.afficherPlateauCache();
        System.out.println("les points signifient case vide, les coeurs un équipement, les X un monstre.");
        jouer();
    }

    /**
     * méthode pour faire la dynamique de chaque tour de jeu : lance le menu jouer avec les choix de l'utilisateur, puis affiche le plateau et résout l'événement de la case.
     *
     * @throws SortieJeuException
     */
    public void jouer() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        while (hero.isAlive()) {
            menuJouer();
            System.out.println("votre personnage est sur la case " + hero.getPosition());
            visualBoard.afficherPlateauFinal(hero.getPosition(), hero.getPreviousPosition());
            resolutionEvent();
            while (hero.isFuyard()) {
                hero.setFuyard(false);
                System.out.println("votre personnage est sur la case " + hero.getPosition());
                visualBoard.afficherPlateauFinal(hero.getPosition(), hero.getPreviousPosition());
                resolutionEvent();
            }
        }
    }

    /**
     * méthode pour afficher les choix utilisateurs à chaque début de tour : lancer le dé, afficher infos du personnage ou quitter.
     */
    public void menuJouer() throws PourcentagesPlateauException, SortieJeuException, FinJeuException {
        System.out.println("");
        System.out.println("Que souhaitez-vous faire ?");
        String decision = "";
        while (!(decision.equals("d") || decision.equals("a") || decision.equals("q"))) {
            System.out.println("Pour jeter le dé, tapez d. Pour afficher les infos de votre héros, tapez a. Pour abandonner la partie, tapez q");
            decision = scanner.nextLine();
        }
        if (decision.equals("d")) {
            int valeurDe = de.valeur();
            System.out.println("Vous avez jeté le dé et fait :" + valeurDe);
            try {
                hero.goesForward(valeurDe);
            } catch (SortieJeuException sortieJeuException) {
                System.out.println("vous êtes arrivé au bout du parcours, vous avez gagné !");
                hero = null;
                menuDemarrage.quitter();
            }
        } else if (decision.equals("q")) {
            menuDemarrage.quitter();
        } else if (decision.equals("a")) {
            System.out.println(hero);
        }

    }

    /**
     * méthode pour lire le contenu de la case, afficher ce qu'il va se passer et faire l'action.
     *
     * @throws SortieJeuException
     */
    public void resolutionEvent() throws SortieJeuException {
        System.out.println(this.plateau.getContenuPlateau(hero.getPosition()).readEvent());
        this.plateau.getContenuPlateau(hero.getPosition()).actionEvent(hero, scanner);
    }

    public void fuite() throws SortieJeuException {
        int nbCases = de.valeur();
        hero.goesBack(nbCases);
        System.out.println("vous avez reculé de " + nbCases + " cases.");
    }


}

