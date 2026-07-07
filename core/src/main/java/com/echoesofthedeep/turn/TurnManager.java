package com.echoesofthedeep.turn;

import com.echoesofthedeep.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *  Classe gérant le déroulement d'un tour.
 *  Cette classe manipule une liste d'{@link Entity} ordonnée selon la vitesse des entités de manière décroissante.
 *  En cas de vitesses égales, les entités concernées sont mélangées aléatoirement.
 *  Cet ordre est recalculé à chaque round afin de refléter l'état actuel des vitesses des entités.
 *  Actuellement, cette version ne gère le déroulement que des tours de combat. À terme,
 *  elle gèrera également les actions des entités dans d'autres endroits du donjon.
 * @author Arthur
 */

public class TurnManager {

    // ========== Attributs d'instances ==========

    /** Liste des entités du combat. */
    private List<Entity> allEntities;

    /** Ordre calculé pour ce tour. */
    private List<Entity> currentOrder;

    /** Progression dans le tour (index de l'entité qui va réaliser son action dans currentOrder). */
    private int cursor;

    /** Générateur aléatoire pour le mélange des entités ayant la même vitesse.*/
    private Random random;

    // ========== Constructeur ==========

    /**
     * Constructeur de la classe TurnManager.
     * Il initialise le TurnManager avec la liste d'entités passée en paramètre.
     * @param entities les entités participant au combat.
     */
    public TurnManager(List<Entity> entities){
        this.allEntities = new ArrayList<>(entities);
        this.random = new Random();
    }

    /**
     * Constructeur de la classe TurnManager.
     * Il initialise le TurnManager avec la liste d'entités et la seed aléatoire passées en paramètre.
     * @param entities les entités participant au combat.
     * @param random la seed aléatoire utilisée.
     */
    public TurnManager(List<Entity> entities, Random random){
        this.allEntities = new ArrayList<>(entities);
        this.random = random;
    }

    // ========== Getters ==========

    /**
     * Renvoie le curseur de progression dans le tour.
     * @return un int correspondant au curseur de progression dans le tour.
     */
    public int getCursor() {
        return cursor;
    }

    /**
     * Renvoie la liste non ordonnée de toutes les entités du tour.
     * @return une liste de toutes les entités du tour.
     */
    public List<Entity> getAllEntities() {
        return allEntities;
    }

    /**
     * Renvoie la liste contenant l'ordre actuel des entités pour ce tour.
     * @return une liste ordonnée des entités pour le tour actuel.
     */
    public List<Entity> getCurrentOrder() {
        return currentOrder;
    }

    // ========== Méthodes ==========

    /**
     * Construit l'ordre d'action du tour selon la vitesse des entités et initialise le curseur.
     * Dans le cas d'égalités de vitesses, un mélange aléatoire est réalisé pour les entités concernées.
     * Cette méthode doit être appelée avant l'utilisation des autres méthodes ({@link #getNextEntity()}, {@link #isOver()} pour garantir
     * leur bon fonctionnement.
     */
    public void buildOrder(){
        currentOrder = new ArrayList<>(allEntities);
        // Trie toutes les entités par vitesses décroissantes
        currentOrder.sort((a,b) -> b.getCurrentSpeed() - a.getCurrentSpeed());

        // Mélange des égalités
        int startOfGroup = 0;
        int index = 0;

        while (index < currentOrder.size()){
            while(index <currentOrder.size() && currentOrder.get(index).getCurrentSpeed() == currentOrder.get(startOfGroup).getCurrentSpeed()){
                index ++;
            }
            List<Entity> group = currentOrder.subList(startOfGroup, index);
            Collections.shuffle(group, random);
            startOfGroup = index;
        }

        // On remet le curseur à zéro pour repartir du début de la liste
        cursor = 0;
    }

    /**
     * Renvoie la prochaine entité qui réalisera son action ce tour.
     * Précondition : {@link #buildOrder()} doit avoir été appelée au préalable.
     * @return l'entité qui réalisera son action ce tour.
     */
    public Entity getNextEntity(){
        skipDeadEntities();
        Entity next = currentOrder.get(cursor);
        cursor ++;
        return next;
    }

    /**
     * Vérifie si toutes les entités du tour ont réalisé leur action.
     * Précondition : {@link #buildOrder()} doit avoir été appelée au préalable.
     * @return true si c'est le cas, false sinon.
     */
    public boolean isOver(){
        skipDeadEntities();
        return cursor >= currentOrder.size();
    }

    /**
     * Passe le tour des entités mortes en incrémentant le curseur qui contrôle l'avancement dans le tour.
     */
    private void skipDeadEntities(){
        while(this.cursor < this.currentOrder.size() && currentOrder.get(cursor).isDead()){
            cursor ++;
        }
    }
}
