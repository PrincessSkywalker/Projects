import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }


  /****************************
   * Your code follow here.   *
   ****************************/ 

  public void addUndirectedEdge(String s, String t, double cost) {
    addEdge(s, t, cost);
    addEdge(t, s, cost);
  }

  public double computeEuclideanCost(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
  }

  public void computeAllEuclideanCosts() {
    for(Vertex v : getVertices()) {
      for(Edge e : v.getEdges()) {
        e.cost = computeEuclideanCost(v.posX, v.posY, e.targetVertex.posX, e.targetVertex.posY);
      }
    }
  }

  /** BFS */
  public void doBfs(String s) {
    LinkedList<Vertex> queue = new LinkedList<>();
    Vertex v = getVertex(s);
    queue.add(v);
    v.visited = true;
    v.cost = 0;

    while (queue.size() > 0){
      Vertex u = queue.poll();
      for(Edge e : u.getEdges()){
        v = e.targetVertex;
        if(!v.visited){
          v.visited = true;
          v.backpointer = u;
          v.cost = u.cost + 1;
          queue.add(v);
        }
      }
    }

  }
  
  public Graph getUnweightedShortestPath(String s, String t) {
    doBfs(s);
    Graph g = new Graph();

    for(Vertex v : getVertices()) {
      Vertex u = new Vertex(v.name, v.posX, v.posY);
      g.addVertex(u);
    }

    Vertex v = getVertex(t);
    while(v.backpointer != null) {
      g.addUndirectedEdge(v.name, v.backpointer.name, 1);
      v = v.backpointer;
    }
    return g;
  }

  /** Dijkstra's */
  private class WeightedVertex implements Comparable<WeightedVertex> {
    Vertex vertex;
    double cost;

    WeightedVertex(Vertex v) {
      vertex = v;
      cost = v.cost;
    }

    public int compareTo(WeightedVertex other) {
      if(cost < other.cost)
        return -1;
      if(cost > other.cost)
        return 1;
      return 0;
    }

    public boolean equals(Object other) {
      return vertex.name.equals(((WeightedVertex)other).vertex.name);
    }
  }

  // NewYork to SanFrancisco
  public void doDijkstra(String s) {
    PriorityQueue<WeightedVertex> queue = new PriorityQueue<>();
    Vertex v = getVertex(s);
    v.cost = 0;
    v.visited = true;
    queue.add(new WeightedVertex(v));

    for(Vertex u : getVertices()){
      u.cost = Double.POSITIVE_INFINITY;
    }

    while(queue.size() > 0){
      WeightedVertex u = queue.poll();
      u.vertex.visited = true;

      for(Edge e : u.vertex.getEdges()){
        v = e.targetVertex;
        if(!v.visited && u.cost + e.cost < v.cost){
          v.cost = u.cost + e.cost;
          v.backpointer = u.vertex;
          WeightedVertex w = new WeightedVertex(v);
          queue.remove(w);
          queue.add(w);
        }
      }
    }
  }

  public Graph getWeightedShortestPath(String s, String t) {
    doDijkstra(s);
    Graph g = new Graph();

    for(Vertex v : getVertices()) {
      Vertex u = new Vertex(v.name, v.posX, v.posY);
      g.addVertex(u);
    }

    Vertex v = getVertex(t);
    while(v.backpointer != null) {
      g.addUndirectedEdge(v.name, v.backpointer.name, 1);
      v = v.backpointer;
    }
    return g;
  }

  /** Prim's */
  public void doPrim(String s) {
    PriorityQueue<WeightedVertex> queue = new PriorityQueue<>();

    for(Vertex v : getVertices()){
      v.cost = Double.POSITIVE_INFINITY;
    }

    Vertex v = getVertex(s);
    v.cost = 0;
    queue.add(new WeightedVertex(v));

    while(queue.size() > 0){
      WeightedVertex u = queue.poll();
      if(!u.vertex.visited){
        u.vertex.visited = true;
        for(Edge e : u.vertex.getEdges()){
          v = e.targetVertex;
          if(!v.visited && e.cost < v.cost){
            v.cost = e.cost;
            v.backpointer = u.vertex;
            queue.add(new WeightedVertex(v));
          }
        }
      }
    }
  }

  public Graph getMinimumSpanningTree(String s) {
    doPrim(s);
    Graph g = new Graph();

    for(Vertex v : getVertices()) {
      Vertex u = new Vertex(v.name, v.posX, v.posY);
      g.addVertex(u);
    }

    for(Vertex v : getVertices()){
      if(v.backpointer != null)
        g.addUndirectedEdge(v.name, v.backpointer.name, 1);
    }
    return g;
  }

  /*************************/

  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    g.addVertex(new Vertex("v3", 1, 1));

    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");

    g.printAdjacencyList();

    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }

}
