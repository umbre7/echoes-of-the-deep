package com.echoesofthedeep.entity;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private Entity entity;

    @BeforeEach
    void setUp() {
        entity = new Entity("Goblin", 10, 3, 1, 5);
    }

    @Test
    void getMaxHealth_returnsMaxHealth(){
        assertEquals(10, entity.getMaxHealth());
    }

    @Test
    void getHealth_returnsCurrentHealth(){
        assertEquals(10, entity.getCurrentHealth());
    }

    @Test
    void getName_returnsCorrectName(){
        assertEquals("Goblin", entity.getName());
    }

    @Test
    void getBaseAttack_returnsBaseAttack(){
        assertEquals(3, entity.getBaseAttack());
    }

    @Test
    void getBaseDefense_returnsBaseDefense(){
        assertEquals(1, entity.getBaseDefense());
    }

    @Test
    void getBaseSpeed_returnsBaseSpeed(){
        assertEquals(5, entity.getBaseSpeed());
    }

    @Test
    void getLevel_returnsLevel(){
        assertEquals(1, entity.getLevel());
    }

    @Test
    void getXp_returnsXP(){
        assertEquals(0, entity.getXp());
    }

    @Test
    void isDead_deadEntity_returnsTrue(){
        entity.takeDamage(10);
        assertTrue(entity.isDead());
    }

    @Test
    void isDead_aliveEntity_returnsFalse(){
        assertFalse(entity.isDead());
    }

    @Test
    void isAlive_aliveEntity_returnsTrue(){
        assertTrue(entity.isAlive());
    }

    @Test
    void isAlive_deadEntity_returnsFalse(){
        entity.takeDamage(10);
        assertFalse(entity.isAlive());
    }

    @Test
    void heal_normalAmount_increasesCurrentHealth(){
        entity.takeDamage(4);
        int healed = entity.heal(2);
        assertEquals(2, healed);
        assertEquals(8, entity.getCurrentHealth());
    }

    @Test
    void heal_overMax_returnsCorrectValue(){
        entity.takeDamage(4);
        int healed = entity.heal(12);
        assertEquals(4, healed);
        assertEquals(10,entity.getCurrentHealth());
    }

    @Test
    void takeDamage_decreasesCurrentHealth(){
        int damage = entity.takeDamage(4);
        assertEquals(4, damage);
        assertEquals(6, entity.getCurrentHealth());
    }

    @Test
    void takeDamage_damageEqualsCurrentHealth_returnsCorrectValue(){
        entity.takeDamage(3);
        int damage = entity.takeDamage(7);
        assertEquals(7, damage);
        assertEquals(0,entity.getCurrentHealth());
    }

    @Test
    void takeDamage_damageOverCurrentHealth_returnsCorrectValue(){
        int damage = entity.takeDamage(15);
        assertEquals(10, damage);
        assertEquals(0, entity.getCurrentHealth());
    }
}
