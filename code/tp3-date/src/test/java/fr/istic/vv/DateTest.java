package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    static Integer[][] dateValidSet = {
            {1, 1, 0},
            {1, 1, 2000},
            {1, 1, -2000},
            {1, 2, 0},
            {1, 12, 0},
            {31, 1, 0},
            {27, 2, 0},
            {31, 3, 0},
            {30, 4, 0},
            {31, 5, 0},
            {30, 6, 0},
            {31, 7, 0},
            {31, 8, 0},
            {30, 9, 0},
            {31, 10, 0},
            {30, 11, 0},
            {31, 12, 0},
    };
    static Integer[][] dateInvalidSet = {
            {0, 1, 0},
            {1, 0, 0},
            {-1, 0, 0},
            {0, -1, 0},
            {32, 1, 0},
            {29, 2, 1},
            {32, 3, 1},
            {1, 13, 1},
    };

    @Test
    void isValidDate() {
        for(Integer[] list : dateValidSet)
            assertTrue(Date.isValidDate(list[0], list[1], list[2]), list[0].toString() + '/' + list[1] + '/' + list[2]);
        for(Integer[] list : dateInvalidSet)
            assertFalse(Date.isValidDate(list[0], list[1], list[2]), list[0].toString() + '/' + list[1] + '/' + list[2]);
    }

    @Test
    void isLeapYear() {
        Integer[] notLeap = {
            1, 2, 3, 5, 6, 7,
            100, 200, 300,
            399, 401, 2100,
        };
        Integer[] leap = {
                0, 4, 8, 12,
                96, 104, 108,
                396, 400, 404,
                1996, 2000, 2004, 2008
        };
        for(int y : notLeap)
            assertFalse(Date.isLeapYear(y), String.valueOf(y));
        for(int y : leap)
            assertTrue(Date.isLeapYear(y), String.valueOf(y));
    }

    @Test
    void getLastDayOfMonth() {
        Integer[] expectedNotLeap = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Integer[] expectedLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for(int i=0; i<12; i++) {
            assertEquals(expectedNotLeap[i], Date.getLastDayOfMonth(i + 1, 1), String.valueOf(i+1));
            assertEquals(expectedLeap[i], Date.getLastDayOfMonth(i + 1, 2004), String.valueOf(i+1));
        }
    }

    @Test
    void nextDate() throws InvalidDate {
        assertEquals(
                new Date(2, 1, 0),
                (new Date(1, 1, 0)).nextDate()
        );
        assertEquals(
                new Date(1, 2, 0),
                (new Date(31, 1, 0)).nextDate()
        );
        assertEquals(
                new Date(1, 1, 1),
                (new Date(31, 12, 0)).nextDate()
        );
        assertEquals(
                new Date(31, 12, 0),
                (new Date(30, 12, 0)).nextDate()
        );
        assertEquals(
                new Date(1, 12, 0),
                (new Date(30, 11, 0)).nextDate()
        );
        assertEquals(
                new Date(1, 3, 1),
                (new Date(28, 2, 1)).nextDate()
        );
        assertEquals(
                new Date(1, 3, 0),
                (new Date(28, 2, 0)).nextDate().nextDate()
        );
    }

    @Test
    void previousDate() throws InvalidDate {
        assertEquals(
                new Date(1, 1, 0),
                (new Date(2, 1, 0)).previousDate()
        );
        assertEquals(
                new Date(31, 1, 0),
                (new Date(1, 2, 0)).previousDate()
        );
        assertEquals(
                new Date(29, 2, 0),
                (new Date(1, 3, 0)).previousDate()
        );
        assertEquals(
                new Date(28, 2, 1),
                (new Date(1, 3, 1)).previousDate()
        );
        assertEquals(
                new Date(31, 12, 0),
                (new Date(1, 1, 1)).previousDate()
        );
    }

    @Test
    void constructorShouldThrow(){
        for(Integer[] list : dateValidSet){
            assertDoesNotThrow(() -> new Date(list[0], list[1], list[2]), list[0].toString() + '/' + list[1] + '/' + list[2]);
        }
        for(Integer[] list : dateInvalidSet){
            assertThrows(InvalidDate.class, () -> new Date(list[0], list[1], list[2]), list[0].toString() + '/' + list[1] + '/' + list[2]);
        }
    }

    @Test
    void compareTo() throws InvalidDate {
        assertEquals(
                0,
                (new Date(1, 1, 0)).compareTo(new Date(1, 1, 0))
        );
        assertEquals(
                0,
                (new Date(15, 2, 2001)).compareTo(new Date(15, 2, 2001))
        );
        assertEquals(
                -1,
                (new Date(1, 1, 0)).compareTo(new Date(2, 1, 0))
        );
        assertEquals(
                -1,
                (new Date(1, 1, 0)).compareTo(new Date(1, 2, 0))
        );
        assertEquals(
                -1,
                (new Date(1, 1, 0)).compareTo(new Date(1, 1, 1))
        );
        assertEquals(
                1,
                (new Date(2, 1, 0)).compareTo(new Date(1, 1, 0))
        );
        assertEquals(
                1,
                (new Date(1, 2, 0)).compareTo(new Date(1, 1, 0))
        );
        assertEquals(
                1,
                (new Date(1, 1, 1)).compareTo(new Date(1, 1, 0))
        );


        assertEquals(
                -1,
                (new Date(31, 1, 0)).compareTo(new Date(1, 2, 0))
        );
        assertEquals(
                -1,
                (new Date(31, 12, 0)).compareTo(new Date(1, 1, 1))
        );
        assertEquals(
                1,
                (new Date(1, 2, 0)).compareTo(new Date(31, 1, 0))
        );
        assertEquals(
                1,
                (new Date(1, 1, 1)).compareTo(new Date(31, 12, 0))
        );
    }

    @Test
    void compareToNull() throws NullPointerException {
        assertThrows(
                NullPointerException.class,
                () ->  (new Date(1, 1, 1)).compareTo(null)
        );
    }

    @Test
    void testToString() throws InvalidDate {
        assertEquals(
                "01/01/2001",
                (new Date(1, 1, 2001)).toString()
        );
        assertEquals(
                "10/01/2001",
                (new Date(10, 1, 2001)).toString()
        );
        assertEquals(
                "01/10/2001",
                (new Date(1, 10, 2001)).toString()
        );
        assertEquals(
                "01/01/00",
                (new Date(1, 1, 0)).toString()
        );
        assertEquals(
                "01/01/05",
                (new Date(1, 1, 5)).toString()
        );
        assertEquals(
                "01/01/10",
                (new Date(1, 1, 10)).toString()
        );
        assertEquals(
                "01/01/100",
                (new Date(1, 1, 100)).toString()
        );
        assertEquals(
                "01/01/1000",
                (new Date(1, 1, 1000)).toString()
        );
    }

    @Test
    void testEquals() throws InvalidDate {
        assertTrue((new Date(1, 1, 1)).equals(new Date(1, 1, 1)));
        assertFalse((new Date(1, 1, 0)).equals(new Date(1, 1, 1)));
        assertFalse((new Date(1, 1, 0)).equals(new String("hello")));
        assertFalse((new Date(1, 1, 0)).equals(null));
    }
}