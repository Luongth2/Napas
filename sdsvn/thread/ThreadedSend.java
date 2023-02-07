package sdsvn.thread;

public class ThreadedSend extends Thread {
    private String msg;
    Sender  sender;

    // Receives a message object and a string
    // message to be sent
    ThreadedSend(String m,  Sender obj)
    {
        msg = m;
        sender = obj;
    }

    public void run()
    {
        // Only one thread can send a message
        // at a time.
        synchronized(sender)
        {
            // synchronizing the send object
            sender.send(msg);
        }
    }
    //we can use synchronized with method also.
    public synchronized void sendSync(String msg)
    {
        System.out.println("sendSync Sending \t" + msg +"\n");
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
        System.out.println("sendSync " + msg + "\t Sent" +"\n");
    }

    //synchronized can be used with only a part of method
    public void sendSyncPart(String msg)
    {
        synchronized(this)
        {
            System.out.println("sendSyncPart: \t" + msg +"\n");
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                System.out.println("Thread interrupted.");
            }
            System.out.println("sendSyncPart " + msg + " Sent"+"\n");
        }
    }
}
