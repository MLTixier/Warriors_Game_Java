package main;

import characters.*;
import equipements.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class Plateau {

    private Map<String, Integer> listeEvents = Map.ofEntries(
            entry("characters.Dragon", 3),
            entry("characters.Sorcier", 20),
            entry("characters.Gobelin", 30),
            entry("equipements.Invisibilite", 3),
            entry("equipements.BouleDeFeu", 3),
            entry("equipements.Eclair", 5),
            entry("equipements.Arc", 3),
            entry("equipements.Epee", 5),
            entry("equipements.Massue", 5),
            entry("equipements.PotionSimple", 15),
            entry("equipements.GrandePotion", 5)
    );

    private IEvent[] plateau = new IEvent[64];

    public Plateau() {
        ajoutEvents();
        fillEmptyCases();
        System.out.println("Plateau de jeu créé !");
    }

    public void setContenuPlateau(IEvent event, int indice) {
        this.plateau[indice] = event;
    }

    public IEvent getContenuPlateau(int indice) {
        return this.plateau[indice] ;
    }

    public void ajoutEvents() {
         //creation de n events pour chaque ligne du tableau listeEvents :
        for (Map.Entry<String, Integer> events : listeEvents.entrySet()) {
            String typeEvent = events.getKey();
            int nombreEvent = events.getValue();
            for (int i = 0; i < nombreEvent; i++) {
                int numeroCaseAleatoire = getRandomInt(64);
                try{
                    Class classe = Class.forName(typeEvent);
                    this.plateau[numeroCaseAleatoire] = (IEvent) classe.getConstructor().newInstance();
                } catch(Exception exception) {
                    System.out.println("erreur");
                }
            }
        }
    }

    public void fillEmptyCases() {
        for (int i = 0; i < plateau.length; i++) {
            if (this.plateau[i] == null) {
                this.plateau[i] = new EmptyCase();
            }
        }
    }

    public static int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

}
