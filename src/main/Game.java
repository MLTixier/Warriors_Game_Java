package main;

import characters.*;
import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;
import main.plateau.FakePlateau;
import main.plateau.Plateau;
import main.plateau.VisualBoard;
import main.random.De6Faces;
import main.random.FakeDe;
import main.random.RandomDe;

import java.util.Scanner;

public class Game {

    public Scanner scanner;
    private Hero hero;
    private Plateau plateau;
    private VisualBoard visualBoard ;
    private RandomDe de ;

    /**
     * constructeur de la classe Game pour récupérer le scanner et le héros créés au menu démarrage, et instancier un plateau, un dé, une position du héros, etc.
     * @param scanner scanner
     * @param hero héros
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public Game(Scanner scanner, Hero hero) throws SortieJeuException, PourcentagesPlateauException {
        this.scanner = scanner;
        this.hero = hero;
        this.plateau = new FakePlateau();
        this.visualBoard = new VisualBoard(plateau);
        this.de = new FakeDe();
        System.out.println("Plateau de jeu créé !");
        visualBoard.afficherPlateauCache();
        System.out.println("les points signifient case vide, les coeurs un équipement, les X un monstre.");
        jouer();
    }

    /**
     * méthode pour faire la dynamique de chaque tour de jeu : lance le menu jouer avec les choix de l'utilisateur, puis affiche le plateau et résout l'événement de la case.
     * @throws SortieJeuException
     */
    public void jouer() throws SortieJeuException {
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
    public void menuJouer() {
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
            }catch (SortieJeuException sortieJeuException){
                System.out.println("vous êtes arrivé au bout du parcours, vous avez gagné !");
                hero = null;
                quitterJeu();
            }
        } else if (decision.equals("q")) {
            quitterJeu();
        } else if (decision.equals("a")) {
            System.out.println(hero);
        }
    }

    /**
     * méthode pour lire le contenu de la case, afficher ce qu'il va se passer et faire l'action.
     * @throws SortieJeuException
     */
    public void resolutionEvent() throws SortieJeuException {
        System.out.println(this.plateau.getContenuPlateau(hero.getPosition()).readEvent());
        this.plateau.getContenuPlateau(hero.getPosition()).actionEvent(hero, scanner);
    }

    /**
     * méthode pour quitter le jeu
     */
    public void quitterJeu() {
        System.out.println("Voulez-vous quitter le jeu définitivement ?");
        String choixQuitter = "";
        while (!(choixQuitter.equals("o") || choixQuitter.equals("r"))) {
            System.out.println("Tapez o si oui, tapez r pour recommencer le jeu");
            choixQuitter = scanner.nextLine();
        }
        if (choixQuitter.equals("o")) {
            hero.setExists(false);
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        } else if (choixQuitter.equals("r")) {
            Main main = new Main();
        }
    }
}
