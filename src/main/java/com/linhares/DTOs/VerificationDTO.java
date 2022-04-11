package com.linhares.DTOs;

public class VerificationDTO {
    private final String seed;
    private final int iterations;

    public VerificationDTO(String seed, int iterations) {
        this.seed = seed;
        this.iterations = iterations;
    }

    public String getSeed() {
        return seed;
    }

    public int getIterations() {
        return iterations;
    }
}
