package org.cgraph.alg.spanning;

import java.util.List;

import org.cgraph.Edge;
import org.cgraph.Graph;

public interface SpanningTreeAlgorithm<T> {
	
	public List<Edge<T>> findMst(Graph<T> graph);
}
