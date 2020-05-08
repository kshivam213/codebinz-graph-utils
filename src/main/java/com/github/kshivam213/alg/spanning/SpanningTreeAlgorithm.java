package com.github.kshivam213.alg.spanning;

import java.util.List;

import com.github.kshivam213.Edge;
import com.github.kshivam213.Graph;

public interface SpanningTreeAlgorithm<T> {
	
	public List<Edge<T>> findMst(Graph<T> graph);
}
