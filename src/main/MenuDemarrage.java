package main;
import characters.Hero;
import characters.Warrior;
import characters.Wizzard;
import java.util.Scanner;

public class MenuDemarrage extends Main {

    public Scanner scanner = new Scanner(System.in);
    public Hero hero ;

    public MenuDemarrage() {
        this.creerPersonnage();
    }

    public void creerPersonnage() {
        System.out.println("Quel personnage voulez-vous créer ?");
        String heroType = "";
        while (!(heroType.equals("g") || heroType.equals("m"))) {
            System.out.println(heroType + "Tapez g pour guerrier ou m pour magicien");
            heroType = scanner.nextLine();
        }
        System.out.println("Entrez le nom de votre personnage");
        String heroName = scanner.nextLine();
        if (heroType.equals("g")) {
            hero = new Warrior(heroName);
            System.out.println("Félicitations ! vous avez créé votre guerrier !");
        } else if (heroType.equals("m")) {
            hero = new Wizzard(heroName);
            System.out.println("Félicitations ! vous avez créé votre magicien !");
        }
        questionsMenu2();
    }

    public void questionsMenu2() {
        System.out.println("Que souhaitez-vous faire ?");
        String menu2Choice = "";
        while (!(menu2Choice.equals("a") || menu2Choice.equals("m") || menu2Choice.equals("j")) ) {
            System.out.println("Pour afficher les infos de votre personnage, tapez a");
            System.out.println("Pour modifier les infos de votre personnage, tapez m");
            System.out.println("Pour commencer à jouer, tapez j");
            menu2Choice = scanner.nextLine();
        }
        if (menu2Choice.equals("a")) {
            afficherPersonnage();
        }
        if (menu2Choice.equals("m")) {
            modifierPersonnage();
        }
        if (menu2Choice.equals("j")) {
            jouer();
        }
    }

    public void afficherPersonnage() {
        System.out.println(hero);
        questionsMenu2();
    }

    public void modifierPersonnage() {
        System.out.println("Que voulez-vous modifier ?");
        String modifSouhaitee = "";
        while (!(modifSouhaitee.equals("q") || modifSouhaitee.equals("n"))) {
            System.out.println("Pour modifier le nom, tapez n. Pour abandonner la modification, tapez q");
            modifSouhaitee = scanner.nextLine();
        }
        if (modifSouhaitee.equals("n")) {
            System.out.println("Rentrez le nouveau nom de votre héros.");
            String newName = scanner.nextLine();
            hero.setName(newName);
            System.out.println("Le nom de votre héros a bien été changé.");
        }
        questionsMenu2();
    }

    public void jouer() {
        Game game = new Game(scanner, hero);
    }

}
