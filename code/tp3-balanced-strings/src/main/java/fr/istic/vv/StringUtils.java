package fr.istic.vv;

import java.util.*;

public class StringUtils {

    private StringUtils() {}

    private static final Map<Character, Character> opposite;
    static {
        Map<Character, Character> m = new HashMap<>();

        m.put('(', ')');
        m.put('[', ']');
        m.put('{', '}');

        opposite = m;
    }

    /**
     * Know if a string is balanced with open and close []{}() in the good order.
     * This function does not handle other characters and returns false if so.
     * @param str a string with only []{}()
     * @return if the chars are balanced in the good order
     */
    public static boolean isBalanced(String str) {
        Stack<Character> expected = new Stack<>();

        for(char c : str.toCharArray()){
            if(opposite.containsKey(c)){
                // If open char, then put the opposite in the stack
                expected.push(opposite.get(c));
            }else if(!expected.isEmpty() && expected.peek().equals(c)){
                // char == expected --> continue
                expected.pop();
            }else{
                // error
                return false;
            }
        }

        // Valid only if no expected left in the stack
        return expected.empty();
    }

}
