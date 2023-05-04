package student;

import java.util.List;


public class CleanerThread implements Runnable {

    private final MyBuffer buffer;

    public CleanerThread(MyBuffer buffer) {
        this.buffer = buffer;
    }

    private synchronized void consoleMessage(String message) {
        System.out.println(message);
    }

    private boolean isBufferNotEmptyOrGenerationNotFinished() {
        return !buffer.isGenerationFinished() || !buffer.isEmpty();
    }
    private boolean isBufferEmptyOrGenerationNotFinished() {
        return buffer.isEmpty() && !buffer.isGenerationFinished();
    }


    @Override
    public void run() {
        while (isBufferNotEmptyOrGenerationNotFinished()) {
            List<Integer> numbersOne;
//            synchronized (buffer) {
                numbersOne = buffer.getAllNumbersOne();
                if (numbersOne.isEmpty() && !buffer.isEmpty()) {
                    consoleMessage("Второй поток отработал. Буфер очищен");
                    buffer.clear();
                    buffer.showBufferConsole();
                } else if(!numbersOne.isEmpty()) {
                    consoleMessage("Второй поток отработал. Единицы извлечены. Кол-во единиц: " + numbersOne.size());
                    buffer.showBufferConsole();
                }
//                if (isBufferEmptyOrGenerationNotFinished()) {
//                    try {
//                        buffer.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }
    }
}



