package main;

import characters.Hero;
import equipements.Equipement;

import java.util.Scanner;

public class CaseEquipement extends Case {

    Equipement equipement;
    Scanner scanner;

    public CaseEquipement(Equipement equipement) {
        super();
        this.equipement = equipement;
    }

    @Override
    public String readEvent() {
        return "la case cachait un équipement.";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        this.scanner = scanner;
        if (equipement.getExists()) {
            regarderEquipement(hero);
        } else {
            System.out.println("L'équipement qui était sur cette case a déjà été pris.");
        }
    }

    public void regarderEquipement(Hero hero) {
        if (equipementIsCompatible(hero)) {
            System.out.println(equipement);
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
                hero.modifieTableauEquipements(equipement, 0);
                equipement.setExists(false);
                hero.afficheTableauEquipements();
                break;
            case "2":
                hero.modifieTableauEquipements(equipement, 1);
                equipement.setExists(false);
                hero.afficheTableauEquipements();
                break;
            case "3":
                break;
        }
    }

    public boolean equipementIsCompatible(Hero hero) {
        boolean result = false;
        String typeEquipement = equipement.getClass().getSimpleName();
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
