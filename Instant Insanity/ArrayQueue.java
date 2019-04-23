import java.util.ArrayList;

public class ArrayQueue<E> implements Queue<E> {

    private static final int MAX_QUEUE_SIZE = 10000;

    private ArrayList<E> arrayList;

    @SuppressWarnings( "unchecked" )

    public ArrayQueue() {
        arrayList = new ArrayList<>();
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.size() == 0;
    }

    public boolean isFull() {
        return arrayList.size() == MAX_QUEUE_SIZE;
    }

    public void enqueue( E elem ) {
        arrayList.add(elem);
    }

    public E dequeue() {
        return arrayList.remove(0);
    }

}