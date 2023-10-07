import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class TextGenerator implements Runnable {
    private static final String LETTERS = "abc";
    private static final int TEXT_LENGTH = 100000;

    private ArrayBlockingQueue<String> queueA;
    private ArrayBlockingQueue<String> queueB;
    private ArrayBlockingQueue<String> queueC;

    public TextGenerator(ArrayBlockingQueue<String> queueA, ArrayBlockingQueue<String> queueB, ArrayBlockingQueue<String> queueC) {
        this.queueA = queueA;
        this.queueB = queueB;
        this.queueC = queueC;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            for (int i = 0; i < 10000; i++) {
                StringBuilder text = new StringBuilder();
                for (int j = 0; j < TEXT_LENGTH; j++) {
                    text.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
                }

                char maxChar = findMaxChar(text.toString());
                switch (maxChar) {
                    case 'a':
                        queueA.put(text.toString());
                        break;
                    case 'b':
                        queueB.put(text.toString());
                        break;
                    case 'c':
                        queueC.put(text.toString());
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char findMaxChar(String text) {
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        for (char c : text.toCharArray()) {
            if (c == 'a') {
                aCount++;
            } else if (c == 'b') {
                bCount++;
            } else if (c == 'c') {
                cCount++;
            }
        }

        if (aCount >= bCount && aCount >= cCount) {
            return 'a';
        } else if (bCount >= aCount && bCount >= cCount) {
            return 'b';
        } else {
            return 'c';
        }
    }
}
