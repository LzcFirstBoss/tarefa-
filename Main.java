import java.util.*;

class Node {
    int data;
    Node prev;
    Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    public void insertSorted(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
        } else if (data <= head.data) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (data >= tail.data) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < data) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
    }

    public void printAscending() {
        Node current = head;
        System.out.print("Lista em ordem crescente: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void printDescending() {
        Node current = tail;
        System.out.print("Lista em ordem decrescente: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public void removePrimes() {
        Node current = head;
        while (current != null) {
            if (isPrime(current.data)) {
                Node toRemove = current;
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                current = current.next;
                toRemove.next = null;
                toRemove.prev = null;
            } else {
                current = current.next;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] vetor = new int[1000];
        DoublyLinkedList lista = new DoublyLinkedList();

        System.out.println("Vetor gerado e inserido na lista:");

        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(19999) - 9999;
            vetor[i] = randomNumber;
            lista.insertSorted(randomNumber);
            System.out.print(randomNumber + " ");
        }
        System.out.println();

        lista.printAscending();
        lista.printDescending();

        lista.removePrimes();

        System.out.println("Lista após remover os números primos:");
        lista.printAscending();
    }
}
