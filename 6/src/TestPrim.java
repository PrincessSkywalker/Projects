public class TestPrim {

  public static void main(String[] args) {
    Graph g = MapReader.readGraph(args[0], args[1]);
    Graph g2 = g.getMinimumSpanningTree(args[2]);
    DisplayGraph display = new DisplayGraph(g2);
    display.setVisible(true);
  }

}
