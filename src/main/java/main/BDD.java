package main;

import characters.Hero;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDD {

    Connection connexion;

    /**
     * constructeur permettant d'établir une connexion à la BDD.
     */
    public BDD() {

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

    /**
     * méthode pour effectuer une requête sur la BDD, permettant d'afficher les héros de la table heroes
     * @return
     */
    public List<Hero> requeteGetHeroes() {
        List<Hero> listeHeroes = new ArrayList<Hero>();
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM heroes;");
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                int atk = rs.getInt("atk");
                int life = rs.getInt("life");
                int maxAtk = rs.getInt("maxAtk");
                int maxLife = rs.getInt("maxLife");
                Class classe = Class.forName(type);
                Constructor cons = classe.getConstructor(String.class, int.class, int.class, int.class, int.class);
                Hero hero = (Hero) cons.newInstance(name, atk, life, maxAtk, maxLife);
                listeHeroes.add(hero);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listeHeroes;
    }

    /**
     * méthode pour effectuer une requête sur la BDD, permettant d'ajouter un héros à la table heroes
     * @return
     */
    public void requetePostHero(Hero hero) {
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO heroes(name, type, atk, life, maxAtk, maxLife) VALUES(?,?,?,?,?,?);");

            preparedStatement.setString(1, hero.getName());
            preparedStatement.setString(2, hero.getClass().getName());
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
