package main.cases;

import characters.Hero;
import main.cases.Case;

import java.util.Scanner;

public class EmptyCase extends Case {

    public EmptyCase(){
        super();
        this.setStringContent("vide");
    }

    @Override
    public String readEvent() {
        return "case vide !";
    }

    @Override
    public void actionEvent(Hero hero, Scanner scanner) {

    }


}
