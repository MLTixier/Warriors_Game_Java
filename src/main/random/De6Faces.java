package main.random;

public class De6Faces implements RandomDe {

    private int de ;

    @Override
    public int valeur() {
        java.util.Random rand = new java.util.Random();
        de = rand.nextInt(6)+1;
        return de;
    }

}
