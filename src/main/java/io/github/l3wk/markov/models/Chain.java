package io.github.l3wk.markov.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Chain {

    private Random random;

    private Prefix prefix;
    private Map<Prefix, List<String>> states;

    public Chain(int prefixLength, long randomSeed) {

        random = randomSeed == 0 ? new Random(System.currentTimeMillis()) : new Random(randomSeed);

        states = new HashMap<>();
        prefix = new Prefix(Prefix.EMPTY_TOKEN, prefixLength);
    }

    public Random getRandom() {
        return random;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public Map<Prefix, List<String>> getStates() {
        return states;
    }

    public void populate(InputStream input) {

        Scanner scanner = new Scanner(input).useDelimiter("[\\W\\s]");

        while (scanner.hasNext()) {
            add(scanner.next());
        }

        scanner.close();

        add(Prefix.EMPTY_TOKEN);
    }

    public String generate(int count) {

        StringBuilder builder = new StringBuilder();

        if (!states.isEmpty()) {

            prefix = new Prefix(Prefix.EMPTY_TOKEN, prefix.size());

            for (int i = 0; i < count; i++) {

                List<String> suffixes = states.get(prefix);
                String suffix = suffixes.get(random.nextInt(suffixes.size()));

                if (suffix.equals(Prefix.EMPTY_TOKEN)) {
                    break;

                } else {

                    builder.append(suffix);
                    builder.append(" ");

                    prefix.add(suffix);
                }
            }
        }

        return builder.toString();
    }

    private void add(String token) {

        if (!token.isEmpty()) {

            states.computeIfAbsent(new Prefix(prefix), $ -> new ArrayList<>()).add(token.intern());
            prefix.add(token);
        }
    }
}
