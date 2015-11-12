import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class BwogBot {
  private Map<String, Integer> map = new AvlMap<>();

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
    }
  }

  public int getCount(String word) {
    Integer i = map.get(word);
    if (i == null) {
      return 0;
    }
    return i;
  }

  public List<String> getNMostPopularWords(int n) {
    return null;
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
