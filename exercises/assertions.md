# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. **Assertion fails**
   
    En Java les nombres flottants utilisent des binaires et non des fractions décimales. 
    Ainsi cette assertion `assertTrue(3 * .4 == 1.2)` est tout le temps fausse puisque la multiplication s'effectue sur le binaire de `0.4` qui n'est pas précis et donne une valeur approchée du résultat.

2. **Différence entre `assertEquals` et `assertSame`**
   
```java
        String a = "La V&V c'est chouette !";
        String b = "La V&V c'est chouette !";
        String c =  new String("La V&V c'est chouette !";);
```
---

   `assertEquals` : assertion qui vérifie si les deux objets sont égaux (comparaison par identité "equals")

 ```java
    @Test
    void testAssertEquals() {
        assertEquals(a,b); // True
        assertEquals(a,c); // True
    }
```
Les deux cas sont vraies puisque `a,b,c` contiennent la même chaine de caractère.

---

   `assertSame` : assertion qui vérifie si les deux références aux objets sont égaux (comparaison par valeurs "==")

```java
    @Test
    void testAssertSame() {
        assertSame(a,b); // True
        assertSame(a,c); // False
    }
```
Le premier cas est vraie puisque `a` et `b` ont la même réference mémoire.

Le second est faux puisque en faisant un `new` Java crée une nouvelle référence mémoire différente de celle de `a`.

