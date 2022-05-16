package main.cases;

import characters.Hero;
import equipements.Potion;

import java.util.Scanner;

public class CasePotion extends Case {

    Potion potion;
    Scanner scanner ;

    public CasePotion(Potion potion) {
        super();
        this.potion = potion;
        this.setStringContent("potion");
    }

    @Override
    public String readEvent() {
        if (potion.getExists()) {
            return "Il y a  une " + potion.getName() + " de vie sur cette case, d'une valeur de " + potion.getLifeGain();
        } else {
            return "case vide";
        }
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        this.scanner = scanner;
        if (potion.getExists()) {
            hero.modifyLife(potion.getLifeGain());
            potion.setExists(false);
            System.out.println("Grâce à la potion, votre niveau de vie est maintenant de :" + hero.getLife());
            System.out.println(hero);
        } else {
            System.out.println("L'équipement qui était sur cette case a déjà été pris.");
        }
    }

}


