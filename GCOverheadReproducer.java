import java.util.LinkedList;

public class GCOverheadReproducer {

  private static final int BLOCK_SIZE = 128 * 1024;

  private static final LinkedList fixedData = new LinkedList();

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Consuming all memory");
    while (true) {
      try {
        fixedData.add(new byte[BLOCK_SIZE]);
      } catch (OutOfMemoryError oome) {
        System.out.println(oome);
        System.out.printf("OOME triggered. Releasing %s bytes of memory.\n", BLOCK_SIZE);
        fixedData.removeLast();
        break;
      }
    }
    System.out.println("Running allocate-release loop");
    while (true) {
      Object temporaryData = new byte[64*1024-1];
    }
  }

}
