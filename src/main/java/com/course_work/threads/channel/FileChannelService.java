package com.course_work.threads.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelService {
  public static void copyFileUsingChannel(final String source, final String target) throws IOException {
    FileChannel sourceChannel = null;
    FileChannel destChannel = null;

    final File sourceDir = new File(source);
    try {
      for (File file : sourceDir.listFiles()) {
        sourceChannel = new FileInputStream(file).getChannel();
        destChannel = new FileOutputStream(new File(target + file.getName())).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
      }
    } finally {
      sourceChannel.close();
      destChannel.close();
    }
  }
}
