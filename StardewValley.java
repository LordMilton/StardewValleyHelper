/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stardewvalley;

/**
 * @author Bradley Dufour
 * @date 2016-04-20
 */
import java.io.*;
import java.util.*;

public class StardewValley {

    private static final String FILENAME = "Characters.txt";
    private static int numCharacters; //This will be calculated later on
    private static ArrayList<String> miscInfo = new ArrayList<String>();
    private static Scanner scan = new Scanner(System.in);
    
    //All part of the sortCharList mergeSort
    //************************
    /**
    * sortCharList - Calls a mergeSort function
    * @helper
    * 
    * @param charList ArrayList of Villagers to sort
    */
    static void sortCharList(ArrayList<Villager> charList) {
        mergeSort(charList, 0, charList.size()-1);
    }

    /**
    * mergeSort - Sorts a list of Villagers in alphabetical order based on
    * their names.
    * @helper
    * @sort
    * @recursive
    * 
    * @param charList ArrayList of Villagers to sort
    * @param left Left side of section to sort
    * @param right Right side of section to sort
    */
    static void mergeSort(ArrayList<Villager> charList, int left, int right) {
        if (left == right) {
            return;
        }
        int endOfLeftHalf = (left + right) / 2;
        mergeSort(charList, left, endOfLeftHalf);
        mergeSort(charList, endOfLeftHalf+1, right);
        mergeLists(charList, left, right);
    }

    /**
    * mergeLists - Given a range of elements in an ArrayList with two sorted halves,
    * merge the two halves and fill in the given array with the newly sorted
    * information.
    * @helper
    * @sort
    * 
    * @param charList ArrayList of Villagers to sort
    * @param left Left side of section to sort
    * @param right Right side of section to sort
    */
    static void mergeLists(ArrayList<Villager> charList, int left, int right) {
        int endOfLeftHalf = (left + right) / 2;
        Villager[] temp = new Villager[right-left+1];
        int el = left;
        int arr = endOfLeftHalf + 1;
        int i = 0;
        while (el <= endOfLeftHalf  &&  arr <= right) {
            if (charList.get(el).getName().compareTo(charList.get(arr).getName()) < 0) {
                temp[i] = charList.get(el);
                el++;
                i++;
            } else {
                temp[i] = charList.get(arr);
                arr++;
                i++;
            }
        }

        // One of the two halves is done.
        if (el > endOfLeftHalf) {
            //Left side ended first
            while (arr <= right) {
                temp[i] = charList.get(arr);
                arr++;
                i++;
            }
        } else {
            //Right side ended first
            while (el <= endOfLeftHalf) {
                temp[i] = charList.get(el);
                el++;
                i++;
            }
        }

        // Fill correct parts of charList with temp's info
        for (int k=0; k<right-left+1; k++) {
            charList.set(left+k, temp[k]);
        }
    }
    //************************
    
