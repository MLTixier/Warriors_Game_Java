package characters;

public class Character {
    private String name;
    private int attack;
    private int life;
    private int position;
    private int maxAttack ;
    private int maxLife ;

    public Character (String myName, int myAttack, int myLife, int myMaxAttack, int myMaxLife){
        name = myName;
        attack = myAttack;
        life = myLife;
        position = 0;
        maxAttack = myMaxAttack;
        maxLife = myMaxLife;
    }

    public Character (){
    }

    /*------------------------------------------- setters et getters ------------------------------------------*/

    public void setPosition(int caseNumber){
        this.position = caseNumber;
    }
    public int getPosition(){
        return this.position;
    }
    public void setName(String myName) {
        this.name = myName;
    }
    public String getName() {
        return this.name;
    }
    public int getAttack() {
        return this.attack;
    }
    public void setAttack(int attackPoints) {
        if (attackPoints > maxAttack){
            attackPoints = maxAttack;
        }
        this.attack = attackPoints;
    }
    public int getLife() {
        return this.life;
    }
    public void setLife(int lifePoints) {
        if (lifePoints > maxLife){
            lifePoints = maxLife;
        }
        this.life = lifePoints;
    }

    /*---------------------------------- gagner ou perdre des points d'attaque ou de vie ----------------------*/

    public void modifyAttack(int attackPoints) {
       int newAttack = this.attack + attackPoints;
       this.attack = newAttack;
        if (newAttack < 0 ){
            this.attack = 0;
        }
    }

    public void modifyLife(int lifePoints) {
        int newLife = this.life + lifePoints;
        this.life = newLife;
        if (newLife <= 0 ){
            this.life = 0;
            //ajouter une sortie de jeu par mort du personnage
        }
    }

    @Override
    public String toString() {
        return   this.getClass().getSimpleName() + " " + this.name + " : " + this.attack + " points d'attaque et " + this.life + " points de vie";
    }
}
