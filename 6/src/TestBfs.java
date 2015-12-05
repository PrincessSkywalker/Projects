public class TestBfs {

  public static void main(String[] args) {
    Graph g = MapReader.readGraph(args[0], args[1]);
    Graph g2 = g.getUnweightedShortestPath(args[2], args[3]);
    DisplayGraph display = new DisplayGraph(g2);
    display.setVisible(true);
  }

}
