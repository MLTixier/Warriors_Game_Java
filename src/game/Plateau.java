package game;

import characters.Monster;

import java.util.Random;

public class Plateau {

    Case[] plateau = new Case[64];

    public Plateau() {
        for (int i = 0; i < 64; i++) {
            this.plateau[i] = new Case("rien");
        }
        creationMonstres();
    }

    //création de 20 monstres, positionnés aléatoirement sur le plateau de jeu.
    public void creationMonstres() {
        int nombreMonstres = 50;
        //pour chaque monstre, attribution d'un type dragon orgue ou gobelin, et d'un numéro de case.
        for (int i = 0; i < nombreMonstres; i++) {
            int choixTypeMonstre = getRandomInt(3);
            int numeroCaseAleatoire = getRandomInt(64);
            Monster monster = new Monster();
            switch (choixTypeMonstre) {
                case 0:
                    monster = new Monster("dragon", 15, 4);
                    monster.setPosition(numeroCaseAleatoire);
                    this.plateau[numeroCaseAleatoire]=new Case(monster);
                    break;
                case 1:
                    monster = new Monster("orque", 10, 6);
                    monster.setPosition(numeroCaseAleatoire);
                    this.plateau[numeroCaseAleatoire]=new Case(monster);
                    break;
                case 2:
                    monster = new Monster("gobelin", 6, 1);
                    monster.setPosition(numeroCaseAleatoire);
                    this.plateau[numeroCaseAleatoire]=new Case(monster);
                    break;
            }
        }
        System.out.println("le jeu a créé et disposé aléatoirement " + nombreMonstres + " monstres. Arriverez-vous à les combattre ?");
    }

    public static int getRandomInt(int entierMax) {
        Random rand = new Random();
        return rand.nextInt(entierMax);
    }

    public String afficheCasePlateau(int numeroCase){
        Case contenuCase = this.plateau[numeroCase];
        return (contenuCase.afficheContenu());
    }

    public Monster afficheMonstre(int numeroCase){
        Case contenuCase = this.plateau[numeroCase];
        return (contenuCase.afficheMonstre());
    }

    public void supprimeMonstreDansTableau(int numeroCase){
        this.plateau[numeroCase].monster = null ;
        this.plateau[numeroCase].evenement = "rien" ;
    }

}
