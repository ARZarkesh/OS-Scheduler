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

    public int getPriorityValue() throws Exception {
        if (priority == 'X') return 1;
        else if (priority == 'Y') return 2;
        else if (priority == 'Z') return 3;

        throw new Exception("The priority is invalid");
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return burstTime - consumedTime;
    }

    public void setConsumedTime(int consumedTime) {
        this.consumedTime = consumedTime;
    }

    @Override
    public String toString() {
        return "Task: " + this.name;
    }
}
