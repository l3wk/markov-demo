package io.github.l3wk.markov.controllers;

import io.github.l3wk.markov.services.MarkovService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MarkovControllerTests {

    private MarkovController controller;

    @Mock
    private MarkovService markovServiceMock;

    @Mock
    private TransformRequest transformRequestMock;

    @Mock
    private BindingResult bindingResultMock;

    @Mock
    private Model modelMock;

    @Mock
    private MultipartFile multipartFileMock;

    @Mock
    private InputStream inputStreamMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        controller = new MarkovController(markovServiceMock);
    }

    @Test
    public void testGetTransformRequestForm() {
        FakeMarkovController controller = new FakeMarkovController();

        assertEquals("request", controller.getTransformRequestForm(modelMock));

        verify(modelMock).addAttribute("transformRequest", transformRequestMock);
    }

    @Test
    public void testHandleTransformRequestWithErrors() throws IOException {
        when(bindingResultMock.hasErrors()).thenReturn(true);

        assertEquals("request", controller.handleTransformRequest(transformRequestMock, bindingResultMock, modelMock));

        verify(modelMock).addAttribute("transformRequest", transformRequestMock);
    }

    @Test
    public void testHandleTransformRequestWithoutErrors() throws IOException {
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(multipartFileMock.getInputStream()).thenReturn(inputStreamMock);
        when(transformRequestMock.getInput()).thenReturn(multipartFileMock);
        when(transformRequestMock.getPrefixLength()).thenReturn(1);
        when(transformRequestMock.getSuffixCount()).thenReturn(10);
        when(markovServiceMock.transform(isA(InputStream.class), anyInt(), anyInt())).thenReturn("suffixes");

        assertEquals("response", controller.handleTransformRequest(transformRequestMock, bindingResultMock, modelMock));

        verify(modelMock).addAttribute("transformResponse", "suffixes");
        verify(markovServiceMock).transform(inputStreamMock, 1, 10);
    }

    @Test
    public void testGetTransformRequest() {
        assertNotNull(controller.getTransformRequest());
    }

    private class FakeMarkovController extends MarkovController {

        private FakeMarkovController() {
            super(markovServiceMock);
        }

        @Override
        protected TransformRequest getTransformRequest() {
            return transformRequestMock;
        }
    }
}
