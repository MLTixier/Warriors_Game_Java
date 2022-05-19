package main;

import exceptions.FinJeuException;
import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;
import main.BDD.DB;
import main.BDD.RealDB;

public class Main {

    public Main() {
    }

    /**
     * méthode main qui est lue au démarrage du programme. lance le menu démarrage et catch l'exception fin du jeu.
     * Arguments possibles : dbExists ("true" ou "false"), diceChoice("fake", "de8Faces", "de6Faces"), gameDifficulty ("dur", "facile", "fake")
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */

    public static void main(String[] args) throws SortieJeuException, PourcentagesPlateauException {
        System.out.println("Bienvenue dans le jeu des Warriors !");
        String dbExists;
        String diceChoice;
        String gameDifficulty;
        if (args.length == 3) {
            dbExists = args[0];
            diceChoice = args[1];
            gameDifficulty = args[2];
        } else {
            dbExists = "false";
            diceChoice = "de6Faces";
            gameDifficulty = "dur";
        }
        try {
            MenuDemarrage menuDemarrage = new MenuDemarrage(dbExists, diceChoice, gameDifficulty);
        } catch (
                FinJeuException finJeuException) {
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        }
    }

}
