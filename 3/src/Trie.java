import java.util.List;

public class Trie {

  private TrieNode root;
  public static final char NULL = '0';

  public Trie() {
    root = new TrieNode(NULL, false);
  }
  
  public void addWord(String word) {
    // implement this
  }
  
  public boolean contains(String word) {
    // implement this
    return false;
  }

  public List<String> getStrings() {
    // implement this
    return null;
  }

  public List<String> getStartsWith(String prefix) {
    // implement this
    return null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb, 0);
    return sb.toString().trim();
  }

  public void buildString(TrieNode node, StringBuilder sb, int layer) {
    for (int i = 0; i < layer; i++) {
      sb.append(" ");
    }
    sb.append(node);
    sb.append("\n");
    for (TrieNode child : node.children) {
      if (child != null) {
        buildString(child, sb, layer + 1);
      }
    }
  }

  private class TrieNode {
    public char letter;
    public boolean endOfWord;
    public TrieNode[] children;

    public TrieNode(char letter, boolean endOfWord) {
      this.letter = letter;
      this.endOfWord = endOfWord;
      children = new TrieNode[26]; // number of alphabets in english
    }

    public String toString() {
      return endOfWord ? Character.toString(Character.toUpperCase(letter)) : Character.toString(letter);
    }
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.addWord("hello");
    trie.addWord("help");
    System.out.println(trie);
    System.out.println(trie.getStartsWith("hell"));
  }
}
