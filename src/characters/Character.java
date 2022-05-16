package characters;

import java.util.Scanner;

public abstract class Character {
    public Scanner scanner;
    private String name;
    private int attack;
    private int life;
    private int maxAttack;
    private int maxLife;
    private boolean exists;

    public Character(String myName, int myAttack, int myLife, int myMaxAttack, int myMaxLife) {
        name = myName;
        maxAttack = myMaxAttack;
        maxLife = myMaxLife;
        exists = true;
        attack = (myAttack <= myMaxAttack) ? myAttack : myMaxAttack;
        life = (myLife <= myMaxLife) ? myLife : myMaxLife;
    }

    public Character() {
        }

        /*------------------------------------------- setters et getters ------------------------------------------*/


        public void setName (String myName){
            this.name = myName;
        }

        public boolean isAlive () {
            return this.exists;
        }

        public void setExists ( boolean exists){
            this.exists = exists;
        }

        public String getName () {
            return this.name;
        }

        public int getAttack () {
            return this.attack;
        }

        public int getMaxAttack () {
            return maxAttack;
        }

        public int getMaxLife () {
            return maxLife;
        }

        public int getLife () {
            return this.life;
        }
        public void setLife ( int lifePoints){
            if (lifePoints > maxLife) {
                lifePoints = maxLife;
            }
            this.life = lifePoints;
        }


        public void modifyLife ( int lifePoints){
            int newLife = this.life + lifePoints;
            this.life = newLife;
            if (newLife <= 0) {
                this.life = 0;
                this.setExists(false);
            } else if (newLife > this.maxLife) {
                this.life = maxLife;
            }
        }

        @Override
        public String toString () {
            return this.getClass().getSimpleName() + " " + this.name + " : " + this.attack + " points d'attaque et " + this.life + " points de vie";
        }
    }
