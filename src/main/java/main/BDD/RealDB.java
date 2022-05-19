package main.BDD;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RealDB extends DB {

    public RealDB() {
        super();

        // Chargement du driver :
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        // Paramètres de la connexion à la BDD :
        try {
            this.connexion = DriverManager.getConnection("jdbc:mysql://adminer.local/warriorsGame", "ML_TIXIER", "MDP");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
