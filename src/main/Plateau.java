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

    private Case[] plateau = new Case[64];

    public Plateau() {
        ajoutEvents();
        System.out.println("Plateau de jeu créé !");
    }

    public Case[] getPlateau() {
        return plateau;
    }

    public Case getContenuPlateau(int indice) {
        Case contenuCase = (Case) this.plateau[indice];
        return contenuCase ;
    }

    public void ajoutEvents() {
        //creation de n events pour chaque ligne du tableau listeEvents :
        int[] randomCases = getTableauRandom(64);
        int i = 0;
        for (Map.Entry<String, Integer> events : listeEvents.entrySet()) {
            String typeEvent = events.getKey();
            int nombreEvent = events.getValue();
            for (int nbE = 0; nbE < nombreEvent; nbE++) {
                try {
                    Class classe = Class.forName(typeEvent);
                    if (typeEvent.equals("main.EmptyCase")){
                        this.plateau[randomCases[i]] = new EmptyCase();
                    } else if (typeEvent.equals("characters.Dragon")||typeEvent.equals("characters.Sorcier")||typeEvent.equals("characters.Gobelin")) {
                        Monster monster = (Monster) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CaseMonster(monster);
                    } else if (typeEvent.equals("equipements.GrandePotion")||typeEvent.equals("equipements.PotionSimple")){
                        Potion potion = (Potion) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CasePotion(potion);
                    } else {
                        //cas des équipements hors potions :
                        Equipement equipement = (Equipement) classe.getConstructor().newInstance();
                        this.plateau[randomCases[i]] = new CaseEquipement(equipement);
                    }
                    i++;
                } catch (Exception exception) {
                    System.out.println("erreur");
                }
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
