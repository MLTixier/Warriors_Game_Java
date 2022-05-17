package characters;

public class Warrior extends Hero {

    public Warrior(String myName) {
        super(myName, 5, 5, 10, 10);
    }

    public Warrior(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
    }

}
