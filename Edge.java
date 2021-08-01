package Grafos;

public class Edge<E> {
	
	protected Vertex<E> refDest;
	protected int weight;
	protected int label;// 0= unexplored 1=discovery 2=back 3=cross
	
	
	public Edge(Vertex<E> refDest) {
		this(refDest,-1);
	}
	
	public Edge(Vertex<E> refDest, int weight) {
		this.refDest= refDest;
		this.weight= weight;
	}
	
	public boolean equals(Object o) {
		if(o instanceof  Edge<?>) {
			Edge<E> e = (Edge<E>)o;
			return this.refDest.equals(e.refDest);
		}
		return false;
	}
	
	public String toString() {
		if(this.weight>-1) return refDest.data + "["+ this.weight+"], ";
		else return refDest.data+" , ";
	}	
}
