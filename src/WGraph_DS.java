package ex1.src;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

public class WGraph_DS implements weighted_graph,Serializable{

	Hashtable<Integer, node_info> graph_nodes;
	int  MC;
	int numOfEdges;

	public WGraph_DS() {
		this.graph_nodes = new Hashtable<Integer, node_info>();
		this.MC = 0;
		this.numOfEdges = 0;
	}


	private class NodeInfo implements node_info,Serializable{
	

		int key;
		double Tag;
		String info;
		public Hashtable<Integer,node_info> table;
		public Hashtable<Integer,Double> w;

		public NodeInfo (int _key) {
			key=_key;
			Tag=0;
			info=" ";
			table = new Hashtable<Integer,node_info>();
			w = new Hashtable<Integer,Double>();
		}
		@Override
		//Return the key (id) associated with this node.
		public int getKey() {

			return key;
		}

		@Override
		//return the remark (meta data) associated with this node.
		public String getInfo() {

			return info;
		}

		@Override
		//Allows changing the remark (meta data) associated with this node.
		public void setInfo(String s) {
			info=s;

		}

		@Override
		//Temporal data (distance, color, or state) which can be used be algorithms
		public double getTag() {

			return Tag;
		}

		@Override
		//Allow setting the "tag" value for temporal marking an node - common practice for marking by algorithms.
		public void setTag(double t) {
			Tag=t;
		}
		public Hashtable<Integer,node_info> getN(){
			return table;
		}
		public Hashtable<Integer,Double> getw(){
			return w;
		}
		
	}
	@Override
	//return the node_data by the node_id,
	//key - the node_id
	//return the node_data by the node_id, null if none.
	public node_info getNode(int key) {
		return this.graph_nodes.get(key);
	}

	@Override
	//return true if and only if there is an edge between node1 and node2.
	public boolean hasEdge(int node1, int node2) {
		if(this.graph_nodes.containsKey(node1)&&this.graph_nodes.containsKey(node2)) {
			return ((NodeInfo)this.getNode(node1)).table.containsKey(node2);
		}
		return false;
	}

	@Override
	//return the weight if the edge (node1, node1). In case there is no such edge - should return -1.
	public double getEdge(int node1, int node2) {
		if(hasEdge(node1,node2)) {
			return ((NodeInfo)this.getNode(node1)).getw().get(node2);
		}
		return -1;
	}

	@Override
	//add a new node to the graph with the given key.
	public void addNode(int key) {
		MC++;
		this.graph_nodes.put(key, new NodeInfo(key));
	}

	@Override
	//Connect an edge between node1 and node2, with an edge with weight >=0.
	public void connect(int node1, int node2, double w) {
		if(this.getNode(node1)!=null&&this.getNode(node2)!=null&&node1!=node2&&!this.hasEdge(node1, node2)) {
			((NodeInfo)this.getNode(node1)).getN().put(node2, this.getNode(node2));
			((NodeInfo)this.getNode(node1)).w.put(node2, w);

			((NodeInfo)this.getNode(node2)).getN().put(node1, this.getNode(node1));
			((NodeInfo)this.getNode(node2)).w.put(node1, w);
			this.numOfEdges++;
			MC++;
		}
	}

	@Override
	//This method return a pointer (shallow copy) for a Collection representing all the nodes in the graph.
	public Collection<node_info> getV() {

		return this.graph_nodes.values(); ///#56 7#47 7#556 9#
	}

	@Override
	//This method returns a Collection containing all the nodes connected to node_id
	public Collection<node_info> getV(int node_id) {

		return ((NodeInfo)this.getNode(node_id)).getN().values();
	}

	@Override
	//Delete the node (with the given ID) from the graph -and removes all edges which starts or ends at this node.
	public node_info removeNode(int key) {
		node_info n=this.getNode(key);
		if(n!=null) {
			Collection<node_info> vs=this.getV(key);
			Iterator<node_info> inter=vs.iterator();

			if(inter!=null) {
				while(inter.hasNext()) {
					node_info n1=inter.next();
					((NodeInfo)n1).w.remove(key);
					((NodeInfo)n1).table.remove(key);
					this.numOfEdges--;
					MC++;
				}
			}
			MC++;
			return this.graph_nodes.remove(key);
		}
		return null;
	}

	@Override
	//Delete the edge from the graph.
	public void removeEdge(int node1, int node2) {

		if(this.getNode(node1)!=null&&this.getNode(node2)!=null&&this.hasEdge(node1, node2)){
			((NodeInfo)this.getNode(node1)).getN().remove(node2);
			((NodeInfo)this.getNode(node1)).w.remove(node2);

			((NodeInfo)this.getNode(node2)).getN().remove(node1);
			((NodeInfo)this.getNode(node2)).w.remove(node1);
			this.numOfEdges--;
			MC++;
		}		
	}

	@Override
	//return the number of vertices (nodes) in the graph.
	public int nodeSize() {
		return this.graph_nodes.size();
	}

	@Override
	//return the number of edges (undirectional graph).
	public int edgeSize() {

		return this.numOfEdges;
	}

	@Override
	//return the Mode Count - for testing changes in the graph.
	public int getMC() {

		return MC;
	}


}
