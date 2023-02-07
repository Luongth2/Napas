package sdsvn.thread;
/*
In Java, a thread always exists in any one of the following states. These states are:
1.New: Whenever a new thread is created, it is always in the new state. For a thread in the new state, the code has not been run yet and thus has not begun its execution.
2.Active: The active state contains two states within it: one is runnable, and the other is running.
3.Blocked / Waiting:  Whenever a thread is inactive for a span of time (not permanently) then, either the thread is in the blocked state or is in the waiting state
4.Timed Waiting: waiting time
5.Terminated: When a thread has finished its job, then it exists or terminates normally or abnormal termination:
 */
class ABC implements Runnable
{
    public void run()
    {

        // try-catch block
        try
        {
            // moving thread t2 to the state timed waiting
            Thread.sleep(100);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        System.out.println("The state of thread t1 while it invoked the method join() on thread t2 -"+ ThreadState.t1.getState());

        // try-catch block
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}

// ThreadState class implements the interface Runnable
public class ThreadState implements Runnable
{
    public static Thread t1;
    public static ThreadState obj;

    // main method
    public static void main(String argvs[])
    {
        // creating an object of the class ThreadState
        obj = new ThreadState();
        t1 = new Thread(obj);

        // thread t1 is spawned
        // The thread t1 is currently in the NEW state.
        System.out.println("The state of thread t1 after spawning it - " + t1.getState());

        // invoking the start() method on    the thread t1
        t1.start();

        // thread t1 is moved to the Runnable state
        System.out.println("The state of thread t1 after invoking the method start() on it - " + t1.getState());
    }

    public void run()
    {
        ABC myObj = new ABC();
        Thread t2 = new Thread(myObj);

        // thread t2 is created and is currently in the NEW state.
        System.out.println("The state of thread t2 after spawning it - "+ t2.getState());
        t2.start();

        // thread t2 is moved to the runnable state
        System.out.println("the state of thread t2 after calling the method start() on it - " + t2.getState());

        // try-catch block for the smooth flow of the  program
        try
        {
            // moving the thread t1 to the state timed waiting
            Thread.sleep(200);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }

        System.out.println("The state of thread t2 after invoking the method sleep() on it - "+ t2.getState() );

        // try-catch block for the smooth flow of the  program
        try
        {
            // waiting for thread t2 to complete its execution
            t2.join();
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        System.out.println("The state of thread t2 when it has completed it's execution - " + t2.getState());
    }

}