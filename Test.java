package Grafos;

public class Test {

	public static void main(String[] args) {
		GraphLink<String> g = new  GraphLink<String>();
		
		g.insertVertex("lima");
		g.insertVertex("aqp");
		g.insertVertex("cusco");
		g.insertVertex("piura");
		g.insertVertex("tarapoto");
		
		g.insertEdge("lima","aqp");
		g.insertEdge("cusco","aqp");
		g.insertEdge("piura","lima");
		g.insertEdge("cusco","lima");
		g.insertEdge("piura","tarapoto");
		g.insertEdge("aqp","lima");
		
		System.out.println("Grafo de ciudades:\n"+g);
		
		g.BFS("lima");
		g.BFS("aqp");
		g.BFS("cusco");
		g.BFS("piura");
		g.BFS("tarapoto");
		
		

	}

}
