package main.plateau;

import main.cases.Case;

import java.util.Map;

public abstract class Plateau {

    protected Map<String, Integer> listeEvents ;
    protected Case[] plateau ;

    public Plateau() {
    }

    public Case[] getPlateau() {
        return plateau;
    }

    public Case getContenuPlateau(int indice) {
        Case contenuCase = (Case) this.plateau[indice];
        return contenuCase;
    }

}
