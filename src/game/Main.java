package game;

import characters.Character;
import characters.Hero;
import characters.Monster;
import characters.Wizzard;
import characters.Warrior;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu des Warriors !");
        Main main = new Main();
        main.premierMenu();
    }

    public void premierMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous créer votre personnage ?");
        String menuChoice = "";
        while (!menuChoiceIsValid(menuChoice)) {
            System.out.println("Tapez o pour créer un personnage, tapez q pour quitter le jeu");
            menuChoice = scanner.nextLine();
        }
        if (menuChoice.equals("o")) {
            this.creerPersonnage(scanner);
        } else if (menuChoice.equals("q")) {
            this.quitterJeu();
        }
    }

    public void creerPersonnage(Scanner scanner) {

        /*--------------------------------- choix du type de héros ---------------------------------------*/

        System.out.println("Quel personnage voulez-vous créer ?");
        String heroType = "";
        while (!heroTypeIsValid(heroType)) {
            System.out.println(heroType + "Tapez g pour guerrier ou m pour magicien");
            heroType = scanner.nextLine();
        }

        /*--------------------------------- choix du nom du héros ---------------------------------------*/

        System.out.println("Entrez le nom de votre personnage");
        String heroName = scanner.next();

        /*--------------------------------- création du héros ---------------------------------------*/

        Hero hero = null;
        if (heroType.equals("g")) {
            hero = new Warrior(heroName);
            System.out.println("Félicitations ! vous avez créé votre guerrier !");
        } else if (heroType.equals("m")) {
            hero = new Wizzard(heroName);
            System.out.println("Félicitations ! vous avez créé votre magicien !");
        }

        questionsMenu2(hero, scanner);
    }

    public void afficherPersonnage(Hero hero, Scanner scanner) {
        System.out.println(hero);
        questionsMenu2(hero, scanner);
    }

    public void modifierPersonnage(Hero hero, Scanner scanner) {
        System.out.println("Que voulez-vous modifier ?");
        String modifSouhaitee = "";
        while (!modifIsValid(modifSouhaitee)) {
            System.out.println("Pour modifier le nom, tapez n. Pour abandonner la modification, tapez q");
            modifSouhaitee = scanner.nextLine();
        }
        if (modifSouhaitee.equals("n")) {
            System.out.println("Rentrez le nouveau nom de votre héros.");
            String newName = scanner.nextLine();
            hero.setName(newName);
            System.out.println("Le type de héros a bien été changé.");
        }
        questionsMenu2(hero, scanner);
    }

    /*----------------------------------- Jouer ou quitter ----------------------------------*/

    public void questionsMenu2(Hero hero, Scanner scanner) {
        System.out.println("Que souhaitez-vous faire maintenant?");
        String menu2Choice = "";
        while (!menu2ChoiceIsValid(menu2Choice)) {
            System.out.println("Pour afficher les infos de votre personnage, tapez a");
            System.out.println("Pour modifier les infos de votre personnage, tapez m");
            System.out.println("Pour commencer à jouer, tapez j");
            System.out.println("Pour quitter le jeu, tapez q");
            menu2Choice = scanner.nextLine();
        }
        if (menu2Choice.equals("a")) {
            afficherPersonnage(hero, scanner);
        }
        if (menu2Choice.equals("m")) {
            modifierPersonnage(hero, scanner);
        }
        if (menu2Choice.equals("j")) {
            jouer();
        }
        if (menu2Choice.equals("q")) {
            quitterJeu();
        }
    }

    public void jouer() {

    }

    public void quitterJeu() {
        System.out.println("Au revoir !");
    }

    /*----------------------------------- Validations des entrées utilisateur ----------------------------------*/

    public boolean menuChoiceIsValid(String menuChoice) {
        boolean isValid = false;
        if (menuChoice.equals("o") || menuChoice.equals("q")) {
            isValid = true;
        }
        return isValid;
    }

    public boolean heroTypeIsValid(String heroType) {
        boolean isValid = false;
        if (heroType.equals("g") || heroType.equals("m")) {
            isValid = true;
        }
        return isValid;
    }

    public boolean menu2ChoiceIsValid(String menu2Choice) {
        boolean isValid = false;
        if (menu2Choice.equals("a") || menu2Choice.equals("m") || menu2Choice.equals("j") || menu2Choice.equals("q")) {
            isValid = true;
        }
        return isValid;
    }

    public boolean modifIsValid(String modif) {
        boolean isValid = false;
        if (modif.equals("q") || modif.equals("n")) {
            isValid = true;
        }
        return isValid;
    }

}
