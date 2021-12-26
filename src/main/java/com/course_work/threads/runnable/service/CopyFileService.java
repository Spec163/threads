package com.course_work.threads.runnable.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import com.course_work.threads.runnable.models.FileThread;

public class CopyFileService {
  public static final int BETA = 20 * 1024 * 1024;
  public static int count = 0;

  public void createFile(File file, int size) {
    FileOutputStream fs;
    try {
      fs = new FileOutputStream(file);
//			for (int i = 0; i < n + 1; i++) {
      byte[] buffer = new byte[size];
      fs.write(buffer);
//			}
      try {
        fs.close();
      } catch (IOException e) {
        System.out.println("Не удалось создать файл");
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Длина:\t" + file.length());
  }

  public int readFile(final File oldFile, final File newPath, final int startIndex, final int len) {
    int byteLen = 0;
    FileInputStream fin = null;
//		FileOutputStream fos = null;
    RandomAccessFile fos = null;
    try {
      fin = new FileInputStream(oldFile);
      // Параметр rw означает открыть файл в режиме чтения-записи
      fos = new RandomAccessFile(newPath, "rw");
      byte[] buff = new byte[20 * 1024 * 1024];
      fin.skip(startIndex);
      fos.seek(startIndex);
      int currentSumLen = 0;
      while (((byteLen = fin.read(buff)) > 0) && currentSumLen < len) {
        // От поля startIndex до endIndex
        fos.write(buff, 0, len);
        currentSumLen += byteLen;
      }
//      System.out.println("Оставшиейся символы:\t" + (byteLen + 1));
      fos.close();
      fin.close();
    } catch (Exception e) {
//      System.out.println("Ошибка копирования файлов!\t" + len + " - " + byteLen);
      e.printStackTrace();
    }
    count++;
//    System.out.println("Пройденный диапазон:\t" + startIndex + "---" + len);
    return count;
  }

  public void copyFile(final File oldFile, final File newPath) {
    int n = Runtime.getRuntime().availableProcessors();
    if (oldFile.exists()) {
      int fileSize = (int) oldFile.length(); // Единица измерения kb
      System.out.println("Размер:\t" + fileSize);
      System.out.println("β * n:\t" + BETA * n);
      // Создать новый файл под целевым путем
      this.createFile(newPath, fileSize);
      n = (fileSize - 1) / BETA + 1; // Запустить n по  токов
      System.out.println("Кол-во потоков:\t" + n);
      FileThread fthread = null;
      int tmp = 0;
      for (int i = 0; i < n; i++) {
        if (i == (n - 1))
          tmp = fileSize - i * BETA;
        else
          tmp = BETA;
        fthread = new FileThread(oldFile, newPath, i * BETA, tmp, n);
        new Thread(fthread).start();
      }
    } else {
      System.out.println(oldFile + " не существует!");
    }
  }
}
