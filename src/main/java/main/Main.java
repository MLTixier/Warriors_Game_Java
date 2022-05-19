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
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */

    public static void main(String[] args) throws SortieJeuException, PourcentagesPlateauException {
        System.out.println("Bienvenue dans le jeu des Warriors !");
        String dbExists = args[0];
        try {
            MenuDemarrage menuDemarrage = new MenuDemarrage(dbExists);
        } catch (
                FinJeuException finJeuException) {
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        }
    }

}
