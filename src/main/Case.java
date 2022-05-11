package main;

import characters.Hero;

import java.util.Scanner;

public abstract class Case {

    public Case(){

    }

    public abstract String readEvent();

    public abstract void actionEvent(Hero hero, Scanner scanner);

}
