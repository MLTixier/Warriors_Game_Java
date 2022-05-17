package main.des;

public class De8Faces implements RandomDe {

    private int de ;
    private java.util.Random rand ;

    @Override
    public int valeur() {
        rand = new java.util.Random();
        de = rand.nextInt(8)+1;
        return de;
    }

}
