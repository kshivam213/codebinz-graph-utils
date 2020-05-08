package com.github.kshivam213.alg.sort;

import java.util.List;

import com.github.kshivam213.Graph;
import com.github.kshivam213.Vertex;

public interface VisitSorted<T> {
	
	public List<Vertex<T>> traverseSorted(Graph<T> graph);
}