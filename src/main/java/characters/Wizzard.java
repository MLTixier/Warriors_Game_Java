package characters;

public class Wizzard extends Hero {

    public Wizzard(String myName) {
        super(myName, 3, 8, 15, 16);
    }

    public Wizzard(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
    }
}
