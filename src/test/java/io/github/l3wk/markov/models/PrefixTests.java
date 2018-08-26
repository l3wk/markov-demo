package io.github.l3wk.markov.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class PrefixTests {

    private Prefix prefix;

    @Before
    public void setUp() {
        prefix = new Prefix();
    }

    @Test
    public void testConstructors() {
        assertEquals(prefix.size(), 2);
        assertEquals(prefix.getTokens().get(0), Prefix.EMPTY_TOKEN);
        assertEquals(prefix.getTokens().get(1), Prefix.EMPTY_TOKEN);

        Prefix copy = new Prefix(prefix);

        assertEquals(copy.getTokens(), prefix.getTokens());

        Prefix one = new Prefix("token", 1);

        assertEquals(one.size(), 1);
        assertEquals(one.getTokens().get(0), "token");

        Prefix two = new Prefix("token", 2);

        assertEquals(two.size(), 2);
        assertEquals(two.getTokens().get(0), "token");
        assertEquals(two.getTokens().get(1), "token");
    }

    @Test
    public void testAddOneToken() {
        prefix.add("one");

        assertEquals(prefix.size(), 2);
        assertEquals(prefix.getTokens().get(0), Prefix.EMPTY_TOKEN);
        assertEquals(prefix.getTokens().get(1), "one");
    }

    @Test
    public void testAddTwoTokens() {
        prefix.add("one");
        prefix.add("two");

        assertEquals(prefix.size(), 2);
        assertEquals(prefix.getTokens().get(0), "one");
        assertEquals(prefix.getTokens().get(1), "two");
    }

    @Test
    public void testAddThreeTokens() {
        prefix.add("one");
        prefix.add("two");
        prefix.add("three");

        assertEquals(prefix.size(), 2);
        assertEquals(prefix.getTokens().get(0), "two");
        assertEquals(prefix.getTokens().get(1), "three");
    }

    @Test
    public void testSize() {
        assertEquals(prefix.size(), 2);
    }

    @Test
    public void testEquals() {
        assertFalse(prefix.equals("blah"));

        Prefix other = new Prefix(prefix);

        assertTrue(prefix.equals(other));
        assertTrue(prefix.equals(prefix));
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(prefix.getTokens()), prefix.hashCode());
    }
}
