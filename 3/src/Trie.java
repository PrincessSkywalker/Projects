import java.util.List;
import java.util.LinkedList;

public class Trie {

  private TrieNode root;
  public static final char NULL = '0';

  public Trie() {
    root = new TrieNode(NULL, false);
  }

  // implement your methods here
  // feel free (and you probably should) add helper private methods
  // problem 4a
  public void addWord(String word) {
    addWordHelper(root, word);
  }

  private void addWordHelper(TrieNode n, String word){
    TrieNode node = n.children[word.charAt(0) - 'a'];;
    
    if(node == null){
      node = new TrieNode(word.charAt(0), false);
      n.children[word.charAt(0) - 'a'] = node;
    }

    if(word.length() == 1){
      node.endOfWord = true;
    }
    else{
      addWordHelper(node, word.substring(1));
    }
  }

  // problem 4b
  public boolean contains(String word) {
    return containsHelper(root, word);
  }

  private boolean containsHelper(TrieNode n, String word){
    TrieNode node = n.children[word.charAt(0) - 'a'];

    if(node == null){
      return false;
    }

    if(word.length() == 1){
      return node.endOfWord;
    }
    
    return containsHelper(node, word.substring(1));
  }

  // problem 4c
  public List<String> getStrings() {
    return getStringsHelper(root, "");
  }

  private List<String> getStringsHelper(TrieNode n, String prefix){
    List<String> list = new LinkedList<String>();

    for(TrieNode i : n.children){
      if(i != null)
        list.addAll(getStringsHelper(i, prefix + i.letter));
    }
    
    if(n.endOfWord){
      list.add(prefix);
    }
    return list;
  }

  // problem 4d
  public List<String> getStartsWith(String prefix) {
    return getStartsWithHelper(root, prefix, 0);
  }

  private List<String> getStartsWithHelper(TrieNode n, String prefix, int index){
    if(prefix.length() == index){
      return getStringsHelper(n, prefix);
    }
    TrieNode nextNode = n.children[prefix.charAt(index) - 'a'];
    if(nextNode != null)
      return getStartsWithHelper(nextNode, prefix, ++index);
    return new LinkedList<String>();
  } 

  public String toString() {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb, 0);
    return sb.toString().trim();
  }
  
  private void buildString(TrieNode node, StringBuilder sb, int layer) {
    for (int i = 0; i < layer; i++) {  // print some indentation
      sb.append(" ");
    }
    sb.append(node);    // print the node itself
    sb.append("\n");
    for (TrieNode child : node.children) {  // recursively print each subtree
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
      children = new TrieNode[26]; // number of letters in English alphabet
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
    // System.out.println(trie.g÷etStartsWith("hell"));
    System.out.println(trie.contains("help"));
    System.out.println(trie.contains("hel"));
    System.out.println(trie.getStrings());
    System.out.println(trie.getStartsWith("hi"));
  }
}
