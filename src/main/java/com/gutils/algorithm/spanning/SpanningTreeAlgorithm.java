package com.gutils.algorithm.spanning;

import java.util.List;

import com.gutils.Edge;
import com.gutils.Graph;

public interface SpanningTreeAlgorithm<T> {
	
	public List<Edge<T>> findMst(Graph<T> graph);
}
