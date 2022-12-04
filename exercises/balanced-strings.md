# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

Note : La fonction ne prend pas en compte d'autres caractères que [](){}

### Input space partitioning

```
true:
    EMPTY
    {}
    []
    ()
    {{}}
    [[]]
    (())
    {}{}
    {}[]
    ()[]
    ()[{}]
false:
    {
    }
    [
    ]
    (
    )
    {[
    }]
    ([
    )]
    ()[
    ([)
    ([]
```

Pour commencer, il ya le test du string vide qui doit être vrai.
On sépare maintenante les cas vrais et faux

Vrai : On commence par des cas simples, avec une ouverture et une fermeture pour chaque type. Ensuite, on en ajoute 2 imbriqué, et 2 à la suite. On test ensuite quelques mélanges pour vérifier que tout se passe bien.

Faux : On commence par tester avec un seul caractère (forcément faux). Puis deux caractères ouvrants, et deux caractères différents. Ensuite, il faut tester une fermeture qui est valide seulement en terme de nombre de caractère, mais pas valide en terme de suite (`([)]`).

Les cas les plus importants sont testés. Toute autre String est une extension de ces exemples. Le test `())` à permis de relever une problème dans le code : Il manquait une condition sur la taille du stack lorsque l'on get une valeur.

### Statement coverage

Code :
```java
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
```

- Pas de passage dans la boucle : `""`
- 1er if vrai : `"("` est ouvrant, on passe dans le 1er if. Pas d'autre tour de boucle.
- 2ème if vrai : `"()"` Premier tour de boucle, on ajoute dans le stack. 2ème tour, on passe dans le 2ème if.
- else vrai : `")"` La map le contient pas de clé de char fermant, on passe dans le else.

Avec ces 4 tests, on peut couvrir toutes les lignes.

### Check bools

1. `opposite.containsKey(c)`\
Vrai : `"("`\
Faux : `")"`

2. `!expected.isEmpty() && expected.peek().equals(c)`\
Vrai : `"()"`\
Faux :
    - `")"`
    - `"(]"`

3. `expected.empty()`\
Vrai : `""`\
Faux : `"("`

### PIT evaluation

```
================================================================================
- Statistics
================================================================================
>> Generated 6 mutations Killed 6 (100%)
>> Ran 7 tests (1.17 tests per mutation)
```
Pour chaque mutation, les tests ont su capturer les erreurs.