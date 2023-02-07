package sdsvn.thread;

public class SyncThread {
    public static void main(String args[])
    {
        // Creating object "obj" of book class and passing it to bookThread class
        book obj = new book();
        book obj2 = new book();
        bookThread t1 = new bookThread(obj, "Shubham", 1);
        bookThread t2 = new bookThread(obj2, "Sharmistha", 1);
        // When a program calls the start() method, a new thread is created and then the run() method is executed.

        // Starting threads created above
        System.out.println("Start t1 \n");
        t1.start();
        System.out.println("Start t2 \n");
        t2.start();

        Sender send = new Sender();
        ThreadedSend S1 =
                new ThreadedSend( " Hi " , send );
        ThreadedSend S2 =
                new ThreadedSend( " Bye " , send );

        // Start two threads of ThreadedSend type
        S1.start();
        S2.start();

        // wait for threads to end
        try
        {
            //S1.join();
            S1.sendSync("Sync with a method");

            //S2.join();
            S2.sendSync("Sync with a method 2");
        }
        catch(Exception e)
        {
            System.out.println("Interrupted");
        }


    }
}
