import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    private static final int QUEUE_CAPACITY = 100;
    private static ArrayBlockingQueue<String> queueA = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static ArrayBlockingQueue<String> queueB = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static ArrayBlockingQueue<String> queueC = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main (String[]args){
        Thread generatorThread = new Thread(new TextGenerator(queueA, queueB, queueC));
        Thread analyzerThreadA = new Thread(new TextAnalyzer(queueA, 'a'));
        Thread analyzerThreadB = new Thread(new TextAnalyzer(queueB, 'b'));
        Thread analyzerThreadC = new Thread(new TextAnalyzer(queueC, 'c'));

        generatorThread.start();
        analyzerThreadA.start();
        analyzerThreadB.start();
        analyzerThreadC.start();

        try {
            generatorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        analyzerThreadA.interrupt();
        analyzerThreadB.interrupt();
        analyzerThreadC.interrupt();
    }
}

