package com.course_work.threads.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class CopyStreamService {
  public static void copyFileUsingStream(final String source, final String target) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
      final File sourceRepo = new File(source);
      for (File file : Objects.requireNonNull(sourceRepo.listFiles())) {
        is = new FileInputStream(file);
        os = new FileOutputStream(target + file.getName());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
          os.write(buffer, 0, length);
        }
      }
    } finally {
      is.close();
      os.close();
    }
  }
}
