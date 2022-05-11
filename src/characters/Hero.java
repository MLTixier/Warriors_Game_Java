package characters;
import equipements.*;

import java.util.Arrays;

public class Hero extends Character {

    private Equipement[] listeEquipements = {null,null} ;

    public Hero(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
    }

    /*-------------------------------------------- avancer et reculer -------------------------------------------*/

    public void modifieTableauEquipements(Equipement equipement, int position){
        listeEquipements[position]=equipement;
    }

    public void afficheTableauEquipements(){
        System.out.println("Vous avez les équipements suivants :");
        System.out.println("Equipement 1 : "+ listeEquipements[0]);
        System.out.println("Equipement 2 : "+ listeEquipements[1]);
    }

    public int calculeAttack(){
        int valeur1 = this.getAttack();
        int valeur2 = 0;
        if (listeEquipements[0]!=null) {
            valeur2 = listeEquipements[0].getAttackGain();
        }
        int valeur3 = 0;
        if (listeEquipements[1]!=null) {
            valeur3 = listeEquipements[1].getAttackGain();
        }
        if ((valeur1 + valeur2 + valeur3)<=getMaxAttack()){
            return (valeur1 + valeur2 + valeur3);
        } else {
            return getMaxAttack();
        }
    }

    @Override
    public String toString() {
        return   this.getClass().getSimpleName() + " " + this.getName() + " : " + this.calculeAttack() + " (" + this.getMaxAttack() + " max) points d'attaque et " + this.getLife() + " (" + this.getMaxLife() + " max) points de vie";
    }

}
