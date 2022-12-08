# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

# Answer
## Input space
Pour couvrir tous les input possible, 3 cas de test :
- Ajouter des chiffres dans l'ordre croissant
- Ajouter des chiffres dans l'ordre décroissant
- Ajouter des chiffres aléatoires

Pour chaque cas, on le répète entre 1 et n nombres

## Coverage
Le test coverage est de 100%

## Predicate
Il n'y a pas de conditions avec plus de 2 booléens

## PIT test
```
================================================================================
- Statistics
================================================================================
>> Generated 30 mutations Killed 28 (93%)
>> Ran 34 tests (1.13 tests per mutation)
```
Le coverage est de 93%. Des warnings de TIMED_OUT apparaissent, car si une condition est toujours vraie, alors les boucles de l'algorithme devient infinie et ne peut se terminer.

Les 2 mutant qui survivent vienne de ces conditions :
```
if(left < array.size() && comparator.compare(array.get(left), array.get(min)) < 0)
    min = left;
if(right < array.size() && comparator.compare(array.get(right), array.get(min)) < 0)
    min = right;
```

Ces conditions sevent à déterminer le minimum. Effectivement, que cette condition soit `<` ou `<=`, cela ne change rien. Que l'on garde le nouvel ou l'ancien élément, le résultat est le même. Les tests ne relèvent donc pas ce problème, car dans la description du problème, il n'est pas mentionné de garder l'ordre d'insertion si les valeurs sont égales. Les conditions de boundary à gauche est forcément vraie, car le changer en `<=` causerait une erreur avec le `array.get()`.