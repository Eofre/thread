package student;

import java.util.*;

public class MyBuffer implements Buffer {
    private final List<Integer> data;
    private final int size;
    private boolean isGenerationFinished;

    public MyBuffer(int size) {
        data = new ArrayList<>();
        this.size = size;
    }

    @Override
    public synchronized void put(Object value) {
        if (data.size() >= size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data.add((Integer) value);
        notify();
    }

    @Override
    public synchronized void clear() {
        data.clear();
        notify();
    }

    @Override
    public synchronized boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return size;
    }

    public boolean isGenerationFinished() {
        return isGenerationFinished;
    }

    public synchronized void setGenerationFinished(boolean generationFinished) {
        isGenerationFinished = generationFinished;
        notify();
    }

    public synchronized void addValues(List<Integer> values) {
        for (int value : values) {
            put(value);
        }
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

    public synchronized List<Integer> getAllNumbersOne() {
        List<Integer> numbersOne = new ArrayList<>();
        List<Integer> toRemove = new ArrayList<>();

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

        return numbersOne;
    }
}

