package com.gutils.algorithm.connectedcomponents;

import java.util.List;

import com.gutils.Graph;
import com.gutils.Vertex;

public interface ConnectedComponent<T> {
	
	public List<List<Vertex<T>>> connectedComponentList(Graph<T> graphs);
}
