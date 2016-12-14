/**
 * Author: Dino Cajic
 *
 * Purpose: Tests the ArrayListDC class.
 *
 * How to use the program: Just run it.
 */
public class ArrayListDCDriver {

    /**
     * Tests the ArrayListDC class
     * Precondition: Must have ArrayListDC class in same folder
     * Postcondition: Creates an ArrayListDC object of size 50. Adds and removes
     * Objects from the list. Tests each of the methods to make sure everything
     * is working. Creates another ArrayListDC object utilizing the default
     * constructor. Again tests each of the ArrayListDC's methods.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Create Constructor setting ArrayListDC to capacity 50");
        ArrayListDC numeric = new ArrayListDC(50);

        System.out.println("The ArrayListDC is empty: " + numeric.isEmpty());
        System.out.println("The numer of elements in array is: " + numeric.size());

        System.out.println("Populate the ArrayListDC with values from 1 to 20");
        for (int i = 0; i < 50; i++) {
            numeric.add((int)(Math.random() * 20) + 1);
        }

        System.out.println("The ArrayListDC is empty: " + numeric.isEmpty());
        System.out.println("The number of elements in array is: " + numeric.size());

        System.out.println("Add an element to the end of the ArrayListDC: i.e. 12345");
        numeric.add(12345);
        System.out.println("The index of 12345 is " + numeric.find(12345));

        System.out.println("The number of elements in array is: " + numeric.size());
        System.out.println("The value of element 50 is: " + numeric.get(50));

        System.out.println("Element at index 5 before removal: " + numeric.get(5));
        System.out.println("The number of elements in array is: " + numeric.size());
        System.out.println("Remove element at index 5");
        numeric.remove(numeric.get(5));
        System.out.println("Element at index 5 after removal: " + numeric.get(5));
        System.out.println("The number of elements in array is: " + numeric.size());

        System.out.println("Add an element at specific position");
        System.out.println("Element at index 10 before insertion: " + numeric.get(10));
        numeric.add(10, 200);
        System.out.println("Element at index 10 after insertion: " + numeric.get(10));
        System.out.println("Element at index 11 after insertion: " + numeric.get(11));

        System.out.println("Check to see if particular element is in array?");
        System.out.println("Is 12345 in array? " + numeric.isIn(12345));
        System.out.println("Is 123456 in array? " + numeric.isIn(123456));

        for (int i = 0; i < numeric.size(); i++) {
            System.out.println("ArrayListDC[" + i + "]: " + numeric.get(i));
        }

        System.out.println("************************************************");

        System.out.println("Tests the default constructor");

        ArrayListDC test = new ArrayListDC();
        System.out.println("Current size: " + test.size());
        System.out.println("Is the Array List empty? : " + test.isEmpty());
        test.add("x");
        test.add("b");
        test.add("aa");
        test.add("bh");
        test.add("G");
        test.add("d");
        test.add("a");
        test.add("e");
        test.add("bd");
        test.add("c");
        test.add(2, "DINO");
        test.add(2, "CAJIC");
        test.add(10, "DINO");
        test.add(19, "DINO");
        test.remove("DINO");
        test.remove("x");
        System.out.println("Current size: " + test.size());
        System.out.println("Is the Array List empty? : " + test.isEmpty());
        System.out.println("Object at index 1: " + test.get(1));
        System.out.println("Object at index 3: " + test.get(3));
        System.out.println("Position of G: " + test.find("G"));
        System.out.println("Position of na: " + test.find("na"));
        System.out.println("Is bd in array list? : " + test.isIn("bd"));
        System.out.println("Is bda in array list? : " + test.isIn("bda"));

        for(int i = 0; i < test.size(); i++) {
            System.out.println("ArrayListDC[" + i + "] : " + test.get(i));
        }

        System.out.println("Out of bounds test: " + test.get(22));
    }
}
