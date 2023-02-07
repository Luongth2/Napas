package sdsvn.thread;

public class AnonymousThreads {
    public static void main(String... ar)
    {
        Thread t= new Thread(){ 	//Creating an object of Anonymous class which extends Thread class and passing this object to the reference of Thread class.
            public void run()	//Anonymous class overriding run() method of Thread class
            {
                setName("Anonymous Thread");
                System.out.println("Name of the other thread - " + getName());
                for(int i=0;i<3;i++)
                {
                    System.out.println(getName() + " " + i);
                }

            }
        };				//Anonymous class ends here

        //Starting anonymous thread
        t.start();

        //Gets the name of main thead
        System.out.println("Name of main thread - "+ Thread.currentThread().getName());

        Runnable r = new Runnable(){	 //Creating an object of Anonymous class that implemented Runnable interface
            public void run()	 //Anonymous class implementing run() method of Runnable class
            {
                Thread t= Thread.currentThread();
                t.setName("AnonymousThread2");
                System.out.println("Name of the other thread 2 - " + t.getName());
                for(int i=0;i<3;i++)
                {
                    System.out.println(t.getName() + " " + i);
                }

            }
        };

        Thread t2= new Thread(r);  //Passing the object of anonymous class to the constructor of Thread
        t2.start();		  //Starting the anonymous thread

        //Gets the name of main thead
        System.out.println("Name of main thread 2 - "+ Thread.currentThread().getName());

    }
}
