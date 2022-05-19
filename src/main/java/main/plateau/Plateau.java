package main.plateau;

import main.Game;
import main.cases.Case;

import java.util.Map;

public abstract class Plateau {

    protected Map<String, Integer> listeEvents ;
    protected Case[] plateau ;
    protected Game game;

    public Plateau(Game game) {
        this.game = game;
    }

    public Case[] getPlateau() {
        return plateau;
    }

    public Case getContenuPlateau(int indice) {
        Case contenuCase = (Case) this.plateau[indice];
        return contenuCase;
    }

}
