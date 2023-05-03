package student;

import java.util.*;

public class MyBuffer {
    private final List<Integer> data;
    private final int size;
    private boolean isGenerationFinished;

    public MyBuffer(int size) {
        data = new ArrayList<>();
        this.size = size;
    }

    public synchronized boolean getIsGenerationFinished() {
        return isGenerationFinished;
    }

    public synchronized void setGenerationFinished(boolean generationFinished) {
        isGenerationFinished = generationFinished;
    }

    public synchronized void put(List<Integer> values) {
        for (int value : values) {
            if (data.size() >= size) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data.add(value);
        }
        notify();
  }


    public synchronized void clear() {
        data.clear();
        notify();
    }

    public synchronized boolean isEmpty() {
        return data.isEmpty();
    }

    public synchronized void showBufferConsole() {
        if (data.isEmpty()) {
            System.out.println("Буфер пуст");
        } else {
            System.out.print("Буфер: ");
            for (int value : data) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public List<Integer> getAllNumbersOne() {
        List<Integer> numbersOne = new ArrayList<>();
        List<Integer> toRemove = new ArrayList<>();
        synchronized (this) {
            for (int number : data) {
                if (number == 1) {
                    numbersOne.add(number);
                    toRemove.add(number);
                }
            }
            data.removeAll(toRemove);
            if (data.isEmpty()) {
                notify();
            }
        }
        return numbersOne;
    }

    public int getSize() {
        return size;
    }

}

