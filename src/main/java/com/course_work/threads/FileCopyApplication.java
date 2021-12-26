package com.course_work.threads;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.course_work.threads.channel.FileChannelService;
import com.course_work.threads.parallel.ParallelTaskService;
import com.course_work.threads.runnable.service.CopyFileService;
import com.course_work.threads.simple.SimpleCopyService;
import com.course_work.threads.stream.CopyStreamService;

public class FileCopyApplication {
  public static void main(String[] args) throws IOException {
    final Scanner sc = new Scanner(new File("input.txt"));
    if (sc.hasNext()) {
//      final String sourceRepo = "D:\\test\\from\\";
      final String sourceRepo = sc.next();
      final CopyFileService cf = new CopyFileService();
      long start = System.nanoTime();


      String targetRepo;

//    targetRepo = "D:\\test\\simple\\";
//    start = System.nanoTime();
//    SimpleCopyService.copyFileUsingJava7Files(sourceRepo, targetRepo);
//    System.out.println("Время копирования файла с помощью класса Files Java 7 = " + (System.nanoTime() - start));

      targetRepo = "D:\\test\\runnable\\";
      start = System.nanoTime();
      final File sourceDir = new File(sourceRepo);
      for (File file : sourceDir.listFiles()) {
        cf.copyFile(file, new File(targetRepo + file.getName()));
      }
      System.out.println("Время копирования файла с помощью реализации интерфейса Runnable = " + (System.nanoTime() - start));

//    targetRepo = "D:\\test\\channel\\";
//    start = System.nanoTime();
//    FileChannelService.copyFileUsingChannel(sourceRepo, targetRepo);
//    System.out.println("Время копирования файла с помощью File Channel = " + (System.nanoTime() - start));

//    target = new File("D:\\test\\parallel\\arch.zip");
//    start = System.nanoTime();
//    ParallelTaskService.parallelTasksService(source, target);
    } else {
      System.out.println("Введите путь до директории с файлами в input.txt");
    }
    sc.close();
  }
}
