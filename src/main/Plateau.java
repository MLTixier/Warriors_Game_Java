package main;

import characters.*;
import equipements.*;

import java.util.Random;

public class Plateau {

    private Case[] plateau = new Case[64];
    private Monster[] listeMonster = {
            new Monster("dragon", 4, 15),
            new Monster("sorcier", 2, 9),
            new Monster("gobelin", 1, 6),
    };
    private Equipement[] listeEquipements = {
            new Sort("invisibilité", 8),
            new Arme("arc", 6),
            new Sort("boule de feu", 7),
            new Sort("éclair", 2),
            new Arme("épée", 5),
            new Arme("massue", 3),
            new Potion("potion de vie", 2),
            new Potion("Grande potion de vie", 7),
    };

    public Plateau() {
        for (int i = 0; i < 64; i++) {
            this.plateau[i] = new Case("rien");
        }
        //implantation e monstres et d'équioement, avec des paramètres précisant la difficulté du parcours :
        creationMonstres(20, 30, 50);
        creationEquipements(20);
    }

    //création de monstres, positionnés aléatoirement sur le plateau de jeu.
    public void creationMonstres(int nombre, int pourcentageSorciers, int pourcentageGobelins) {
        //pour chaque monstre, attribution d'un type dragon orgue ou gobelin, et d'un numéro de case.
        for (int i = 0; i < nombre; i++) {
            int choixTypeMonstre = getRandomInt(100);
            int numeroCaseAleatoire = getRandomInt(64);
            if (choixTypeMonstre < pourcentageGobelins) {
                this.plateau[numeroCaseAleatoire] = new Case(listeMonster[2]);
            } else if (choixTypeMonstre < pourcentageGobelins + pourcentageSorciers) {
                this.plateau[numeroCaseAleatoire] = new Case(listeMonster[1]);
            } else {
                this.plateau[numeroCaseAleatoire] = new Case(listeMonster[0]);
            }
        }
        System.out.println("le jeu a créé et disposé aléatoirement " + nombre + " monstres. Arriverez-vous à les combattre ?");
    }

    //création d'équipements, positionnés aléatoirement sur le plateau de jeu.
    public void creationEquipements(int nombre) {
        //pour chaque équipement, attribution d'un type, et d'un numéro de case.
        for (int i = 0; i < nombre; i++) {
            int choixTypeEquipement = getRandomInt(8);
            int numeroCaseAleatoire = getRandomInt(64);
            this.plateau[numeroCaseAleatoire] = new Case(listeEquipements[choixTypeEquipement]);
        }
        System.out.println("le jeu a créé et disposé aléatoirement " + nombre + " équipements. Arriverez-vous à les récupérer pour gagner des points d'attaque ou de vie ?");
    }

    public static int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    public String afficheCasePlateau(int numeroCase) {
        Case contenuCase = this.plateau[numeroCase];
        return (contenuCase.afficheContenu());
    }

    public Monster afficheMonstre(int numeroCase) {
        Case contenuCase = this.plateau[numeroCase];
        return (contenuCase.afficheMonstre());
    }

    public Equipement afficheEquipement(int numeroCase) {
        Case contenuCase = this.plateau[numeroCase];
        return (contenuCase.afficheEquipement());
    }

    public void supprimeEvenementDansTableau(int numeroCase) {
        this.plateau[numeroCase] = new Case("rien");
    }

}
