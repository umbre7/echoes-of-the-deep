package com.echoesofthedeep.turn;

import com.echoesofthedeep.entity.Entity;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TurnManagerTest {

    private TurnManager turnManager;

    private List<Entity> createStandardRoster(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));
        entities.add(new Entity("Dwarf", 10, 3, 1, 5));
        entities.add(new Entity("Hobgoblin", 10, 3, 1, 5));

        return entities;
    }

    @Test
    void orderCorrect_afterBuildOrder(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));
        entities.add(new Entity("Dwarf", 10, 3, 1, 4));
        turnManager = new TurnManager(entities);
        turnManager.buildOrder();

        assertEquals("Elf",turnManager.getNextEntity().getName());
        assertEquals("Dwarf", turnManager.getNextEntity().getName());
        assertEquals("Orc", turnManager.getNextEntity().getName());
    }

    @Test
    void sameSpeedGroup_staysGrouped(){
        turnManager = new TurnManager(createStandardRoster());
        turnManager.buildOrder();
        Entity compared = turnManager.getNextEntity();
        while(turnManager.getCursor() < turnManager.getCurrentOrder().size()){
            Entity entity = turnManager.getNextEntity();
            assertTrue(entity.getCurrentSpeed() <= compared.getCurrentSpeed());
            compared = entity;
        }
    }

    @Test
    void noLoss_orDuplication(){
        turnManager = new TurnManager(createStandardRoster());
        turnManager.buildOrder();
        int expectedSize = turnManager.getAllEntities().size();
        int count = 0;
        Set<Entity> hashSet = new HashSet<>();
        for(int i = 0; i< turnManager.getAllEntities().size();i++){
            Entity e = turnManager.getNextEntity();
            count++;
            hashSet.add(e);
        }
        assertEquals(expectedSize, count,"Perte d'entité avec getNextEntity");
        assertEquals(expectedSize, hashSet.size(), "Duplication d'une entité avec getNextEntity");
    }

    @Test
    void fixedSeed_sameReproductibleOrder(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));
        entities.add(new Entity("Dwarf", 10, 3, 1, 5));
        entities.add(new Entity("Hobgoblin", 10, 3, 1, 5));

        turnManager = new TurnManager(entities, new Random(1));
        turnManager.buildOrder();
        assertEquals("Elf", turnManager.getNextEntity().getName());
        assertEquals("Dwarf", turnManager.getNextEntity().getName());
        assertEquals("Hobgoblin", turnManager.getNextEntity().getName());
        assertEquals("Goblin", turnManager.getNextEntity().getName());
        assertEquals("Orc", turnManager.getNextEntity().getName());
    }

    @Test
    void isOver_reflectsCorrectState(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));

        turnManager = new TurnManager(entities);
        turnManager.buildOrder();
        assertFalse(turnManager.isOver());

        turnManager.getNextEntity();
        turnManager.getNextEntity();
        assertFalse(turnManager.isOver());
        turnManager.getNextEntity();
        assertTrue(turnManager.isOver());
    }

    @Test
    void oneEntity_correctlyResolved(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));

        turnManager = new TurnManager(entities);
        turnManager.buildOrder();

        assertEquals("Goblin", turnManager.getNextEntity().getName());
        assertTrue(turnManager.isOver());
    }

    @Test
    void entityDeathDuringTurnSkipped(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));

        turnManager = new TurnManager(entities);
        turnManager.buildOrder();

        turnManager.getNextEntity();
        Entity target = turnManager.getCurrentOrder().get(1);
        target.takeDamage(10);

        assertEquals("Orc", turnManager.getNextEntity().getName());
    }

    @Test
    void multipleDeathInARowSkippedCorrectly(){
        List<Entity> entities = createStandardRoster();

        turnManager = new TurnManager(entities);
        turnManager.buildOrder();

        turnManager.getNextEntity();
        for(int i = 1; i < turnManager.getCurrentOrder().size() - 1 ; i++){
            Entity target = turnManager.getCurrentOrder().get(i);
            target.takeDamage(10);
        }
        assertEquals("Orc", turnManager.getNextEntity().getName());
    }

    @Test
    void multipleTurnCorrectlyResolved(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("Goblin", 10, 3, 1, 5));
        entities.add(new Entity("Elf", 10, 3, 1, 10));
        entities.add(new Entity("Orc", 10, 3, 1, 3));

        turnManager = new TurnManager(entities);
        turnManager.buildOrder();

        while(!turnManager.isOver()){
            turnManager.getNextEntity();
        }

        assertTrue(turnManager.isOver());
        assertEquals(turnManager.getCurrentOrder().size(), turnManager.getCursor());

        turnManager.buildOrder();
        assertEquals(0, turnManager.getCursor());
        assertEquals("Elf", turnManager.getNextEntity().getName());
    }
}
