package multithread.allbyte;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  public static void main(String[] args) {

    final var filePath = "/Users/yoon/Downloads/a.txt";
    final var numThreads = 4;
    final var file = new File(filePath);
    final var fileSize = file.length();
    final var chunkSize = fileSize / numThreads;

    //https://www.baeldung.com/java-executors-cached-fixed-threadpool
    final var executorService = Executors.newFixedThreadPool(numThreads);

    final List<Callable<byte[]>> tasks = new ArrayList<>();

    for(int i=0;i<numThreads-1;i++){
      tasks.add(new FileRead(i*chunkSize, (int) chunkSize,file));
    }

    //나머지
    tasks.add(new FileRead((numThreads -1 )* chunkSize,(int)(fileSize - chunkSize * (numThreads - 1)),file));

    try{
      final List<Future<byte[]>> futures = executorService.invokeAll(tasks);
      int totalSize = (int) fileSize;
      byte[] combinedResult = new byte[totalSize];
      int currentIndex = 0;

      for (Future<byte[]> result : futures) {
        byte[] data = result.get();
        System.arraycopy(data, 0, combinedResult, currentIndex, data.length);
        currentIndex += data.length;
      }

      // Do something with the combined result
      System.out.println(new String(combinedResult, "UTF-8"));
    } catch (InterruptedException | ExecutionException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }


  }

}
