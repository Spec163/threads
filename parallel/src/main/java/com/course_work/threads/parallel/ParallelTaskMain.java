package com.course_work.threads.parallel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParallelTaskMain {
  private static final int kByte = 1024;

  public static void main(String[] args) throws FileNotFoundException {
    final Scanner sc = new Scanner(new File("input.txt"));
    final String targetRepo;
    final long start;

    if (sc.hasNext()) {
      final File sourceRepo = new File(sc.next());

      if (sc.hasNext()) {
        targetRepo = sc.next();
      } else {
        throw new RuntimeException("Введите путь до целевого метста копирования!");
      }
      if (sourceRepo.exists() && !sourceRepo.isDirectory()) {
        throw new RuntimeException("В указанной директории отсутствуют файлы!");
      } else {

        start = System.currentTimeMillis();
        long folderSize = 0;
        try {

          ParallelTasks tasks = new ParallelTasks();
          for (File file : sourceRepo.listFiles()) {
            System.out.println(String.format("Размер файла %s: %s КБ", file.getName(), (file.length() / kByte)));
            folderSize += file.length();
            tasks.add(new CopyFileTask(file.getAbsolutePath(), targetRepo + file.getName()));
          }
          tasks.go();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
        System.out.println(String.format("\nОбщий объём скопированных файлов = %s КБ", (folderSize / kByte)));
        System.out.println(String
            .format("Время параллельного копирования файлов = %s мс", (System.currentTimeMillis() - start)));
      }
    } else {
      throw new RuntimeException("Введите путь до директории с файлами в input.txt");
    }
  }
}
