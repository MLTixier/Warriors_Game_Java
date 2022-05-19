package main.BDD;

public class FakeDB extends DB {

    public FakeDB() {
        super();
        //connexion à une "fake" DB
        System.out.println("il n'y a pas de base de données ; les options d'enregistrement ne sont pas disponibles.");
    }
}
