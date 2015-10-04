import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SimpleLinkedListTest{

  @Test
  public void indexOfTest() {
    // Test against the Java Linked List
    SimpleLinkedList<Integer> studentList = new SimpleLinkedList<Integer>();
    LinkedList<Integer> javaList = new LinkedList<Integer>();

    int size = 100;
    for (int i = 0; i < size; i++) {
      int element = (int) (size * Math.random());
      studentList.add(element);
      javaList.add(element);
    }

    int testSize = 10; 

    for (int i = 0; i < testSize; i++) {
        int element = (int) (size * Math.random());
        assertEquals(studentList.indexOf(i),javaList.indexOf(i));
    }
  }

  @Test
  public void reverseTest() {
    // Test against the Java Linked List 
    SimpleLinkedList<Integer> studentList = new SimpleLinkedList<Integer>();
    LinkedList<Integer> javaList = new LinkedList<Integer>();

    int size = 10;

    for (int i = 0; i < size; i++) {
      int element = (int) (size * Math.random());
      studentList.add(element);
      javaList.add(element);
    }

    Collections.reverse(javaList);
    studentList.reverse();

    Iterator<Integer> iteJavaList = javaList.iterator();
    Iterator<Integer> iteStudentList = studentList.iterator();

    while (iteJavaList.hasNext()) {
      assertEquals("reverse function failed", iteJavaList.next(),
          iteStudentList.next());
    }
  }

  @Test
  public void removeDuplicatesTest() {
    SimpleLinkedList<Integer> studentList = new SimpleLinkedList<Integer>();
    LinkedList<Integer> javaList = new LinkedList<Integer>();

    int size = 10000;
    int modFactor = 20;

    for (int i = 0; i < size; i++) {
      int element = ((int) (size * Math.random())) % modFactor;
      studentList.add(element);
      javaList.add(element);
    }

    // Create an ordered set of all elements. Result list shoudl be the same. 
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>(javaList);
    javaList.clear();
    javaList.addAll(linkedHashSet);
    studentList.removeDuplicates();

    Iterator<Integer> iteJavaList = javaList.iterator();
    Iterator<Integer> iteStudentList = studentList.iterator();

    while (iteJavaList.hasNext()) {
      assertEquals("removeDuplicates function failed", iteJavaList.next(),
          iteStudentList.next());
    }
  }


  @Test
  public void interleaveTest() {
    // Test against gold implementation of interleave
    SimpleLinkedList<Integer> studentList1 = new SimpleLinkedList<Integer>();
    SimpleLinkedList<Integer> studentList2 = new SimpleLinkedList<Integer>();
    
    for (int i=1; i<=3;i++) {
        studentList1.add(i);
    }

    for (int i=10; i>=6;i--) {
        studentList2.add(i);
    }

    List<Integer> targetList1 = java.util.Arrays.asList(1, 10, 2, 9, 3, 8, 7, 6);
    List<Integer> targetList2 = java.util.Arrays.asList(10, 1, 9, 2, 8, 3, 7, 6);

    studentList1.interleave(studentList2);
    Iterator<Integer> studentListIter = studentList1.iterator();
    for (Integer i : targetList1) {
        assertEquals(i, studentListIter.next());
    }
    
    
    studentList1 = new SimpleLinkedList<Integer>();
    for (int i=1; i<=3;i++) {
        studentList1.add(i);
    }
    
    studentList2.interleave(studentList1);
    studentListIter = studentList2.iterator();
    for (Integer i : targetList2) { 
        assertEquals(i, studentListIter.next());
    }


  }

  private <T> LinkedList<T> interleave(LinkedList<T> list1, LinkedList<T> list2) {
    LinkedList<T> result = new LinkedList<T>();

    Iterator<T> ite1 = list1.iterator();
    Iterator<T> ite2 = list2.iterator();

    while (ite1.hasNext() || ite2.hasNext()) {
      if (ite1.hasNext()) {
        result.add(ite1.next());
      }
      if (ite2.hasNext()) {
        result.add(ite2.next());
      }
    }

    return result;
  }

}
