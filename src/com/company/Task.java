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
        this.state = State.READY;
    }

    public String getName() {
        return name;
    }

    public char getPriority() {
        return priority;
    }

    public int getPriorityValue() {
        if (priority == 'X') return 3; // the lowest
        else if (priority == 'Y') return 2;
        else if (priority == 'Z') return 1; // the highest

        return 0;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return burstTime - consumedTime;
    }

    public int getConsumedTime() {
        return consumedTime;
    }

    public void increaseConsumedTime(int consumedTime) {
        this.consumedTime += consumedTime;
    }

    @Override
    public String toString() {
        return "Task: " + this.name;
    }

    public void setState(State state) {
        this.state = state;
    }
}
