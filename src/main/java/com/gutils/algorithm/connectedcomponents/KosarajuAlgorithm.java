package com.gutils.algorithm.connectedcomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.gutils.Edge;
import com.gutils.Graph;
import com.gutils.Vertex;

public class KosarajuAlgorithm<T> implements ConnectedComponent<T>{

	private void fillOrder(Vertex<T> vertex, Map<Integer, Boolean> visited, Stack<Integer> stack) {
		
		visited.put(vertex.getId(), true);
		
		for(Vertex<T> adj: vertex.getAdjacentVertexes()) {
			
			if(visited.get(adj.getId()) == null) {
				fillOrder(adj, visited, stack);
			}
		}
		stack.push(vertex.getId());
	}
	
	private Graph<T> getTranspose(Graph<T> graph) {
		
		Graph<T> revGraph = new Graph<T>(true);
		for (Edge<T> edge: graph.getAllEdges()) 
        { 
			revGraph.addEdge(edge.getVertex2().getId(), edge.getVertex1().getId());
        }
        return revGraph;
	}
	
	private void dfsUtil(Integer id, Map<Integer, Boolean> visited, List<Vertex<T>> result, Graph<T> graph) 
    { 	
		visited.put(id, true);
		
		Vertex<T> vertex = graph.getVertex(id);
        result.add(vertex);
		
        for(Vertex<T> adj : vertex.getAdjacentVertexes())
        { 
        		
        		if (visited.get(adj.getId()) == null) 
        			dfsUtil(adj.getId(),visited, result, graph); 
        }
    } 
	
	public void updateMap(Map<Integer, Boolean> visited) {
		
		Set<Integer> keys = visited.keySet();
		for(Integer key: keys)
			visited.put(key, false);
	}
	
	@Override
	public List<List<Vertex<T>>> connectedComponentList(Graph<T> graphs) {
		
		List<List<Vertex<T>>> vertexes = new ArrayList<List<Vertex<T>>>();
		Stack<Integer> stack= new Stack<>();
		
		Map<Integer, Boolean> visited = new HashMap<>();
		
		for(Vertex<T> vertex: graphs.getAllVertex()) {
			
			Integer id = vertex.getId();
			if(visited.get(id) == null) {
				fillOrder(vertex, visited, stack);
			}
		}
		
		Graph<T> revGraph = getTranspose(graphs);
		updateMap(visited);
		
		while(stack.isEmpty()) {
			
            Integer popedEl = stack.pop(); 
            
            if (visited.get(popedEl) == null) 
            { 
            		List<Vertex<T>> result= new ArrayList<>();
                dfsUtil(popedEl, visited, result, revGraph);
                
                vertexes.add(result);
            } 
		}
		return vertexes;
	}
}
