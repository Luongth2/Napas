package java8;

// Java program to demonstrate the real-world example of Interfaces
/*
We can’t create an instance(interface can’t be instantiated) of the interface but we can make the reference of it that refers to the Object of its implementing class.
A class can implement more than one interface.
An interface can extend to another interface or interface (more than one interface).
A class that implements the interface must implement all the methods in the interface.
All the methods are public and abstract. And all the fields are public, static, and final.
It is used to achieve multiple inheritances.
It is used to achieve loose coupling.
 */

import java.io.*;

interface Vehicle {

    // all are the abstract methods.
    void changeGear(int a);
    void speedUp(int a);
    void applyBrakes(int a);
}

class Bicycle implements Vehicle{

    int speed;
    int gear;

    // to change gear
    @Override
    public void changeGear(int newGear){

        gear = newGear;
    }

    // to increase speed
    @Override
    public void speedUp(int increment){

        speed = speed + increment;
    }

    // to decrease speed
    @Override
    public void applyBrakes(int decrement){

        speed = speed - decrement;
    }

    public void printStates() {
        System.out.println("speed: " + speed
                + " gear: " + gear);
    }
}

class Bike implements Vehicle {

    int speed;
    int gear;

    // to change gear
    @Override
    public void changeGear(int newGear){

        gear = newGear;
    }

    // to increase speed
    @Override
    public void speedUp(int increment){

        speed = speed + increment;
    }

    // to decrease speed
    @Override
    public void applyBrakes(int decrement){

        speed = speed - decrement;
    }

    public void printStates() {
        System.out.println("speed: " + speed
                + " gear: " + gear);
    }

}
interface InterfaceWithDeflautImp
{
    int a = 10; //default final
    default void display()
    {
        //a = 10; //java: cannot assign a value to final variable a
        System.out.println("\nWe can now add default implementation for interface methods. JDK.1.8 or Later. a = " + a);
    }

    static void displayStatic()
    {
        System.out.println("\nAnother feature that was added in JDK 8 is that we can now define static methods in interfaces that can be called independently without an object. Note: these methods are not inherited.");
    }
}
class InterfaceImp implements InterfaceWithDeflautImp {

    public static void main (String[] args) {

        // creating an instance of Bicycle
        // doing some operations
        Bicycle bicycle = new Bicycle();
        bicycle.changeGear(2);
        bicycle.speedUp(3);
        bicycle.applyBrakes(1);

        System.out.println("Bicycle present state :");
        bicycle.printStates();

        // creating instance of the bike.
        Bike bike = new Bike();
        bike.changeGear(1);
        bike.speedUp(4);
        bike.applyBrakes(3);

        System.out.println("\nBike present state :");
        bike.printStates();
        InterfaceImp objInterfaceImp = new InterfaceImp();
        objInterfaceImp.display();
        InterfaceWithDeflautImp.displayStatic();
    }
}
