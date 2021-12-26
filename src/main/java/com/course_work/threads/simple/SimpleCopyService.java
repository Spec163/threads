package com.course_work.threads.simple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleCopyService {
  public static void copyFileUsingJava7Files(final File source, final File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath(), REPLACE_EXISTING);
  }
}
