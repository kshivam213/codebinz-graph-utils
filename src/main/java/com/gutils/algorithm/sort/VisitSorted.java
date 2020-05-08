package com.gutils.algorithm.sort;

import java.util.List;

import com.gutils.Graph;
import com.gutils.Vertex;

public interface VisitSorted<T> {
	
	public List<Vertex<T>> traverseSorted(Graph<T> graph);
}