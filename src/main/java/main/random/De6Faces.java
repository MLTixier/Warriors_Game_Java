package main.random;

public class De6Faces implements RandomDe {

    private int de ;
    private java.util.Random rand ;


    public De6Faces (){
        rand = new java.util.Random();
    }

    @Override
    public int valeur() {
       return rand.nextInt(6)+1;
    }

}
