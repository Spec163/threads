package com.course_work.threads.simple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleCopyService {
  public static void copyFileUsingJava7Files(final String source, final String target) throws IOException {
    final File sourceDir = new File(source);
    for (File file: sourceDir.listFiles()) {
      Files.copy(file.toPath(), Paths.get(target + file.getName()), REPLACE_EXISTING);
    }
  }
}
