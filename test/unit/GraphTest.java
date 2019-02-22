/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Ensures graph algorithms function correctly
class GraphTest {

    private final static Node A, B, C, D, E, F, G;

    static {
        A = new Node("A");
        B = new Node("B");
        C = new Node("C");
        D = new Node("D");
        E = new Node("E");
        F = new Node("F");
        G = new Node("G");

        B.cost(5.0).to(A);
        B.cost(6.0).to(C).cost(7).to(D).cost(2).to(E).cost(3).to(B).cost(4).to(F);
        C.cost(1.0).to(D);
        C.cost(8.0).to(E);
    }

    @Test
    void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }

    @Test
    void hopCount() {
        assertEquals(0, B.hopCount(B));
        assertEquals(1, B.hopCount(A));
        assertEquals(1, B.hopCount(F));
        assertEquals(2, B.hopCount(D));
        assertEquals(3, C.hopCount(F));
        assertThrows(IllegalArgumentException.class, () -> G.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> A.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
    }

    @Test
    void cost() {
        assertEquals(0, B.cost(B));
        assertEquals(5, B.cost(A));
        assertEquals(4, B.cost(F));
        assertEquals(7, B.cost(D));
        assertEquals(10, C.cost(F));
        assertThrows(IllegalArgumentException.class, () -> G.cost(B));
        assertThrows(IllegalArgumentException.class, () -> A.cost(B));
        assertThrows(IllegalArgumentException.class, () -> B.cost(G));
    }

    @Test
    void path() {
        assertEquals(0, A.path(A).hopsCount());
        assertEquals(0, A.path(A).cost());
        assertEquals(1, B.path(F).hopsCount());
        assertEquals(4, B.path(F).cost());
        assertEquals(3, B.path(E).hopsCount());
        assertEquals(9, B.path(E).cost());
        assertThrows(IllegalArgumentException.class, () -> A.path(F));
    }

}
