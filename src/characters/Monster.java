package characters;

import main.EmptyCase;
import main.IEvent;

import java.util.Scanner;

public class Monster extends Character implements IEvent {

    public Monster(String myName, int myAttack, int myLife){
        super(myName, myAttack, myLife,20,20);
        //les monstres ont un maximum de 20 points de vie et d'attaque par défaut)
    }

    public Monster(){
    }

    public String toString() {
        return "monstre de type " + this.getName() + " : " + this.getAttack() + " points d'attaque et " + this.getLife() + " points de vie";
    }

    @Override
    public String readEvent() {
        return "la case cachait un monstre, vous allez devoir combattre !";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        if (this.getExists()) {
            this.scanner = scanner;
            System.out.println(this + " VS " + hero);
            if (hero.calculeAttack() > this.getLife()) {
                this.setExists(false);
                System.out.println("vous avez tué le monstre !");
            } else if (hero.getLife() > this.getAttack()) {
                int pointsViePerdusHeros = this.getAttack();
                int pointsViePerdusMonstre = hero.calculeAttack();
                this.modifyLife(-pointsViePerdusMonstre);
                hero.modifyLife(-pointsViePerdusHeros);
                System.out.println("vous avez attaqué le monstre et il a fui, mais vous perdez "  + pointsViePerdusHeros + " points de vie.");
                System.out.println(hero);
            } else {
                System.out.println("Le monstre vous a tué, vous avez perdu la partie.");
            }
        }else{
            System.out.println("Le monstre qui était sur cette case a déjà été tué !");
        }
    }
}
