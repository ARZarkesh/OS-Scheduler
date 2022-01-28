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
                printInfo(runningTask);
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
                printInfo(runningTask);
                time++;
            }
        }
    }

    private void printInfo(Task runningTask) {
        System.out.print("Ready queue: " + Color.YELLOW);
        readyQueue.forEach(task -> System.out.print(task.getName() + " "));
        System.out.println(Color.RESET);
        System.out.println("Running task: " + Color.RED_BACKGROUND + runningTask.getName() + Color.RESET);
        System.out.println();
    }

    public int RR(LinkedList<Task> tasks, int quantum, int initTime) {
        readyQueue = tasks;
        int time = initTime;
        while (!readyQueue.isEmpty()) {
            Task runningTask = readyQueue.pop();
            if (runningTask.getRemainingTime() >= quantum) {
                runningTask.increaseConsumedTime(quantum);

                System.out.println("Time: " + time + " -> " + (time + quantum));
                printInfo(runningTask);
                if (runningTask.getRemainingTime() > 0) {
                    readyQueue.add(runningTask);
                }

                time += quantum;
            } else {
                System.out.println("Time: " + time + " -> " + (time + runningTask.getRemainingTime()));
                printInfo(runningTask);

                time += runningTask.getRemainingTime();
                runningTask.increaseConsumedTime(runningTask.getRemainingTime());
            }
        }

        return time;
    }

    public int RR(LinkedList<Task> tasks, int quantum) {
        return RR(tasks, quantum, 0);
    }

    public void HRRN(LinkedList<Task> tasks) {
        readyQueue = new LinkedList<>(tasks);
        int time = 0;
        sortHRRNReadyQueue(readyQueue, time);

        while (!readyQueue.isEmpty()) {
            Task runningTask = readyQueue.pop();
            System.out.println("Time: " + time + " -> " + (time + runningTask.getRemainingTime()));
            printInfo(runningTask);

            time += runningTask.getRemainingTime();
            runningTask.increaseConsumedTime(runningTask.getRemainingTime());
            sortHRRNReadyQueue(readyQueue, time);
        }
    }

    public void MLFQ(LinkedList<Task> tasks) {
        LinkedList<Task> queue1 = new LinkedList<>(tasks);
        queue1.removeIf(task -> task.getPriority() != 'Z');
        LinkedList<Task> queue2 = new LinkedList<>(tasks);
        queue2.removeIf(task -> task.getPriority() != 'Y');
        LinkedList<Task> queue3 = new LinkedList<>(tasks);
        queue3.removeIf(task -> task.getPriority() != 'X');

        int time = 0;

        while (!queue1.isEmpty()) {
            System.out.println(Color.RED + "Queue Level 1: " + Color.RESET);
            time = RR(queue1, 2, time);
        }

        while (!queue2.isEmpty()) {
            System.out.println(Color.RED + "Queue Level 2: " + Color.RESET);
            time = RR(queue2, 4, time);
        }

        while (!queue3.isEmpty()) {
            System.out.println(Color.RED + "Queue Level 3: " + Color.RESET);
            time = RR(queue3, 8, time);
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
