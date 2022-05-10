package main;

import characters.Hero;

import java.util.Scanner;

public class EmptyCase implements IEvent {

    public EmptyCase(){
    }

    @Override
    public String readEvent() {
        return "case vide !";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {
    }
}
