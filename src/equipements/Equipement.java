package equipements;

import characters.Gobelin;
import characters.Hero;
import main.EmptyCase;
import main.IEvent;

import java.util.Scanner;

public class Equipement implements IEvent {
    private String name;
    private int attackGain;
    private int lifeGain;
    private boolean exists;
    public Scanner scanner;

    public Equipement(String name, int attackGain, int lifeGain) {
        this.name = name;
        this.attackGain = attackGain;
        this.lifeGain = lifeGain;
        this.exists = true;
    }

    public Equipement() {
    }

    public int getAttackGain() {
        return attackGain;
    }

    public int getLifeGain() {
        return lifeGain;
    }

    public String getName() {
        return name;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String toString() {
        return "équipement de type " + this.getName() + " : " + this.getAttackGain() + " points d'attaque supplémentaires et : " + this.getLifeGain() + " points de vie supplémentaires";
    }

    @Override
    public String readEvent() {
        return "la case cachait un équipement.";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        this.scanner = scanner;
        if (this.exists) {
            regarderEquipement(hero);
        } else {
            System.out.println("L'équipement qui était sur cette case a déjà été pris.");
        }
    }

    public void regarderEquipement(Hero hero) {
        if (equipementIsCompatible(hero)) {
            System.out.println(this);
            prendreEquipement(hero);
        } else {
            System.out.println("L'équipement sur cette case n'est pas adapté à votre personnage, vous ne pouvez pas le prendre.");
        }
    }

    public void prendreEquipement(Hero hero) {
        hero.afficheTableauEquipements();
        System.out.println("Souhaitez-vous prendre cet équipement ?");
        String decisionEquipement = "";
        while (!(decisionEquipement.equals("1") || decisionEquipement.equals("2") || decisionEquipement.equals("3"))) {
            System.out.println("Pour mettre l'équipement en numéro 1, tapez 1");
            System.out.println("Pour mettre l'équipement en numéro 2, tapez 2");
            System.out.println("Pour ne pas prendre l'équipement, tapez 3");
            decisionEquipement = scanner.nextLine();
        }
        switch (decisionEquipement) {
            case "1":
                hero.modifieTableauEquipements(this, 0);
                this.exists = false;
                hero.afficheTableauEquipements();
                break;
            case "2":
                hero.modifieTableauEquipements(this, 1);
                this.exists = false;
                hero.afficheTableauEquipements();
                break;
            case "3":
                break;
        }
    }

    public boolean equipementIsCompatible(Hero hero) {
        boolean result = false;
        String typeEquipement = this.getClass().getSimpleName();
        String typeHero = hero.getClass().getSimpleName();
        System.out.println(typeEquipement);
        if ((typeEquipement.equals("Arc") || typeEquipement.equals("Epee") || typeEquipement.equals("Massue")) && (typeHero.equals("Warrior"))) {
            result = true;
        } else if ((typeEquipement.equals("Invisibilite") || typeEquipement.equals("BouleDeFeu") || typeEquipement.equals("Eclair")) && (typeHero.equals("Wizzard"))) {
            result = true;
        }
        return result;
    }


}
