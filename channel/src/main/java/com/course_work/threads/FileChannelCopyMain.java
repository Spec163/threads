package com.course_work.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class FileChannelCopyMain {
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
      fileChannelCopy(sourceRepo, targetRepo);
    } else {
      throw new RuntimeException("Введите путь до директории с файлами в input.txt");
    }
  }

  private static void fileChannelCopy(final File sourceRepo, final String targetRepo) throws IOException {
    long start = System.currentTimeMillis();
    FileChannel sourceChannel = null;
    FileChannel destChannel = null;
    long folderSize = 0;
    try {
      for (File file : sourceRepo.listFiles()) {
        sourceChannel = new FileInputStream(file).getChannel();
        destChannel = new FileOutputStream(new File(targetRepo + file.getName())).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        System.out.println(String.format("Размер файла %s: %s КБ", file.getName(), (file.length() / kByte)));
        folderSize += file.length();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      sourceChannel.close();
      destChannel.close();
    }
    System.out.println(String
        .format("\nОбщий объём скопированных файлов = %s КБ", (folderSize / kByte)));
    System.out.println(String
        .format(
            "Время копирования файлов с помощью FileChannel = %s мс",
            (System.currentTimeMillis() - start)));
  }
}
