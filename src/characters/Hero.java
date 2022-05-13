package characters;

import equipements.*;
import exceptions.SortieJeuException;

import java.util.Arrays;

public class Hero extends Character {

    private Equipement[] listeEquipements = {null, null};
    private int position = 0;
    private int previousPosition = 0;
    private boolean fuyard = false;

    public Hero(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
    }

    public void setFuyard(boolean fuyard) {
        this.fuyard = fuyard;
    }

    public boolean isFuyard() {
        return fuyard;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public void setPreviousPosition(int newPosition) {
        previousPosition = newPosition;
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    /*-------------------------------------------- avancer et reculer -------------------------------------------*/

    public void goesForward(int numberOfCase) throws SortieJeuException {
        this.previousPosition = this.getPosition();
        int newPosition = this.getPosition() + numberOfCase;
        if (newPosition > 64) {
            throw new SortieJeuException("Sortie plateau !");
        }
        this.setPosition(newPosition);
    }

    public void goesBack(int numberOfCase) {
        this.previousPosition = this.getPosition();
        int newPosition = this.getPosition() - numberOfCase;
        if (newPosition < 0) {
            newPosition = 0;
        }
        this.setPosition(newPosition);
    }

    public void modifieTableauEquipements(Equipement equipement, int position) {
        listeEquipements[position] = equipement;
    }

    public void afficheTableauEquipements() {
        System.out.println("Vous avez les équipements suivants :");
        System.out.println("Equipement 1 : " + listeEquipements[0]);
        System.out.println("Equipement 2 : " + listeEquipements[1]);
    }

    public int calculeAttack() {
        int valeur1 = this.getAttack();
        int valeur2 = 0;
        if (listeEquipements[0] != null) {
            valeur2 = listeEquipements[0].getAttackGain();
        }
        int valeur3 = 0;
        if (listeEquipements[1] != null) {
            valeur3 = listeEquipements[1].getAttackGain();
        }
        if ((valeur1 + valeur2 + valeur3) <= getMaxAttack()) {
            return (valeur1 + valeur2 + valeur3);
        } else {
            return getMaxAttack();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getName() + " : " + this.calculeAttack() + " (" + this.getMaxAttack() + " max) points d'attaque et " + this.getLife() + " (" + this.getMaxLife() + " max) points de vie";
    }


}
