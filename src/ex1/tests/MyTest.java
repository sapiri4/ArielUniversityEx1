package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

class MyTest {
	
	
	weighted_graph g;
	
	@BeforeEach
	void befortest() {
		g=new WGraph_DS();
	}
	@Test
	void test1() {
	g.addNode(0);
	g.addNode(1);
	g.addNode(2);
	if(g.nodeSize()!=3) {
		fail("graph should be size 3");
	}
	}
	
	@Test
	void test2() {
		for(int i=0;i<1000;i++) {
			g.addNode(i);
		}
		for(int i=0;i<1000;i+=2) {
			g.connect(i, i+1, 50);
		}
		if(g.nodeSize()!=1000) {
		fail("graph should to be size 1000");
		}
		if(g.edgeSize()!=500) {
			fail("graph should be 500 edges");
		}
	}
	
	@Test
	void test3() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.connect(0, 1, 20);
		g.connect(1, 2, 30);
		g.connect(0, 2, 55);
		weighted_graph_algorithms algo=new WGraph_Algo();
		algo.init(g);
		if(algo.isConnected()!=true) {
		fail("graph isconnected");
		}
		if(algo.shortestPathDist(0, 2)!=50) {
			fail("graph cost 50 way");
		}
	}
	
	
	@Test
	void test4() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.connect(0, 1, 20);
		g.connect(1, 2, 30);
		g.connect(0, 2, 45);
		weighted_graph_algorithms algo=new WGraph_Algo();
		algo.init(g);
		if(algo.isConnected()!=true) {
		fail("graph isconnected");
		}
		if(algo.shortestPathDist(0, 2)!=45) {
			fail("graph cost 45 way");
		}
	}
	
	
	@Test
	void test5() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.removeNode(2);
		if(g.nodeSize()!=2) {
		fail("needs to be 2 ");
		}
	}
	
	
	@Test
	void test6() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		
		g.removeNode(2);
		g.removeNode(2);
		if(g.nodeSize()!=2) {
		fail("needs to be 2 ");
		}
	}
	
	
	@Test
	void test7() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.connect(0, 1, 20);
		g.connect(1, 2, 30);
		g.connect(0, 2, 45);
		g.removeNode(0);
		if(g.nodeSize()!=2&&g.edgeSize()!=1) {
		fail("Not yet implemented");
		}
	}
	
	@Test
	void test8() {
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.connect(0, 1, 20);
		g.connect(1, 2, 30);
		g.connect(0, 2, 45);
		g.removeNode(0);
		g.removeNode(0);
		g.removeNode(0);
		g.removeNode(0);
		g.addNode(0);
		if(g.nodeSize()!=3&&g.edgeSize()!=1) {
		fail("Not yet implemented");
		}
	}

}
