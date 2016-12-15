import java.util.Arrays;

/**
 * Created by Dino on 7/14/2016.
 * Dino Cajic
 *
 * Many different recursive tests
 */
public class RecursiveTests {

    public static void main(String[] args) {
        identifierTest();
        palindromeTest();
        anagramTest();
        factorialTest();
        fibonacciTest();
        reverseStringTest();
        removeDuplicatesTest();
        isAnBnTest();
        //stringPermutationTest();
    }

    /******************************************************************************************************************/
    /**                                                                                                               */
    /** Grammar                                                                                                       */
    /**                                                                                                               */
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /** Java Identifiers                                                                                              */
    /**                                                                                                               */
    /** JavaIds = {w : w is a legal Java identifier}                                                                  */
    /** < identifier > = < letter > | < identifier > < letter > | < identifier > < digit>                             */
    /** < letter > = a | b | ... | z | A | B | ... | Z | _ | $                                                        */
    /** < digit > = 0 | 1 | ... | 9                                                                                   */
    /**                                                                                                               */
    /******************************************************************************************************************/
    public static void identifierTest() {
        System.out.println(
                        "*****************************************************\n" +
                        "* Grammar: Identifier Test                          *\n" +
                        "*****************************************************");

        String id = "3Hello";

        if (isIdentifier(id)) {
            System.out.println(id + " is an identifier");
        } else {
            System.out.println(id + " is not an identifier");
        }

        System.out.println("\n\n\n\n");
    }

    public static boolean isIdentifier(String word) {
        if (word.length() == 1) {
            return Character.isLetter(word.charAt(0));
        } else if (Character.isLetter(word.charAt(word.length() - 1)) ||
                   Character.isDigit(word.charAt(word.length() - 1))) {
            return isIdentifier(word.substring(0, word.length()-1));
        }

        return false;
    }


    /******************************************************************************************************************/
    /** Palindrome                                                                                                    */
    /******************************************************************************************************************/
    public static void palindromeTest() {
        System.out.println(
                "*****************************************************\n" +
                "* Palindrome Test                                   *\n" +
                "*****************************************************");

        String test = "noon";
        if (isPalindrome(test)) {
            System.out.println(test + " is a palindrome.");
        }

        System.out.println("\n\n\n\n");
    }

    /**
     * Tests to see if a String is a palindrome
     * @param word the word to test if palindrome
     * @return boolean
     */
    public static boolean isPalindrome(String word) {
        // Base case
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }

        if (word.charAt(0) == word.charAt( word.length() - 1 )) {
            return isPalindrome( word.substring( 1, word.length()-1 ));
        }

