package com.company;

import java.util.Comparator;
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

    public void RR(LinkedList<Task> tasks, int quantum) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        while (!readyQueue.isEmpty()) {
            Task runningTask = readyQueue.pop();
            if (runningTask.getRemainingTime() >= quantum) {
                runningTask.increaseConsumedTime(quantum);

                System.out.println("Time: " + time + " -> " + (time + quantum));
                System.out.print("Ready queue: ");
                readyQueue.forEach(task -> System.out.print(task.getName() + " "));
                System.out.println();
                System.out.println("Running task: " + runningTask.getName());
                System.out.println();
                if (runningTask.getRemainingTime() > 0) {
                    readyQueue.add(runningTask);
                }

                time += quantum;
            } else {
                System.out.println("Time: " + time + " -> " + (time + runningTask.getRemainingTime()));
                System.out.print("Ready queue: ");
                readyQueue.forEach(task -> System.out.print(task.getName() + " "));
                System.out.println();
                System.out.println("Running task: " + runningTask.getName());
                System.out.println();

                time += runningTask.getRemainingTime();
                runningTask.increaseConsumedTime(runningTask.getRemainingTime());
            }
        }

    }

    public void HRRN(LinkedList<Task> tasks) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        sortHRRNReadyQueue(readyQueue, time);

        while (!readyQueue.isEmpty()) {
            Task runningTask = readyQueue.pop();
            System.out.println("Time: " + time + " -> " + (time + runningTask.getRemainingTime()));
            System.out.print("Ready queue: ");
            readyQueue.forEach(task -> System.out.print(task.getName() + " "));
            System.out.println();
            System.out.println("Running task: " + runningTask.getName());
            System.out.println();

            time+= runningTask.getRemainingTime();
            runningTask.increaseConsumedTime(runningTask.getRemainingTime());
            sortHRRNReadyQueue(readyQueue, time);
        }
    }

    private float getHRRNRatio(Task task, int time) {
        float waitingTime = time - task.getConsumedTime();
        return (waitingTime + task.getRemainingTime()) / task.getRemainingTime();
    }

    private void sortHRRNReadyQueue(LinkedList<Task> readyQueue, int time) {
        readyQueue.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                float subtract = getHRRNRatio(o2, time) - getHRRNRatio(o1, time);
                if (subtract > 0) return 1;
                else if (subtract < 0) return -1;
                else {
                    return o1.getPriorityValue() - o2.getPriorityValue();
                }
            }
        });
    }
}
