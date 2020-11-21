ID:308418276

Algorithem:

dijkstra:
Given a graph with adjacency list representation of the edges between the nodes, 
the task is to implement Dijkstra’s Algorithm for single source shortest path using Priority Queue in Java.
Given a graph and a source vertex in graph, find shortest paths from source to all vertices in the given graph.

BFS:
Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree.
The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again. 
To avoid processing a node more than once, we use a boolean visited array. For simplicity, 
it is assumed that all vertices are reachable from the starting vertex.


Data structher:


Queue:
The Queue interface present in the java.util package and extends the Collection interface
is used to hold the elements about to be processed in FIFO(First In First Out) order. 
It is an ordered list of objects with its use limited to insert elements at the end
of the list and deleting elements from the start of the list, (i.e.), 
it follows the FIFO or the First-In-First-Out principle.


ArrayList:
ArrayList is a part of collection framework and is present in java.util package. 
It provides us with dynamic arrays in Java. Though, 
it may be slower than standard arrays but can be helpful in programs
 where lots of manipulation in the array is needed. 
This class is found in java.util package.


HashTable:
This class implements a hash table, which maps keys to values. Any non-null object can be used as a key or as a value.
To successfully store and retrieve objects from a hashtable, the objects used as keys must implement the hashCode method and the equals method.

It is similar to HashMap, but is synchronised.
Hashtable stores key/value pair in hash table.
In Hashtable we specify an object that is used as a key, 
and the value we want to associate to that key. 
The key is then hashed, and the resulting hash code is used as the index at which 
the value is stored within the table.