package io.github.l3wk.markov.models;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ChainTests {

    private Chain chain;

    @Before
    public void setUp() {
        chain = new Chain(1, 1);
    }

    @Test
    public void testConstructor() {
        assertNotNull(chain.getRandom());
        assertNotNull(chain.getPrefix());
        assertNotNull(chain.getStates());

        assertEquals(chain.getPrefix().size(), 1);
    }

    @Test
    public void testPopulateWithEmptyStream() {
        chain.populate(stringToStream(""));

        assertEquals(chain.getStates().size(), 1);
        assertEquals(chain.getPrefix().getTokens().get(0), Prefix.EMPTY_TOKEN);

        Prefix empty = new Prefix(Prefix.EMPTY_TOKEN, 1);
        assertSuffixes(chain, empty, Collections.singletonList(Prefix.EMPTY_TOKEN));
    }

    @Test
    public void testPopulateWithDelimitedStream() {
        chain.populate(stringToStream("\"one. two\""));

        assertEquals(chain.getStates().size(), 3);
        assertEquals(chain.getPrefix().getTokens().get(0), Prefix.EMPTY_TOKEN);

        Prefix empty = new Prefix(Prefix.EMPTY_TOKEN, 1);
        assertSuffixes(chain, empty, Collections.singletonList("one"));

        Prefix one = new Prefix("one", 1);
        assertSuffixes(chain, one, Collections.singletonList("two"));

        Prefix two = new Prefix("two", 1);
        assertSuffixes(chain, two, Collections.singletonList(Prefix.EMPTY_TOKEN));
    }

    @Test
    public void testGenerateWithEmptyStates() {
        assertEquals(chain.generate(1), "");
        assertEquals(chain.generate(2), "");
        assertEquals(chain.generate(3), "");
    }

    @Test
    public void testGenerate() {
        chain.populate(stringToStream("one two"));

        assertEquals(chain.generate(1), "one ");
        assertEquals(chain.generate(2), "one two ");
        assertEquals(chain.generate(3), "one two ");
    }

    private InputStream stringToStream(String data) {
        return new ByteArrayInputStream(data.getBytes());
    }

    private void assertSuffixes(Chain chain, Prefix expectedPrefix, List<String> expectedSuffixes) {
        List<String> suffixes = chain.getStates().get(expectedPrefix);

        assertEquals(suffixes.size(), expectedSuffixes.size());
        expectedSuffixes.forEach(suffix -> assertTrue(suffixes.contains(suffix)));
    }
}
