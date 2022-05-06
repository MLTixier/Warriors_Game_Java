package main;
import characters.*;
import equipements.*;
import java.util.Random;
import java.util.Scanner;

public class Game extends Main {

    public Scanner scanner ;
    private Hero hero;
    private Plateau plateau ;
    private int position = 0;

    public Game(Scanner scanner, Hero hero) {
        this.scanner = scanner;
        this.hero = hero ;
        this.plateau = new Plateau();
        lanceDeEtAvance();
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public void lanceDeEtAvance() {
        System.out.println("Que souhaitez-vous faire ?");
        String decisionDe = "";
        while (!(decisionDe.equals("d") || decisionDe.equals("a") || decisionDe.equals("q"))) {
            System.out.println("Pour jeter le dé, tapez d. Pour afficher les infos de votre héros, tapez a. Pour abandonner la partie, tapez q");
            decisionDe = scanner.nextLine();
        }
        if (decisionDe.equals("d")) {
            int valeurDe = getRandomInt(6) + 1;
            System.out.println("Vous avez jeté le dé et fait :" + valeurDe);
            this.goesForward(valeurDe);
            System.out.println("Vous avez avancé jusqu'à la case :" + position);
            resolutionConflit();
        } else if (decisionDe.equals("q")) {
            quitterJeu();
        } else if (decisionDe.equals("a")) {
            System.out.println(hero);
            lanceDeEtAvance();
        }
    }

    public void resolutionConflit() {
        if (position >= 64) {
            System.out.println("vous êtes arrivé au bout du parcours, vous avez gagné !");
            quitterJeu();
        } else {
            String contenu = plateau.afficheCasePlateau(position);
            if (contenu.equals("rien")) {
                System.out.println("la case est vide, vous pouvez continuer !");
                lanceDeEtAvance();
            } else if (contenu.equals("monstre")) {
                System.out.println("la case cachait un monstre, vous allez devoir combattre !");
                Monster monster = plateau.afficheMonstre(position);
                combattre(monster);
            } else if (contenu.equals("equipement")) {
                System.out.println("la case cachait un équipement.");
                Equipement equipement = plateau.afficheEquipement(position);
                regarderEquipement(equipement);
            }
        }
    }

    public void combattre(Monster monster) {
        System.out.println(monster + " VS " + hero);
        if (hero.calculeAttack() > monster.getLife()) {
            supprimerEvenement();
            System.out.println("vous avez tué le monstre !");
            lanceDeEtAvance();
        } else if (hero.getLife() > monster.getAttack()) {
            int pointsViePerdusHeros = monster.getAttack();
            int pointsViePerdusMonstre = hero.calculeAttack();
            monster.modifyLife(-pointsViePerdusMonstre);
            hero.modifyLife(-pointsViePerdusHeros);
            System.out.println("vous avez attaqué le monstre et il a fui, mais vous perdez "  + pointsViePerdusHeros + " points de vie.");
            System.out.println(hero);
        } else {
            System.out.println("Le monstre vous a tué, vous avez perdu la partie.");
            quitterJeu();
        }
    }

    public void regarderEquipement(Equipement equipement) {
        System.out.println(equipement);
        if (equipementIsCompatible(equipement)) {
            prendreEquipement(equipement);
            supprimerEvenement();
        } else {
            System.out.println("Cet équipement n'est pas adapté à votre personnage, vous ne pouvez pas le prendre.");
        }
        lanceDeEtAvance();
    }

    public void prendreEquipement(Equipement equipement) {
        if (equipement.getClass().getSimpleName().equals("Potion")) {
            hero.modifyLife(equipement.getLifeGain());
            supprimerEvenement();
            System.out.println("Grâce à la potion, votre niveau de vie a été augmenté de :" + equipement.getLifeGain());
        } else {
            //cas où l'équipement est une arme ou un sort
            hero.afficheTableauEquipements();
            System.out.println("Souhaitez-vous prendre cet équipement ?");
            String decisionEquipement = "";
            while (!(decisionEquipement.equals("1") || decisionEquipement.equals("2") || decisionEquipement.equals("3"))) {
                System.out.println("Pour mettre l'équipement en numéro 1, tapez 1");
                System.out.println("Pour mettre l'équipement en numéro 2, tapez 2");
                System.out.println("Pour ne pas prendre l'équipement, tapez 3");
                decisionEquipement = scanner.nextLine();
            }
            switch (decisionEquipement) {
                case "1":
                    hero.modifieTableauEquipements(equipement, 0);
                case "2":
                    hero.modifieTableauEquipements(equipement, 1);
                    break;
                case "3":
                    break;
            }
        }
    }

    public void supprimerEvenement() {
        plateau.supprimeEvenementDansTableau(position);
    }

    /*----------------------------- génération aléatoire de nombres ------------------------------*/

    public int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    /*----------------------------- compatibilité héros - équipement ------------------------------*/

    public boolean equipementIsCompatible(Equipement equipement) {
        boolean result = false;
        String typeEquipement = equipement.getClass().getSimpleName();
        String typeHero = hero.getClass().getSimpleName();
        switch (typeEquipement) {
            case "Arme":
                if (typeHero.equals("Warrior")) {
                    result = true;
                }
                break;
            case "Sort":
                if (typeHero.equals("Wizzard")) {
                    result = true;
                }
                break;
            case "Potion":
                result = true;
                break;
        }
        return result;
    }

    /*----------------------------- déplacement du héros ------------------------------*/

    public void goesForward(int numberOfCase){
        int newPosition = this.getPosition() + numberOfCase;
        this.setPosition(newPosition);
    }

    public void goesBack(int numberOfCase){
        int newPosition = this.getPosition() - numberOfCase;
        this.setPosition(newPosition);
        if (newPosition < 0){
            this.setPosition(0) ;
        }
    }

    public void quitterJeu() {
        System.out.println("Etes-vous sûr de vouloir quitter le jeu ?");
        String choixQuitter = "";
        while (!(choixQuitter.equals("o") || choixQuitter.equals("r"))) {
            System.out.println("Tapez o si oui, tapez r pour recommencer le jeu");
            choixQuitter = scanner.nextLine();
        }
        if (choixQuitter.equals("o")) {
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        } else if (choixQuitter.equals("r")) {
            Main main = new Main();
        }
    }
}
