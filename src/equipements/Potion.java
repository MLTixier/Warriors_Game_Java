package equipements;

import characters.Hero;
import main.*;

import java.util.Scanner;

public class Potion extends Equipement {

    public Potion(String name, int gainLife) {
        super(name, 0, gainLife);
    }

    public Potion() {
        super();
    }

    @Override
    public String readEvent() {
        if (this.getExists()) {
            return "Il y a  une " + this.getName() + " de vie sur cette case, d'une valeur de " + this.getLifeGain();
        } else {
            return "case vide";
        }
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        this.scanner = scanner;
        if (this.getExists()) {
            hero.modifyLife(this.getLifeGain());
            this.setExists(false);
            System.out.println("Grâce à la potion, votre niveau de vie est maintenant de :" +hero.getLife());
            System.out.println(hero);
        } else {
            System.out.println("L'équipement qui était sur cette case a déjà été pris.");
        }
    }
}
