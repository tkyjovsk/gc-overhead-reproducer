import java.util.LinkedList;

public class GCOverheadReproducer {

  private static final LinkedList fixedData = new LinkedList();

  private static final int FIXED_DATA_ITEM_SIZE = 128 * 1024;
  private static final int TEMPORARY_DATA_ITEM_SIZE = FIXED_DATA_ITEM_SIZE / 2 - 1;

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Consuming all memory");
    while (true) {
      try {
        fixedData.add(new byte[FIXED_DATA_ITEM_SIZE]);
      } catch (OutOfMemoryError oome) {
        System.out.println(oome);
        System.out.printf("OOME triggered. Releasing %s bytes of memory.\n", FIXED_DATA_ITEM_SIZE);
        fixedData.removeLast();
        break;
      }
    }
    System.out.println("Running allocate-release loop");
    while (true) {
      Object temporaryData = new byte[TEMPORARY_DATA_ITEM_SIZE];
    }
  }

}
