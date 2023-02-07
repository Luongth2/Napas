package sdsvn.thread;

public class book {
    static int tickets = 1;
    // static synchronized method to book movie ticket
    static synchronized void book(int request, String name)
    {
        // logic to book ticket
        if (tickets >= request) {
            System.out.println(name + " booked " + request
                    + " ticket.");
            tickets = tickets - 1;
            System.out.println("Tickets left: " + tickets);
        }
        else {
            System.out.println(
                    name + ", No tickets are available.");
        }
    }
}
