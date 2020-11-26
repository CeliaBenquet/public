package ex6;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<T> implements Iterable<T> {

    private Element<T> head;
    private Element<T> tail;

    public MyLinkedList(){
    }

    public void add(T data){
        Element<T> newElement = new Element<>(data);
        if(head == null){
            head = newElement;
        }
        else{
            tail.setNext(newElement);
        }
        tail = newElement;
    }

    public Element<T> getHead(){
        return head;
    }

    public Element<T> getTail(){
        return tail;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator<T>(this);
    }

}
