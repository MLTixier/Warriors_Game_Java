package equipements;

import characters.Hero;
import main.*;

import java.util.Scanner;

public class Potion extends Equipement {

    public Potion(String name, int gainLife) {
        super(name, 0, gainLife);
    }

    public Potion() {
        super();
    }

}
