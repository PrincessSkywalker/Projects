import java.util.Scanner;
import java.io.File;

public class MapReader {

  public static Graph readGraph(String vertexfile, String edgefile) {
  	try{
	  	Graph graph = new Graph();
	    Scanner scan = new Scanner(new File(vertexfile)).useDelimiter(",|\\s");
	    while(scan.hasNext()){
	    	Vertex v = new Vertex(scan.next(), scan.nextInt(), scan.nextInt());
	    	graph.addVertex(v);
	    }

	    scan = new Scanner(new File(edgefile)).useDelimiter(",|\\s");
	    while(scan.hasNext()){
	    	graph.addUndirectedEdge(scan.next(), scan.next(), 1);
	    }
	    return graph;
	}
	catch(Exception e){
		e.printStackTrace();
		return null;
	}
  }

  public static void main(String[] args) {
  	Graph g = readGraph(args[0], args[1]);
    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }

}
