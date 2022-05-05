package game;

import characters.*;

import java.util.Random;
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
            System.out.println("Tapez c pour créer un personnage, tapez q pour quitter le jeu");
            menuChoice = scanner.nextLine();
        }
        if (menuChoice.equals("c")) {
            this.creerPersonnage(scanner);
        } else if (menuChoice.equals("q")) {
            this.quitterJeu(scanner);
        }
    }

    public void quitterJeu(Scanner scanner) {
        System.out.println("Etes-vous sûr de vouloir quitter le jeu ?");
        String choixQuitter = "";
        while (!choixQuitterIsValid(choixQuitter)) {
            System.out.println("Tapez o si oui, tapez r pour recommencer le jeu");
            choixQuitter = scanner.nextLine();
        }
        if (choixQuitter.equals("o")) {
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        } else if (choixQuitter.equals("r")) {
            main(null);
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
            System.out.println("Le nom de votre héros a bien été changé.");
        }
        questionsMenu2(hero, scanner);
    }

    /*------------------------------------------- jeu -------------------------------------------*/

    public void jouer(Hero hero, Scanner scanner) {
        Plateau plateau = new Plateau();
        lanceDeEtAvance(hero, scanner, plateau);
    }

    public void lanceDeEtAvance(Hero hero, Scanner scanner, Plateau plateau) {
        System.out.println("Que souhaitez-vous faire ?");
        String decisionDe = "";
        while (!decisionDeIsValid(decisionDe)) {
            System.out.println("Pour jeter le dé, tapez d. Pour abandonner la partie, tapez q");
            decisionDe = scanner.nextLine();
        }
        if (decisionDe.equals("d")) {
            int valeurDe = getRandomInt(6) + 1;
            System.out.println("Vous avez jeté le dé et fait :" + valeurDe);
            hero.goesForward(valeurDe);
            System.out.println("Vous avez avancé jusqu'à la case :" + hero.getPosition());
            resolutionConflit(hero, scanner, plateau);
        } else if (decisionDe.equals("q")) {
            quitterJeu(scanner);
        }
    }

    public void resolutionConflit(Hero hero, Scanner scanner, Plateau plateau) {
        int position = hero.getPosition();
        if (position >= 64) {
            System.out.println("vous êtes arrivés au bout du parcours, vous avez gagné !");
            quitterJeu(scanner);
        } else {
            String contenu = plateau.afficheCasePlateau(position);
            if (contenu.equals("rien")) {
                System.out.println("la case est vide, vous pouvez continuer !");
                lanceDeEtAvance(hero, scanner, plateau);
            } else if (contenu.equals("monstre")) {
                System.out.println("la case cachait un monstre, vous allez devoir combattre !");
                Monster monster = plateau.afficheMonstre(position);
                combattre(hero, scanner, plateau, monster);
            }
        }
    }

    public void combattre(Hero hero, Scanner scanner, Plateau plateau, Monster monster) {
        System.out.println(monster);
        System.out.println(hero);
        if (hero.getAttack() > monster.getLife()) {
            System.out.println("vous avez tué le monstre !");
            supprimer(hero, plateau);
            lanceDeEtAvance(hero, scanner, plateau);
        } else if (hero.getLife() > monster.getAttack()) {
            int pointsViePerdusHeros = monster.getAttack() - hero.getLife();
            int pointsViePerdusMonstre = hero.getAttack() - monster.getLife();
            monster.modifyLife(-pointsViePerdusMonstre);
            hero.modifyLife(-pointsViePerdusHeros);
            System.out.println("vous avez attaqué le monstre et il a fui, mais vous perdez" + pointsViePerdusHeros + " points de vie.");
            System.out.println(hero);
        } else {
            System.out.println("Le monstre vous a tué, vous avez perdu la partie.");
            quitterJeu(scanner);
        }
    }

    public void supprimer(Hero hero, Plateau plateau) {
        int position = hero.getPosition();
        plateau.supprimeMonstreDansTableau(position);
    }

    /*----------------------------- génération aléatoire de nombres ------------------------------*/

    public static int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    /*----------------------------------- Questions menu 2 ----------------------------------*/

    public void questionsMenu2(Hero hero, Scanner scanner) {
        System.out.println("Que souhaitez-vous faire ?");
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
            jouer(hero, scanner);
        }
        if (menu2Choice.equals("q")) {
            quitterJeu(scanner);
        }
    }

    /*----------------------------------- Validations des entrées utilisateur ----------------------------------*/

    public boolean menuChoiceIsValid(String menuChoice) {
        boolean isValid = false;
        if (menuChoice.equals("c") || menuChoice.equals("q")) {
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

    public boolean decisionDeIsValid(String decisionDe) {
        boolean isValid = false;
        if (decisionDe.equals("q") || decisionDe.equals("d")) {
            isValid = true;
        }
        return isValid;
    }

    public boolean choixQuitterIsValid(String choix){
        boolean isValid = false;
        if (choix.equals("o") || choix.equals("r")) {
            isValid = true;
        }
        return isValid;
    }

}
