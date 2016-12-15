import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Author: Dino Cajic
 * Class: CSC3410
 * Time: Monday/Wednesday 1:50pm to 4:20pm
 * Lab: Friday @ 1:00pm to 2:30pm
 *
 * Tested with: jdk1.8.0_45 | jre1.8.0_66
 *
 * Purpose: To gain experience with Stacks and Queues and to gain experience with library functions that generates
 * random numbers and provide time. The program will display palindromes from a provided file.
 *
 * Data Structures Used: Stacks, Queues, LinkedList, Array (when converting a string to a character array
 * with toCharArray())
 *
 * The program reads an input file and extracts all of the words that are palindromes. The program first prompts the
 * user for an input file name from which it must read the input. The program proceeds to read the file word by word
 * and then extract each word's characters inserting these characters into a queue. The program then copies the queue
 * into a stack. It pops each character from the queue and the stack and compares them for equality. If the characters
 * are equal, it continues to compare them until both containers are empty. At the end, the word is returned with the
 * message that the word is a palindrome.
 */
public class palindrome {

    /** Contains a character sequence of a word. */
    Stack<Character> stack;

    /** Contains a character sequence of a word. */
    Queue<Character> queue;

    /**
     * Instantiates the Stack and the Queue
     */
    public palindrome() {
        this.stack = new Stack<>();
        this.queue = new LinkedList<>();
    }

    /**
     * Main method: Causes the execution of the program.
     *
     * Precondition: There must exist a text file; Words must be separated with a space, new-line or punctuation.
     * Example of file input:
     *      C:\\src\\test.txt OR
     *      C:\src\test.txt OR
     *      C:/src/test.txt
     * Postcondition: Calls the start() method to run the program. If a word is a palindrome, it will be displayed
     * on the screen.
     *
     * @param args Default array required to be passed.
     */
    public static void main(String[] args) {
        palindrome obj = new palindrome();
        obj.start();
    }

    /**
     * The main brain of the program. Reads the text file and sees if a word is a palindrome.
     *
     * Precondition: Must have a file with words in it and the path to that file.
     * Postcondition: Prompts the user for a file location. After entering the location, it makes sure that the file
     * path is escaped correctly. Tries to create a Scanner object from the provided file path. If the path is
     * incorrect, the user is prompted to enter a path again. The user may choose to enter the path again or 'q' to
     * exit. If the path is correct, each word from the file is cleaned (to remove punctuation). The clean word is
     * added to the Stack and to the Queue. A check is performed to see if the word is a palindrome. If it is, the
     * word is displayed, otherwise the Stack and Queues are cleaned to prepare for the next word.
     */
    public void start() {
        Scanner fileNameInput = new Scanner(System.in);
        System.out.println("Please enter the name of the file: ");
        String path = fileNameInput.nextLine();

        if (path.equals("q")) {
            System.exit(-1);
        }

        String fileName = testBackslashesEscaped(path);

        Scanner fileInput = null;
        File inFile = new File(fileName);
        String word;

        try {
            fileInput = new Scanner(inFile);

            while (fileInput.hasNext()) {
                word = fileInput.next();
                word = cleanWord(word);

                addToStack(word);
                addToQueue(word);

                if (checkIfPalindrome()) {
                    System.out.println(word + " is a palindrome");
                } else {
                    // Could have used clear() here but it was mentioned in class not to use any Vector methods
                    this.stack = new Stack<>();
                    this.queue = new LinkedList<>();
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Incorrect file path. Try again. (You may enter 'q' to exit.");
            start();
        }
        finally {
            try {
                fileInput.close();
            } catch(NullPointerException e) {
                System.exit(-1);
            }
        }
    }

    /**
     * Makes sure that the file path is written correctly.
     *
     * Precondition: Must supply a file location path as a String.
     * Postcondition: Cleans up the file location to escape any backslashes that haven't been escaped and converts any
     * forward slashes to escaped backslashes.
     *
     * @param fileName The exact location of a file of type String.
     * @return String The escaped file path.
     */
    private static String testBackslashesEscaped(String fileName) {
        fileName = fileName.replace("\\", "\\\\");
        fileName = fileName.replace("/", "\\\\");
        return fileName;
    }

    /**
     * Adds a word to a stack as characters.
     *
     * Precondition: Must supply a String as an argument.
     * Postcondition: Cycles through the String and adds each character to the Stack.
     *
     * @param word The String that will be converted into an Array and whose each characters will be added to the stack.
     */
    private void addToStack(String word) {
        for (char c: word.toLowerCase().toCharArray()) {
            this.stack.push(c);
        }
    }

    /**
     * Adds a word to a queue as characters.
     *
     * Precondition: Must supply a String as an argument.
     * Postcondition: Cycles through the String and adds each character to the Queue.
     *
     * @param word The String that will be converted into an Array and whose each characters will be added to the queue.
     */
    private void addToQueue(String word) {
        for (char c: word.toLowerCase().toCharArray()) {
            queue.add(c);
        }
    }

    /**
     * Removes all non-alphabetic character from the String.
     *
     * Precondition: Must supply a String as an argument.
     * Postcondition: Uses a regular expression to remove all non-alphabetic characters from the String and returns the
     * clean word.
     *
     * @param word The String that will be cleaned of all non-alphabetic characters.
     */
    private String cleanWord(String word) {
        return word.replaceAll("[^a-zA-Z]", "");
    }

    /**
     * Checks to see if the word is a palindrome.
     *
     * Precondition: There must exist a stack and a queue instance variable and they should be populated
     * with characters.
     * Postcondition: While there still exist characters in a stack, it removes the last character from the stack and
     * the first character from the queue and compares them. If they're not identical, the method returns false. If
     * they are identical, the characters are continued to be compared until there aren't any more characters in the
     * stack. If an empty stack is reached, the while loop terminates and true is returned notifying the calling method
     * that the word is a palindrome.
     *
     * @return boolean
     */
    private boolean checkIfPalindrome() {
        while (!this.stack.isEmpty()) {
            if (!this.stack.pop().equals( this.queue.remove() )) {
                return false;
            }
        }

        return true;
    }
}
