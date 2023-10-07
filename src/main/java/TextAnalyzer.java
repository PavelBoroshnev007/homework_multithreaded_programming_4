import java.util.concurrent.ArrayBlockingQueue;

public class TextAnalyzer implements Runnable {
    private ArrayBlockingQueue<String> queue;
    private char targetChar;

    public TextAnalyzer(ArrayBlockingQueue<String> queue, char targetChar) {
        this.queue = queue;
        this.targetChar = targetChar;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String text = queue.take();
                int maxCharCount = countMaxChar(text, targetChar);
                System.out.println("Максимальное количество символа " + targetChar + " в тексте: " + maxCharCount);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private int countMaxChar(String text, char targetChar) {
        int maxCharCount = 0;
        int currentCount = 0;

        for (char c : text.toCharArray()) {
            if (c == targetChar) {
                currentCount++;
            } else {
                if (currentCount > maxCharCount) {
                    maxCharCount = currentCount;
                }
                currentCount = 0;
            }
        }

        return Math.max(maxCharCount, currentCount);
    }
}
