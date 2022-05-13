package main;

import characters.*;
import equipements.*;
import exceptions.*;
import main.random.RandomDe;

import java.util.*;

import static java.util.Map.entry;

public class Plateau {

    private Map<String, Integer> listeEvents = Map.ofEntries(
            entry("characters.Dragon", 5),
            entry("characters.Sorcier", 15),
            entry("characters.Gobelin", 15),
            entry("equipements.Invisibilite", 3),
            entry("equipements.BouleDeFeu", 4),
            entry("equipements.Eclair", 1),
            entry("equipements.Arc", 3),
            entry("equipements.Epee", 4),
            entry("equipements.Massue", 1),
            entry("equipements.PotionSimple", 5),
            entry("equipements.GrandePotion", 3),
            entry("main.EmptyCase", 6)
    );

    private Case[] plateau = new Case[65];

    public Plateau() throws PourcentagesPlateauException {
        if (!pourcentagesPlateauOK()) {
            throw new PourcentagesPlateauException();
        }
        ajoutEvents();
        ImageDragon image = new ImageDragon();
        image.afficheDragon();
    }

    public Case[] getPlateau() {
        return plateau;
    }

    public Case getContenuPlateau(int indice) {
        Case contenuCase = (Case) this.plateau[indice];
        return contenuCase;
    }

    public void ajoutEvents() {
        //creation de n events pour chaque ligne du tableau listeEvents :
        int[] randomCases = getTableauRandom(65);
        this.plateau[randomCases[0]] = new EmptyCase();
        int i = 1;
        for (Map.Entry<String, Integer> events : listeEvents.entrySet()) {
            String typeEvent = events.getKey();
            int nombreEvent = events.getValue();
            for (int nbE = 0; nbE < nombreEvent; nbE++) {
                try {
                    Class classe = Class.forName(typeEvent);
                    if (typeEvent.equals("main.EmptyCase")) {
                        this.plateau[randomCases[i]] = new EmptyCase();
                    } else if (typeEvent.equals("characters.Dragon") || typeEvent.equals("characters.Sorcier") || typeEvent.equals("characters.Gobelin")) {
                        Monster monster = (Monster) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CaseMonster(monster);
                    } else if (typeEvent.equals("equipements.GrandePotion") || typeEvent.equals("equipements.PotionSimple")) {
                        Potion potion = (Potion) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CasePotion(potion);
                    } else {
                        //cas des équipements hors potions :
                        Equipement equipement = (Equipement) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CaseEquipement(equipement);
                    }
                    i++;
                } catch (Exception exception) {
                }
            }
        }
    }

    public static int getRandomInt(int entierMax) {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(entierMax);
    }

    public int[] getTableauRandom(int entierMax) {
        int[] ar = new int[entierMax];
        for (int i = 0; i < entierMax; i++) {
            ar[i] = i;
        }
        int b = ar.length;
        return rand(ar, b);
    }

    static int[] rand(int array[], int a) {
        java.util.Random rand = new java.util.Random();
        for (int i = a - 1; i > 1; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        for (int i = 0; i < a; i++) {
            if (array[i] == 0) {
                array[i] = array[0];
                array[0] = 0;
            }
        }
        return array;
    }

    public boolean pourcentagesPlateauOK() {
        int somme = 0;
        for (Integer value : listeEvents.values()) {
            somme += value;
        }
        return (somme == 65);
    }

}
