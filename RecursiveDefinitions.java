import java.util.Scanner;

/**
 * Author: Dino Cajic
 *
 * Java Identifiers
 *
 * Write a recursive function that accepts one parameter and test to see if the input parameter to the function is a
 * legitimate java identifier. Prompt the user for a java identifier and test it. run the program indefinitely until the
 * user enters "No" to not run the program again.
 *
 * Java identifier grammar
 *
 * <identifier> = <letter> | <identifier><letter> | <identifier><digit>
 * <letter> = a | b | ... | z | A | B | ... | Z | _ | $
 * <digit> = 0 | 1 | ... | 9
 *
 * QUIZ 2
 */
public class RecursiveDefinitions {

    /**
     * Calls the driver method to run the program
     * @param args Standard Java program parameter
     */
    public static void main(String[] args) {
        driver();
    }

    /**
     * Prompts the user to enter a java identifier and outputs whether it is a correct identifier or not.
     *
     * Precondition: None
     * Postcondition: Loops and prompts the user to enter an identifier. It will be in a while loop until the user
     * enters "No." It checks to see if the identifier supplied is an identifier. If it is, a message is displayed
     * letting the user know that the word is an identifier. Otherwise, a message is displayed letting the user
     * know that it isn't an identifier.
     */
    public static void driver() {
        Scanner input = new Scanner(System.in);
        String id;

        while (true) {
            System.out.println("Enter an identifier to test: ");
            id = input.next();

            if (id.toLowerCase().equals("no")) {
                System.exit(-1);
            }

            if (isIdentifier(id)) {
                System.out.println(id + " is an identifier");
            } else {
                System.out.println(id + " is not an identifier");
            }

        }
    }

    /**
     * Tests to see if the word provided is a valid Java identifier
     *
     * Precondition: Must supply String as parameter
     * Postcondition: Tests to see if the length of the word being supplied is 1 character long. If it is, it tests
     * to see the character is a letter or if it's a $ or if it's an underscore _ . If it is, it returns true.
     * Otherwise, it returns false. If the word has multiple characters, then it removes the last letter and
     * recursively calls the isIdentifier method to test it again. It keeps doing this until either a character is
     * not a letter, digit, $ or _, OR until the String only has one character left.
     *
     * @param word The String that will be tested to see if it's an identifier
     * @return boolean
     */
    public static boolean isIdentifier(String word) {
        if (word.length() == 1) {
            if (Character.isLetter(word.charAt(0)) || word.equals("$") || word.equals("_")) {
                return true;
            }
        } else if(Character.isLetter( word.charAt(word.length() - 1) ) ||
                Character.isDigit( word.charAt(word.length() - 1 )) ||
                word.charAt(word.length() - 1) == '$' ||
                word.charAt(word.length() - 1) == '_') {
            return isIdentifier( word.substring(0, word.length() - 1));
        }

        return false;
    }
}
