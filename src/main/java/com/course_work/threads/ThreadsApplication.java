package com.course_work.threads;

import com.course_work.threads.service.CopyFileService;

public class ThreadsApplication {
  public static void main(String[] args) {
    long time = System.currentTimeMillis();
    System.out.println("Initial time:\t" + time);

    CopyFileService cf = new CopyFileService();
//		System.out.println(cf.move());
    cf.copyFile("D:\\test.txt", "D:\\test\\test.txt"); // Тест
//    cf.copyFile("D:\\arch.zip", "D:\\test\\arch.zip"); // Тест
    System.out.println("End time:\t" + System.currentTimeMillis());
    System.out.println("Время выполнения:\t" + (System.currentTimeMillis() - time));
  }
}
