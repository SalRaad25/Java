/**
 * An implementation of StringBuffer using
 * linked elements.
 *
 * @author Sally Raad
 */

import java.util.*;

public class ITIStringBuffer {
    private SinglyLinkedList<Character> characters;
    private boolean mod;
    private String previousResult;

    /**
     * Constructor used to initialize our implementation of StringBuffer
     */
    public ITIStringBuffer() {
        characters = new SinglyLinkedList<>();
        mod = true;
    }

    /**
     * Constructor used to initialize our implementation of StringBuffer
     *
     * @param firstString
     *             intial string for the buffer's linked list
     */
    public ITIStringBuffer(String  firstString){
        characters = new SinglyLinkedList<>();
        for (char c : firstString.toCharArray()) {
            characters.add(c);
        }
        mod = true;
    }

    /**
     * Adds a string to the buffer's linked list
     *
     * @param nextString
     *             string to be added to the list
     */
    public void append(String nextString){
        for (char c : nextString.toCharArray()) {
            characters.add(c);
        }
        mod = true;
    }

    /**
     * Returns a string representation of the class
     *
     * @return string representing the class
     */
    public String toString(){
        if (characters.size() == 0) return "";
        if (mod) {
            Iterator<Character> characterIterator = characters.iterator();
            char[] currentResult = new char[characters.size()];
            int index = 0;
            while (characterIterator.hasNext()) {
                currentResult[index] = characterIterator.next();
                index++;
            }
            previousResult = new String(currentResult);
            mod = false;
        }
        return previousResult;
    }
}
