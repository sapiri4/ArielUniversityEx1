	package ex1.src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class WGraph_Algo implements weighted_graph_algorithms,Serializable{
	private weighted_graph g;
	public static boolean flag=true;
	public static int darga=0;
	public static String info="";
	
	//Init the graph on which this set of algorithms operates on.
	@Override
	public void init(weighted_graph g) {
		this.g=g;

	}
	
	//Return the underlying graph of which this class works.
	@Override
	public weighted_graph getGraph() {
		return g;
	}

	@Override
	//Compute a deep copy of this weighted graph.
	public weighted_graph copy() {
		weighted_graph copy=new WGraph_DS();
		Collection<node_info> vs=g.getV();
		Iterator<node_info> iter=vs.iterator();
		if(iter!=null) {
			while(iter.hasNext()) {
				node_info n=iter.next();
				darga=g.getV(n.getKey()).size();
				info=n.getInfo();
				copy.addNode(n.getKey());
				darga=0;
				info="";
			}
		}
		return null;
	}

	@Override
	//Returns true if and only if (iff) there is a valid path from EVREY node to each other node.
	public boolean isConnected() {

		if(g!=null) {
			if(g.nodeSize()==0) {
				return true;
			}
			else {
				Iterator<node_info> n=g.getV().iterator();
				while (n.hasNext()) {
					n.next().setTag(0);
				}
				Queue<node_info> queue=new ArrayBlockingQueue<node_info>(g.nodeSize());
				Iterator<node_info> a=g.getV().iterator();
				node_info start=a.next();
				start.setTag(1);
				queue.add(start);
				while (!queue.isEmpty()){
					node_info u = queue.poll();//retrieves and removes the head of this queue, or returns null if this queue is empty
					Collection<node_info> arr=g.getV(u.getKey());
					Iterator<node_info> vs=arr.iterator();
					while (vs.hasNext()) {
						node_info v=vs.next();
						if(v.getTag()==0) {
							v.setTag(1);
							queue.add(v);
						}
					}
					u.setTag(2);
				}
				Iterator<node_info> b=g.getV().iterator();
				while (b.hasNext()) {
					node_info v=b.next();
					if(v.getTag()==0) {
						return false;
					}
				}
				return true;
			}
		}
		return true;
	}

	@Override
	//returns the length of the shortest path between src to dest.
	public double shortestPathDist(int src, int dest) {
		if(g!=null) {
			if(g.nodeSize()==0) {
				return -1;
			}
			else {
				Iterator<node_info> n=g.getV().iterator();
				while (n.hasNext()) {
					n.next().setTag(Double.MAX_VALUE);
				}
				Queue<node_info> queue=new ArrayBlockingQueue<node_info>(g.nodeSize());
				Iterator<node_info> a=g.getV().iterator();
				node_info start=g.getNode(src);
				start.setTag(0);
				queue.add(start);
				while (!queue.isEmpty()){
					node_info u = queue.poll();//retrieves and removes the head of this queue, or returns null if this queue is empty
					Collection<node_info> arr=g.getV(u.getKey());
					Iterator<node_info> vs=arr.iterator();
					while (vs.hasNext()) {
						node_info v=vs.next();
						if(u.getTag()+g.getEdge(u.getKey(), v.getKey())<v.getTag()) {
							v.setTag(u.getTag()+g.getEdge(u.getKey(), v.getKey()));
							queue.remove(v);
							queue.add(v);
						}
					}
				}
				if(g.getNode(dest).getTag()!=Double.MAX_VALUE) {
					return g.getNode(dest).getTag();
				}
				else {
					return -1;
				}
			}
		}
		return -1;
	}

	@Override
	//returns the the shortest path between src(start node) to dest(end node) - as an ordered List of nodes:
	//src--> n1-->n2-->...dest
	//if no such path --> returns null;
	public List<node_info> shortestPath(int src, int dest) {
		if(g!=null&&g.getNode(src)!=null&&g.getNode(dest)!=null) {
			if(g.nodeSize()==0) {
				return null;
			}
			else {
				Iterator<node_info> n=g.getV().iterator();
				while (n.hasNext()) {
					n.next().setTag(Double.MAX_VALUE);
				}
				Queue<node_info> queue=new ArrayBlockingQueue<node_info>(g.nodeSize());
				int[] fathers=new int[g.nodeSize()];
				Iterator<node_info> a=g.getV().iterator();
				node_info start=g.getNode(src);
				start.setTag(0);
				queue.add(start);
				fathers[start.getKey()%g.nodeSize()]=-1;
				while (!queue.isEmpty()){
					node_info u = queue.poll();//retrieves and removes the head of this queue, or returns null if this queue is empty
					Collection<node_info> arr=g.getV(u.getKey());
					Iterator<node_info> vs=arr.iterator();
					while (vs.hasNext()) {
						node_info v=vs.next();
						if(u.getTag()+g.getEdge(u.getKey(), v.getKey())<v.getTag()) {
							v.setTag(u.getTag()+g.getEdge(u.getKey(), v.getKey()));
							fathers[v.getKey()%g.nodeSize()]=u.getKey();
							queue.remove(v);
							queue.add(v);
						}
					}
				}
				if(g.getNode(dest).getTag()!=Double.MAX_VALUE) {
					List<node_info> Path=new ArrayList<node_info>();
					int path=g.getNode(dest).getKey();
					while(path!=-1) {				
						Path.add(0,g.getNode(path));
						path=fathers[path%g.nodeSize()];
					}
					return Path;
				}
				else {
					return null;
				}
			}
		}
		return null;
	}


	@Override
	// Saves this weighted (undirected) graph to the given file name
	public boolean save(String file) { //serializable 
        String s="";
        Collection<node_info> vs=g.getV();
        Iterator<node_info> iter=vs.iterator();
        while(iter.hasNext()) {
        	node_info v=iter.next();
        	s += ("key="+v.getKey()+" info="+v.getInfo()+"\n");
        }
		try {
			File f1 = new File (file);
			BufferedWriter out;
			out = new BufferedWriter(new FileWriter(file));
			out.write(s);
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	//This method load a graph to this graph algorithm.
    //if the file was successfully loaded - the underlying graph
    //of this class will be changed (to the loaded one), in case the
    //graph was not loaded the original graph should remain "as is".
	public boolean load(String file) { 
		try {
			File f=new File(file);
			BufferedReader r = new BufferedReader(new FileReader(f));
			try { 
				String line;
				
				//r.readLine();
				
				//System.out.println(g.getV());
				while ((line=r.readLine())!=null ) { 
					//System.out.println(line);
					int i1=line.indexOf('=');
					int i2=line.indexOf(' ',i1);
					int key=Integer.parseInt(line.substring(i1+1, i2));
					
					int i3=line.indexOf('=',i2+1);
					String infoL=line.substring(i3+1);
					
					info=infoL;
					g.removeNode(key);
					g.addNode(key);
					info="";
					//System.out.println(g.getV());
				}           
			} finally {
				r.close();    
			}
		} catch (Exception ex) {
			ex.printStackTrace();  
			return false;
		}
		return true;
	}
}
