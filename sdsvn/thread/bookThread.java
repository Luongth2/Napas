package sdsvn.thread;

public class bookThread extends Thread {
    book obj;
    int n;
    String name;
    bookThread(book obj, String name, int n)
    {
        this.obj = obj;
        this.n = n;
        this.name = name;
    }
    // runs Threads
    public void run() {
        System.out.println("run bookThread " + name +"\n");
        obj.book(n, name); }
}
