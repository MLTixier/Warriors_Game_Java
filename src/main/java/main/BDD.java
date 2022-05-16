package main;

import characters.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDD {

    Connection connexion;

    public BDD() {
        //connexion à la BDD :

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

    public List<Hero> requeteGetHeroes() {
        //requte pour afficher les héros de la table heroes :
        List<Hero> listeHeroes = new ArrayList<Hero>();
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM heroes;");
            //System.out.println("Connected");
            while (rs.next()) {
                String name = rs.getString("name");
                int atk = rs.getInt("atk");
                int life = rs.getInt("life");
                int maxAtk = rs.getInt("maxAtk");
                int maxLife = rs.getInt("maxLife");
                Hero hero = new Hero(name, atk, life, maxAtk, maxLife);
                listeHeroes.add(hero);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listeHeroes;
    }

    public void requetePostHero(Hero hero) {
        //requête pour ajouter un héros à la table heroes :
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO heroes(name, atk, life, maxAtk, maxLife) VALUES(?,?,?,?,?);");

            preparedStatement.setString(1, hero.getName());
            preparedStatement.setInt(2, hero.getAttack());
            preparedStatement.setInt(3, hero.getLife());
            preparedStatement.setInt(4, hero.getMaxAttack());
            preparedStatement.setInt(5, hero.getMaxLife());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
