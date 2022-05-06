package equipements;

public class Equipement {
    private String name ;
    private int attackGain ;
    private int lifeGain;

    public Equipement(String name, int attackGain, int lifeGain){
        this.name = name;
        this.attackGain = attackGain;
        this.lifeGain = lifeGain;
    }

    public Equipement(){
    }

    public int getAttackGain() {
        return attackGain;
    }

    public int getLifeGain(){
        return lifeGain;
    }

    public String getName(){
        return name;
    }

    public String toString() {
        return "équipement de type " + this.getName() + " : " + this.getAttackGain() + " points d'attaque supplémentaires et : " + this.getLifeGain() + " points de vie supplémentaires";
    }
}
