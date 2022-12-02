import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class ClasseTest {
    @Test
    void testCopyInt() {
        // Les deux objets sont égaux
        int a=1;
        int b=1;
        int c=a;
        assertSame(a,b); // False
        assertSame(a,c); // True

        // Les deux objets ne sont pas égaux
        int d=9;
        assertSame(a,d); // False

    }

    @Test
    void testNewInt() {
        
    }
}
