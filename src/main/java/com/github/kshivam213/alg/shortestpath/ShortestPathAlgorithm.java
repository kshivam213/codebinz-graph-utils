package com.github.kshivam213.alg.shortestpath;

import java.util.List;
import java.util.Map;

import com.github.kshivam213.Graph;
import com.github.kshivam213.Vertex;


public interface ShortestPathAlgorithm<T> {
	
	public Map<Vertex<T>, Integer> shortestDistanceToAllVertexFromSource(Graph<T> graph, Vertex<T> sourceVertex);
	public List<Vertex<T>> shortestPathFromSourceToDest(Graph<T> graph, Vertex<T> sourceVertex, Vertex<T> destination);
	
}