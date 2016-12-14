import java.io.*;
import java.util.Scanner;

/**
 * Author: Dino Cajic
 *
 * Purpose: To read a text file and display any anagrams of a word
 * on the same line. Words that aren't anagrams of each other are
 * placed on the next line.
 *
 * Solution to the problem: The file presented will be read and
 * stored in an array. A second array will be created whose
 * elements will undergo manipulation to sort each String element
 * alphanumerically in ascending order. Once the elements are sorted,
 * the array is sorted and the unsorted arrays element's positions
 * will mimic those of the sorted array. Each word in the sorted
 * array will be compared and tested to see if they are anagrams of
 * each other. If an anagram exists, the words will be written
 * in-line. Non-anagram words are written on a new line and both are
 * stored in the results.txt file.
 *
 * Anagram Logic: If we have 3 words, SNAP, PANS and STOP. SPAN and
 * PANS are anagrams. STOP is not an anagram of the other 2 since it
 * has different characters. After each word is sorted, we'll have
 * something like:
 *   SNAP - ANPS
 *   PANS - ANPS
 *   STOP - OPST
 *
 * Data structures used: Array
 *
 * To use the program, input the correct file location of a text
 * document that has a list of no more than 50 words and whose words
 * are each less than 12 characters long. Each word must be separated
 * with a space. The expected outcome is a results.txt file that has
 * the list of words that have been provided through the input text
 * file, but whose anagrams of each word appear on the same line and
 * whose non-anagram words appear on new lines.
 *
 * Use test.txt for testing
 */
public class anagram {

    /**
     * Main method: Causes the execution of the program.
     *
     * Precondition: There must exist a text file; no word may be
     * more than 12 characters long: if they exist, they'll quietly
     * be removed from the results.txt file; there cannot be more
     * than 50 words in a text file. Words must be separated with a
     * space or with new-line.
     * Example of file input:
     *      C:\\src\\test.txt OR
     *      C:\src\test.txt OR
     *      C:/src/test.txt
     * Postcondition: The number of words in a file will be counted.
     * If there are more than 50 words, the program will terminate.
     * An unsorted array (String[] unsortedWords) will be created to
     * store each word. Also, a sorted word array, String[]
     * sortedWords, will also be created. The arrays are sorted with
     * reference to the sortedWords array to make sure that each word
     * in the sortedWords array is sorted alphabetically. A file will
     * be created with the name of results.txt that contains all of
     * the words written with the following conditions:
     *      - The anagrams of each word will appear on the same line
     *        with a single space separating each word.
     *      - Strings containing digits will not be included.
     *      - Strings larger than 12 characters will not be included.
     *      - All other words will appear on their own individual line.
     *
     * @param args Default array required to be passed.
     */
    public static void main(String[] args) {
        Scanner fileNameInput = new Scanner(System.in);
        System.out.println("Please enter the name of the file: ");
        String fileName = testBackslashesEscaped(fileNameInput.nextLine());

        Scanner fileInput = null;
        File inFile = new File(fileName);

        int totalWords = countWords(inFile, fileInput);

        if (totalWords > 50) {
            System.out.println("You cannot have more than 50 words in a file");
            System.exit(-1);
        }

        String[] unsortedWords = convertFileToArray(fileInput, inFile, totalWords);
        String[] sortedWords = createSortedWordsArray(unsortedWords);

        sortArray(sortedWords, unsortedWords);
        createFile(sortedWords, unsortedWords);
    }

    /**
     * Makes sure that the file path is written correctly
     *
     * Precondition: Must supply a file location path as a String.
     * Postcondition: Cleans up the file location to escape any backslashes that
     * haven't been escaped and converts any forward slashes to escaped backslashes.
     *
     * @param fileName  | The exact location of a file of type String.
     * @return fileName | The escaped file path.
     */
    private static String testBackslashesEscaped(String fileName) {
        fileName = fileName.replace("\\", "\\\\");
        fileName = fileName.replace("/", "\\\\");
        return fileName;
    }

