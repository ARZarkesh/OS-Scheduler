package com.company;

import java.util.LinkedList;

public class Scheduler {
    LinkedList<Task> readyQueue;

    public void FCFS(LinkedList<Task> tasks) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        while (!readyQueue.isEmpty()) {
            Task runningTask = readyQueue.pop();
            int burstTime = runningTask.getBurstTime();
            for (int i = 0; i < burstTime; i++) {
                System.out.println("Time: " + time + " -> " + (time + 1));
                System.out.print("Ready queue: ");
                readyQueue.forEach(task -> System.out.print(task.getName() + " "));
                System.out.println();
                System.out.println("Running task: " + runningTask.getName());
                System.out.println();
                time++;
            }

        }
    }
}
