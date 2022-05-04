package characters;

public class Monster extends Character {
    private boolean isAlive = true ;

    public Monster(String myName, int myAttack, int myLife){
        super(myName, myAttack, myLife,20,20);
        //les monstres ont un maximum de 20 points de vie et d'attaque par défaut)
    }

    /*------------------------------------------- setter et getter -----------------------------------------*/

    public void setIsAlive(boolean isAliveOrNot){
        this.isAlive = isAliveOrNot;
    }

    public boolean getIsAlive(){
        return this.isAlive ;
    }

}
