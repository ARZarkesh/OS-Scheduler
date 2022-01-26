package com.company;

public class Task {
    private String name;
    private char priority;
    private State state;
    private int burstTime;
    private int consumedTime;
    private Resource[] needed;
    private Resource[] allocation;

    public Task(String name, char priority, int burstTime) {
        this.name = name;
        this.priority = priority;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public char getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }
}
