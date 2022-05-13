package main;

import characters.*;
import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public Scanner scanner;
    private Hero hero;
    private Plateau plateau;
    private VisualBoard visualBoard ;

    public Game(Scanner scanner, Hero hero) throws SortieJeuException, PourcentagesPlateauException {
        this.scanner = scanner;
        this.hero = hero;
        this.plateau = new Plateau();
        this.visualBoard = new VisualBoard(plateau);
        System.out.println("Plateau de jeu créé !");
        visualBoard.afficherPlateauCache();
        System.out.println("les points signifient case vide, les coeurs un équipement, les X un monstre.");
        jouer();
    }

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

    public void menuJouer() {
        System.out.println("");
        System.out.println("Que souhaitez-vous faire ?");
        String decision = "";
        while (!(decision.equals("d") || decision.equals("a") || decision.equals("q"))) {
            System.out.println("Pour jeter le dé, tapez d. Pour afficher les infos de votre héros, tapez a. Pour abandonner la partie, tapez q");
            decision = scanner.nextLine();
        }
        if (decision.equals("d")) {
            int valeurDe = getRandomInt(6) + 1;
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

    public void resolutionEvent() throws SortieJeuException {
        System.out.println(this.plateau.getContenuPlateau(hero.getPosition()).readEvent());
        this.plateau.getContenuPlateau(hero.getPosition()).actionEvent(hero, scanner);
    }

    /*----------------------------- génération aléatoire de nombres ------------------------------*/

    public int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }


    public void quitterJeu() {
        System.out.println("Voulez-vous quitter le jeu définitivement ?");
        String choixQuitter = "";
        while (!(choixQuitter.equals("o") || choixQuitter.equals("r"))) {
            System.out.println("Tapez o si oui, tapez r pour recommencer le jeu");
            choixQuitter = scanner.nextLine();
        }
        if (choixQuitter.equals("o")) {
            hero = null;
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        } else if (choixQuitter.equals("r")) {
            Main main = new Main();
        }
    }



}
