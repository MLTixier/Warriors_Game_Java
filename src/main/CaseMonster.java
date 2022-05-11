package main;

import characters.Hero;
import characters.Monster;

import java.util.Scanner;

public class CaseMonster extends Case {

    Monster monster ;
    Scanner scanner ;

    public CaseMonster(Monster monster){
        super();
        this.monster = monster;
    }

    @Override
    public String readEvent() {
        return "la case cachait un monstre, vous allez devoir combattre !";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
        if (monster.getExists()) {
            this.scanner = scanner;
            System.out.println(monster + " VS " + hero);
            if (hero.calculeAttack() > monster.getLife()) {
                monster.setExists(false);
                System.out.println("vous avez tué le monstre !");
            } else if (hero.getLife() > monster.getAttack()) {
                int pointsViePerdusHeros = monster.getAttack();
                int pointsViePerdusMonstre = hero.calculeAttack();
                monster.modifyLife(-pointsViePerdusMonstre);
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
