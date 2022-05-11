package main;

import characters.*;
import equipements.*;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public Scanner scanner;
    private Hero hero;
    private Plateau plateau;
    private int position = 0;

    public Game(Scanner scanner, Hero hero) {
        this.scanner = scanner;
        this.hero = hero;
        this.plateau = new Plateau();
       jouer();
    }

    public void jouer() {
        while (hero != null) {
            menuJouer();
        }
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public void menuJouer() {
        System.out.println("Que souhaitez-vous faire ?");
        String decision = "";
        while (!(decision.equals("d") || decision.equals("a") || decision.equals("q"))) {
            System.out.println("Pour jeter le dé, tapez d. Pour afficher les infos de votre héros, tapez a. Pour abandonner la partie, tapez q");
            decision = scanner.nextLine();
        }
        if (decision.equals("d")) {
            int valeurDe = getRandomInt(6) + 1;
            System.out.println("Vous avez jeté le dé et fait :" + valeurDe);
            this.goesForward(valeurDe);
            System.out.println("Vous avez avancé jusqu'à la case :" + position);
            if (position >= 64) {
                System.out.println("vous êtes arrivé au bout du parcours, vous avez gagné !");
                hero = null;
                quitterJeu();
            } else {
                resolutionEvent();
            }
        } else if (decision.equals("q")) {
            quitterJeu();
        } else if (decision.equals("a")) {
            System.out.println(hero);
        }
    }

    public void resolutionEvent() {
        System.out.println(this.plateau.getContenuPlateau(position).readEvent());
        this.plateau.getContenuPlateau(position).actionEvent(hero, scanner);
    }

    /*----------------------------- génération aléatoire de nombres ------------------------------*/

    public int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    /*----------------------------- compatibilité héros - équipement ------------------------------*/


    public void goesForward(int numberOfCase) {
        int newPosition = this.getPosition() + numberOfCase;
        this.setPosition(newPosition);
    }

    public void goesBack(int numberOfCase) {
        int newPosition = this.getPosition() - numberOfCase;
        this.setPosition(newPosition);
        if (newPosition < 0) {
            this.setPosition(0);
        }
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
