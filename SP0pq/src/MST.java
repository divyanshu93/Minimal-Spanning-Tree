import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MST {

	/**
	 * @param g
	 * @return weight of minimum spanning tree.
	 * 
	 */
	static int PrimMST(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);

		// Code for Prim's algorithm needs to be written
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
		}
		src.seen = true;
		// PriorityQueue<Edge> q = new PriorityQueue<>();
		ComparatorEdge ce = new ComparatorEdge();
		// comparator for comparing edges.
		BinaryHeap<Edge> q = new BinaryHeap<>(g.noOfEdges, ce);
		Vertex temp;
		for (Edge e : src.Adj) {
			q.add(e);
		}
		while (q.size > 0) {

			Edge e = q.remove();
			if (e.From.seen && e.To.seen) {
				continue;
			}

			if (e.From.seen == true) {
				temp = e.To;
				e.To.seen = true;
			} else {
				temp = e.From;
				e.From.seen = true;
			}

			wmst = wmst + e.Weight;

			for (Edge f : temp.Adj) {
				Vertex w = f.otherEnd(temp);
				if (!w.seen) {
					q.add(f);
				}
			}
		}

		return wmst;
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("MST");
		File f = new File("C:/Users/divya/workspace/SP0pq/src/test.txt");
		Scanner in = new Scanner(f);
		Graph g = Graph.readGraph(in, false);
		System.out.println(System.currentTimeMillis());
		long e1 = System.currentTimeMillis();
		System.out.println(PrimMST(g));
		System.out.println(System.currentTimeMillis());
		long e2 = System.currentTimeMillis();
		System.out.println(e2 - e1);
	}
}
