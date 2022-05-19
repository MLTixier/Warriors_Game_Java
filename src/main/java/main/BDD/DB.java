package main.BDD;

import java.sql.*;

public abstract class DB {

    protected Connection connexion;

    public DB() {
    }


    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }
}
