package com.course_work.threads.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceMain {
  private static final int kByte = 1024;

  private static void processDirectory(final File sourceRepo, final String targetRepo) {
    final ExecutorService service = Executors.newFixedThreadPool(10);
    long start = System.currentTimeMillis();
    long folderSize = 0;
    for (final File file : sourceRepo.listFiles()) {
      service.execute(new Runnable() {
        @Override
        public void run() {
          try {
            FileChannel sourceChannel = new FileInputStream(file).getChannel();
            FileChannel targetChannel = new FileOutputStream(new File(targetRepo + file.getName())).getChannel();
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
      System.out.println(String.format("Размер файла %s: %s КБ", file.getName(), (file.length() / kByte)));
      folderSize += file.length();
    }
    service.shutdown();
    try { // ожидание выполнения потоков 3 минуты
      service.awaitTermination(3, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(String
        .format("\nОбщий объём скопированных файлов = %s КБ", (folderSize / kByte)));
    System.out.println(String
        .format(
            "Время копирования файлов с помощью FileChannel = %s мс",
            (System.currentTimeMillis() - start)));
  }
}
