package main.BDD;

import characters.Hero;

import java.sql.Connection;
import java.util.List;

public abstract class Request<T> {


    protected Connection connexion;

    public Request(Connection connexion) {
        this.connexion = connexion;
    }

    public abstract void post(T t);

    public abstract List<T> getAll();

    public abstract T get(int id);

    public abstract void put(T t);

    public abstract void delete(T t);

}
