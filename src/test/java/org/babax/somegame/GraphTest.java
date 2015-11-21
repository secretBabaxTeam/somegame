package org.babax.somegame;

import org.babax.somegame.models.Field;
import org.babax.somegame.models.Point;
import org.babax.somegame.models.Team;
import org.babax.somegame.models.Vertex;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by dos65 on 21.11.15.
 */
public class GraphTest {

    @Test
    public void testVertexCount() throws Exception {
        Field f = Fixture.getField(2, 2);
        Graph g = new Graph(f);
        assertEquals((f.length+1) * (f.width+1), g.key2Vertex.size());
    }

    @Test
    public void testGraphInitWithTraps() throws Exception {
        Field f = Fixture.getField(2, 2);
        Set<Point> traps = new HashSet<>();
        traps.add(new Point(1, 1));
        f.traps = traps;
        Graph g = new Graph(f);
        assertEquals((f.length+1) * (f.width+1) - 1 , g.key2Vertex.size());
        assertEquals(8, getEdgesCount(g));
    }

    @Test
    public void testEdges() throws Exception {
        Field f = Fixture.getField(2, 2);
        Graph g = new Graph(f);
        int count = getEdgesCount(g);
        assertEquals(24, count);
    }

    private int getEdgesCount(Graph g) {
        int count = 0;
        for(Vertex v : g.key2Vertex.values()) {
            count += v.getAccepted().size();
            v.getAccepted().forEach(System.out::println);
        }
        return count;
    }

    @Test
    public void testFindSome() throws Exception {
        Field f = Fixture.getField(5, 4);
        Graph g = new Graph(f);
        System.out.println(f.gate2);
        boolean result = g.findMove(new Point(0, 0), f.gate2);
        assertTrue(result);
    }

    @Test
    public void testMarkDisabled() throws Exception {
        Field f = Fixture.getField(5, 4);
        Graph g = new Graph(f);
        assertTrue(g.markDisabled(new Point(2, 1), new Point(1, 1)));
        assertFalse(g.markDisabled(new Point(2, 1), new Point(1, 1)));
    }
}