import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: Dino Cajic
 *
 * Purpose: To store and retrieve phone record entries.
 *
 * Data Structures Used: Array, LinkedList
 *
 * The phonedir class maintains a list of records containing names (last and first) and phone numbers of a phone
 * company customers. The program will prompt the user for a command, execute the command, then prompt the user for
 * another command. The commands must be chosen from the following possibilities:
 *   a Show all records
 *   d Delete the current record
 *   f Change the first name in the current record
 *   l Change the last name in the current record
 *   n Add a new record
 *   p Change the phone number in the current record
 *   q Quit
 *   s Select a record from the record list to become the current record
 */
public class phonedir {

    /** Stores the Phone Directory individuals in a LinkedList */
    LinkedList<Person> persons = new LinkedList<Person>();

    /** Scanner Class */
    private Scanner input;

    /** The current record of the phone directory */
    private int currentRecord = 0;

    /**
     * phonedir constructor
     * Instantiates the Scanner Class
     */
    public phonedir() {
        input = new Scanner(System.in);
    }

    /**
     * Runs the phone directory program.
     *
     * Precondition: Must run the program.
     * Postcondition: Instantiates the phonedir class and calls the start() method to begin everything.
     * @param args Array
     */
    public static void main(String[] args) {
        phonedir phoneDirectory = new phonedir();
        phoneDirectory.start();
    }