    /**
     * Counts the number of words in a file.
     *
     * Precondition: Must supply the instantiated File variable and the Declared
     * Scanner variable.
     * Example: Scanner fileInput = null;
     *          File inFile = new File(fileName); where String fileName is the path
     *          to a text file on the computer
     * Postcondition: Reads the file with the Scanner class. Counts each word by
     * adding one to the count variable only if the word is less than 12 characters
     * long and it doesn't contain any digits. Throws and handles a
     * FileNotFoundException if the file does not exist. Returns the number of
     * words in a text file as an int.
     *
     * @param inFile     | The text file that the countWords method will read.
     * @param fileInput  | Passed from the previous method so that the Scanner class
     *                     doesn't have to be re-declared.
     * @return int count | The number of words in a text file.
     */
    private static int countWords(File inFile, Scanner fileInput) {
        int count = 0;
        String input;

        try {
            fileInput = new Scanner(inFile);

            while (fileInput.hasNext()) {
                input = fileInput.next();
                if (input.length() < 12 && !testForDigits(input)) {
                    count++;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        finally {
            fileInput.close();
        }

        return count;
    }

    /**
     * Tests to see if a String has any digits in it
     *
     * Precondition: Must supply a String.
     * Postcondition: The method will convert the string to an array and cycle
     * through each character to see if it contains a digit. If it does, it
     * will return true; if it doesn't it will return false.
     *
     * @param word | A String that's passed to test if it contains a digit
     * @return boolean hasDigit | True if the String contains digits; false otherwise
     */
    private static boolean testForDigits(String word) {
        boolean hasDigit = false;

        if (word != null && !word.isEmpty()) {
            for (char c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                    break;
                }
            }
        }

        return hasDigit;
    }

    /**
     * Reads the text file and converts it to an array.
     *
     * Precondition: Must supply the method with the declared Scanner class as
     * fileInput, the instantiated File variable as inFile, and the number of words
     * that the file contains so that the array of a specific size may be created.
     * Example: Scanner fileInput = null;
     *          File inFile = new File(fileName); where fileName is the path to a
     *          text file on the computer.
     * Postcondition: Creates a String[] wordsArray. Opens the File inFile for reading.
     * Cycles through each word and stores it in the wordsArray. If the word is larger
     * than 12 characters long or contains a digit, it skips it. Throws and handles the
     * FileNotFoundException that can be triggered when creating the fileInput by
     * instantiating the Scanner class. Upon completion, it returns the
     * String[] wordsArray.
     *
     * @param fileInput   | Passed from the previous method so that the Scanner class
     *                      doesn't have to be re-declared.
     * @param inFile      | The text file that the method will read.
     * @param wordsInFile | The number of words that the text file contains.
     * @return String[] wordsArray | The array that contains all of the words from the file.
     */
    private static String[] convertFileToArray(Scanner fileInput, File inFile, int wordsInFile) {
        String input;
        String[] wordsArray = null;

        try {
            fileInput = new Scanner(inFile);
            wordsArray = new String[wordsInFile];

            for (int i = 0; i < wordsInFile; i++) {
                if (fileInput.hasNext()) {
                    input = fileInput.next();

                    if (input.length() >= 12 || testForDigits(input)) {
                        i--;
                        continue;
                    }

                    wordsArray[i] = input;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        finally {
            fileInput.close();
        }

        return wordsArray;
    }

    /**
     * Sorts each word in a String[] array.
     *
     * Precondition: The user must pass a String[] array of any length including 0.
     * Postcondition: The sortedWords array is created. The method loops through each
     * unsortedWords item and removes all special characters from each String item. The
     * cleaned up item is converted to lower case and stored in the variable word. Each word
     * is converted to a character array and passed through the sortEachWord() method to get
     * a character array that's sorted alphabetically. The sortedWord character array is
     * stored as a String within the sortedWords array. Once the loop has cycled through each
     * element in the unsortedWords array, the String[] sortedWords is returned giving the
     * user with an array whose String elements are sorted alphabetically.
     *
     * @param unsortedWords | An array containing any length of String elements.
     * @return String[] sortedWords | An array whose String elements have each been
     *                                sorted alphabetically.
     */
    private static String[] createSortedWordsArray(String[] unsortedWords) {
        String[] sortedWords = new String[unsortedWords.length];

        int i = 0;

        for (String word : unsortedWords) {
            word = word.replaceAll("[^a-zA-Z]+","");
            word = word.toLowerCase();

            char[] sortedWord = sortEachWord(word.toCharArray());

            sortedWords[i] = new String(sortedWord);
            i++;
        }

        return sortedWords;
    }

    /**
     * Sorts a character array alphanumerically in ascending order.
     *
     * Precondition: The user must supply a char[] array.
     * Postcondition: The method will loop through the character array starting at
     * position 0 all the way to the second to the last element. For each character
     * looped, a new loop goes through the remainder of array and compares the
     * current element with the element starting at the position after the current
     * element and going to the end of the array. If the element from the first loop
     * is greater than the element of the second loop, the elements are swapped since
     * the array needs to be sorted in ascending order. Once the array has been sorted,
     * it is returned.
     *
     * @param arrayToSort | The character array that needs to be sorted alphanumerically
     *                      in ascending order.
     * @return char[] arrayToSort | The sorted character array.
     */
    private static char[] sortEachWord(char[] arrayToSort) {
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (arrayToSort[i] > arrayToSort[j]) {
                    char temp      = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[i];
                    arrayToSort[i] = temp;
                }
            }
        }

        return arrayToSort;
    }

    /**
     * Sorts the sortedWords array in lexicographical order. The unsortedWords array's
     * elements will be sorted in accordance to the sorted array.
     *
     * Precondition: The user must pass 2 arrays. The first array will be the
     * String[] sortedWords whose elements have already been sorted alphanumerically.
     * The second array is the unsorted String[] array; this is the original
     * array whose elements have not been sorted.
     * Postcondition: The method loops through the sortedWords array and compares each
     * word with the words following it by going through a second loop. If the compareTo()
     * method returns a negative number, then the string is lexicographically larger than
     * the string it's compared to. If that's the case, the elements are swapped. At the
     * same time, the unsorted elements get the same treatment to mimic the sorting of the
     * sortedWords array. Since the arrays are passed by reference, there's no need to
     * return anything. The arrays will be sorted according to the sortedWords
     * array arrangement.
     *
     * @param sortedWords   | An array whose String elements have each been sorted
     *                        alphanumerically in ascending order.
     * @param unsortedWords | An array whose String elements have not been sorted.
     */
    private static void sortArray(String[] sortedWords, String[] unsortedWords) {
        for (int i = 0; i < sortedWords.length - 1; i++) {
            for (int j = i+1; j < sortedWords.length; j++) {
                if (sortedWords[i].compareTo(sortedWords[j]) < 0) {
                    String tempSorted = sortedWords[i];
                    String tempUnsorted = unsortedWords[i];

                    sortedWords[i] = sortedWords[j];
                    unsortedWords[i] = unsortedWords[j];

                    sortedWords[j] = tempSorted;
                    unsortedWords[j] = tempUnsorted;
                }
            }
        }
    }

    /**
     * Creates a text file named results.txt and stores the words from the unsortedWords
     * array into it with anagrams of each word appearing on the same line and non-anagrams
     * appearing on individual lines.
     *
     * Precondition: Must supply an array that has been sorted alphanumerically and an
     * unsortedWords array whose element positions have been mapped to the sorted array.
     * Postcondition: The method creates the results.txt file if it doesn't exist. If the
     * file cannot be created the method throws and handles the IOException by printing
     * the stack trace and terminating the program. The method loops through the String[]
     * sortedWords and checks to see if the current element is equal to the next element in
     * the sorted array. If it is, the original word that's stored in the String[]
     * unsortedArray is written to the results.txt file without a new line character. If
     * it's not equal, the word is still written from the unsortedArray, but the new line
     * character is included this time. The compareToWord is an integer value that
     * makes sure that the position of the element that's being compared doesn't get
     * out of scope.
     *
     * @param sortedWords   | An array that has been sorted alphanumerically, and whose
     *                        elements are also sorted.
     * @param unsortedWords | An array that maps the sortedWords array but whose elements
     *                        have not been sorted.
     */
    private static void createFile(String[] sortedWords, String[] unsortedWords) {
        try {
            PrintWriter output = null;
            output = new PrintWriter(new FileWriter("results.txt"));

            for (int i = 0; i < sortedWords.length; i++) {
                int compareToWord = (i == sortedWords.length - 1) ? i : i+1;

                if (sortedWords[i].compareTo(sortedWords[compareToWord]) == 0) {
                    output.print(unsortedWords[i] + " ");
                }
                else {
                    output.println(unsortedWords[i]);
                }
            }

            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
