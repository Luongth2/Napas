/*
In java, the following some important observations about abstract classes are as follows:

1. An instance of an abstract class can not be created.
2. Constructors are allowed.
3. We can have an abstract class without any abstract method.
4. There can be a final method in abstract class but any abstract method in class(abstract class) can not be declared as final  or in simpler terms final method can not be abstract itself as it will yield an error: “Illegal combination of modifiers: abstract and final”
We can define static methods in an abstract class
5. We can use the abstract keyword for declaring top-level classes (Outer class) as well as inner classes as abstract
6. If a class contains at least one abstract method then compulsory should declare a class as abstract
7. If the Child class is unable to provide implementation to all abstract methods of the Parent class then we should declare that Child class as abstract so that the next level Child class should provide implementation to the remaining abstract method
*/

//3. An abstract class without any abstract method
abstract class Base2 {

    // Demo method. This is not an abstract method.
    void fun()
    {
        // Print message if class 1 function is called
        System.out.println("Function of Base class is called");
    }
}
//4. Abstract classes can also have final methods
abstract class Base {

    final void fun()
    {
        System.out.println("Base fun() called");
    }
}

// Class 2
class Derived extends Base {

}
//Similar to the interface we can define static methods in an abstract class that can be called independently without an object.
abstract class Helper {

    // Abstract method
    static void demofun()
    {

        // Print statement
        System.out.println("Static method in Abstract class called");
    }
}

abstract class B {
    // declaring inner class as abstract with abstract method
    abstract class C {
        abstract void myAbstractMethod();
    }
}
class D extends B {
    class E extends C {
        // implementing the abstract method
        void myAbstractMethod()
        {
            System.out.println(
                    "Inside abstract method implementation");
        }
    }
}

public class AbstractJava {
    // Main driver method
    public static void main(String args[])
    {
        //1.Trying to create an object Base
        //For any abstract java class we are not allowed to create an object i.e., for abstract class instantiation is not possible.
        //Base objBase = new Base(); //Compile error

        Helper.demofun();

        // Instantiating the outer class
        D outer = new D();

        // Instantiating the inner class
        D.E inner = outer.new E();
        inner.myAbstractMethod();
    }
}
