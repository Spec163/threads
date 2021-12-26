package com.course_work.threads;

import java.io.File;
import java.io.IOException;
import com.course_work.threads.runnable.service.CopyFileService;
import com.course_work.threads.simple.SimpleCopyService;
import com.course_work.threads.stream.CopyStreamService;

public class FileCopyApplication {
  public static void main(String[] args) throws IOException {
    final CopyFileService cf = new CopyFileService();
    long start = System.nanoTime();
    final String initialRepo = "D:\\arch.zip";
    final File source = new File(initialRepo);
    File target;

    target = new File("D:\\test\\simple\\arch.zip");
    start = System.nanoTime();
    SimpleCopyService.copyFileUsingJava7Files(source, target);
    System.out.println("Время копирования файла с помощью класса Files Java 7 = " + (System.nanoTime() - start));


    target = new File("D:\\test\\stream\\arch.zip");
    start = System.nanoTime();
    CopyStreamService.copyFileUsingStream(source, target);
    System.out.println("Время копирования файла с помощью stream copy = " + (System.nanoTime() - start));


    target = new File("D:\\test\\guava\\arch.zip");
    com.google.common.io.Files.copy(source, target);
    System.out.println("Время копирования файла с помощью guava = " + (System.nanoTime() - start));


    target = new File("D:\\test\\runnable\\arch.zip");
    start = System.nanoTime();
    cf.copyFile(source, target);
    System.out.println("Время копирования файла с помощью реализации интерфейса Runnable = " + (System.nanoTime() - start));
  }
}
