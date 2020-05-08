package com.github.kshivam213.alg.connectedcomponents;

import java.util.List;

import com.github.kshivam213.Graph;
import com.github.kshivam213.Vertex;

public interface ConnectedComponent<T> {
	
	public List<List<Vertex<T>>> connectedComponentList(Graph<T> graphs);
}
