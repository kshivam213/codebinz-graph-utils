package com.gutils.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gutils.Edge;
import com.gutils.Graph;
import com.gutils.MinHeap;
import com.gutils.Vertex;

public class DIjskstraAlgorithm<T> implements ShortestPathAlgorithm<T>{

	private Map<Vertex<T>, Integer> distance = new HashMap<>();

	private Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();
	
	@Override
	public Map<Vertex<T>, Integer> shortestDistanceToAllVertexFromSource(Graph<T> graph, Vertex<T> sourceVertex) {

		MinHeap<Vertex<T>> minHeap = new MinHeap<>();
		
		for (Vertex<T> vertex : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, vertex);
		}
				
		minHeap.decrease(sourceVertex, 0);

		distance.put(sourceVertex, 0);
		
		parent.put(sourceVertex, null);
		
		while (!minHeap.empty()) {
			
			MinHeap<Vertex<T>>.Node heapNode = minHeap.extractMinNode();
			Vertex<T> current = heapNode.getKey();

			distance.put(current, heapNode.getWeight());
			
			for (Edge<T> edge : current.getEdges()) {

				Vertex<T> adjacent = getVertexForEdge(current, edge);

				if (!minHeap.containsData(adjacent)) {
					continue;
				}
				
				int newDistance = distance.get(current) + edge.getWeight();

				if (minHeap.getWeight(adjacent) > newDistance) {
					minHeap.decrease(adjacent, newDistance);
					parent.put(adjacent, current);
				}
			}
		}
		
		return distance;
	}
	
	private Vertex<T> getVertexForEdge(Vertex<T> v, Edge<T> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}

	@Override
	public List<Vertex<T>> shortestPathFromSourceToDest(Graph<T> graph, Vertex<T> sourceVertex, Vertex<T> destination) {
		
		 	this.shortestDistanceToAllVertexFromSource(graph, sourceVertex);
		 	
		 	List<Vertex<T>> path = new ArrayList<Vertex<T>>();
		 	
		 	while(destination != sourceVertex) {
		 		
		 		path.add(destination);
		 		destination = parent.get(destination);
		 	}
		 	
		 	return path;
	}
}