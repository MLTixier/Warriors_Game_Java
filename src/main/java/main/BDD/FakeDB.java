package main.BDD;

public class FakeDB extends DB {

    public FakeDB() {
        super();
        //connexion à une "fake" DB
        System.out.println("Nota : la base de données est indisponible ; les options d'enregistrement ne sont donc pas disponibles.");
    }
}
