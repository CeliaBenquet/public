package ex6;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyIterator<T> implements Iterator<T> {
    private Element<T> current;

    public MyIterator(MyLinkedList<T> list) {
        this.current = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {

        T data = current.getData();
        current = current.getNext();
        return data;

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
