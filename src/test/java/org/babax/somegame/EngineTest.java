package org.babax.somegame;

import org.babax.somegame.models.Field;
import org.babax.somegame.models.Point;
import org.babax.somegame.models.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by blvp on 21.11.15.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class EngineTest {

    @Test
    public void testInitWithoutTraps() throws Exception {
        Engine engine = new Engine();
        engine.init(Arrays.asList(
                "L1",
                "30", "60",
                "1", "1",
                "0"
        ));
        assertEquals(engine.getLevel(), "L1");
        Field dummyField = Fixture.dummyField();
        Field expectedField = engine.getField();
        assertEquals(expectedField.width, dummyField.width);
        assertEquals(expectedField.length, dummyField.length);
        assertEquals(expectedField.gate1.top.x, dummyField.gate1.top.x);
        assertEquals(expectedField.gate1.top.x, dummyField.gate1.top.x);
        assertEquals(expectedField.gate2.top.y, dummyField.gate2.top.y);
        assertEquals(expectedField.gate2.top.y, dummyField.gate2.top.y);
        assertNotNull(expectedField.traps);
        assertEquals(engine.getPosition().x, 1);
        assertEquals(engine.getPosition().y, 1);
    }

    @Test
    public void testEngineInitWithTraps(){

        Engine engine = new Engine();
        engine.init(Arrays.asList(
                "L1",
                "30", "60",
                "1", "1",
                "1",
                "3", "3"
        ));

        assertEquals(engine.getField().traps.size(), 1);
        Point actualTrapPoint = engine.getField().traps.iterator().next();
        assertEquals(3, actualTrapPoint.x);
        assertEquals(3, actualTrapPoint.y);
    }

    @Test
    public void testFindNextMove() throws Exception {
        Engine engine = new Engine();
        engine.init(Arrays.asList(
                "L1",
                "5", "4",
                "0", "0",
                "0"
        ));
        Point nextMove = engine.findNextMove();
        assertEquals(1, nextMove.x);
        assertEquals(1, nextMove.y);
    }

    @Test
    public void testEnemyMove() throws Exception {
        Engine engine = Fixture.dummyEngine();
        engine.handleEnemyMove(new Point(1, 2));
        assertEquals(1, engine.getPosition().x);
        assertEquals(2, engine.getPosition().y);
        assertEquals(Team.SECOND, engine.getTeam());
    }

}