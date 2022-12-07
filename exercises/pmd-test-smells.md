# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

**Liste de BadSmells** :
* DetachedTestCase : pas de `@Test`
* JUnit4SuitesShouldUseSuiteAnnotation : Présence des `suite()` en fonction de la version de JUnit
* JUnit4TestShouldUseAfterAnnotation : vérification de la bonne implémentaton de `tearDown` avec `@After`
* JUnit4TestShouldUseBeforeAnnotation : vérification de la bonne implémentaton de `setUp` avec `@Before`
* JUnit4TestShouldUseTestAnnotation : vérification du décorateur `@Test` avant les tests
* JUnitAssertionsShouldIncludeMessage : vérifie la présence du 3ème paramètre (commentaire) de `assertEquals`
* JUnitSpelling : vérification de la bonne appelation des méthodes `setUp` et `tearDown`
* JUnitStaticSuite : vérifie que le méthode `suite()` possède une variante public et statique
* JUnitTestContainsTooManyAsserts : vérifie la complexité des tests en fonction du nombre d'`assert` effectué
* JUnitTestsShouldIncludeAssert : vérifie qu'il y a au moins un `assert` dans un test
* JUnitUseExpected : vérification de l'annotation `@Test(expected)` lorsqu'un test teste une exeception
* UnnecessaryBooleanAssertion : vérifie s'il y a des `assertTrue(X)` qui évalue la même chose
* UseAssertEqualsInsteadOfAssertTrue : vérification de l'usage de `assertEquals` au lieu d'un `assertTrue`
* UseAssertNullInsteadOfAssertTrue : vérification de l'usage de `assertNull` au lieu d'un `assertTrue`
* UseAssertSameInsteadOfAssertTrue : vérification de l'usage de `assertSame` au lieu d'un `assertTrue`
* UseAssertTrueInsteadOfAssertEquals : vérification de l'usage de `assertTrue` lorsqu'on compare avec une booléen
---
Utilisation de `AbstractArrayListTest` :

```
commons-collections-master\src\test\java\org\apache\commons\collections4\AbstractArrayListTest.java:42:  JUnitTestContainsTooManyAsserts:        Unit tests should not contain more than 1 assert(s)
```
L'erreur provient de ce test :
```java 

    @Test
    public void testNewArrayList() {
        final ArrayList<E> list = makeObject();
        assertTrue("New list is empty", list.isEmpty());
        assertEquals("New list has size zero", 0, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

```

Ici on remarque que comme beaucoup de test, il y a plusieurs `assert` ce qui complexifie le role de chaque test. 


D'autre part on peut remarquer qu'une exception est testée or cela n'est pas indique avec le `@Test(expected=Exception.class)`.
