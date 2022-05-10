package main;

import characters.Hero;

import java.util.Scanner;

public interface IEvent {

    public String readEvent();

    public void actionEvent(Hero hero, Scanner scanner);

}
