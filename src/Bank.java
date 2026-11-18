public class Bank {

    private String[] tellers;
    private Queue waitingQueue;
    private boolean firstArrival = true; // ilk müşteriden önce boş satır yazmamak için

    public Bank() {

        // Step 1: Create a String array with 3 elements for tellers.
        tellers = new String[3];

        // Step 2: Initialize all teller positions as null.
        for (int i = 0; i < tellers.length; i++) {
            tellers[i] = null;
        }

        // Step 3: Create a waiting Queue object.
        waitingQueue = new Queue();
    }

    public void arrive(String customerName) {

        // İlk müşteri hariç, her müşteriden önce boş satır yazdır.
        if (!firstArrival) {
            System.out.println();
        }
        firstArrival = false;

        // Step 1: Display the customer's arrival message.
        System.out.println("Customer " + customerName + " arrived.");

        // Step 2: Search for an available teller.
        for (int i = 0; i < tellers.length; i++) {
            if (tellers[i] == null) {

                // Step 3: Assign the customer to that teller.
                tellers[i] = customerName;
                System.out.println(customerName + " is assigned to Teller " + (i + 1) + ".");
                return;
            }
        }

        // Step 4: Otherwise, add the customer to the waiting queue.
        System.out.println("All tellers are busy.");
        waitingQueue.enqueue(customerName);
        System.out.println(customerName + " joins the waiting queue.");
    }

    public void completeTransaction(int tellerNumber) {

        // Önceki status çıktısından sonra boş satır.
        System.out.println();

        // Step 1: Check whether tellerNumber is between 1 and 3.
        if (tellerNumber < 1 || tellerNumber > 3) {
            System.out.println("Invalid teller number!");
            return;
        }

        int index = tellerNumber - 1;

        // Step 2: Check whether the teller is already available.
        if (tellers[index] == null) {
            System.out.println("Teller " + tellerNumber + " is already available.");
            return;
        }

        // Step 3: Display the completed customer's name.
        System.out.println("Teller " + tellerNumber + " completed " + tellers[index] + "'s transaction.");

        // Step 4: Set the selected teller position to null.
        tellers[index] = null;

        // Step 5: Call assignNextCustomer(tellerNumber) for that teller.
        assignNextCustomer(tellerNumber);
    }

    public void assignNextCustomer(int tellerNumber) {

        // Step 1: Check whether there are customers waiting.
        // Step 2: If it is not empty:
        if (!waitingQueue.isEmpty()) {

            // Remove the next customer from the queue.
            String nextCustomer = waitingQueue.dequeue();

            // Assign that customer to the available teller.
            tellers[tellerNumber - 1] = nextCustomer;

            // Display the assignment message.
            System.out.println(nextCustomer + " is assigned to Teller " + tellerNumber + ".");
        }
    }

    public void displayStatus() {

        System.out.println("\n---------- BANK STATUS ----------");

        // Display the status of all tellers.
        for (int i = 0; i < tellers.length; i++) {

            System.out.print("Teller " + (i + 1) + ": ");

            if (tellers[i] == null) {
                System.out.println("Available");
            } else {
                System.out.println(tellers[i]);
            }
        }

        // Display the waiting queue.
        System.out.print("Waiting Queue: ");
        waitingQueue.display();

        System.out.println("---------------------------------");
    }
}