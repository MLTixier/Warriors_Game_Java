package main;

import exceptions.SortieJeuException;

public class Main {

    public Main(){
    }

    public static void main(String[] args) throws SortieJeuException {
        System.out.println("Bienvenue dans le jeu des Warriors !");
        MenuDemarrage menuDemarrage = new MenuDemarrage();
    }

}
