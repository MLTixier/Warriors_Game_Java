package main;

import exceptions.FinJeuException;
import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws SortieJeuException, PourcentagesPlateauException {
        System.out.println("Bienvenue dans le jeu des Warriors !");
        try {
            MenuDemarrage menuDemarrage = new MenuDemarrage();
        } catch (
                FinJeuException finJeuException) {
            System.out.println("Au revoir et merci d'avoir joué avec nous !");
        }
    }

}