package student;

public class Main {

    public static void main(String[] args) {
        MyBuffer buffer = new MyBuffer(Constants.SIZE_BUFFER);

        RandomNumberSequenceGeneratorThread randomNumberSequenceGeneratorThread = new RandomNumberSequenceGeneratorThread(
                buffer, Constants.NUMBER_ITERATIONS);
        CleanerThread extractingAllNumbersOneThread = new CleanerThread(buffer);

        Thread firstThread = new Thread(randomNumberSequenceGeneratorThread);
        Thread secondThread = new Thread(extractingAllNumbersOneThread);

        firstThread.start();
        secondThread.start();
    }
}