    /**
     * Starts the phone directory program.
     *
     * Precondition: phonedir class must be instantiated in main().
     * Postcondition: Displays the prompt message to specify what to select. Allows the user to select an option
     * until 'q' is specified in which case the program is terminated.
     */
    private void start() {
        String option;
        displayPromptMessage();

        while (input.hasNext()) {
            try {
                option = input.next();

                switch(option) {
                    case "q": System.exit(-1);        break;
                    case "a": showAllRecords();       break;
                    case "d": deleteCurrentRecord();  break;
                    case "f": changeFirstName();      break;
                    case "l": changeLastName();       break;
                    case "n": addNewRecord();         break;
                    case "p": changePhoneNumber();    break;
                    case "s": selectRecord();         break;
                    default:
                        System.out.println("The option you've selected is not available. Please choose from the " +
                                "available options.");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("The option you've selected is not available. Please choose from the " +
                        "available options.");
            } finally {
                displayPromptMessage();
            }
        }
    }

    /**
     * Displays the message to the user.
     *
     * Precondition: Must be called in the start() method.
     * Postcondition: Displays the instructions for the user to follow.
     */
    private void displayPromptMessage() {
        System.out.println("\nA Program to keep a Phone Directory:\n\n" +
                           "          a     Show all records\n" +
                           "          d     Delete the current record\n" +
                           "          f     Change the first name in the current record\n" +
                           "          l     Change the last name in the current record\n" +
                           "          n     Add a new record\n" +
                           "          p     Change the phone number in the current record\n" +
                           "          q     Quit\n" +
                           "          s     Select a record from the record list to become the current record\n");
        System.out.print("Enter a command from the list above (q to quit): ");
    }

    /**
     * Displays the phone directory entries.
     *
     * Precondition: Must add an entry to the phone directory prior to calling.
     * Postcondition: Displays each first name, last name and phone number. The lengths are used to calculate how much
     * white space should be added in order to line up first name, last name and phone number with the headers. If the
     * method is called without having at least one record in the phone directory, a no records found message is
     * displayed and the method execution is terminated.
     */
    private void showAllRecords() {
        if (persons.size() == 0) {
            System.out.println("No current record.");
            return;
        }

        System.out.println("First Name          Last Name           Phone Number       ");
        System.out.println("------------------- ------------------- -------------------");

        for (Person individual: this.persons) {
            System.out.print(individual.getFirstName());
            // Invariant: individual.getFirstName().length() <= i < 20
            for (int i = individual.getFirstName().length(); i < 20; i++) {
                System.out.print(" ");
            }

            System.out.print(individual.getLastName());
            // Invariant: individual.getLastName().length() <= i < 20
            for (int i = individual.getLastName().length(); i < 20; i++) {
                System.out.print(" ");
            }

            System.out.print(individual.getPhoneNumber());
            // Invariant: individual.getPhoneNumber().length() <= i < 20
            for (int i = individual.getPhoneNumber().length(); i < 20; i++) {
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    /**
     * Deletes the currently selected record.
     *
     * Precondition: Must have at least one record in the phone directory. The record to be deleted must be selected
     * either by adding the current record (in which case that record is automatically set as the current record) or
     * by using the selectRecord() method to select a record from the phone directory.
     * Postcondition: Removes the record from the phone directory. Displays that the record has been removed and shows
     * all of the available remaining records within the phone directory. If the method is called without having at
     * least one record in the phone directory, a no records found message is displayed and the method execution is
     * terminated.
     */
    private void deleteCurrentRecord() {
        if (persons.size() == 0 || this.currentRecord == -1) {
            System.out.println("No current record");
            return;
        }

        this.persons.remove( this.currentRecord );
        this.currentRecord = -1;
        System.out.println("Record Removed");
        showAllRecords();
    }

    /**
     * Displays the current record selected.
     *
     * Precondition: Must have at least one record entered; the record must be selected either by adding a new record
     * or implicitly using the select feature to select the item.
     * Postcondition: Displays the first name, last name and phone number of the record.
     */
    private void displayCurrentRecord() {
        System.out.println("Current record is: " +
                persons.get(currentRecord).getFirstName() + " " +
                persons.get(currentRecord).getLastName() + " " +
                persons.get(currentRecord).getPhoneNumber());
    }

    /**
     * Changes the first name of the current record.
     *
     * Precondition: The record that you want to change must be selected; the record must be selected either by adding
     * a new record or implicitly using the select feature to select the item.
     * Postcondition: Prompts the user to enter the first name. Capitalizes the first letter. Makes a copy of the
     * current record. Removes the current record from the phone directory. Updates the first name in the copy that was
     * just created. Gets the index as to where the record should be inserted. Inserts the updated copy back into the
     * phone directory. Signifies that the record has been updated and displays the current record. If the method is
     * called without having at least one record in the phone directory, a no records found message is displayed and
     * the method execution is terminated.
     */
    private void changeFirstName() {
        if (persons.size() == 0 || this.currentRecord == -1) {
            System.out.println("No current record");
            return;
        }

        System.out.print("Enter first name: ");
        String newFirstName = input.next();
        String s1 = newFirstName.substring(0, 1).toUpperCase();
        newFirstName = s1 + newFirstName.substring(1);

        Person current = persons.get(currentRecord);
        persons.remove(currentRecord);
        current.setFirstName(newFirstName);

        getIndexOfInsertion(current);
        this.persons.add(this.currentRecord, current);

        System.out.println("First name has been changed.");
        displayCurrentRecord();
    }

    /**
     * Changes the last name of the current record.
     *
     * Precondition: The record that you want to change must be selected; the record must be selected either by adding
     * a new record or implicitly using the select feature to select the item.
     * Postcondition: Prompts the user to enter the last name. Capitalizes the first letter. Makes a copy of the
     * current record. Removes the current record from the phone directory. Updates the last name in the copy that was
     * just created. Gets the index as to where the record should be inserted. Inserts the updated copy back into
     * the phone directory. Signifies that the record has been updated and displays the current record. If the method
     * is called without having at least one record in the phone directory, a no records found message is displayed and
     * the method execution is terminated.
     */
    private void changeLastName() {
        if (persons.size() == 0 || this.currentRecord == -1) {
            System.out.println("No current record");
            return;
        }

        System.out.print("Enter last name: ");
        String newLastName = input.next();
        String s1 = newLastName.substring(0, 1).toUpperCase();
        newLastName = s1 + newLastName.substring(1);

        Person current = persons.get(currentRecord);
        persons.remove(currentRecord);
        current.setLastName(newLastName);

        getIndexOfInsertion(current);
        this.persons.add(this.currentRecord, current);

        System.out.println("Last name has been changed");
        displayCurrentRecord();
    }

    /**
     * Changes the phone number of the current record.
     *
     * Precondition: The record that you want to change must be selected; the record must be selected either by adding
     * a new record or implicitly using the select feature to select the item.
     * Postcondition: Initializes the phoneNum variable with the current record's phone umber. Enters a while loop to
     * check and see if the number already exists in the phone directory. Prompts the user to enter a phone number.
     * It formats the number into a standard xxx-xxx-xxxx. Checks to see if the number exists in the phone directory.
     * If it does, it notifies the user and asks for him/her to enter the number again (they may enter 'q' to quit or
     * 'a' to display all of the records again). If the phone number does not exist in the phone directory, it makes a
     * copy of the current record. Removes the current record from the phone directory. Updates the phone number in
     * the copy that was just created. Gets the index as to where the record should be inserted. Inserts the updated
     * copy back into the phone directory. Signifies that the record has been updated and displays the current record.
     * If the method is called without having at least one record in the phone directory, a no records found message is
     * displayed and the method execution is terminated.
     */
    private void changePhoneNumber() {
        if (persons.size() == 0 || this.currentRecord == -1) {
            System.out.println("No current record");
            return;
        }

        String phoneNum = persons.get(currentRecord).getPhoneNumber();

        boolean numExists = true;

        while (numExists) {
            System.out.println("Enter phone number: ");
            phoneNum = input.next();
            phoneNum = formatNumber(phoneNum);

            if (phoneNum.equals("q")) {
                System.exit(-1);
            } else if (phoneNum.equals("d")) {
                this.showAllRecords();
            }

            numExists = checkIfPhoneNumberExists(phoneNum);

            if (numExists) {
                System.out.println("Number already exists, try another.");
                System.out.println("You can enter 'q' to quit, 'a' to display all records or add a new number to " +
                        "try again.");
            }
        }

        Person current = this.persons.get( this.currentRecord );
        this.persons.remove( this.currentRecord );
        current.setPhoneNumber(phoneNum);

        getIndexOfInsertion(current);
        this.persons.add(this.currentRecord, current);

        System.out.println("The phone number has been successfully updated.");
        this.displayCurrentRecord();
    }

    /**
     * Checks to see if the phone number exists.
     *
     * Precondition: Must pass a phone number of type string in the format xxx-xxx-xxxx.
     * Postcondition: Cycles through the phone directory and checks to see if the phone number already exists. If it
     * does, it returns true; otherwise, it returns false.
     *
     * @param phoneNumber the number to check and see if it exists in the phone directory.
     * @return boolean
     */
    private boolean checkIfPhoneNumberExists(String phoneNumber) {
        for (Person individual: this.persons) {
            if (individual.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a new entry to the phone directory.
     *
     * Precondition: Must select "n" from the available options.
     * Postcondition: Prompts the user for the first name, last name and phone number. Capitalizes the first and last
     * names. Cycles through a while loop to make sure that the phone number entered does not match a phone number
     * already in the phone directory. Within the loop, it prompts the user to enter a phone number. The number is run
     * through a formatNumber() method to format it into the standard xxx-xxx-xxxx. The number is sent to the
     * checkIfPhoneNumberExists() method to see if it exists in the phone directory. If it does, a prompt is issued to
     * the user to enter a new phone number or enter 'q' to quit the program. The length of the number is verified to
     * make sure that it's 12 characters long and that it matches a north american phone number standard. If everything
     * is ok, the isNumberCorrect variable is set to true so that the loop may terminate. A new Person object is
     * created. The correct insertion place is determined with the getIndexOfInsertion() method. The newly created
     * Person object is inserted into the persons Linked List and the record is displayed.
     */
    private void addNewRecord() {
        System.out.print("Add first name: ");
        String first = input.next();
        String s1 = first.substring(0, 1).toUpperCase();
        first = s1 + first.substring(1);

        System.out.print("Add last name: ");
        String last = input.next();
        s1 = last.substring(0,1).toUpperCase();
        last = s1 + last.substring(1);

        boolean isNumberCorrect = false;
        String number = "";

        while (!isNumberCorrect) {
            System.out.print("Enter phone number: ");
            number = input.next();

            if (number.equals("q")) {
                System.exit(-1);
            }

            number = formatNumber(number);

            if (checkIfPhoneNumberExists(number)) {
                System.out.println("Number already exists. Try again");
                System.out.println("You may also enter 'q' to quit.");
                continue;
            }

            // Validates all North American Phone standards
            // i.e. 123456789 OR 123-456-7890 OR 123.456.7890 OR 123 456 7890 OR (123) 456 7890
            String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

            if (number.length() != 12 || !number.matches(regex)) {
                System.out.println("Incorrect Number entered. Try again.");
                System.out.println("You may enter 'q' to quit or try again.");
            } else {
                isNumberCorrect = true;
            }
        }

        Person individual = new Person(first, last, number);
        getIndexOfInsertion(individual);
        this.persons.add(this.currentRecord, individual);

        displayCurrentRecord();
    }

    /**
     * Formats the phone number into the correct North American phone standard: i.e. xxx-xxx-xxxx.
     *
     * Precondition: Must pass a phoneNumber variable with 10 digits within it.
     * Postcondition: Strips all of the non-digits from the phoneNumber variable. Creates an empty newNum char array
     * that will store the individual phone number characters. Creates a num char array that stores a character copy
     * of the phoneNumber String. Double checks to make sure that the phoneNumber variable only contains digits and
     * checks to see if there are 10 digits stored in it. If there aren't, it returns an empty String. If the correct
     * amount of digits are stored within the phoneNumber String, the createCorrectPhoneFormat() method is called to
     * help construct the newly formatted phone number. The createCorrectPhoneFormat() array stores the characters as
     * newNum array elements. A new String is generated from the newNum array and that string is returned. The final
     * output should be in the format xxx-xxx-xxxx.
     *
     * @param phoneNumber the phone number that will be formatted to the xxx-xxx-xxxx standard.
     * @return String
     */
    private String formatNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");

        String regex = "^[0-9]*$";
        char[] newNum = new char[12];
        char[] num = phoneNumber.toCharArray();

        if (phoneNumber.matches(regex)) {
            if (phoneNumber.length() != 10) {
                return "";
            }

            createCorrectPhoneFormat(0, 3, newNum, num, 1);
            createCorrectPhoneFormat(3, 6, newNum, num, 2);
            createCorrectPhoneFormat(6, 10, newNum, num, 3);
        }

        phoneNumber = new String(newNum);
        return phoneNumber;
    }

    /**
     * Helps to store the correct phone number format into a newNum array.
     *
     * Precondition: Must have newNum of size 12 created in calling method. Must pass the newNum array and the num
     * array. The num array contains the copy of the phone number in array format
     * i.e. num = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]; Must also pass where to start copying the num array and where to
     * end the copying of the num array into the newNum array. Lastly, it must provide the pass argument. The pass
     * argument signifies which time is this being submitted. i.e. the first time will provide digits 1, 2, 3 from the
     * num char array.
     * Postcondition: Cycles through the num array between the start and end positions. Initializes the j variable. If
     * this was the first pass, num[i] would be stored in newNum[i + 0]. If this was the second pass, num[i] would be
     * stored in newNum[i + 1] and if this was the third pass, num[i] would be stored in newNum[i + 2]. The reason for
     * the adding of +1 in the second pass and +2 in the third pass is because the dashes will be added after the first
     * and second pass. i.e. First Pass newNum[1,2,3,-] Second Pass: newNum[1,2,3,-,4,5,6,-] and finally after the
     * Third pass, newNum = [1,2,3,-,4,5,6,-,7,8,9,0].
     *
     * @param start where to start copying the num array into the newNum array.
     * @param end where to end copying the num array into the newNum array.
     * @param newNum the array that will store the formatted copy of the phone number i.e. xxx-xxx-xxxx.
     * @param num the array that stores the copy of the currently submitted, unformatted number.
     * @param pass the time that this method was called. Used to help with creating the dashes '-' in the number.
     */
    private void createCorrectPhoneFormat(int start, int end, char[] newNum, char[] num, int pass) {
        // Invariant: start <= i < end
        for (int i = start; i < end; i++) {
            int j = 0;

            if (pass == 2) {
                j = 1;
            } else if (pass == 3) {
                j = 2;
            }

            newNum[i + j] = num[i];
        }

        if (pass == 1) {
            newNum[end] = '-';
        } else if (pass == 2) {
            newNum[end+1] = '-';
        }
    }

    /**
     * Selects a record based on the first name, last name and phone number.
     *
     * Precondition: Must have at least one entry in the phone directory.
     * Postcondition: Prompts the user to enter the first name and last name. Capitalizes the first and last name.
     * Enters a while loop and prompts the user to enter a phone number. Creates the correct number format:
     * xxx-xxx-xxxx. Checks to see if the number is of valid length and of valid format. If not, the user can try
     * entering the number again or enter 'q' to quit. If the number is of correct format, the loop terminates. A new
     * Person object is created and passed to the getIndexOfPerson() method to attempt to find the person's location
     * in the persons Linked List. If there are no matches, the method recursively calls itself to try again.
     * Otherwise, if there are matches, the record is selected and the current record is displayed. If the method is
     * called without having at least one record in the phone directory, a no records found message is displayed and
     * the method execution is terminated.
     */
    private void selectRecord() {
        if (persons.size() == 0) {
            System.out.println("No current record. You must add at least one record.");
            return;
        }

        showAllRecords();

        System.out.print("Enter first name: ");
        String first = input.next();
        String s1 = first.substring(0, 1).toUpperCase();
        first = s1 + first.substring(1);

        System.out.print("Enter last name: ");
        String last = input.next();
        s1 = last.substring(0,1).toUpperCase();
        last = s1 + last.substring(1);

        boolean isNumberCorrect = false;
        String phoneNumber = "";

        while (!isNumberCorrect) {
            System.out.print("Enter phone number: ");
            phoneNumber = input.next();

            if (phoneNumber.equals("q")) {
                System.exit(-1);
            }

            phoneNumber = formatNumber(phoneNumber);

            //Validates all North American Phone standards
            //i.e. 123456789 OR 123-456-7890 OR 123.456.7890 OR 123 456 7890 OR (123) 456 7890
            String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

            if (phoneNumber.length() != 12 || !phoneNumber.matches(regex)) {
                System.out.println("Incorrect Number entered. Try again.");
                System.out.println("You may enter 'q' to quit or try again.");
            } else {
                isNumberCorrect = true;
            }
        }

        int indexOfPerson = getIndexOfPerson( new Person(first, last, phoneNumber) );

        if (indexOfPerson == -1) {
            System.out.println("No matches. Try again.");
            selectRecord();
        }

        if (indexOfPerson >= 0) {
            this.currentRecord = indexOfPerson;
            System.out.println("Record successfully selected.");
            displayCurrentRecord();
        }

    }

    /**
     * Returns the location of the Person object within the persons Linked List.
     *
     * Precondition: Must supply an argument of type Person.
     * Postcondition: Concatenates the first name, last name and phone number of the Person. Compares the concatenated
     * String to a Person within the persons Linked List. Each one of the individuals receives the same concatenation
     * in order to compare the terms. If a match is found, the position of the Person object is returned; if no match
     * is found, a -1 is returned.
     *
     * @param person the Person Object that contains first name, last name and phone number instance variables.
     * @return int
     */
    private int getIndexOfPerson(Person person) {
        String currentPerson = person.getFirstName() + " " + person.getLastName() + " " + person.getPhoneNumber();

        // Invariant: 0 <= i < persons.size()
        for (int i = 0; i < persons.size(); i++) {
            String compareToPerson = persons.get(i).getFirstName() + " " +
                    persons.get(i).getLastName() + " " +
                    persons.get(i).getPhoneNumber();

            if (currentPerson.toLowerCase().compareTo( compareToPerson.toLowerCase() ) == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the index of where the Person object should be inserted in order in the persons Linked List.
     *
     * Precondition: Must provide a Person object containing a first name, last name and phone number
     * instance variables.
     * Postcondition: Creates a currentPerson String (i.e. Dino Cajic 444-222-3333). Cycles through the persons
     * Linked List. It compares the current record with each record in the phone directory. If the current record is
     * lexicographically smaller, the currentRecord data field is set to that index and the loop abandoned. Otherwise,
     * if the loop reaches the end and the person's information is lexicographically larger than the other Person
     * entries in the phone directory, the currentRecord data field is assigned the value of the size of the persons
     * Linked List i.e. the last entry + 1. It also checks to see if the size of the list is empty. If it is, it's
     * automatically set to 0. This was implemented to make sure that during the deletion of the last name in the
     * entry, when currentRecord is set to -1 that it changes it back to zero when adding a new record.
     *
     * @param current the Person object that needs to be inserted into the persons Linked List.
     */
    private void getIndexOfInsertion(Person current) {
        String currentPerson = current.getLastName() + " " + current.getFirstName() + " " + current.getPhoneNumber();

        if (persons.size() == 0) {
            this.currentRecord = 0;
            return;
        }

        // Invariant: 0 <= i < persons.size()
        for (int i = 0; i < persons.size(); i++) {
            String compareToPerson = persons.get(i).getLastName() + " " +
                    persons.get(i).getFirstName() + " " +
                    persons.get(i).getPhoneNumber();

            if (currentPerson.toLowerCase().compareTo( compareToPerson.toLowerCase() ) < 0) {
                this.currentRecord = i;
                return;
            } else {
                this.currentRecord = i+1;
            }
        }
    }

    /**
     * Stores the person's first name, last name and phone number.
     */
    public class Person {

        /** The First name of an individual. */
        private String firstName;

        /** The Last name of an individual. */
        private String lastName;

        /** An individual's phone number in the format xxx-xxx-xxxx. */
        private String phoneNumber;

        /**
         * Sets the firstName, lastName and phoneNumber data fields with the provided constructor arguments.
         *
         * @param first First Name of an individual.
         * @param last Last Name of an individual.
         * @param phone Phone Number of an individual.
         */
        public Person(String first, String last, String phone) {
            setFirstName(first);
            setLastName(last);
            setPhoneNumber(phone);
        }

        /**
         * Sets the firstName data field as the provided argument.
         * @param first The first name of the person.
         */
        public void setFirstName(String first) {
            firstName = first;
        }

        /**
         * Sets the lastName data field as the provided argument.
         * @param last The last name of the person.
         */
        public void setLastName(String last) {
            lastName = last;
        }

        /**
         * Sets the phoneNumber data field as the provided argument.
         * @param number The phone number of the person.
         */
        public void setPhoneNumber(String number) {
            phoneNumber = number;
        }

        /**
         * Returns the first name of the person.
         * @return String
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Returns the last name of the person.
         * @return String
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Returns the phone number of the person.
         * @return String
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
