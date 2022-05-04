package characters;

public class Hero extends Character {

    public Hero(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
    }

    /*-------------------------------------------- avancer et reculer -------------------------------------------*/

    public void goesForward(int numberOfCase){
        int newPosition = this.getPosition() + numberOfCase;
        this.setPosition(newPosition);
        if (newPosition >= 64){
            this.setPosition(64) ;
            //ajouter une sortie de jeu par victoire du personnage
        }
    }

    public void goesBack(int numberOfCase){
        int newPosition = this.getPosition() - numberOfCase;
        this.setPosition(newPosition);
        if (newPosition < 1){
            this.setPosition(1) ;
        }
    }

}
