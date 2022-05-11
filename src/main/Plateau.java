package main;

import characters.*;
import equipements.*;
import exceptions.CaseVideException;

import java.util.*;

import static java.util.Map.entry;

public class Plateau {

    private Map<String, Integer> listeEvents = Map.ofEntries(
            entry("characters.Dragon", 2),
            entry("characters.Sorcier", 8),
            entry("characters.Gobelin", 10),
            entry("equipements.Invisibilite", 3),
            entry("equipements.BouleDeFeu", 4),
            entry("equipements.Eclair", 5),
            entry("equipements.Arc", 3),
            entry("equipements.Epee", 4),
            entry("equipements.Massue", 5),
            entry("equipements.PotionSimple", 10),
            entry("equipements.GrandePotion", 5),
            entry("main.EmptyCase", 5)
    );

    private IEvent[] plateau = new IEvent[64];

    public Plateau() {
        ajoutEvents();
        try {
            verifyEmptyCases();
        } catch (CaseVideException caseVideException) {
            System.out.println(caseVideException.getMessage());
        }
        System.out.println("Plateau de jeu créé !");
    }

    public IEvent[] getPlateau() {
        return plateau;
    }

    public void setContenuPlateau(IEvent event, int indice) {
        this.plateau[indice] = event;
    }

    public IEvent getContenuPlateau(int indice) {
        return this.plateau[indice];
    }

    public void ajoutEvents() {
        //creation de n events pour chaque ligne du tableau listeEvents :
        int[] randomCases = getTableauRandom(64);
        System.out.println(randomCases);
        int i = 0;
        for (Map.Entry<String, Integer> events : listeEvents.entrySet()) {
            String typeEvent = events.getKey();
            int nombreEvent = events.getValue();
            for (int nbE = 0; nbE < nombreEvent; nbE++) {
                try {
                    Class classe = Class.forName(typeEvent);
                    this.plateau[randomCases[i]] = (IEvent) classe.getConstructor().newInstance();
                    i++;
                } catch (Exception exception) {
                    System.out.println("erreur");
                }
            }
        }
    }

    public void verifyEmptyCases() throws CaseVideException {
        for (int i = 0; i < plateau.length; i++) {
            if (this.plateau[i] == null) {
                String err = "il y a au moins une case vide dans le tableau : " + i;
                throw new CaseVideException(err);
            }
        }
    }

    public static int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    public int[] getTableauRandom(int entierMax) {
        int[] ar = new int[64];
        for (int i = 0; i < entierMax; i++) {
            ar[i] = i;
        }
        int b = ar.length;
        return rand(ar, b);
    }

    static int[] rand(int array[], int a) {
        Random rd = new Random();
        for (int i = a - 1; i > 0; i--) {
            int j = rd.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }


}
