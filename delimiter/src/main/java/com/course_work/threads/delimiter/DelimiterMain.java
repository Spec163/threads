package com.course_work.threads.delimiter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DelimiterMain {
  private static final int kByte = 1024;

  public static void main(String[] args) throws IOException {
    final Scanner sc = new Scanner(new File("input.txt"));
    final String targetRepo;

    if (sc.hasNext()) {
      final File sourceRepo = new File(sc.next());
      if (sc.hasNext()) {
        targetRepo = sc.next();
      } else {
        throw new RuntimeException("Введите путь до целевого метста копирования!");
      }
      if (sourceRepo.exists() && !sourceRepo.isDirectory()) {
        throw new RuntimeException("В указанной директории отсутствуют файлы!");
      }
      CopyFileService copyFileService;
      long startTime;
      long startFullTime = System.currentTimeMillis();
      long folderSize = 0;
      for (final File file : sourceRepo.listFiles()) {
        startTime = System.currentTimeMillis();
        copyFileService = new CopyFileService();
        copyFileService.copyFile(file, new File(targetRepo + file.getName()));
        folderSize += file.length();
        System.out.println(String.format(
            "Время копирования файла %s с помощью деления файла на части = %s мс \t вес %s КБ",
            file.getName(),
            (System.currentTimeMillis() - startFullTime),
            (file.length() / kByte)));
      }
      System.out.println(String
          .format("\nОбщий объём скопированных файлов = %s КБ", (folderSize / kByte)));
      System.out.println(String
          .format(
              "Время копирования файлов с помощью FileChannel = %s мс",
              (System.currentTimeMillis() - startFullTime)));
    } else {
      throw new RuntimeException("Введите путь до директории с файлами в input.txt");
    }
  }
}
