package io.github.l3wk.markov.services;

import io.github.l3wk.markov.models.Chain;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MarkovServiceTests {

    private MarkovService service;

    @Mock
    private Chain chainMock;

    @Mock
    private InputStream inputStreamMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        service = new MarkovService(1);
    }

    @Test
    public void testConstructors() {
        assertEquals(service.getRandomSeed(), 1);

        service = new MarkovService();

        assertEquals(service.getRandomSeed(), 0);
    }

    @Test
    public void testTransform() {
        FakeMarkovService service = new FakeMarkovService();

        service.transform(inputStreamMock, 1, 10);

        assertEquals(service.prefixLength, 1);

        verify(chainMock).populate(inputStreamMock);
        verify(chainMock).generate(10);
    }

    @Test
    public void testGetChain() {
        Chain chain = service.getChain(1);

        assertNotNull(chain);
        assertNotNull(chain.getRandom());
        assertEquals(chain.getPrefix().size(), 1);
    }

    private class FakeMarkovService extends MarkovService {

        int prefixLength;

        @Override
        public Chain getChain(int prefixLength) {
            this.prefixLength = prefixLength;

            return chainMock;
        }
    }
}
