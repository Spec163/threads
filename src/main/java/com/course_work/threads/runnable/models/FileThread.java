package com.course_work.threads.runnable.models;

import java.io.File;
import com.course_work.threads.runnable.service.CopyFileService;

public class FileThread implements Runnable {

  private File oldPath = null;
  private File newPath = null;
  private int startIndex = 0;
  private int len = 0;
  private int count = 0;

  public FileThread(
      final File oldPath,
      final File newPath,
      final int startIndex,
      final int len,
      final int count
  ) {
    this.oldPath = oldPath;
    this.newPath = newPath;
    this.startIndex = startIndex;
    this.len = len;
    this.count = count;
  }

  public void run() {
    final CopyFileService cf = new CopyFileService();
    final int tmpCount = cf.readFile(oldPath, newPath, startIndex, len);
//    if (count == tmpCount) { // удаление временно отключено
//      cf.deleteFile(oldPath);
//    }
  }

}