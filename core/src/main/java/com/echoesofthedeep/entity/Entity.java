package com.echoesofthedeep.entity;

/**
 * Classe représentant une Entité.
 *
 * @author Arthur
 */
public class Entity {

    // ========== Attributs d'instances ==========

    /** Le nom de l'entité. */
    private String name;

    /** Les points de vie maximum de l'entité. */
    private int maxHealth;

    /** Les points de vie actuels de l'entité. */
    private int currentHealth;

    /** L'attaque de base de l'entité. */
    private int baseAttack;

    /** La défense de base de l'entité. */
    private int baseDefense;

    /** La vitesse de base de l'entité. */
    private int baseSpeed;

    /** Le niveau de l'entité. */
    private int level;

    /** Les points d'expérience de l'entité. */
    private int xp;

    // ========== Constructeur ==========

    /**
     * Constructeur de la classe Entité.
     * Il initialise une entité avec les paramètres entrés.
     * @param name le nom de l'entité.
     * @param health les points de vie de l'entité initialement.
     * @param attack l'attaque de base de l'entité.
     * @param defense la défense de base de l'entité.
     * @param speed la vitesse de base de l'entité.
     */
    public Entity(String name, int health, int attack, int defense, int speed){
        this.name = name;
        this.maxHealth = health;
        this.currentHealth = health;
        this.baseAttack = attack;
        this.baseDefense = defense;
        this.baseSpeed = speed;
        this.level = 1;
        this.xp = 0;
    }

    // ========== Getters ==========

    /**
     * Renvoie le nom de l'entité.
     * @return un String correspondant au nom de l'entité.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Renvoie le niveau de l'entité.
     * @return un int correspondant au niveau de l'entité.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Renvoie les points d'expérience de l'entité.
     * @return un int correspondant aux points d'expérience de l'entité.
     */
    public int getXp() {
        return this.xp;
    }

    /**
     * Renvoie l'attaque de base de l'entité.
     * @return un int correspondant à l'attaque de base de l'entité.
     */
    public int getBaseAttack() {
        return this.baseAttack;
    }

    /**
     * Renvoie la défense de base de l'entité.
     * @return un int correspondant à la défense de base de l'entité.
     */
    public int getBaseDefense() {
        return this.baseDefense;
    }

    /**
     * Renvoie la vitesse de base de l'entité.
     * @return un int correspondant à la vitesse de base de l'entité.
     */
    public int getBaseSpeed() {
        return this.baseSpeed;
    }

    /**
     * Renvoie les points de vie maximum de l'entité.
     * @return un int correspondant aux points de vie maximum de l'entité.
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Renvoie les points de vie actuels de l'entité.
     * @return un int correspondant aux points de vie actuels de l'entité.
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    // ========== Méthodes ==========

    /**
     * Soigne l'entité d'un montant de points de vie.
     * Si ce montant est supérieur à la différence entre les points de vie actuels et les points de vie maximum,
     * le montant réellement soigné est cette différence.
     * @param amount le montant de points de vie soignés de base.
     * @return le montant de points de vie réellement soignés.
     */
    public int heal(int amount){
        int healthDiff = this.getMaxHealth() - this.getCurrentHealth();
        if(amount > healthDiff){
            this.currentHealth = this.maxHealth;
            return healthDiff;
        } else{
            this.currentHealth += amount;
            return amount;
        }
    }

    /**
     * Inflige les dégâts entrés en paramètre à l'entité.
     * Si les dégâts infligés sont supérieurs aux points de vie actuels, le montant de dégâts infligés
     * est le nombre de points de vie actuels.
     * @param amount le montant de dégâts infligés avant vérification avec les points de vie actuels.
     * @return le montant de dégâts réellement infligés.
     */
    public int takeDamage(int amount){
        int previousHealth = this.currentHealth;
        if(amount >= previousHealth){
            this.currentHealth = 0;
            return previousHealth;
        } else{
            this.currentHealth -= amount;
            return amount;
        }
    }

    /**
     * Vérifie si l'entité est vivante.
     * @return true si c'est le cas, false sinon.
     */
    public boolean isAlive(){
        return this.currentHealth > 0;
    }

    /**
     * Vérifie si l'entité est morte.
     * @return true si c'est le cas, false sinon.
     */
    public boolean isDead(){
        return this.currentHealth <= 0;
    }
}
