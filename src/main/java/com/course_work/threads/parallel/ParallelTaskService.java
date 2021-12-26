package com.course_work.threads.parallel;

import java.io.File;

public class ParallelTaskService {
//  public static void parallelTasksService(final File source, final File target) {
//    long start = System.nanoTime();
//    try {
//      ParallelTasks tasks = new ParallelTasks();
//      for (File file : source.listFiles()) {
//        tasks.add(new CopyFileTask(source, target));
//      }
//      tasks.go();
//    } catch (Exception e) {
//      System.err.println(e.getMessage());
//    }
//
//    System.out.println("Время копирования файла с помощью класса параллельного копирвоания = " + (System.nanoTime() - start));
//  }

  public static void main(String[] args) {
    long start = System.nanoTime();
    try {
      ParallelTasks tasks = new ParallelTasks();
      File folder = new File("D:\\test\\from\\");
      for (File file : folder.listFiles()) {
        tasks.add(new CopyFileTask(file.getAbsolutePath(), "D:\\test\\parallel\\" + file.getName()));
      }
      tasks.go();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    System.out.println("Время копирования файла с помощью класса параллельного копирвоания = " + (System.nanoTime() - start));
  }
}