    /**
    * findPerson - Looks for a person in the ArrayList of Villagers who matches
    * the given name
    * @helper
    * @search
    * 
    * @param name Name of Villager to look for
    * @param charList ArrayList of Villagers to search in
    */
    private static int findPerson(String name, ArrayList<Villager> charList) {
        for (int i = 0; i < charList.size(); i++) {
            if (charList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return (-1);
    }

    /**
    * doAThing - Based off the user's response to the printed menu, responds and
    * creates changes appropriately
    * 
    * @param charList List of Villagers the method will create changes to
    * @param choice Choice the user made based on printMenu()
    */
    private static void doAThing(ArrayList<Villager> charList, int choice) {
        switch (choice) {
            //Add a 'loved' gift?
            case 1:
                System.out.print("\nWhat is the gift? ");
                String gift = scan.next(); scan.nextLine();
                String person;

                int personNum;
                boolean correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                charList.get(personNum).addLove(gift);
                writeToFile(charList);
                break;
            //Add a 'liked' gift?
            case 2:
                System.out.print("\nWhat is the gift? ");
                gift = scan.next(); scan.nextLine();

                correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                charList.get(personNum).addLike(gift);
                writeToFile(charList);
                break;
            //Add a 'disliked' gift?
            case 3:
                System.out.print("\nWhat is the gift? ");
                gift = scan.next(); scan.nextLine();

                correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                charList.get(personNum).addDislike(gift);
                writeToFile(charList);
                break;
            //See all information about a certain gift??
            case 4:
                System.out.print("What is the gift? ");
                gift = scan.next(); scan.nextLine();
                ArrayList<Villager> lovers = new ArrayList<Villager>(0);
                for (Villager v : charList) {
                    for (String g : v.getLove()) {
                        if (g.equalsIgnoreCase(gift)) {
                            lovers.add(v);
                        }
                    }
                }
                if (lovers.isEmpty()) {
                    System.out.println("No one appears to love " + gift + ".");
                } else {
                    for (int i = 0; i < lovers.size()-1; i++) {
                        System.out.print(lovers.get(i).getName() +", ");
                    }
                    System.out.print(lovers.get(lovers.size()-1) +" ");
                    System.out.print("love receiving " + gift + ".");
                    System.out.println();
                }
                //***************************************************************
                //Like
                ArrayList<Villager> likers = new ArrayList<Villager>(0);
                for (Villager v : charList) {
                    for (String g : v.getLike()) {
                        if (g.equalsIgnoreCase(gift)) {
                            likers.add(v);
                        }
                    }
                }
                if (likers.isEmpty()) {
                    System.out.println("No one appears to like " + gift + ".");
                } else {
                    for (int i = 0; i < likers.size()-1; i++) {
                        System.out.print(likers.get(i).getName() +", ");
                    }
                    System.out.print(likers.get(likers.size()-1) +" ");
                    System.out.print("like receiving " + gift + ".");
                    System.out.println();
                }
                //***************************************************************
                //Dislike
                ArrayList<Villager> dislikers = new ArrayList<Villager>(0);
                for (Villager v : charList) {
                    for (String g : v.getDislike()) {
                        if (g.equalsIgnoreCase(gift)) {
                            dislikers.add(v);
                        }
                    }
                }
                if (dislikers.isEmpty()) {
                    System.out.println("No one appears to dislike " + gift + ".");
                } else {
                    for (int i = 0; i < dislikers.size()-1; i++) {
                        System.out.print(dislikers.get(i).getName() +", ");
                    }
                    System.out.print(dislikers.get(dislikers.size()-1) +" ");
                    System.out.print("dislike receiving " + gift + ".");
                    System.out.println();
                }
                System.out.println("[Enter to move on]");
                scan.nextLine();
                break;
            //See all information about a certain person?
            case 5:
                correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    }
                    else
                        correctPerson = true;
                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                if (charList.get(personNum).getLove().isEmpty()) {
                    System.out.print(person + " seems to love nothing.");
                } 
                else if(charList.get(personNum).getLove().size() == 1)
                {
                    System.out.print(charList.get(personNum).getLove().get(0) +" is loved by "+ person);
                }
                else {
                    for (int i = 0; i < charList.get(personNum).getLove().size()-1; i++) {
                        System.out.print(charList.get(personNum).getLove().get(i) + ", ");
                    }
                    System.out.print(charList.get(personNum).getLove().get(charList.get(personNum).getLove().size()-1) +" ");
                    System.out.print("are loved by " + person);
                }
                System.out.println();
                //*********************************************************************
                //Like
                if (charList.get(personNum).getLike().isEmpty()) {
                    System.out.print(person + " seems to like nothing.");
                } 
                else if(charList.get(personNum).getLike().size() == 1)
                {
                    System.out.print(charList.get(personNum).getLike().get(0) +" is liked by "+ person);
                }
                else {
                    for (int i = 0; i < charList.get(personNum).getLike().size()-1; i++) {
                        System.out.print(charList.get(personNum).getLike().get(i) + ", ");
                    }
                    System.out.print(charList.get(personNum).getLike().get(charList.get(personNum).getLike().size()-1) +" ");
                    System.out.print("are liked by " + person);
                }
                System.out.println();
                //*********************************************************************
                //Dislike
                if (charList.get(personNum).getDislike().isEmpty()) {
                    System.out.print(person + " seems to dislike nothing.");
                } 
                else if(charList.get(personNum).getDislike().size() == 1)
                {
                    System.out.print(charList.get(personNum).getDislike().get(0) +" is disliked by "+ person);
                }
                else {
                    for (int i = 0; i < charList.get(personNum).getDislike().size()-1; i++) {
                        System.out.print(charList.get(personNum).getDislike().get(i) + ", ");
                    }
                    System.out.print(charList.get(personNum).getDislike().get(charList.get(personNum).getDislike().size()-1) +" ");
                    System.out.print("are disliked by " + person);
                }
                System.out.println();
                System.out.println("[Enter to move on]");
                scan.nextLine();
                break;
            //Check if a person is single?
            case 6:
                correctPerson = false;
                do {
                    System.out.print("To whom does this apply? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                if (charList.get(personNum).isSingle()) {
                    System.out.println(person + " is single.");
                } else {
                    System.out.println(person + " is not single.");
                }
                System.out.println("[Enter to move on]");
                scan.nextLine();
                break;
            //Set whether or not a character is single.
            case 7:
                correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);
                
                char answer;
                boolean doneOnce = false;
                do{
                    if(doneOnce)
                        System.out.println("Answer must be '(Y)es' or '(N)o'");
                    System.out.println("Is this character single? [y/n]");
                    answer = scan.next().charAt(0);
                    doneOnce = true;
                }while(answer != 'y' && answer != 'n' && answer != 'Y' && answer != 'N');
                boolean singleness;
                if(answer == 'y' || answer == 'Y')
                    singleness = true;
                else //if answer was 'no'
                    singleness = false;
                    
                charList.get(personNum).setSingleness(singleness);
                break;
                
            //Set a person's birthday?
            case 8:
                correctPerson = false;
                do {
                    System.out.print("Whom does this apply to? ");
                    person = scan.next(); scan.nextLine();

                    personNum = findPerson(person, charList);
                    if (personNum == -1) {
                        correctPerson = checkForNewCharacter(person, charList);
                    } else {
                        correctPerson = true;
                    }

                    if (correctPerson) {
                        personNum = findPerson(person, charList);
                    }
                } while (!correctPerson);

                System.out.print("What is said person's birthday?");
                String date = scan.nextLine();
                charList.get(personNum).setBirthday(date);
                break;
            //Check if there are any birthdays today?
            case 9:
                System.out.print("What is the date? ");
                date = scan.nextLine();
                boolean birthdayFound = false;
                for (Villager person9 : charList) {
                    if ((person9.getBirthday().equals(date)) && !birthdayFound) {
                        System.out.println(person9.getName() + "'s birthday is today, " + date + ".");
                        birthdayFound = true;
                    }
                }
                if (!birthdayFound) {
                    System.out.println("No one's birthday is today, " + date + ".");
                }
                System.out.println("[Enter to move on]");
                scan.nextLine();
                break;
            //Add any miscellaneous information?
            case 10:
                System.out.println("What information?");
                miscInfo.add(scan.nextLine());
                break;
            //View all miscellaneous information?
            case 11:
                System.out.println("Keyword?");
                String keyword = scan.nextLine();
                for (String info : miscInfo) {
                    if (info.toUpperCase().indexOf(keyword.toUpperCase()) >= 0) {
                        System.out.println(info);
                    }
                }
                System.out.println();
                System.out.println("[Enter to move on]");
                scan.nextLine();
                break;
            //Save and quit?
            case 12:
                writeToFile(charList);
                scan.close();
                System.exit(0);
            default:
                System.out.println("That was not an applicable choice");
        }
    }

    /**
     * checkForNewCharacter - Checks if user wants to add a new character with
     * the given name or if input was just a fluke.
     *
     * @helper
     *
     * @param name Name of potential new character
     * @return True if the character was added, false if the user did not want
     * to add the new character
     */
    private static boolean checkForNewCharacter(String name, ArrayList<Villager> charList) {
        System.out.println("Would you like to add a new character "
                + "named " + name + "? [y/n]");
        char response = scan.next().charAt(0); scan.nextLine();
        boolean correctResponse = false;
        do {
            if (response == 'y') {
                charList.add(new Villager(name));
                sortCharList(charList);
                System.out.println(name +" has been defined as not single and they're birthday is unknown.\n"+
                                   "This can be changed after returning to the menu.");
                return true;
            }
            if (response == 'n') {
                return false;
            }
            System.out.println("You must provide a yes or no response.");
            System.out.println("Would you like to add a new character "
                    + "named " + name + "? [y/n]");
            response = scan.next().charAt(0); scan.nextLine();
        } while (!correctResponse);

        return false; //This should never be reached
    }

    /**
    * printMenu - Prints the menu of options to the screen
    * @helper
    */
    private static void printMenu() {
        System.out.println("Would you like to:");
        System.out.println("1) Add a 'loved' gift?");
        System.out.println("2) Add a 'liked' gift?");
        System.out.println("3) Add a 'disliked' gift?");
        System.out.println("4) See all information about a certain gift?");
        System.out.println("5) See all information about a certain person?");
        System.out.println("6) Check if a person is single?");
        System.out.println("7) Set whether or not a person is single?");
        System.out.println("8) Set a person's birthday?");
        System.out.println("9) Check if there are any birthdays today?");
        System.out.println("10) Add any miscellaneous information");
        System.out.println("11) View all miscellaneous information?");
        System.out.println("12) Save and quit?");
    }

    /**
    * updateCharList - Creates a Villager and adds that to the list based on the
    * provided line of information
    * @helper
    * 
    * @param info Information for creating the Villager
    * @param charList List of Villagers to which the new Villager will be added
    */
    private static void updateCharList(String info, ArrayList<Villager> charList) {
        String[] charInfo = info.split(" ");
        //Finds birthday String
        String birthday = charInfo[charInfo.length - 2] + " " + charInfo[charInfo.length - 1];
        //Finds name String
        String name = charInfo[1];
        boolean single = false;
        //Finds 'single' boolean
        if (charInfo[charInfo.length - 3].equals("true")) {
            single = true;
        }
        Villager villager = new Villager(name, single, birthday);
        int position = 3;
        //Fills in the Villager's loved items
        while (!charInfo[position].equals("like")) {
            villager.addLove(charInfo[position]);
            position++;
        }
        position++;
        //Fills in the Villager's liked items
        while (!charInfo[position].equals("dislike")) {
            villager.addLike(charInfo[position]);
            position++;
        }
        position++;
        //Fills in the Villager's disliked items
        for (position = position; position < charInfo.length - 3; position++) {
            villager.addDislike(charInfo[position]);
        }
        
        charList.add(villager);
    }

    /**
    * writeToFile - Writes all of the held information to the database text file
    * 
    * @param charList List of all the characters with information to write to the
    *        file
    */
    private static void writeToFile(ArrayList<Villager> charList) {
        try {
            FileWriter write = new FileWriter(FILENAME);
            BufferedWriter writer = new BufferedWriter(write);
            for (Villager c : charList) {
                writer.write("Character " + c.getName() + " love ");
                for (String g : c.getLove()) {
                    writer.write(g + " ");
                }
                writer.write("like ");
                for (String g : c.getLike()) {
                    writer.write(g + " ");
                }
                writer.write("dislike ");
                for (String g : c.getDislike()) {
                    writer.write(g + " ");
                }
                writer.write(c.isSingle() + " ");
                writer.write(c.getBirthday());
                writer.newLine();
            }
            for (String info : miscInfo) {
                writer.write(info);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.err.println("Failed to write to file");
            System.exit(0);
        }
    }
    //***********************************************************************
    //***********************************************************************

    public static void main(String[] args) throws Exception {
        //Filling array from file before accepting user input
        Scanner fileScan = new Scanner(new java.io.File(FILENAME));
        ArrayList<Villager> charList = new ArrayList<Villager>(numCharacters);

        String nextLine = fileScan.nextLine();
        int counter = 0;
        while (nextLine.startsWith("Character") && fileScan.hasNextLine()) {
            counter++;
            updateCharList(nextLine, charList);
            if(fileScan.hasNextLine())
                nextLine = fileScan.nextLine();
        }
        numCharacters = counter;
        
        if(fileScan.hasNextLine())
            miscInfo.add(nextLine);
        while (fileScan.hasNextLine()) {
            miscInfo.add(fileScan.nextLine());
        }
        fileScan.close();
        //User input
        do {
            printMenu();
            int answer = scan.nextInt(); scan.nextLine();
            doAThing(charList, answer);
        } while (true);
    }
    //***********************************************************************
    //***********************************************************************
}
