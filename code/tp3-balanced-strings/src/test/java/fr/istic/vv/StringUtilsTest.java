package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {


    @Test
    void isBalancedEmpty() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void isBalancedTrue(){
        String[] list = {
                "()",
                "[]",
                "{}",
                "()()",
                "[][]",
                "{}{}",
                "()[]",
                "[]()",
                "[]{}",
                "({})[]",
                "((()))()",
                "[[[]]][]",
                "{{{}}}{}",
                "[{()}]",
        };

        for(String str : list){
            assertTrue(StringUtils.isBalanced(str), '"' + str + '"');
        }
    }

    @Test
    void isBalancedFalse(){
        String[] list = {
                "a",
                "(",
                "[",
                "{",
                "]",
                ")",
                "}",
                "(]",
                "[)",
                "{]",
                "()(",
                "[][",
                "{}{",
                "())",
                "[]]",
                "{}}",
                "([)]",
                "{[}]",
                "(())}"
        };

        for(String str : list){
            assertFalse(StringUtils.isBalanced(str), '"' + str + '"');
        }
    }
}