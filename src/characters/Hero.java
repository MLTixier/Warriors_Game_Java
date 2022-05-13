package characters;

import equipements.*;
import exceptions.SortieJeuException;

import java.util.Arrays;

public class Hero extends Character {

    private Equipement[] listeEquipements ;
    private int position ;
    private int previousPosition ;
    private boolean fuyard;

    /**
     *La classe héros extends de la classe character et définit les règles de jeu propres au héros : avancer, reculer, consulter ses armes, calculer sa force d'attaque...
     * @param myName nom du héros
     * @param myAttack points d'attaques du héros
     * @param myLife points de vie du héros
     * @param maxAttack points max d'attaque du héros
     * @param maxLife points max de vie du héros
     */

    public Hero(String myName, int myAttack, int myLife, int maxAttack, int maxLife) {
        super(myName, myAttack, myLife, maxAttack, maxLife);
        this.fuyard = false ;
        this.position = 0 ;
        this.previousPosition = 0 ;
        this.listeEquipements =  new Equipement[2];
    }

    /*-------------------------------------------- getter et setter -------------------------------------------*/


    public void setFuyard(boolean fuyard) {
        this.fuyard = fuyard;
    }
    public boolean isFuyard() {
        return fuyard;
    }
    public void setPosition(int newPosition) {
        position = newPosition;
    }
    public int getPosition() {
        return position;
    }
    public void setPreviousPosition(int newPosition) {
        previousPosition = newPosition;
    }
    public int getPreviousPosition() {
        return previousPosition;
    }

    /*-------------------------------------------- avancer et reculer -------------------------------------------*/

    /**
     * méthode pour faire avancer le héros et modifier sa position.
     * @param numberOfCase nombre de cases à ajouter.
     * @throws SortieJeuException exception si on dépasse le plateau.
     */
    public void goesForward(int numberOfCase) throws SortieJeuException {
        this.previousPosition = this.getPosition();
        int newPosition = this.getPosition() + numberOfCase;
        if (newPosition > 64) {
            throw new SortieJeuException("Sortie plateau !");
        }
        this.setPosition(newPosition);
    }

    /**
     * méthode pour faire reculer le héros et modifier sa position.
     * @param numberOfCase nombre de cases à retrancher.
     * @throws SortieJeuException exception si on dépasse le plateau.
     */
    public void goesBack(int numberOfCase) {
        this.previousPosition = this.getPosition();
        int newPosition = this.getPosition() - numberOfCase;
        if (newPosition < 0) {
            newPosition = 0;
        }
        this.setPosition(newPosition);
    }

    /*-------------------------------------------- tableau d'équipements -------------------------------------------*/

    /**
     * méthode pour modifier le tableau d'équipements en ajoutant un équipement ou en en remplaçant un.
     * @param equipement équipement à mettre dans le tableau des équipements du héros.
     * @param position position dans le tableau (1 ou 2) pour mettre l'équipement.
     */
    public void modifieTableauEquipements(Equipement equipement, int position) {
        listeEquipements[position] = equipement;
    }

    /**
     * méthode pour afficher les équipements que possède le héros.
     */
    public void afficheTableauEquipements() {
        System.out.println("Vous avez les équipements suivants :");
        System.out.println("Equipement 1 : " + listeEquipements[0]);
        System.out.println("Equipement 2 : " + listeEquipements[1]);
    }

    /*----------------------------------- calcul force attaque avec equipements  ----------------------------------*/

    /**
     * méthide pour calculer le nombre réel de points d'attaque du héros en additionnant son nombre d'attaque et les points ajoutés par ses équipements.
     * @return le nombre de points d'attaque avec lequel le héros s'engage dans un combat.
     */
    public int calculeAttack() {
        int valeur1 = this.getAttack();
        int valeur2 = 0;
        if (listeEquipements[0] != null) {
            valeur2 = listeEquipements[0].getAttackGain();
        }
        int valeur3 = 0;
        if (listeEquipements[1] != null) {
            valeur3 = listeEquipements[1].getAttackGain();
        }
        if ((valeur1 + valeur2 + valeur3) <= getMaxAttack()) {
            return (valeur1 + valeur2 + valeur3);
        } else {
            return getMaxAttack();
        }
    }

    /*-------------------------------------------- affichage du héros -------------------------------------------*/

    /**
     * méthode pour afficher les attributs du héros.
     * @return String avec la classe du héros (warrior ou wizzard), son nom et ses nombres de points de vie et d'attaque.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getName() + " : " + this.calculeAttack() + " (" + this.getMaxAttack() + " max) points d'attaque et " + this.getLife() + " (" + this.getMaxLife() + " max) points de vie";
    }

}
