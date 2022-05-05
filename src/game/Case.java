package game;

import characters.Monster;

public class Case {
    public String evenement ;
    public Monster monster ;
    
    public Case(Monster monster){
        this.monster = monster;
    }

    public Case(String evenement){
        this.evenement = evenement;
    }

    public String afficheContenu(){
        if (this.evenement!=null){
            return this.evenement;
        } else if (this.monster!=null) {
            String descriptionMonster = "monstre";
            return descriptionMonster;
        } else {
            return "";
        }
    }

    public Monster afficheMonstre(){
        return this.monster;
    }

}
