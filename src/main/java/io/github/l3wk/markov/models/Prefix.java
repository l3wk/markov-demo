package io.github.l3wk.markov.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prefix {

    public static final String EMPTY_TOKEN = "\n";

    private List<String> tokens;

    public Prefix() {
        this(Prefix.EMPTY_TOKEN, 2);
    }

    public Prefix(Prefix other) {
        tokens = new ArrayList<>(other.tokens);
    }

    public Prefix(String token, int count) {
        tokens = IntStream.range(0, count).mapToObj($ -> token).collect(Collectors.toList());
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void add(String token) {

        tokens.remove(0);
        tokens.add(token);
    }

    public int size() {
        return tokens.size();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prefix prefix = (Prefix) o;

        return Objects.equals(tokens, prefix.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }
}
