import junit.framework.*;
import static org.junit.Assert.assertEquals;


public class Test extends TestCase{


  @Test
  public void test1() throws Exception {
    int a=Classe.calculer();
    int b=Classe.calculer();
    assertEquals(a,b);
  }

  @Test
  public void test2() throws Exception {
    int a=Classe.calculer();
    int b=a;
    assertEquals(a,b);
  }

}
