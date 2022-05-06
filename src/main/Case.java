package main;
import characters.*;
import equipements.*;

public class Case {
    private String evenement ;
    private Monster monster ;
    private Equipement equipement ;
    
    public Case(Monster monster){
        this.monster = monster;
        this.evenement=null;
        this.equipement = null;
    }

    public Case(String evenement){
        this.evenement = evenement;
        this.monster = null;
        this.equipement = null;
    }

    public Case(Equipement equipement){
        this.equipement = equipement;
        this.monster = null;
        this.evenement=null;
    }

    public String afficheContenu(){
        if (this.evenement!=null){
            return this.evenement;
        } else if (this.monster!=null) {
            String descriptionMonster = "monstre";
            return descriptionMonster;
        } else if (this.equipement!=null) {
            String descriptionEquipement = "equipement";
            return descriptionEquipement;
        } else {
            return "";
        }
    }

    public Monster afficheMonstre(){
        return this.monster;
    }

    public Equipement afficheEquipement(){
        return this.equipement;
    }

}
