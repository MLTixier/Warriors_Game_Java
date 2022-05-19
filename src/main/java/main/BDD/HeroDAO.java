package main.BDD;

import characters.Hero;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO extends Request<Hero> {

    public HeroDAO(Connection connexion){
        super(connexion);
    }

    @Override
    public void post(Hero hero) {
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO heroes(name, type, atk, life, maxAtk, maxLife) VALUES(?,?,?,?,?,?);");

            preparedStatement.setString(1, hero.getName());
            preparedStatement.setString(2, hero.getClass().getName());
            preparedStatement.setInt(3, hero.getAttack());
            preparedStatement.setInt(4, hero.getLife());
            preparedStatement.setInt(5, hero.getMaxAttack());
            preparedStatement.setInt(6, hero.getMaxLife());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Hero> getAll() {
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
                Class classe = Class.forName("characters." + type);
                Constructor cons = classe.getConstructor(String.class, int.class, int.class, int.class, int.class);
                Hero hero = (Hero) cons.newInstance(name, atk, life, maxAtk, maxLife);
                listeHeroes.add(hero);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listeHeroes;
    }

    @Override
    public Hero get(int id) {
        return  this.getAll().get(id);
    }

    @Override
    public void put(Hero hero) {

    }

    @Override
    public void delete(Hero hero) {

    }



}
