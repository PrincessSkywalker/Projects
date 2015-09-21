# Homework 1
COMS W3134 - Data Structures in Java
* 100pts toal
* Due: Sun 9/27 11:59pm

This first homework contains only programming problems. Future problem sets will also contain a theory portion.

Please be aware of the [late homework and academic honesty policy on the course website](http://www.cs.columbia.edu/~bauer/cs3134/homework.html).


To get this homework problem into your own repository, you should, in your `homework-<youruni>` directory, run

```
$ git pull upstream master
```

### Problem 1 - Linked Lists (70pts)

For this problem, you will implement functionality for different types of *List* data structures. Take a look at the file `1/src/SimpleLinkedList.java` that defines a simple LinkedList.

You may not use any data structures from the Java API to solve these problems. 

#### a) (14pts)

Write a method int `public int indexOf(Object o)`, which returns the index of the first occurence of the element `o`, or -1 if the element is not found.  Make sure to use
`.equals(o)` to compare elements, and not `==`.

#### b) (16 pts)
Add a method `public void reverse()` to SimpleLinkedList that flips the order of the list.  For example
```java
SimpleLinkedList <Integer > list = new  SimpleLinkedList <>();
for (i=1; i <=10; i++)
    list.add(i);
list.reverse ();
System.out.println(list);
```
should print
`[ 10 9 8 7 6 5 4 3 2 1 ]`

Can you think of a way  to make `reverse` operate in a single timestep (i.e. the time should not depend on the length of the list).
(Answer add-on questions as comments near the beginning of the method).

#### c) (20 pts)
Write a method ```public void removeDuplicates()``` that removes duplicate elements in the list. Modify the original linked list in place.
For example, if `list` was `[ 1 2 3 1 5 3 2 8 ]`, calling `list.removeDuplicates()` should result in `[ 1 2 3 5 8 ]` .
How many `.equals` comparisons does `removeDuplicates()` take, as a function of **N** (the number of elements in the list)?
Can you come up with a faster algorithm if you assume that the list is sorted in increasing order (describe your answer in a comment)?

#### d) (20 pts)
Write a method
`public void interleave(SimpleLinkedList<T> other)` that interleaves elements from  the `other` list  into  the  linked  list.
If `other` is  longer  than  the  current  list,  its  remaining elements  should  simply  be  appended.

For  example,  if `l1` is `[ 1 2 3 ]`
and
`l2` is `[ 10 9 8 7 6 ]`, `l1.interleave(t2)` would change `l1` to `[ 1 10 2 9 3 8 7 6 ]` and `l2.interleave(l1)` would change `l2` to `[ 10 1 9 2 8 3 7 6 ]` .

### Problem 2 - Range (30 pts)

In python, one can use `range` to generate integer sequences to iterate over.
Write a java class `Range` that can be used as an Iterable and has similar functionality.

To help you along, `Range.java` containing the basic structure of the program has been given to you.

```java

for(Integer j : new Range(1,8,1)) {
  System.out.print(j);
}
// 1234567


for(Integer j : new Range(1,8,2)) {
  System.out.print(j);
}
// 1357

for(Integer j : new Range(1,8)) {
  System.out.print(j);
}
// 1234567

for(Integer j : new Range(8,0,-1)) {
  System.out.print(j);
}
// prints 87654321
```

The args to the constructor are `new Range(int start, int stop, int incr)`

- `start` - first index produced
- `stop` - iteration stops before reaching stop(start/stop are inclusive/exclusive) 
- `incr` - (**this argument is optional for users, defaults to 1. Not optional for you -- you need to implement this with the optionality.**). the increment each time around the loop The best way to implement this is to use two constructors (overloading constructors). Note that `incr` can be negative as well (last example). 


