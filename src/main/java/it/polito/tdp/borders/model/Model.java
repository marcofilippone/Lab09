package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> idMap;

	public Model() {
		dao = new BordersDAO();
		idMap = new HashMap<>();
		dao.loadAllCountries(idMap);
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici(idMap, anno));
		for(Border b : dao.getCountryPairs(idMap, anno)) {
			if(!grafo.containsEdge(b.getA(), b.getB())) {
				grafo.addEdge(b.getA(), b.getB());
			}
		}
	}
	
	public int componentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> insp = new ConnectivityInspector<>(this.grafo);
		return insp.connectedSets().size();
	}
	
	public int calcolaGrado(Country c) {
		return grafo.degreeOf(c);
	}
	
	public Set<Country> getVertici(){
		return grafo.vertexSet();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Country> statiRaggiungibili(Country partenza){
		List<Country> ragg = new LinkedList<>();
		BreadthFirstIterator<Country, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo, partenza);
		Country c;
		while(it.hasNext()) {
			c = it.next();
			if(!c.equals(partenza))
			ragg.add(c);
		}
		return ragg;
	}

}
