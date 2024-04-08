
package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E>{

    private Object [] itemArray;
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;
    public QueueImplementation() throws QueueAllocationException{
        // call the constructor with size parameter with default size of 10
        this(DEFAULT_QUEUE_SIZE);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity<2){
            throw  new QueueAllocationException("Capacity must greater than 2");
        }
        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
            //To initialize the data, the index of head and tail is 0 and the value of size is 0
            head = 0;
            tail = 0;
            size = 0;
        } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Failed to allocate memory for the stack.");
        }
    }


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null){
            throw new NullPointerException("The element to enqueue can't be null");
        }
        if (size >= capacity){
            try {
                int newCapacity = 2 * capacity;
                Object [] newArray = new Object[newCapacity];
                int i = 0;
                while (i<size){
                    if (head+i<capacity){
                        newArray[i] = itemArray[head+i];
                    }else {
                        newArray[i] = itemArray[i-(capacity-head)];
                    }
                    i++;
                }
                itemArray = newArray;
                capacity = newCapacity;
                head = 0;
                tail = size;
            } catch (OutOfMemoryError e) {
                throw new QueueAllocationException("Failed to allocate more room for the stack.");
            }
        }
        itemArray[tail] = element;
        if (tail == capacity-1){
            tail = 0;
        }else {
            tail = tail+1;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("The queue is empty!");
        }
        Object dequeueElement = itemArray[head];
        itemArray[head] = null;
        if (head == capacity - 1){
            head = 0;
        }else {
            head = head+1;
        }
        size--;
        return (E) dequeueElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("The queue is empty!");
        }
        Object element = itemArray[head];
        return (E) element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (head == tail && size != capacity){
            return true;
        }
        else return false;
    }

    @Override
    public void clear() {
        for (int i=0;i <capacity;i++){
            itemArray[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int i = 0;
        while (i<size){
            if (head+i<capacity){
                builder.append(itemArray[head+i].toString());
            }else {
                builder.append(itemArray[i-(capacity-head)].toString());
            }
            if (i < size-1) {
                builder.append(", ");
            }
            i++;
        }
        builder.append("]");
        return builder.toString();
    }
}
