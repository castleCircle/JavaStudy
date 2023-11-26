package multithread.allbyte;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class FileRead implements Callable<byte[]> {

  long start;
  int chunks;
  File file;

  public FileRead(
      long start,
      int chunks,
      File file
  ){
    this.start = start;
    this.chunks = chunks;
    this.file = file;
  }

  @Override
  public byte[] call() throws Exception {

    try(RandomAccessFile raf = new RandomAccessFile(file,"r")){
      byte[] data= new byte[chunks];
      raf.seek(start);
      raf.read(data);
      return data;
    }

  }
}
