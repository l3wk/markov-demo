package io.github.l3wk.markov.controllers;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransformRequest {

    @NotNull
    @Min(1)
    @Max(1000)
    private int suffixCount;

    @NotNull
    @Min(1)
    @Max(5)
    private int prefixLength;

    @NotNull
    private MultipartFile input;

    public int getSuffixCount() {
        return suffixCount;
    }

    public void setSuffixCount(int suffixCount) {
        this.suffixCount = suffixCount;
    }

    public int getPrefixLength() {
        return prefixLength;
    }

    public void setPrefixLength(int prefixLength) {
        this.prefixLength = prefixLength;
    }

    public MultipartFile getInput() {
        return input;
    }

    public void setInput(MultipartFile input) {
        this.input = input;
    }
}
