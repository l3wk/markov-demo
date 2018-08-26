package io.github.l3wk.markov.controllers;

import io.github.l3wk.markov.services.MarkovService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MarkovController {

    private final MarkovService service;

    @Autowired
    public MarkovController(MarkovService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getTransformRequestForm(Model model) {

        model.addAttribute("transformRequest", getTransformRequest());

        return "request";
    }

    @PostMapping("/")
    public String handleTransformRequest(@ModelAttribute @Valid TransformRequest request,
                                         BindingResult result,
                                         Model model) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("transformRequest", request);

            return "request";

        } else {
            model.addAttribute("transformResponse", service.transform(request.getInput().getInputStream(),
                                                                      request.getPrefixLength(),
                                                                      request.getSuffixCount()));
            return "response";
        }
    }

    TransformRequest getTransformRequest() {
        return new TransformRequest();
    }
}
