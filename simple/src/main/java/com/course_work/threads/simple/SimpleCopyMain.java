package com.course_work.threads.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Scanner;

public class SimpleCopyMain {
  private static final int kByte = 1024;

  private static void copyFileUsingStream(final File sourceRepo, final String target) throws IOException {
    final long start = System.currentTimeMillis();
    InputStream is = null;
    OutputStream os = null;
    long folderSize = 0;
    try {
      for (File file : Objects.requireNonNull(sourceRepo.listFiles())) {
        is = new FileInputStream(file);
        os = new FileOutputStream(target + file.getName());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
          os.write(buffer, 0, length);
        }
        System.out.println(String.format("Размер файла %s: %s КБ", file.getName(), (file.length() / kByte)));
        folderSize += file.length();
      }
    } finally {
      is.close();
      os.close();
    }
    System.out.println(String.format("\nОбщий объём скопированных файлов = %s КБ", (folderSize / kByte)));
    System.out.println(String.format(
            "Время копирования файлов с помощью FileChannel = %s мс",
            (System.currentTimeMillis() - start)));
  }

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
      copyFileUsingStream(sourceRepo, targetRepo);
    } else {
      throw new RuntimeException("Введите путь до директории с файлами в input.txt");
    }
  }
}
