package Grafos;

public class GraphLink <E>{
	
	protected ListLinked<Vertex<E>> listVertex;
	
	public GraphLink() {
		listVertex = new ListLinked<Vertex<E>>();
	}
	
	public void insertVertex(E data) {
		Vertex<E> nuevo = new Vertex<E>(data);
		if(this.listVertex.search(nuevo) !=null) {
			System.out.println("Vertice insertado con anterioridad ....");
			return;
		}
		
		this.listVertex.insertFirst(nuevo);
	}
	//No ponderado
	public void insertEdge(E verOri, E verDes) {
		Vertex<E> refOri = this.listVertex.search(new Vertex<E>(verOri));
		Vertex<E> refDes = this.listVertex.search(new Vertex<E>(verDes));
		
		if(refOri == null || refDes ==null) {
			System.out.println("Vertice origen y/o destino no existen..");
			return;	
		}
		
		if(refOri.listAdj.search(new Edge<E>(refDes)) != null) {
			System.out.println("Arista ya insertada..");
			return;	
		}
		
		refOri.listAdj.insertFirst(new Edge<E>(refDes));//dirigido
		//refDes.listAdj.insertFirst(new Edge<E>(refOri));//no dirigido
	}
	
	
	//Ponderado 
	public void insertEdgePond(E verOri, E verDes) {
		insertEdgePond(verOri,verDes,-1);
	}
	
	//Ponderado 
	public void insertEdgePond(E verOri, E verDes, int weight) {
		Vertex<E> refOri = this.listVertex.search(new Vertex<E>(verOri));
		Vertex<E> refDes = this.listVertex.search(new Vertex<E>(verDes));
		
		if(refOri == null || refDes ==null) {
			System.out.println("Vertice origen y/o destino no existen..");
			return;	
		}
		
		if(refOri.listAdj.search(new Edge<E>(refDes)) != null) {
			System.out.println("Arista ya insertada..");
			return;	
		}
		
		refOri.listAdj.insertFirst(new Edge<E>(refDes,weight));//dirigido
		refDes.listAdj.insertFirst(new Edge<E>(refOri,weight));//no dirigido
	}
	
	public String toString() {
		return this.listVertex.toString();
	}
	
	private void initLabel() {
		Node <Vertex<E>> aux = this.listVertex.first;
		for(; aux !=null ; aux =aux.getNext()) {
			aux.data.label=0;
			Node<Edge<E>> auxE = aux.data.listAdj.first;
			
			for(; auxE !=null ; auxE =auxE.getNext()) 
				auxE.data.label =0;
		}
	}
	
	public void DFS (E data) {
		Vertex<E> nuevo = new Vertex<E>(data);
		Vertex<E> v = this.listVertex.search(nuevo);
		if(v==null) {
			System.out.println("Vertice no existe..");
			return;
		}
		initLabel();
		DFSRec(v);
	}
	
	private void DFSRec (Vertex<E> v) {
		v.label=1; // 1 visitado, 0 dicovery , 2 Back
		System.out.println(v+" "); //saca lista de adyacencia
		//System.out.println(v.data+" "); //solo el dato
		Node<Edge<E>> e = v.listAdj.first;
		
		for(; e!=null;e=e.getNext()) {
			if(e.data.label == 0) {
				Vertex<E> w = e.data.refDest;
				if(w.label == 0) {
					e.data.label =1;
					DFSRec(w);
				}
				else 
					e.data.label=2;
			}
		}
		
	}
	
	public void BFS (E data) {
		Vertex<E> nuevo = new Vertex<E>(data);
		Vertex<E> v = this.listVertex.search(nuevo);
		if(v==null) {
			System.out.println("Vertice no existe..");
			return;
		}
		initLabel();
		BFSRec(v);
	}
	
	private void BFSRec (Vertex<E> s) {
		ListLinked<Vertex<E>> nuevo = new ListLinked<Vertex<E>>();
		s.label=1;
		nuevo.insertLast(s);
		Node<Vertex<E>> i=nuevo.last;
		//System.out.println(s.data+" ");//solo el dato
		System.out.println(s+" ");//saca lista de adyacencia
		while(!nuevo.isEmpty()) {
			ListLinked<Vertex<E>> nu = new ListLinked<Vertex<E>>();
			Node<Edge<E>> e = s.listAdj.first;
			for(; i!=null; i=i.getNext() ) {
				for(; e!=null;e=e.getNext()) {
					if(e.data.label == 0) {
						Vertex<E> w = e.data.refDest;
						if(w.label == 0) {
							e.data.label = 1;
							w.label = 1;
							nu.insertLast(w);
						}
						else 
							e.data.label=3;
					}
				}
			}
			//i=i.getNext();
		}
	}
}
