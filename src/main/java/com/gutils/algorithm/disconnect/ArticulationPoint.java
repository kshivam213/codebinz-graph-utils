package com.gutils.algorithm.disconnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gutils.Graph;
import com.gutils.Vertex;

public class ArticulationPoint<T> {
	
	private Integer time =0;
	private void apUtils(Vertex<T> vertex, Map<Integer, Boolean> visited, List<Integer> disc, List<Integer> low, Map<Integer, Integer> parent, List<Vertex<T>> aPoints) {
		
		int children= 0;
		visited.put(vertex.getId(), true);
		
		++time;
		disc.set(vertex.getId(), time);
		low.set(vertex.getId(),	time);
		
		for(Vertex<T> vtx: vertex.getAdjacentVertexes()) {
			
			if(visited.get(vtx.getId()) == null) {
				
				children++;
				parent.put(vtx.getId(), vertex.getId());
				
				apUtils(vtx, visited, disc, low, parent, aPoints);
				
				low.set(vertex.getId(), Math.min(low.get(vertex.getId()), low.get(vtx.getId())));
			
				if(parent.get(vertex.getId()) == null && children > 1)
					aPoints.add(vertex);
				
				if(parent.get(vertex.getId()) != null && low.get(vtx.getId()) >= disc.get(vertex.getId()))
					aPoints.add(vertex);
			}else if(vtx.getId() != parent.get(vertex.getId()))
				low.set(vertex.getId(), Math.min(low.get(vertex.getId()), disc.get(vtx.getId())));
		}
		
	}
	
	public List<Vertex<T>> findAllArticulationPoint(Graph<T> graph){
		
		Map<Integer, Boolean> visited = new HashMap<>();
		List<Integer> disc= new ArrayList<>();
		List<Integer> low = new ArrayList<>();
		Map<Integer, Integer> parent= new HashMap<Integer, Integer>();
		List<Vertex<T>> aPoints = new ArrayList<>();
		
		for(Vertex<T> vertex: graph.getAllVertex()) {
			
			if(visited.get(vertex.getId()) == null) {
				apUtils(vertex, visited, disc, low, parent, aPoints);
			}
		}
		
		return aPoints;
	}
}
