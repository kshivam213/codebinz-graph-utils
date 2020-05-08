package org.cgraph.alg.sort;

import java.util.List;

import org.cgraph.Graph;
import org.cgraph.Vertex;

public interface VisitSorted<T> {
	
	public List<Vertex<T>> traverseSorted(Graph<T> graph);
}