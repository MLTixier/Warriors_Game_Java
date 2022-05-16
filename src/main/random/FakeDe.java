package main.random;

public class FakeDe implements RandomDe {

    private int de ;
    private int[] valeursFakeDe = {60,5,5,5,5,5,5,5,5,5,5,5,5,5} ;
    private int compteurTours = 0 ;

    @Override
    public int valeur() {
        de = valeursFakeDe[compteurTours];
        compteurTours ++;
        return de;
    }

}
