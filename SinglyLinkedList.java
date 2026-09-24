import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (head == null || head == tail) {
            return;
        }

        // 1) We want to containerize each node, such that we have a pointer pointed to
        // each element in the linked list
        List<Node<E>> arr = new ArrayList<>();
        Node<E> temp = head;
        while (temp != null) {
            arr.add(temp);
            temp = temp.getNext();
        }
        int len = arr.size();

        // 2) Sort the list based on the value in ascending order
        Comparator<Node<E>> sortByValue = (a, b) -> ((Comparable<E>) a.getElement()).compareTo(b.getElement());

        List<Node<E>> sortedArr = new ArrayList<>(arr);
        sortedArr.sort(sortByValue);

        // 3) Place the nodes in the correct order
        List<Node<E>> res = new ArrayList<>();
        for (Node<E> node : arr) {
            int i = Collections.binarySearch(sortedArr, node, sortByValue);
            res.add(sortedArr.get(len - i - 1));
        }

        // 4) Wire the nodes together
        for (int i = 0; i < len - 1; i++) {
            res.get(i).setNext(res.get(i + 1));
        }
        head = res.get(0);
        tail = res.get(len - 1);
        res.get(len - 1).setNext(null);
    }
   
}

