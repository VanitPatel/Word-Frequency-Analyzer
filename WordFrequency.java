import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Name:        Patel, Vanit
 * Project:     #3
 * Due:         11/15/2024
 * Course       cs-2400-02 
 * 
 * Descrption:  
 *              This project takes in the file "usconstitution.txt". 
 *              The program collects only words in the file, not numbers and puts them in a hash dictionary.
 *              It finds the unique words, shows the word count for each word in the file, and shows the number of collision in the hash dictionary
 */
public class WordFrequency {
    public static void main (String[] args) throws FileNotFoundException {

        File file = new File("usconstitution.txt");
        Scanner scanner = new Scanner(file);

        DictionaryInterface <String, Integer> dictionary1361 = new HashedDictionary<>(1361);
        DictionaryInterface <String, Integer> dictionary2003 = new HashedDictionary<>(2003);
        DictionaryInterface <String, Integer> dictionary3001 = new HashedDictionary<>(3001);

        /**
         * converts the words in file into lowercase.
         * adds them to the dictionary
         */

        while(scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            addWord(dictionary1361, word);
            addWord(dictionary2003, word);
            addWord(dictionary3001, word);
        }
        scanner.close();

        /**
         * Start of the I/O
         * shows the word count of each words in the dictioanry and the number of unqiue words
         * shows the number of collision in each hash dictionary length (1361, 2003, 3001)
         */
        System.out.println("Word Frequency by V. Patel");

        wordCountAndUniqueWord(dictionary1361);

        System.out.println("\nTable");
        System.out.println("Length  Collisions");
        System.out.println("  1361  " + ((HashedDictionary<String, Integer>) dictionary1361).getCollisionCount());
        System.out.println("  2003  " + ((HashedDictionary<String, Integer>) dictionary2003).getCollisionCount());
        System.out.println("  3001  " + ((HashedDictionary<String, Integer>) dictionary3001).getCollisionCount());


    }

    /**
     * Goes through each words in the dictionary 
     * If the word contains a number then it will not be processed into the hash dictionary
     * If the word already exist in the hash dictionary, then the value of the word will increase by 1
     * If the word does not exist in the hashDictionary, then the value of the word will be 1
     * @param dictionary sends the words to each hash dictionary length
     * @param word checks if the word has numbers
     */

    private static void addWord(DictionaryInterface<String, Integer> dictionary, String word) {
        try {
            Integer.parseInt(word);
        }
        catch (NumberFormatException e) {
            Integer count = dictionary.getValue(word);
            
            if (count == null) {
                dictionary.add(word, 1);
            } else  {
                dictionary.add(word, count + 1);
            }
        }
    }

    /**
     * prints out the word count and the number of unique words 
     * Uses the iterator object to find the each word and their value(word count)
     * @param dictionary takes in the dictionary with the length of 1361
     */
    private static void wordCountAndUniqueWord(DictionaryInterface<String, Integer> dictionary) {
            
        System.out.println("Count Word");
        System.out.println("----- --------------------");
        int uniqueWordCount = 0;
        
        Iterator<String> iterator = dictionary.getKeyIterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer count = dictionary.getValue(key);
            System.out.printf("%-5d %-20s\n", count, key);
            uniqueWordCount++;
        }
        System.out.println("\nUnique word count = " + uniqueWordCount);
    }
}