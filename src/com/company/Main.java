package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            File file = new File("src/com/company/input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                tasks.add(new Task(splited[0], splited[1].charAt(0), Integer.parseInt(splited[2])));
            }
            fr.close();
            System.out.println("Tasks: ");
            tasks.forEach(task -> System.out.println(task.getName() + " " + task.getPriority() + " " + task.getBurstTime()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Choose the algorithm: ");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Round-Robin");
        System.out.println("4. HRRN");
        System.out.println("5. MLFQ");
        Scanner scanner = new Scanner(System.in);
        int algorithmNumber = scanner.nextInt();

        Scheduler scheduler = new Scheduler();
        switch (algorithmNumber) {
            case 1:
                scheduler.FCFS(tasks);
                break;
            case 2:
                scheduler.SJF(tasks);
                break;
            case 3:
                scheduler.RR(tasks, 2);
                break;
            case 4:
                scheduler.HRRN(tasks);
                break;
            case 5:
                scheduler.MLFQ(tasks);
                break;
            default:
                System.out.println("Algorithm doesn't exist");
        }
    }
}