package main.plateau;

import characters.Monster;
import equipements.Equipement;
import equipements.Potion;
import exceptions.PourcentagesPlateauException;
import main.cases.*;

import java.util.Map;

import static java.util.Map.entry;

public class FakePlateau extends Plateau {

    public FakePlateau(int nombreCasesPlateau) {
        super();
        int espace = 5 ;
        this.listeEvents = Map.ofEntries(
                entry("characters.Gobelin", espace*1),
                entry("equipements.Eclair", espace*2),
                entry("equipements.Epee", espace*3),
                entry("equipements.PotionSimple", espace*4),
                entry("characters.Sorcier", espace*5),
                entry("equipements.Invisibilite", espace*6),
                entry("equipements.Arc", espace*7),
                entry("equipements.GrandePotion", espace*8),
                entry("equipements.Massue", espace*9),
                entry("equipements.BouleDeFeu", espace*10),
                entry("characters.Dragon", espace*11)
                );
        this.plateau = new Case[nombreCasesPlateau];
        ajoutEvents();
        remplissageCasesEmpty();
        ImageDragon image = new ImageDragon();
        image.afficheDragon();
    }

    public void ajoutEvents() {
        //creation de chaque event pour chaque ligne du tableau listeEvents au numéro de case donnée :
        for (Map.Entry<String, Integer> events : listeEvents.entrySet()) {
            String typeEvent = events.getKey();
            int numeroCaseEvent = events.getValue();
            try {
                Class classe = Class.forName(typeEvent);
                if (typeEvent.equals("characters.Dragon") || typeEvent.equals("characters.Sorcier") || typeEvent.equals("characters.Gobelin")) {
                    Monster monster = (Monster) classe.getConstructor().newInstance();
                    this.plateau[numeroCaseEvent] = new CaseMonster(monster);
                } else if (typeEvent.equals("equipements.GrandePotion") || typeEvent.equals("equipements.PotionSimple")) {
                    Potion potion = (Potion) classe.getConstructor().newInstance();
                    this.plateau[numeroCaseEvent] = new CasePotion(potion);
                } else {
                    //cas des équipements hors potions :
                    Equipement equipement = (Equipement) classe.getConstructor().newInstance();
                    this.plateau[numeroCaseEvent] = new CaseEquipement(equipement);
                }
            } catch (Exception exception) {
            }
        }
    }

    public void remplissageCasesEmpty() {
        for (int i = 0; i < plateau.length; i++) {
            if (plateau[i] == null) {
                plateau[i] = new EmptyCase();
            }
        }
    }

}
