package main.cases;

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

    /**
     * méthode pour lire la classe de la case et ce qu'il va arriver au personnage.
     * @return String qui donne le type d'événement sur la case.
     */
    public abstract String readEvent();

    /**
     * méthode pour dérouler l'événement déclenché par le personnage sur la case, selon les spécificités de cette case.
     * @param hero
     * @param scanner
     * @throws SortieJeuException dans le cas où l'action génrère une sortie de plateau (action reculer pendant le combat par ex.)
     */

    public abstract void actionEvent(Hero hero, Scanner scanner) throws SortieJeuException;

}
