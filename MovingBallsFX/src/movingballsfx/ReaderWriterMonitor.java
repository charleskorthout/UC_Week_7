package movingballsfx;

public class ReaderWriterMonitor implements RWMonitorInterface {

    /**
     * I try to avoid synchronizing on 'this' because that would allow everybody
     * from the outside who has a reference to the object to block synchronization.
     * Instead, I create a local, private, synchronization object
     */
    private final Object syncObject = new Object();
    private static ReaderWriterMonitor instance = null;

    private ReaderWriterMonitor() {

    }

    /**
     * Create a singleton for the ReaderWriterMonitor
     * @return a ReaderWriterMonitor instance
     */
    public static ReaderWriterMonitor getInstance() {
      if (instance == null) {
            instance = new ReaderWriterMonitor();
      }
      return instance;
    }

    @Override
    public void enterReader() throws InterruptedException {

    }

    @Override
    public void enterWriter() throws InterruptedException {
        synchronized (syncObject) {
            // do something thread-safe
        }
    }

    @Override
    public void exitReader() {

    }

    @Override
    public void exitWriter() {

    }
}
