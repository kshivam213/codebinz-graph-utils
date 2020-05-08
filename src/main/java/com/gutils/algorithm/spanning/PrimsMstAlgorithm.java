package com.gutils.algorithm.spanning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gutils.Edge;
import com.gutils.Graph;
import com.gutils.MinHeap;
import com.gutils.Vertex;

public class PrimsMstAlgorithm<T> implements SpanningTreeAlgorithm<T> {

	
	@Override
	public List<Edge<T>> findMst(Graph<T> graph) {
		
		MinHeap<Vertex<T>> minHeap = new MinHeap<>();
		
		Map<Vertex<T>, Edge<T>> vertexToEdge = new HashMap<>();
		
		List<Edge<T>> result = new ArrayList<>();
		
		for (Vertex<T> v : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, v);
		}
		
		Vertex<T> startVertex = graph.getAllVertex().iterator().next();
		
		minHeap.decrease(startVertex, 0);
		
		while (!minHeap.empty()) {
			
			Vertex<T> current = minHeap.extractMin();

			Edge<T> spanningTreeEdge = vertexToEdge.get(current);
			if (spanningTreeEdge != null) {
				result.add(spanningTreeEdge);
			}

			for (Edge<T> edge : current.getEdges()) {
				
				Vertex<T> adjacent = getVertexForEdge(current, edge);
			
				if (minHeap.containsData(adjacent) && minHeap.getWeight(adjacent) > edge.getWeight()) {
					
					minHeap.decrease(adjacent, edge.getWeight());
					vertexToEdge.put(adjacent, edge);
				}
			}
		}
		return result;
	}
	
	private Vertex<T> getVertexForEdge(Vertex<T> v, Edge<T> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}

}