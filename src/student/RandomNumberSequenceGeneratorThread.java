package student;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberSequenceGeneratorThread implements Runnable {
    private final MyBuffer buffer;
    private final int numberIterations;

    public RandomNumberSequenceGeneratorThread(MyBuffer buffer, int numberIterations) {
        this.buffer = buffer;
        this.numberIterations = numberIterations;
    }

    @Override
    public void run() {
        String message = "";
        buffer.setGenerationFinished(false);
        for (int i = 0; i < numberIterations; i++) {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < buffer.getSize(); j++) {
                int number = (int) Math.round(Math.random());
                numbers.add(number);
            }
            synchronized (buffer) {
                buffer.put(numbers);
                int iteration = i + 1;
                message = "Итерация " + iteration + ". Первый поток отработал! В буфер добавлена последовательность: " + numbers.toString();
                consoleMessage(message);
                buffer.showBufferConsole();
            }
        }
        synchronized (buffer) {
            buffer.setGenerationFinished(true);
        }
    }

    private void consoleMessage(String message) {
        System.out.println(message);
    }
}

