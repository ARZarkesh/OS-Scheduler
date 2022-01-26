package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            File file = new File("src/com/company/input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
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
//        Queue ready = new LinkedList();
//        Thread mainThread = new Thread() {
//            public void run() {
//                System.out.println("t1 running");
//            }
//        };
//        mainThread.start();

    }
}
