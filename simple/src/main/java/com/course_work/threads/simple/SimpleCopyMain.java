package com.course_work.threads.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class SimpleCopyMain {
  private static void copyFileUsingStream(final File sourceRepo, final String target) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
      for (File file : Objects.requireNonNull(sourceRepo.listFiles())) {
        is = new FileInputStream(file);
        os = new FileOutputStream(target + file.getName());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
          os.write(buffer, 0, length);
        }
      }
    } finally {
      is.close();
      os.close();
    }
  }

  public static void main(String[] args) throws IOException {
    final Scanner sc = new Scanner(new File("input.txt"));
    final String targetRepo;

    if (sc.hasNext()) {
      final File sourceRepo = new File(sc.next());

      if (sc.hasNext()) {
        targetRepo = sc.next();
      } else {
        throw new RemoteException("Введите путь до целевого метста копирования!");
      }
      if (sourceRepo.exists() && !sourceRepo.isDirectory()) {
        throw new RemoteException("В указанной директории отсутствуют файлы!");
      }
      final long start = System.nanoTime();
      copyFileUsingStream(sourceRepo, targetRepo);
      System.out.println("Время копирования файлов в один поток = " + (System.nanoTime() - start));
    } else {
      throw new RemoteException("Введите путь до директории с файлами в input.txt");
    }
  }
}
