package main;

import characters.Hero;
import exceptions.SortieJeuException;

import java.util.Scanner;

public abstract class Case {

    private String stringContent ;

    public Case(){

    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public abstract String readEvent();

    public abstract void actionEvent(Hero hero, Scanner scanner) throws SortieJeuException;

}
