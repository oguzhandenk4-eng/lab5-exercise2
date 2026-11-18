public class Queue {

    private Node front;
    private Node rear;
    private int size;

    public Queue() {

        // Step 1: Initialize front and rear as null.
        front = null;
        rear = null;

        // Step 2: Initialize size as 0.
        size = 0;
    }

    public void enqueue(String customerName) {

        // Step 1: Create a new Node using customerName.
        Node newNode = new Node(customerName);

        // Step 2: Check whether the queue is empty.
        if (isEmpty()) {
            // Step 3: If the queue is empty:
            front = newNode;
            rear = newNode;
        } else {
            // Step 4: Otherwise:
            rear.next = newNode;
            rear = newNode;
        }

        // Step 5: Increase size.
        size++;
    }

    public String dequeue() {

        // Step 1: Check whether the queue is empty.
        if (isEmpty()) {
            return null;
        }

        // Step 2: Store the customer name at the front.
        String removedName = front.customerName;

        // Step 3: Move front to the next node.
        front = front.next;

        // Step 4: If front becomes null, set rear to null.
        if (front == null) {
            rear = null;
        }

        // Step 5: Decrease size.
        size--;

        // Step 6: Return the removed customer's name.
        return removedName;
    }

    public boolean isEmpty() {

        // Step 1: Check whether front is null.
        return front == null;
    }

    public int size() {

        // Step 1: Return the current size.
        return size;
    }

    public void display() {

        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }

        Node current = front;

        while (current != null) {

            System.out.print(current.customerName);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}