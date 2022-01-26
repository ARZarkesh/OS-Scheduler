package com.company;

import java.util.Comparator;
import java.util.LinkedList;

public class Scheduler {
    LinkedList<Task> readyQueue;

    public void FCFS(LinkedList<Task> tasks) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        while (!readyQueue.isEmpty()) {
            readyQueue.sort((o1, o2) -> o1.getPriorityValue() - o2.getPriorityValue());
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

    public void SJF(LinkedList<Task> tasks) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        readyQueue.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getBurstTime() - o2.getBurstTime() != 0) {
                    return o1.getBurstTime() - o2.getBurstTime();
                } else {
                    return o1.getPriorityValue() - o2.getPriorityValue();
                }
            }
        });

        while (!readyQueue.isEmpty()) {
            readyQueue.sort((o1, o2) -> o1.getPriorityValue() - o2.getPriorityValue());
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
