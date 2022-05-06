package characters;

public class Monster extends Character {

    public Monster(String myName, int myAttack, int myLife){
        super(myName, myAttack, myLife,20,20);
        //les monstres ont un maximum de 20 points de vie et d'attaque par défaut)
    }

    public Monster(){
    }

    public String toString() {
        return "monstre de type " + this.getName() + " : " + this.getAttack() + " points d'attaque et " + this.getLife() + " points de vie";
    }
}
