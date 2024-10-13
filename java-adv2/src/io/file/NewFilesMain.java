package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {

  public static void main(String[] args) throws IOException {
    Path file = Path.of("temp/example.txt");
    Path directory = Path.of("temp/exampleDir");

    System.out.println("File exists: " + Files.exists(file));

    try{
      Files.createFile(file);
      System.out.println("File Created");
    }catch (FileAlreadyExistsException e){
      System.out.println(file + " File already exists");
    }catch (IOException e){
      throw new RuntimeException(e);
    }

    try{
      Files.createDirectory(directory);
    }catch (FileAlreadyExistsException e){
      System.out.println(directory + " Directory already exists");
    }

//    Files.delete(file);
//    System.out.println("File deleted");

    System.out.println("Is regular file:" + Files.isRegularFile(file));
    System.out.println("Is Directory: " + Files.isDirectory(directory));
    System.out.println("File size: " + Files.size(file) + " bytes");

    final Path newFile = Path.of("temp/newExample.txt");
    Files.move(file,newFile, StandardCopyOption.REPLACE_EXISTING);
    System.out.println("File moved/renamed");

    System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

    final BasicFileAttributes attrs = Files.readAttributes(newFile,
        BasicFileAttributes.class);
    System.out.println("===== Attiributes =====");
    System.out.println("Creation time: " + attrs.creationTime());
    System.out.println("Is directory: " + attrs.isDirectory());
    System.out.println("Is regular file: " + attrs.isRegularFile());
    System.out.println("Is sysmbolic line: " + attrs.isSymbolicLink());
  }

}
