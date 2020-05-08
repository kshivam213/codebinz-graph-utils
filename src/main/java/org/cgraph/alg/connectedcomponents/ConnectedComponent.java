package org.cgraph.alg.connectedcomponents;

import java.util.List;

import org.cgraph.Graph;
import org.cgraph.Vertex;

public interface ConnectedComponent<T> {
	
	public List<List<Vertex<T>>> connectedComponentList(Graph<T> graphs);
}
