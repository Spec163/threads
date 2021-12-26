//package com.course_work.threads.group;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//
//public class Copier extends Thread{
//
//  private File in, out;
//  private int lenght;
//  private long pos;
//
//  public Copier(ThreadGroup tg, int number, File in, File out, int pos, int lenght) {
//    super(tg, "Block " + number); // конструктор Thread, для группы потоков и имени потока
//    this.in = in;
//    this.out = out;
//    this.pos = pos;
//    this.lenght = lenght;
//  }
//
//  @Override
//  public void run() {
//    // создание автозакр. потоков на чтение и запись с произвольным доступом
//    try (RandomAccessFile input = new RandomAccessFile(in, "r")){ // читающий поток
//      try (RandomAccessFile output = new RandomAccessFile(out, "rw")){ // пишущий поток
//        byte[] buffer = new byte[lenght]; // массив по размеру блока
//        input.seek(pos); // установка позиции для чтения
//        input.read(buffer); // чтение в массив
//        output.seek(pos); // установка позиции для письма
//        output.write(buffer); // запись из массива
//      }
//    } catch (IOException exc) {
//      System.out.println(getName() + "error...");
//    }
//    System.out.println(getName() + " complete...");
//  }
//
//}