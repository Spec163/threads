package com.course_work.threads.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import com.course_work.threads.models.FileThread;

public class CopyFileService {
  // параметр β = 20mb, который является константой, единица измерения kb
  public static final int BETA = 20 * 1024 * 1024;
  public static int count = 0;


  public void deleteFile(String filePath) {
    File file = new File(filePath);
    file.delete();
  }

  // Создаем новый файл, размер - размер, единица измерения - КБ
  public void createFile(String filePath, int size) {
    FileOutputStream fs;
    try {
      fs = new FileOutputStream(filePath);
//			for (int i = 0; i < n + 1; i++) {
      byte[] buffer = new byte[size]; // Создать файл размера
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
    File file = new File(filePath);
    System.out.println("Длина:\t" + file.length());
  }

  public int readFile(final String oldPath, final String newPath, final int startIndex, final int len) {
    int byteLen = 0;
    File oldFile = new File(oldPath);
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
      System.out.println("Оставшиейся символы:\t" + (byteLen + 1));
      fos.close();
      fin.close();
    } catch (Exception e) {
      System.out.println("Ошибка копирования файлов!\t" + len + " - " + byteLen);
      e.printStackTrace();
    }
    count++;
    System.out.println("Пройденный диапазон:\t" + startIndex + "---" + len);
    return count;
  }

  public void copyFile(String oldPath, String newPath) {
    File oldFile = new File(oldPath);
    // Количество созданных потоков, хотя бы один поток создан для записи в файл
    int n = 1;
    if (oldFile.exists()) {
      int fileSize = (int) oldFile.length(); // Единица измерения kb
      System.out.println("Размер:\t" + fileSize);
      System.out.println("β * n:\t" + BETA * n);
      // Создать новый файл под целевым путем
      this.createFile(newPath, fileSize);
      n = (fileSize - 1) / BETA + 1; // Запустить n потоков
      System.out.println("Кол-во потоков:\t" + n);
      FileThread fthread = null;
      int tmp = 0;
      for (int i = 0; i < n; i++) {
        if (i == (n - 1))
          tmp = fileSize - i * BETA;
        else
          tmp = BETA;
        // Начать запись с i * β и записать байты tmp
        fthread = new FileThread(oldPath, newPath, i * BETA, tmp, n);
        // Запустить поток
        new Thread(fthread).start();
      }
    } else {
      System.out.println(oldPath + " не существует!");
    }
  }

  public boolean move() {
    boolean flag = false;
    File oldFile = new File("D:\\123.txt");
    String newName = "D: \\ \\123-copy.txt";
    File newFile = new File(newName);
    flag = oldFile.renameTo(newFile);
    return flag;
  }
}
