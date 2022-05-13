package main;

import exceptions.PourcentagesPlateauException;
import exceptions.SortieJeuException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main extends JFrame {

    public Main() {

        super("Bienvenue dans le jeu des Warriors !");
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(50,50);
        setVisible(true);

    }

    public static void main(String[] args) throws SortieJeuException, PourcentagesPlateauException {
        //System.out.println("Bienvenue dans le jeu des Warriors !");
        JFrame frame = new Main();
        MenuDemarrage menuDemarrage = new MenuDemarrage();
    }

}
