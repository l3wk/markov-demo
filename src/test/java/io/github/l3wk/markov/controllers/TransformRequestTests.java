package io.github.l3wk.markov.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;

public class TransformRequestTests {

    private TransformRequest request;

    @Mock
    private MultipartFile multipartFileMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        request = new TransformRequest();
    }

    @Test
    public void testSuffixCount() {
        request.setSuffixCount(10);
        assertEquals(request.getSuffixCount(), 10);
    }

    @Test
    public void testPrefixLength() {
        request.setPrefixLength(1);
        assertEquals(request.getPrefixLength(), 1);
    }

    @Test
    public void testInput() {
        request.setInput(multipartFileMock);
        assertEquals(request.getInput(), multipartFileMock);
    }
}
