import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class BinaryHeap<T> implements PQ<T> {
	T[] pq;
	Comparator<T> c;
	public int size;
	int hole = 0;

	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		size = q.length;
		// Initialize a new array with given array
		pq = (T[]) new Object[size + 1];
		for (int i = 0; i < size; i++) {
			pq[i + 1] = q[i];
		}
		c = comp;
	}

	/** Create an empty priority queue of given maximum size */
	BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
		c = comp;
		// size = n;
		pq = (T[]) new Object[n];
	}

	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void add(T x) { /* to be implemented */
		size++;
		hole = size;
		pq[hole] = x;
		percolateUp(hole);
		// pq[0] = x;
		//
		/*
		 * size++; pq[size+1]=x; hole=size+1; percolateUp(hole); pq[hole] = x;//
		 * adding new element to the heap according to its value size++;
		 */
	}

	public T remove() { /* to be implemented */
		T max_priority = pq[1];
		pq[1] = pq[size];// replacing element with max priority with the last
							// element of the heap and removing the last element
		size--;
		percolateDown(1);// structuring the heap again
		return max_priority;
	}

	public T peek() { /* to be implemented */
		return null;
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) {/* to be implemented */
		// Run until the child node is greater than its parent node
		// Swap child and parent node if child has higher priority than parent
		// node
		// Iterate again with old parent now as child and new child's parent
		pq[0] = pq[i];
		while (c.compare(pq[i / 2], pq[0]) < 0) {
			pq[i] = pq[i / 2];
			i /= 2;
		}
		pq[i] = pq[0];
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) {
		int child;
		if (2 * i > size) {
			// nothing to do
		} else if (2 * i == size) {
			if (c.compare(pq[i], pq[2 * i]) < 0) {
				T temp = pq[2 * i];
				pq[2 * i] = pq[i];
				pq[i] = temp;
			}
		} else {
			if (c.compare(pq[2 * i], pq[2 * i + 1]) > 0) {
				child = 2 * i;
			} else {
				child = 2 * i + 1;
			}
			if (c.compare(pq[i], pq[child]) < 0) {
				T temp = pq[i];
				pq[i] = pq[child];
				pq[child] = temp;
				percolateDown(child);
			}
		}

	}

	/** Create a heap. Precondition: none. */
	// RT = O(n)
	void buildHeap() {

		for (int i = size / 2; i > 0; i--) {
			System.out.println("size is:" + size);
			percolateDown(i);
		}
		System.out.println("Heap order");
		for (int i = 1; i <= size; i++) {
			System.out.print(pq[i] + " ");
		}
		System.out.println();
	}

	/*
	 * sort array A[1..n]. A[0] is not used. Sorted order depends on comparator
	 * used to buid heap. min heap ==> descending order max heap ==> ascending
	 * order
	 */
	public static <T> void heapSort(T[] A,
			Comparator<T> comp) { /* to be implemented */
		BinaryHeap<T> obj = new BinaryHeap<>(A, comp);
		System.out.println("Before build heap");
		for (int i = 1; i < obj.pq.length; i++) {
			System.out.print(obj.pq[i] + " ");
		}
		obj.buildHeap();
		// for(int i=size; i>0;i--){
		// T temp = obj.pq[1];
		// obj.pq[1] = obj.pq[i];
		// obj.pq[i] = temp;
		// size--;
		// obj.percolateDown(1);
		// }
		System.out.println("Sorted Array");
		for (int i = 1; i < obj.pq.length; i++) {
			System.out.print(obj.pq[i] + " ");
		}
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		int n;
		Scanner in;
		Comparator<Integer> comp = new Comparator<Integer>() {

			public int compare(Integer x, Integer y) {
				return x.intValue() - y.intValue();
			}
		};
		in = new Scanner(System.in);
		System.out.println("Enter value of n:");
		n = in.nextInt();
		Integer[] A = new Integer[n];
		System.out.println("Enter numbers");
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		BinaryHeap.heapSort(A, comp);
	}
}
