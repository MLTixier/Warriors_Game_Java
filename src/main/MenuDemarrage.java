package main;
import characters.Hero;
import characters.Warrior;
import characters.Wizzard;
import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;

import java.util.Scanner;

public class MenuDemarrage {

    public Scanner scanner ;
    public Hero hero ;

    /**
     * constructeur pour la classe MenuDemarrage : créée un scanner et créée un héros.
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public MenuDemarrage() throws SortieJeuException, PourcentagesPlateauException {
        this.scanner = new Scanner(System.in);
        this.creerPersonnage();
    }

    /**
     * méthode pour instancier un héros, selon le type souhaité et le nom souhéité.
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void creerPersonnage() throws SortieJeuException, PourcentagesPlateauException {
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

    /**
     * méthode pour afficher les choix utilisateur après la création du héros (afficher, modifier ou jouer)
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void questionsMenu2() throws SortieJeuException, PourcentagesPlateauException {
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

    /**
     * méthide pour afficher les infos du personnage
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void afficherPersonnage() throws SortieJeuException, PourcentagesPlateauException {
        System.out.println(hero);
        questionsMenu2();
    }

    /**
     * Méthode pour modifier le nom du personnage
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void modifierPersonnage() throws SortieJeuException, PourcentagesPlateauException {
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

    /**
     * Méthode pour lancer le jeu.
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void jouer() throws SortieJeuException, PourcentagesPlateauException {
        Game game = new Game(scanner, hero);
    }

}
