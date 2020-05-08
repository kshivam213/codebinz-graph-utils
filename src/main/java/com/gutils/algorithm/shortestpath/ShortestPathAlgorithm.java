package com.gutils.algorithm.shortestpath;

import java.util.List;
import java.util.Map;

import com.gutils.Graph;
import com.gutils.Vertex;


public interface ShortestPathAlgorithm<T> {
	
	public Map<Vertex<T>, Integer> shortestDistanceToAllVertexFromSource(Graph<T> graph, Vertex<T> sourceVertex);
	public List<Vertex<T>> shortestPathFromSourceToDest(Graph<T> graph, Vertex<T> sourceVertex, Vertex<T> destination);
	
}