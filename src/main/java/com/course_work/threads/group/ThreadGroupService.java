//package com.course_work.threads.group;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class ThreadGroupService {
//  public static void threadGroupCopy(final File filein, final File fileout) {
//    int blocksize = 10, infsize = 0;
//    // проверка наличия файла, объема информации
//    try (FileInputStream fstream = new FileInputStream(filein)) {
//      infsize = fstream.available();
//    } catch (IOException exc) {
//      exc.printStackTrace();
//      return;
//    }
//
//    ThreadGroup tg = new ThreadGroup("Copiers"); // создание группы потоков
//    int tnum = 0, pos = 0;
//    // запуск потоков
//    while (true){
//      if ((pos + blocksize) < infsize) // определение величины блока
//        new Copier(tg, tnum++, filein, fileout, pos, blocksize).start(); // в полный блок
//      else{
//        new Copier(tg, tnum++, filein, fileout, pos, infsize - pos).start(); // в остаток
//        break;
//      }
//      pos += blocksize;
//    }
//
//    while (tg.activeCount() > 0); // ожидание завершения потоков группы
//
//  }
//}
