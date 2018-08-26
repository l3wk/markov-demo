package io.github.l3wk.markov.services;

import io.github.l3wk.markov.models.Chain;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MarkovService {

    private long randomSeed;

    public MarkovService() {
        this.randomSeed = 0;
    }

    public MarkovService(long randomSeed) {
        this.randomSeed = randomSeed;
    }

    public String transform(InputStream input, int prefixLength, int suffixCount) {

        Chain chain = getChain(prefixLength);

        chain.populate(input);

        return chain.generate(suffixCount);
    }

    public long getRandomSeed() {
        return randomSeed;
    }

    Chain getChain(int prefixLength) {
        return new Chain(prefixLength, randomSeed);
    }
}
