package student;

public interface Buffer {
    void put(Object object);
    void clear();
    int getSize();
    boolean isEmpty();
}
