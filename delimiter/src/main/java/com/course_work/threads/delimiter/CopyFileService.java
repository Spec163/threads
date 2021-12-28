package com.course_work.threads.delimiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CopyFileService {
  public static final int BETA = 20 * 1024 * 1024;

  public void createFile(final File file, final int size) {
    FileOutputStream fs;
    try {
      fs = new FileOutputStream(file);
      byte[] buffer = new byte[size];
      fs.write(buffer);
      try {
        fs.close();
      } catch (IOException e) {
        System.out.println("Не удалось создать файл");
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void readFile(final File oldFile, final File newPath, final int startIndex, final int len) {
    int byteLen = 0;
    FileInputStream fin = null;
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
        fos.write(buff, 0, len);
        currentSumLen += byteLen;
      }
      fos.close();
      fin.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void copyFile(final File oldFile, final File newPath) {
    int n = Runtime.getRuntime().availableProcessors();
    if (oldFile.exists()) {
      int fileSize = (int) oldFile.length();
      this.createFile(newPath, fileSize);
      n = (fileSize - 1) / BETA + 1;
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
