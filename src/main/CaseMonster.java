package main;

import characters.Hero;
import characters.Monster;
import exceptions.SortieJeuException;
import main.random.De6Faces;

import java.util.Random;
import java.util.Scanner;

public class CaseMonster extends Case {

    Monster monster;
    Scanner scanner;

    public CaseMonster(Monster monster) {
        super();
        this.monster = monster;
        this.setStringContent("monstre");
    }

    @Override
    public String readEvent() {
        if (monster.isAlive()) {
            return "la case cachait un monstre, vous allez devoir combattre !";
        } else {
            return "case vide";
        }
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) throws SortieJeuException {
        if (monster.isAlive()) {
            this.scanner = scanner;
            if (monster.isAlive()) {
                System.out.println(monster + " VS " + hero);
                System.out.println("Que souhaitez-vous faire ?");
                String decision = "";
                while (!(decision.equals("a") || decision.equals("f"))) {
                    System.out.println("Pour attaquer, tapez a, pour fuir, tapez f");
                    decision = scanner.nextLine();
                }
                if (decision.equals("a")) {
                    engagementCombat(hero);
                } else {
                    fuite(hero);
                    hero.setFuyard(true);
                }
            }
        } else {
            System.out.println("Le monstre qui était sur cette case a déjà été tué !");
        }
    }

    public void engagementCombat(Hero hero) throws SortieJeuException {
        if (hero.calculeAttack() >= monster.getLife()) {
            monster.setExists(false);
            System.out.println("vous avez tué le monstre !");
        } else if (hero.getLife() > monster.getAttack()) {
            int pointsViePerdusHeros = monster.getAttack();
            int pointsViePerdusMonstre = hero.calculeAttack();
            monster.modifyLife(-pointsViePerdusMonstre);
            hero.modifyLife(-pointsViePerdusHeros);
            System.out.println("vous avez attaqué le monstre et il a riposté, vous perdez " + pointsViePerdusHeros + " points de vie.");
            actionEvent(hero, scanner);
        } else {
            System.out.println("Le monstre vous a tué, vous avez perdu la partie.");
            hero.setExists(false);
        }
    }

    public void fuite(Hero hero) throws SortieJeuException {
        De6Faces de = new De6Faces();
        int nbCases = de.valeur();
        hero.goesBack(nbCases);
        System.out.println("vous avez reculé de " + nbCases + " cases.");
    }

    public int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

}
