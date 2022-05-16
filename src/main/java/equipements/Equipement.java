package equipements;

import java.util.Scanner;

public class Equipement {
    private String name;
    private int attackGain;
    private int lifeGain;
    private boolean exists;
    public Scanner scanner;

    public Equipement(String name, int attackGain, int lifeGain) {
        this.name = name;
        this.attackGain = attackGain;
        this.lifeGain = lifeGain;
        this.exists = true;
    }

    public Equipement() {
    }

    public int getAttackGain() {
        return attackGain;
    }

    public int getLifeGain() {
        return lifeGain;
    }

    public String getName() {
        return name;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String toString() {
        return "équipement de type " + this.getName() + " : " + this.getAttackGain() + " points d'attaque supplémentaires et : " + this.getLifeGain() + " points de vie supplémentaires";
    }


}
