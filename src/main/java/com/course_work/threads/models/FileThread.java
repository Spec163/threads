package com.course_work.threads.models;

import com.course_work.threads.service.CopyFileService;

public class FileThread implements Runnable {

  private String oldPath = null;
  private String newPath = null;
  private int startIndex = 0;
  private int len = 0;
  private int count = 0;

  public FileThread(
      final String oldPath,
      final String newPath,
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
//    if (count == tmpCount) { // копирование временно отключено
//      cf.deleteFile(oldPath);
//    }
  }

}