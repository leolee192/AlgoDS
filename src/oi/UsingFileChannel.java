package oi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UsingFileChannel {
  public static void main(String[] args) throws IOException {
    StopWatch.start();

    long lineNum = 0;

    try (RandomAccessFile raf = new RandomAccessFile(DumpDataWriter.input1000MB, "rw");) {
      FileChannel channel = raf.getChannel();

      int bufferSize = 65536;
      ByteBuffer buff = ByteBuffer.allocate(bufferSize);

      int n;
      while ((n = channel.read(buff)) > 0) {
        buff.flip();

        for (int i=0; i<n; ++i) {
          if (buff.get() == '\n') {
            lineNum++;
          }
        }

        buff.clear();
      }
    }



    long duration4 = StopWatch.stop();
    System.out.println(duration4);
    System.out.println(lineNum + " lines");
  }
}
