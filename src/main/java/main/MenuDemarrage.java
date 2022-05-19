package main;

import characters.*;
import exceptions.*;
import main.BDD.DB;
import main.BDD.FakeDB;
import main.BDD.HeroDAO;
import main.BDD.RealDB;

import java.util.List;
import java.util.Scanner;

public class MenuDemarrage {

    private Scanner scanner;
    private Hero hero;
    private DB db;
    private HeroDAO heroDAO ;
    private String dbExists ;
    private String diceChoice;
    private String gameDifficulty;

    /**
     * constructeur pour la classe MenuDemarrage : créée un scanner et créée un héros.
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */

    public MenuDemarrage(String dbExists, String diceChoice, String gameDifficulty) throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        if (dbExists.equals("true")){
            this.db = new RealDB();
        } else {
            this.db = new FakeDB();
        }
        this.heroDAO = new HeroDAO(db.getConnexion());
        this.dbExists = dbExists;
        this.diceChoice = diceChoice;
        this.gameDifficulty=gameDifficulty;
        this.scanner = new Scanner(System.in);
        questionsMenu1();
    }

    /**
     * méthode pour créer un 1er menu de choix utilisateur : créer, afficher ou sélectionner un personnage.
     * @throws PourcentagesPlateauException
     * @throws FinJeuException
     * @throws SortieJeuException
     */
    public void questionsMenu1() throws PourcentagesPlateauException, FinJeuException, SortieJeuException {
        System.out.println("Que souhaitez-vous faire ?");
        String menu1Choice = "";
        while (!(menu1Choice.equals("c") || menu1Choice.equals("a"))) {
            System.out.println("Pour créer votre personnage, tapez c");
            if (dbExists.equals("true")) {
                System.out.println("Pour afficher et choisir un des personnages pré-enregistrés, tapez a");
            }
            menu1Choice = scanner.nextLine();
        }
        if (menu1Choice.equals("c")) {
            creerPersonnage();
        }
        if (menu1Choice.equals("a")) {
            if (dbExists.equals("true")){
                selectionnerHerosDB();
            } else {
                System.out.println("La réponse donnée est invalide.");
                questionsMenu1();
            }
        }
    }

    /**
     * méthode pour afficher les héros pré-enregistrés issus de la table heroes de la BDD.
     */
    public void afficherHerosDB() {
        System.out.println("les héros pré-enregistrés sont les suivants : ");
        System.out.println("");
        List<Hero> listeHeroes = heroDAO.getAll();
        int nbHeroes = listeHeroes.size();
        for (int i = 0; i < nbHeroes; i++) {
            System.out.print(i);
            System.out.print(" : ");
            System.out.println(listeHeroes.get(i));
        }
        System.out.println("");
    }

    /**
     * méthode qui affiche les héros pré-enregistrés de la BDD et demande à l'utilisateur d'ne sélectionner un, qui devient alors le héros du jeu.
     * @throws PourcentagesPlateauException
     * @throws FinJeuException
     * @throws SortieJeuException
     */
    public void selectionnerHerosDB() throws PourcentagesPlateauException, FinJeuException, SortieJeuException {
        afficherHerosDB();
        int nbHeroes = heroDAO.getAll().size();
        System.out.println("Quel héros souhaitez-vous prendre ?");
        String choice = "";
        while (!(choiceInfNbHeroes(choice, nbHeroes) || choice.equals("q"))) {
            System.out.println("Entrez le numéro du héros souhaité ou tapez q pour revenir au menu principal");
            choice = scanner.nextLine();
        }
        if (choice.equals("q")) {
            questionsMenu1();
        } else {
            this.hero = heroDAO.get(Integer.valueOf(choice));
            questionsMenu2();
        }
    }

    /**
     * méthode utilisée pour vérifier si la sélection effectuée par l'utilisateur dans la méthode selectionnerHerosBD correspond bien à un numéro de héros de la BDD.
     * @param choice
     * @param nbHeroes
     * @return
     */
    public boolean choiceInfNbHeroes(String choice, int nbHeroes) {
        boolean rst = false;
        if (choice.equals("")){
        //verif si choice n'est pas numerique :
        }else if (!choice.matches("[+-]?\\d*(\\.\\d+)?")) {
        } else {
            for (int i = 0; i < nbHeroes; i++) {
                if (Integer.valueOf(choice)==i) {
                    rst = true;
                }
            }
        }
        return rst;
    }

    /**
     * méthode pour instancier un héros, selon le type souhaité et le nom souhéité.
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void creerPersonnage() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
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
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void questionsMenu2() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        System.out.println("Que souhaitez-vous faire ?");
        String menu2Choice = "";
        while (!(menu2Choice.equals("a") || menu2Choice.equals("m") || menu2Choice.equals("j"))) {
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
     * méthode pour afficher les infos du personnage
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void afficherPersonnage() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        System.out.println(hero);
        questionsMenu2();
    }

    /**
     * Méthode pour modifier le nom du personnage
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void modifierPersonnage() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
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
     *
     * @throws SortieJeuException
     * @throws PourcentagesPlateauException
     */
    public void jouer() throws SortieJeuException, PourcentagesPlateauException, FinJeuException {
        Game game = new Game(scanner, hero, gameDifficulty, 65, db, this, diceChoice);
    }


    /**
     * méthode pour quitter le jeu
     */
    public void quitter() throws PourcentagesPlateauException, SortieJeuException, FinJeuException {
        System.out.println("Voulez-vous quitter le jeu définitivement ?");
        String choixQuitter = "";
        while (!(choixQuitter.equals("o") || choixQuitter.equals("r"))) {
            System.out.println("Tapez o si oui, tapez r pour recommencer le jeu");
            choixQuitter = scanner.nextLine();
        }
        if (choixQuitter.equals("o")) {
            menuEnregistrerPartie();
            throw new FinJeuException();
        } else if (choixQuitter.equals("r")) {
            MenuDemarrage menuDemarrage = new MenuDemarrage(dbExists, diceChoice, gameDifficulty);
        }
    }

    /**
     * méthode demandant à l'utilisateur de sauvegarder des éléments d'une partie avant de quitter.
     */
    private void menuEnregistrerPartie() {
        System.out.println("Voulez-vous enregistrer votre héros avant de quitter le jeu ?");
        String choix = "";
        while (!(choix.equals("o") || choix.equals("n"))) {
            System.out.println("Tapez o si oui, tapez n pour quitter sans enregistrer");
            choix = scanner.nextLine();
        }
        if (choix.equals("o")) {
            if (dbExists.equals("true")){
                heroDAO.post(hero);
                System.out.println("Votre personnage a bien été enregistré.");
            } else {
                System.out.println("La base de données n'est pas accesible.");
            }
        }
    }


}