        return false;
    }

    /******************************************************************************************************************/
    /** Anagram                                                                                                       */
    /******************************************************************************************************************/
    public static void anagramTest() {
        System.out.println(
                "*****************************************************\n" +
                "* Anagram Test                                      *\n" +
                "*****************************************************");
        String word1 = "Dino";
        String word2 = "inod";

        if (isAnagram(word1, word2)) {
            System.out.println(word1 + " is an anagram with " + word2);
        }

        System.out.println("\n\n\n\n");
    }

    /**
     * Checks to see if word1 and word2 are anagrams of each other
     * @param word1 the word to test against word2
     * @param word2 the word to test against word1
     * @return boolean
     */
    public static boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        if (word1.length() == 0) {
            return true;
        }

        char[] word1array = word1.toLowerCase().toCharArray();
        char[] word2array = word2.toLowerCase().toCharArray();

        Arrays.sort(word1array);
        Arrays.sort(word2array);

        if (word1array[0] == word2array[0]) {
            word1 = new String(word1array);
            word2 = new String(word2array);
            return isAnagram(word1.substring(1, word1.length() - 1), word2.substring(1, word2.length() - 1));
        }

        return false;
    }

    /******************************************************************************************************************/
    /** String Permutation                                                                                            */
    /******************************************************************************************************************/
    public static void stringPermutationTest() {
        System.out.println(
                "*****************************************************\n" +
                "* Permutation Test                                  *\n" +
                "*****************************************************");
        permuteString("", "String");

        System.out.println("\n\n\n\n");
    }

    public static void permuteString(String beginningString, String endingString) {
        if (endingString.length() <= 1)
            System.out.println(beginningString + endingString);
        else
            for (int i = 0; i < endingString.length(); i++) {
                try {
                    String newString = endingString.substring(0, i) + endingString.substring(i + 1);

                    permuteString(beginningString + endingString.charAt(i), newString);
                } catch (StringIndexOutOfBoundsException exception) {
                    exception.printStackTrace();
                }
            }
    }

    /******************************************************************************************************************/
    /** Factorial                                                                                                     */
    /******************************************************************************************************************/
    public static void factorialTest() {
        System.out.println(
                "*****************************************************\n" +
                "* Factorial Test                                    *\n" +
                "*****************************************************");

        System.out.println("The factorial of 10 is: " + factorial(10));

        System.out.println("\n\n\n\n");
    }

    public static int factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }

        return num * factorial(num - 1);
    }

    /******************************************************************************************************************/
    /** Fibonacci                                                                                                     */
    /******************************************************************************************************************/
    public static void fibonacciTest() {
        System.out.println(
                "*****************************************************\n" +
                "* Fibonacci Test                                    *\n" +
                "*****************************************************");

        System.out.println("Fibonacci of 23 is: " + fibonacci(23));

        System.out.println("\n\n\n\n");
    }

    public static int fibonacci(int num) {
        if (num <= 2) {
            return 1;
        }

        return fibonacci(num - 1) + fibonacci(num - 2);
    }

    /******************************************************************************************************************/
    /** Reverse String                                                                                                */
    /******************************************************************************************************************/
    public static void reverseStringTest() {
        System.out.println(
                        "*****************************************************\n" +
                        "* Reverse String                                    *\n" +
                        "*****************************************************");

        reverseString("Dino");

        System.out.println("\n\n\n\n");
    }

    public static void reverseString(String word) {
        if (word.length() == 0) {
            return;
        }

        System.out.print( word.charAt( word.length()-1 ) );
        reverseString( word.substring( 0, word.length()-1) );
    }

    /******************************************************************************************************************/
    /** Remove Duplicates                                                                                             */
    /******************************************************************************************************************/
    public static void removeDuplicatesTest() {
        System.out.println(
                        "*****************************************************\n" +
                        "* Remove Duplicate Characters                       *\n" +
                        "*****************************************************");

        System.out.println( removeDuplicates("Dinoo") );
        System.out.println("\n\n\n\n");
    }

    public static String removeDuplicates(String word) {
        if (word.length() <= 1) {
            return word;
        } else if (word.charAt(0) == word.charAt(1)) {
            return removeDuplicates(word.substring(1));
        } else {
            return word.charAt(0) + removeDuplicates( word.substring(1) );
        }
    }

    /******************************************************************************************************************/
    /** Strings of Form AnBn                                                                                          */
    /******************************************************************************************************************/
    public static void isAnBnTest() {
        System.out.println(
                        "*****************************************************\n" +
                        "* Is the word in form AnBn                          *\n" +
                        "*****************************************************");

        System.out.println("The word AAABBB is in form AnBn? " + isAnBn("AAABBB"));
        System.out.println("The word AAABCB is in form AnBn? " + isAnBn("AAABCB"));
        System.out.println("\n\n\n\n");
    }

    public static boolean isAnBn(String word) {
        if (word.length() == 0) {
            return true;
        } else if(word.toUpperCase().charAt(0) == 'A' && word.toUpperCase().charAt(word.length() - 1) == 'B') {
            return isAnBn(word.substring(1, word.length()-1));
        } else {
            return false;
        }
    }

}