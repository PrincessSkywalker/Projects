import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;

public class BwogBot {
  private Map<String, Integer> map = new AvlMap<>();
  private List<String> keys = new LinkedList<>();

  public BwogBot() {

  }

  // I choose to use AvlMap because it ran in about 1 second less
  // the resizing of the SeparateChainingMap causes outweighs the 
  // improved get performance.
  public void readFile(String fileName) throws IOException {
    Scanner scan = new Scanner(new File(fileName));
    while(scan.hasNext()){
      String word = scan.next();
      map.put(word, getCount(word)+1);
      if(!keys.contains(word)){
        keys.add(word);
      }
    }
  }

  public int getCount(String word) {
    Integer i = map.get(word);
    if (i == null) {
      return 0;
    }
    return i;
  }

  private class CountCompare implements Comparator<String>{
    public int compare(String s1, String s2) {
      return getCount(s2) - getCount(s1);
    }
  }

  // The words are in english in general, but to some extent give us
  // an insight into the Bwog contents (ie. Columbia, students, Barnard).
  // This is helpful in that it gives us an overview of the most commonly 
  // used words, but the downside is that this method is not guaranteed
  // to yield words which are specific to the target text.
  public List<String> getNMostPopularWords(int n) {
    Collections.sort(keys, new CountCompare());
    return keys.subList(0, n);
  }

  public Map<String, Integer> getMap() {
    return map;
  }

  public static void main(String[] args) throws IOException {
    BwogBot bot = new BwogBot();
    bot.readFile("comments.txt");
    System.out.println(bot.getCount("hamdel")); // because linan's hungry now
    System.out.println(bot.getNMostPopularWords(100));
  }
}
